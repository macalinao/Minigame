package tk.hintss.minigame.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.hintss.minigame.Minigame;

import java.util.HashMap;

public class CompassUtil {
    public static HashMap<String, BukkitRunnable> targets = new HashMap<String, BukkitRunnable>();

    public static void setCompassTarget(Player p, Location loc) {
        resetCompass(p);

        p.setCompassTarget(loc);
    }

    public static void setCompassTarget(final Player p, final Entity e) {
        resetCompass(p);

        targets.put(p.getName(), new BukkitRunnable() {
            @Override
            public void run() {
                p.setCompassTarget(e.getLocation());
            }
        });

        targets.get(p.getName()).runTaskTimer(Minigame.getInstance(), 0, 10);
    }

    public static void resetCompass(Player p) {
        if (targets.containsKey(p.getName())) {
            targets.get(p.getName()).cancel();
            targets.remove(p.getName());
        }

        p.setCompassTarget(p.getWorld().getSpawnLocation());
    }
}
