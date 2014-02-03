package tk.hintss.minigame;

import org.bukkit.Location;

public class Arena {
    private String name;

    private Location playerSpawn;
    private Location spectatorSpawn;

    public String getName() {
        return name;
    }

    public Location getPlayerSpawn() {
        return playerSpawn;
    }

    public Location getSpectatorSpawn() {
        return spectatorSpawn;
    }
}
