package tk.hintss.minigame;

import org.bukkit.entity.Player;

public class PlayerObject extends PlayerOrigin {
    private int kills = 0;
    private int deaths = 0;

    public PlayerObject(Player player, boolean isPlayer) {
        super(player, isPlayer);
    }

    public int getKills() {
        // gets the number of kills this player has

        return kills;
    }

    public void addKill() {
        // adds one to this player's kill counter

        kills++;
    }

    public int getDeaths() {
        // gets the number of times the player has died in this game

        return deaths;
    }

    public void addDeath() {
        // adds one to this player's death counter

        deaths++;
    }
}
