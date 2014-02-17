package tk.hintss.minigame.util;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.hintss.minigame.Minigame;

public class CountdownTask extends BukkitRunnable {
    private Player player;
    private int time;
    private String line;

    public CountdownTask(Player player, int time, String line) {
        this.player = player;
        this.time = time;
        this.line = line;
    }

    public void run() {
        final int[] minutes = {15, 10, 5, 3, 2, 1};
        final int[] seconds = {45, 30, 20, 15, 10, 5, 4, 3, 2, 1};

        if (time >= 60 * 20) {
            for (int i = 0; i < minutes.length; i++) {
                if (minutes[i] * 60 * 20 < time) {
                    new CountdownTask(player, minutes[i] * 60 * 20, line).runTaskLater(Minigame.getInstance(), time - minutes[i] * 60 * 20);
                    return;
                } else if (minutes[i] * 60 * 20 == time) {
                    player.sendMessage(String.format(line, Integer.valueOf(minutes[i]) + " minutes"));

                    if (i < minutes.length - 1) {
                        new CountdownTask(player, minutes[i] * 60 * 20, line).runTaskLater(Minigame.getInstance(), time - minutes[i] * 60 * 20);
                    } else {
                        new CountdownTask(player, seconds[0] * 20, line).runTaskLater(Minigame.getInstance(), time - seconds[0] * 20);
                    }

                    return;
                }
            }
        } else {
            for (int i = 0; i < seconds.length; i++) {
                if (seconds[i] * 20 < time) {
                    new CountdownTask(player, minutes[i] * 60 * 20, line).runTaskLater(Minigame.getInstance(), time - minutes[i] * 60 * 20);
                    return;
                } else if (seconds[i] * 20 == time) {
                    player.sendMessage(String.format(line, Integer.valueOf(seconds[i] + "seconds")));

                    if (i < seconds.length - 1) {
                        new CountdownTask(player, minutes[i] * 60 * 20, line).runTaskLater(Minigame.getInstance(), time - minutes[i] * 60 * 20);
                    }

                    return;
                }
            }
        }
    }
}
