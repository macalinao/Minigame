package tk.hintss.minigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameObject extends Arena {
    private HashMap<String, PlayerOrigin> players = new HashMap<String, PlayerOrigin>();
    private HashMap<String, PlayerOrigin> spectators = new HashMap<String, PlayerOrigin>();

    private GameState currentState = GameState.WAITING_FOR_PLAYERS;

    // TODO - global player -> game lookups

    public boolean addPlayer(Player player) {
        // adds a player to the game, teleports them in, saves their inv, all that jazz

        if (currentState.getCanJoin()) {
            players.put(player.getName(), new PlayerOrigin(player));
            player.getInventory().clear();

            for (String p : spectators.keySet()) {
                player.hidePlayer(Bukkit.getPlayer(p));
            }

            player.teleport(getPlayerSpawn());
            return true;
        } else {
            return false;
        }
    }

    private void removePlayer(Player player) {
        // removes a player from the game
        // does not do any checks for wins, as it might be called by reload logic

        players.get(player.getName()).restore(player);
        spectators.remove(player.getName());

        for (String p : spectators.keySet()) {
            player.showPlayer(Bukkit.getPlayer(p));
        }
    }

    public void PlayerLost(Player p) {
        // called when a player loses

        removePlayer(p);
        addSpectator(p);

        checkIfWin();
    }

    public void PlayerQuit(Player p) {
        // called when a player quits the game (as in the minigame, not necesarily the game game)

        removePlayer(p);
        broadcast(Statics.getPlayerQuitMessage(p));

        checkIfWin();
    }

    public void addSpectator(Player player) {
        // adds a spectator to the game, saves inv, sets invisibility, etc

        spectators.put(player.getName(), new PlayerOrigin(player));
        player.getInventory().clear();

        // TODO - give stuffs

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).hidePlayer(player);
        }

        player.teleport(getSpectatorSpawn());
    }

    public void removeSpectator(Player player) {
        // removes a spectator from the game

        spectators.get(player.getName()).restore(player);
        spectators.remove(player.getName());

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
