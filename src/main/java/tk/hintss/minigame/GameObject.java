package tk.hintss.minigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.hintss.minigame.util.CompassUtil;
import tk.hintss.minigame.util.WorldResetter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameObject extends Arena {
    private HashMap<String, PlayerObject> players = new HashMap<String, PlayerObject>();
    private HashMap<String, PlayerOrigin> spectators = new HashMap<String, PlayerOrigin>();

    private GameState currentState = GameState.WAITING_FOR_PLAYERS;

    private BukkitRunnable timerObject;

    public GameObject(String s) {
        super(s);

        if (Statics.resetWorlds) {
            WorldResetter.resetWorld(getPlayerSpawn().getWorld().getName());
        }
    }

    public void addPlayer(Player player) {
        // adds a player to the game, teleports them in, saves their inv, all that jazz

        if (currentState.getCanJoin() && players.size() < getMaxPlayers()) {
            if (ServerManager.getInstance().isPlayer(player)) {
                ServerManager.getInstance().getGameByPlayer(player).playerQuit(player);
            } else if (ServerManager.getInstance().isSpectator(player)) {
                ServerManager.getInstance().getGameByPlayer(player).removeSpectator(player);
            }

            players.put(player.getName(), new PlayerObject(player, true));

            player.getInventory().clear();

            ServerManager.getInstance().addPlayer(player, this);

            for (String p : spectators.keySet()) {
                player.hidePlayer(Bukkit.getPlayer(p));
            }

            player.teleport(getPlayerSpawn());

            broadcast(Statics.getPlayerJoinMessage(player, players.size(), getMinPlayers(), getMaxPlayers()));

            if (players.size() >= getMinPlayers()) {
                startCountDown();
            }
        } else {
            addSpectator(player, true);
            player.sendMessage(Statics.getNextGameMessage());
        }
    }

    private void removePlayer(Player player) {
        // removes a player from the game
        // does not do any checks for wins, as this might be called by reload logic

        players.get(player.getName()).restore(player);
        players.remove(player.getName());

        ServerManager.getInstance().removePlayer(player);

        for (String p : spectators.keySet()) {
            player.showPlayer(Bukkit.getPlayer(p));
        }

        CompassUtil.resetCompass(player);
    }

    public void playerLost(Player p) {
        // called when a player loses

        if (currentState.partOfGame()) {
            removePlayer(p);
            addSpectator(p, true);

            checkIfWin();
        }
    }

    public void playerQuit(Player p) {
        // called when a player quits the game (as in the minigame, not necesarily the game game)

        removePlayer(p);
        broadcast(Statics.getPlayerQuitMessage(p));

        checkPlayerCount();
        checkIfWin();
    }

    private void addSpectator(Player player, boolean isPlayer) {
        // adds a spectator to the game, with the added option of setting if this spectator should be a player
        // (which is used by addPlayer and by playerLost)

        if (ServerManager.getInstance().isPlayer(player)) {
            ServerManager.getInstance().getGameByPlayer(player).playerQuit(player);
        } else if (ServerManager.getInstance().isSpectator(player)) {
            ServerManager.getInstance().getGameByPlayer(player).removeSpectator(player);
        }

        spectators.put(player.getName(), new PlayerOrigin(player, isPlayer));
        player.getInventory().clear();

        ServerManager.getInstance().addSpectator(player, this);

        // TODO - give stuffs (spectating stuffs)

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).hidePlayer(player);
        }

        player.teleport(getSpectatorSpawn());
    }

    public void addSpectator(Player player) {
        // adds a spectator to the game

        addSpectator(player, false);
    }

    public void removeSpectator(Player player) {
        // removes a spectator from the game

        spectators.get(player.getName()).restore(player);
        spectators.remove(player.getName());

        ServerManager.getInstance().removeSpectator(player);

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).showPlayer(player);
        }

        CompassUtil.resetCompass(player);
    }

    public void killGame() {
        // used on onDisable or when the game is deleted to return player's stuff, remove from hashmaps, etc

        broadcast(Statics.getReloadMessage());

        for (String p : players.keySet()) {
            removePlayer(Bukkit.getPlayer(p));
        }

        for (String p : spectators.keySet()) {
            removeSpectator(Bukkit.getPlayer(p));
        }

        if (Statics.resetWorlds) {
            WorldResetter.resetWorld(getPlayerSpawn().getWorld().getName());
        }
    }

    public PlayerObject getPlayerObject(Player p) {
        // gets the playerobject for a player

        return players.get(p.getName());
    }

    private void checkIfWin() {
        // checks if the game has a winner
        // is called by PlayerLost(Player) and PlayerQuit(Player)
        // (and not by removePlayer(Player) because reasons

        if (currentState.partOfGame()) {
            if (players.size() == 1) {
                for (String p : players.keySet()) {
                    broadcast(Statics.getPlayerWinMessage(Bukkit.getPlayer(p)));

                    onWin();
                }
            } else if (players.size() <= 0) {
                onWin();
            }
        }
    }

    public void checkPlayerCount() {
        // run by the playerQuit, checks if the player count is still high enough to start the game

        if (currentState == GameState.GAME_STARTING) {
            if (players.size() < getMinPlayers()) {
                broadcast(Statics.getTooFewMessage());
                timerObject.cancel();
            }

            if (players.size() < getMaxPlayers()) {
                int diff = getMaxPlayers() - players.size();

                for (PlayerOrigin p : spectators.values()) {
                    if (diff > 0 && p.isPlayer()) {
                        addPlayer(Bukkit.getPlayer(p.getName()));
                    }
                }
            }
        }
    }

    public void startCountDown() {
        // runs when the minPlayers is met

        if (currentState == GameState.WAITING_FOR_PLAYERS) {
            currentState = GameState.GAME_STARTING;

            broadcast(Statics.getPlayersMetMessage(getMinPlayers()));
            timerObject = new BukkitRunnable() {
                @Override
                public void run() {
                    startGame();
                }
            };
            timerObject.runTaskLater(Minigame.getInstance(), Statics.gameStartDelay);
        }
    }

    public void startGame() {
        // runs when the start countdown is over

        currentState = GameState.GAME_GOING;
        broadcast(Statics.getGameStartMessage());
    }

    public void onWin() {
        // runs when a player wins/when there is somehow 0 players

        currentState = GameState.GAME_ENDING;

        broadcast(Statics.getGameOverMessage());

        Iterator<Map.Entry<String, PlayerObject>> iter = players.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, PlayerObject> entry = iter.next();
            removePlayer(Bukkit.getPlayer(entry.getKey()));
            addSpectator(Bukkit.getPlayer(entry.getKey()));
        }

        timerObject = new BukkitRunnable() {
            @Override
            public void run() {
                resetGame();
            }
        };
        timerObject.runTaskLater(Minigame.getInstance(), Statics.gameResetDelay);
    }

    public void resetGame() {
        // runs some time after onWin, resets game & adds spectating players to game

        currentState = GameState.WAITING_FOR_PLAYERS;

        broadcast(Statics.getResetMessage());

        int count = 0;

        Iterator<Map.Entry<String, PlayerOrigin>> iter = spectators.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, PlayerOrigin> entry = iter.next();
            if (entry.getValue().isPlayer() && count <= getMaxPlayers()) {
                addPlayer(Bukkit.getPlayer(entry.getKey()));
                Bukkit.getPlayer(entry.getKey()).sendMessage(Statics.getAutoaddedMessage());
                count++;
            }
        }

        if (Statics.resetWorlds) {
            WorldResetter.resetWorld(getPlayerSpawn().getWorld().getName());
        }
    }

    public GameState getCurrentState() {
        // gets the GameState that this game is currently in

        return currentState;
    }

    public void broadcast(String message) {
        // sends a message to all players and spectators in this game instance

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).sendMessage(message);
        }

        for (String p : spectators.keySet()) {
            Bukkit.getPlayer(p).sendMessage(message);
        }
    }
}
