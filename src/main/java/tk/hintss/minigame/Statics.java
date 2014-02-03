package tk.hintss.minigame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Statics {
    public static String getGameName() {
        return "Minigame";
    }

    public static String getPlayerJoinMessage(Player p) {
        return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " joined the game!";
    }

    public static String getPlayerQuitMessage(Player p) {
        return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " left the game!";
    }

    public static String getPlayerWinMessage(Player p) {
        return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " won the game!";
    }

    public static String getReloadMessage() {
        return ChatColor.RED + "The game was ended because the plugin was reloaded.";
    }

    public static String getNextGameMessage() {
        return ChatColor.GREEN + "A game is currently going, you will be put in the next game.";
    }
}
