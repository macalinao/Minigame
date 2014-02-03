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
        kills++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeath() {
        deaths++;
    }
}
