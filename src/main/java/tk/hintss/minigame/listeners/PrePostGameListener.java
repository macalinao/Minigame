package tk.hintss.minigame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import tk.hintss.minigame.ServerManager;

public class PrePostGameListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && ServerManager.getInstance().isPlayer((Player) event.getEntity())) {
            if (!ServerManager.getInstance().getGameByPlayer((Player) event.getEntity()).getCurrentState().partOfGame()) {
                event.setCancelled(true);
            }
        }
    }
}
