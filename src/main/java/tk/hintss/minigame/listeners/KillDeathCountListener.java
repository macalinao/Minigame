package tk.hintss.minigame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import tk.hintss.minigame.ServerManager;

public class KillDeathCountListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        // increments kill/death counters

        if (ServerManager.getInstance().isPlayer(event.getEntity())) {
            ServerManager.getInstance().getGameByPlayer(event.getEntity()).getPlayerObject(event.getEntity()).addDeath();
        }

        if (event.getEntity().getKiller() != null && ServerManager.getInstance().isPlayer(event.getEntity())) {
            ServerManager.getInstance().getGameByPlayer(event.getEntity().getKiller()).getPlayerObject(event.getEntity().getKiller()).addKill();
        }
    }
}
