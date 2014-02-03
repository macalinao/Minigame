package tk.hintss.minigame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameObject extends Arena {
    private HashMap<String, PlayerOrigin> players = new HashMap<String, PlayerOrigin>();
    private HashMap<String, PlayerOrigin> spectators = new HashMap<String, PlayerOrigin>();

    // TODO - add/remove player things, quits

    // TODO - global player -> game lookups
    public void addSpectator(Player player) {
        spectators.put(player.getName(), new PlayerOrigin(player));
        // TODO - give stuffs

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).hidePlayer(player);
        }

        player.teleport(getSpectatorSpawn());
    }

    public void removeSpectator(Player player) {
        spectators.get(player.getName()).restore(player);

        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).showPlayer(player);
        }
    }

    // TODO - kill game thing

    // TODO - game flow

    public void broadcast(String s) {
        for (String p : players.keySet()) {
            Bukkit.getPlayer(p).sendMessage(s);
        }

        for (String p : spectators.keySet()) {
            Bukkit.getPlayer(p).sendMessage(s);
        }
    }
}
