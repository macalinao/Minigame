package tk.hintss.minigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameObject extends Arena {
    private HashMap<String, PlayerObject> players = new HashMap<String, PlayerObject>();
    private HashMap<String, PlayerOrigin> spectators = new HashMap<String, PlayerOrigin>();

    private GameState currentState = GameState.WAITING_FOR_PLAYERS;

    public GameObject(String s) {
        super(s);
    }

    public void addPlayer(Player player) {
        // adds a player to the game, teleports them in, saves their inv, all that jazz

        if (currentState.getCanJoin()) {
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
        } else {
            addSpectator(player, true);
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
    }

    public void playerLost(Player p) {
        // called when a player loses

        removePlayer(p);
        addSpectator(p, true);

        checkIfWin();
    }

    public void playerQuit(Player p) {
        // called when a player quits the game (as in the minigame, not necesarily the game game)

        removePlayer(p);
        broadcast(Statics.getPlayerQuitMessage(p));

        checkIfWin();
    }

    private void addSpectator(Player player, boolean isPlayer) {
        // adds a spectator to the game, with the added option of setting if this spectator should be a player
        // (which is used by addPlayer and by playerLost)

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
    }

    // TODO - kill game thing

    // TODO - game flow

    private void checkIfWin() {
        // checks if the game has a winner
        // is called by PlayerLost(Player) and PlayerQuit(Player)

        // TODO - this
        if (currentState.partOfGame()) {

        }
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
