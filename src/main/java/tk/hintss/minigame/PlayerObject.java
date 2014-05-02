package tk.hintss.minigame;

import org.bukkit.entity.Player;

public class PlayerObject {
    private PlayerOrigin origin;

    private int kills = 0;
    private int deaths = 0;

    public PlayerObject(Player player) {
        this.origin = new PlayerOrigin(player, true);
    }

    public PlayerObject(Player player, PlayerOrigin origin) {
        this.origin = origin;
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

    public PlayerOrigin getOrigin() {
        return origin;
    }
}
