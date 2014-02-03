package tk.hintss.minigame;

import org.bukkit.plugin.java.JavaPlugin;
import tk.hintss.minigame.listeners.PlayerQuitListener;

public class Minigame extends JavaPlugin {
    private ServerManager serverManager = null;

    @Override
    public void onEnable() {
        serverManager = new ServerManager(this);

        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
