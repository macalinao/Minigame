package tk.hintss.minigame;

import org.bukkit.entity.Player;

public class PlayerObject extends PlayerOrigin {
    private int kills = 0;
    private int deaths = 0;

    public PlayerObject(Player player, boolean isPlayer) {
        super(player, isPlayer);
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        // adds one to this player's kill counter

        kills++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeath() {
        // adds one to this player's death counter

        deaths++;
    }
}
