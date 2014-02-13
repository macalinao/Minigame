package tk.hintss.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import tk.hintss.minigame.ServerManager;

public class SpectatorListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (ServerManager.getInstance().isSpectator(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && ServerManager.getInstance().isSpectator((Player) event.getDamager())) {
            event.setCancelled(true);
        }
    }
}
