package tk.hintss.minigame;

import org.bukkit.Location;
import tk.hintss.minigame.util.LocationUtil;

public class Arena {
    private String name;

    private int minPlayers;
    private int maxPlayers;

    private Location playerSpawn;
    private Location spectatorSpawn;

    private boolean isGame = false;

    public Arena(String name, int minPlayers, int maxPlayers) {
        // constructor for making a new Arena (as in in the game, not as in the object)

        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public Arena(String line) {
        // used by ServerManager/GameObject to load an arena from config

        String[] s = line.split("|");

        name = s[0];

        minPlayers = Integer.valueOf(s[1]);
        maxPlayers = Integer.valueOf(s[2]);

        playerSpawn = LocationUtil.locationFromString(s[3]);
        spectatorSpawn = LocationUtil.locationFromString(s[4]);

        isGame = true;
    }

    public String saveToString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append("|");
        sb.append(minPlayers);
        sb.append("|");
        sb.append(maxPlayers);
        sb.append("|");
        sb.append(LocationUtil.stringFromLocation(playerSpawn));
        sb.append("|");
        sb.append(LocationUtil.stringFromLocation(spectatorSpawn));

        return sb.toString();
    }

    public void upgrade() {
        if (!isGame &&playerSpawn != null && spectatorSpawn != null) {
            ServerManager.getInstance().newGame(saveToString());
            ServerManager.getInstance().removeArena(name);
        }
    }

    public String getName() {
        return name;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setPlayerSpawn(Location loc) {
        this.playerSpawn = loc;
    }

    public Location getPlayerSpawn() {
        return playerSpawn;
    }

    public void setSpectatorSpawn(Location loc) {
        this.spectatorSpawn = loc;
    }

    public Location getSpectatorSpawn() {
        return spectatorSpawn;
    }
}
