package tk.hintss.minigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.hintss.minigame.ServerManager;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (ServerManager.getInstance().isPlayer(event.getPlayer())) {
            ServerManager.getInstance().getGameByPlayer(event.getPlayer()).playerQuit(event.getPlayer());
        }

        if (ServerManager.getInstance().isSpectator(event.getPlayer())) {
            ServerManager.getInstance().getGameByPlayer(event.getPlayer()).removeSpectator(event.getPlayer());
        }
    }
}
