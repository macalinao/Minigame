package tk.hintss.minigame.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CountdownTask extends BukkitRunnable {
    // schedule this to run once a second

    private Set<String> players;
    private int time;
    private String line;

    public CountdownTask(Set<String> players, int time, String line) {
        this.players = players;
        this.time = time;
        this.line = line;
    }

    public void run() {
        final List<Integer> minutes = Arrays.asList(60, 45, 30, 15, 10, 5, 3, 2, 1);
        final List<Integer> seconds = Arrays.asList(45, 30, 20, 15, 10, 9, 8, 7, 6,  5, 4, 3, 2, 1);

        if (time >= 60 && time % 60 == 0) {
            if (minutes.contains(time / 60)) {
                for (String p : players) {
                    if (Bukkit.getPlayer(p) != null) {
                        if (time / 60 == 1) {
                            Bukkit.getPlayer(p).sendMessage(String.format(line, String.valueOf(time / 60) + " minute"));
                        } else {
                            Bukkit.getPlayer(p).sendMessage(String.format(line, String.valueOf(time / 60) + " minutes"));
                        }
                    }
                }
            }
        } else if (time > 0) {
            if (seconds.contains(time)) {
                for (String p : players) {
                    if (Bukkit.getPlayer(p) != null) {
                        if (time == 1) {
                            Bukkit.getPlayer(p).sendMessage(String.format(line, String.valueOf(time) + " second"));
                        } else {
                            Bukkit.getPlayer(p).sendMessage(String.format(line, String.valueOf(time) + " seconds"));
                        }
                    }
                }
            }
        }

        if (time <= 1) {
            this.cancel();
        }

        time--;
    }
}
