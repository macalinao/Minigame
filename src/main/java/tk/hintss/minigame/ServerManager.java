package tk.hintss.minigame;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ServerManager {
    private Minigame plugin;
    private static ServerManager instance;

    private HashMap<String, GameObject> playerToGame = new HashMap<String, GameObject>();
    private HashMap<String, GameObject> spectatorrToGame = new HashMap<String, GameObject>();

    public ServerManager(Minigame plugin) {
        this.plugin = plugin;
        instance = this;
    }

    public static ServerManager getInstance() {
        return instance;
    }

    public void addPlayer(Player player, GameObject g) {
        // adds a player to the playerToGame HashMap, called by GameObject.addPlayer(Player)

        playerToGame.put(player.getName(), g);
    }

    public void removePlayer(Player player) {
        // removes a player from playerToGame, called by GameObject.removePlayer(Player)

        playerToGame.remove(player.getName());
    }

    public void addSpectator(Player player, GameObject g) {
        // adds a player to the spectatorToGame HashMap, called by GameObject.addSpectator(Player)

        spectatorrToGame.put(player.getName(), g);
    }

    public void removeSpectator(Player player) {
        // removes a player from spectatorToGame, called by GameObject.removeSpectator(Player)

        spectatorrToGame.remove(player.getName());
    }

    public boolean isPlayer(Player player) {
        // returns if the player is in a game

        return playerToGame.containsKey(player.getName());
    }

    public boolean isSpectator(Player player) {
        // returns if the player is a spectator in a game

        return spectatorrToGame.containsKey(player.getName());
    }
}
