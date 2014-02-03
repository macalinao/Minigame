package tk.hintss.minigame;

import org.bukkit.Location;
import tk.hintss.minigame.util.LocationUtil;

public class Arena {
    private String name;

    private Location playerSpawn;
    private Location spectatorSpawn;

    public Arena(String name) {
        // used to create a blank new arena

        this.name = name;
    }

    public Arena(String[] s) {
        // used by ServerManager/GameObject to load an arena from config

        name = s[0];

        playerSpawn = LocationUtil.locationFromString(s[1]);
        spectatorSpawn = LocationUtil.locationFromString(s[2]);
    }

    public String getName() {
        // returns the arena name

        return name;
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
