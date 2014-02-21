package tk.hintss.minigame;

import org.bukkit.plugin.java.JavaPlugin;
import tk.hintss.minigame.listeners.*;

public class Minigame extends JavaPlugin {
    public static Minigame instance;

    @Override
    public void onEnable() {
        instance = this;

        new ServerManager(this);

        getCommand(Statics.gameName.toLowerCase()).setExecutor(new MinigameCommand());

        getServer().getPluginManager().registerEvents(new KillDeathCountListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PrePostGameListener(), this);
        getServer().getPluginManager().registerEvents(new SignListener(), this);
        getServer().getPluginManager().registerEvents(new SpectatorListener(), this);
    }

    @Override
    public void onDisable() {
        ServerManager.getInstance().killGames();
    }

    public static Minigame getInstance() {
        return instance;
    }
}
