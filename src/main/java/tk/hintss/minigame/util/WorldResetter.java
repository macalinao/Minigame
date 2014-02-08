package tk.hintss.minigame.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import tk.hintss.minigame.Minigame;

import java.util.HashMap;

public class WorldResetter {
    public static void resetWorld(String name) {
        World w = Bukkit.getWorld(name);

        HashMap<Player, Location> playerLocs = new HashMap<Player, Location>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld() == w) {
                playerLocs.put(p, p.getLocation());
                p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        }

        if (Bukkit.unloadWorld(w, false)) {
            Minigame.getInstance().getLogger().info("Successfully unloaded world " + name);
        } else {
            Minigame.getInstance().getLogger().severe("Error unloading world " + name);
        }

        w = Bukkit.createWorld(new WorldCreator(name));
        w.setAutoSave(false);

        for (Player p : playerLocs.keySet()) {
            p.teleport(playerLocs.get(p));
        }
    }
}
