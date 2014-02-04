package tk.hintss.minigame;

import org.bukkit.plugin.java.JavaPlugin;
import tk.hintss.minigame.listeners.KillDeathCountListener;
import tk.hintss.minigame.listeners.PlayerQuitListener;

public class Minigame extends JavaPlugin {
    public static Minigame instance;

    @Override
    public void onEnable() {
        instance = this;

        new ServerManager(this);

        getServer().getPluginManager().registerEvents(new KillDeathCountListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        ServerManager.getInstance().killGames();
    }

    public static Minigame getInstance() {
        return instance;
    }
}
