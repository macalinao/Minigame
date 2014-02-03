package tk.hintss.minigame.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtil {
    public static Location locationFromString(String location) {
        // takes a String of the form [world:]x:y:z[:yaw:pitch] and returns a location

        String[] split = location.split(":");

        if (split.length == 6) {
            // world:x:y:z:yaw:pitch
            return new Location(Bukkit.getWorld(split[0]), new Double(split[1]), new Double(split[2]), new Double(split[3]), new Float(split[4]), new Float(split[5]));
        } else if (split.length == 5) {
            // x:y:z:yaw:pitch
            return new Location(Bukkit.getWorlds().get(0), new Double(split[0]), new Double(split[1]), new Double(split[2]), new Float(split[3]), new Float(split[4]));
        } else if (split.length == 4) {
            // world:x:y:z
            return new Location(Bukkit.getWorld(split[0]), new Double(split[1]), new Double(split[2]), new Double(split[3]));
        } else if (split.length == 3) {
            // x:y:z
            return new Location(Bukkit.getWorlds().get(0), new Double(split[0]), new Double(split[1]), new Double(split[2]));
        } else {
            return null;
        }
    }

    public static String stringFromLocation(Location loc) {
        // gives a world:x:y:z:yaw:pitch from a Location

        StringBuilder sb = new StringBuilder();

        sb.append(loc.getWorld().getName());
        sb.append(":");
        sb.append(loc.getX());
        sb.append(":");
        sb.append(loc.getY());
        sb.append(":");
        sb.append(loc.getZ());
        sb.append(":");
        sb.append(loc.getYaw());
        sb.append(":");
        sb.append(loc.getPitch());

        return sb.toString();
    }
}
