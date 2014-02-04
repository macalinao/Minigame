package tk.hintss.minigame;

import org.bukkit.Location;
import tk.hintss.minigame.util.LocationUtil;

public class Arena {
    private String name;

    private int minPlayers;
    private int maxPlayers;

    private Location playerSpawn;
    private Location spectatorSpawn;

    public Arena(String name) {
        // used to create a blank new arena

        this.name = name;
    }

    public Arena(String[] s) {
        // used by ServerManager/GameObject to load an arena from config

        name = s[0];

        minPlayers = Integer.valueOf(s[1]);
        maxPlayers = Integer.valueOf(s[2]);

        playerSpawn = LocationUtil.locationFromString(s[3]);
        spectatorSpawn = LocationUtil.locationFromString(s[4]);
    }

    public String getName() {
        // returns the arena name

        return name;
    }

    public int getMinPlayers() {
        // gets the number of players needed to start a game in this arena

        return minPlayers;
    }

    public int getMaxPlayers() {
        // gets the max players this arena supports

        return maxPlayers;
    }

    public Location getPlayerSpawn() {
        // returns the location where players are teleported to on joining the arena

        return playerSpawn;
    }

    public Location getSpectatorSpawn() {
        // returns the location where spectators are teleported to on joining the arena

        return spectatorSpawn;
    }
}
