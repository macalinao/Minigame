package tk.hintss.minigame;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Statics {
    public static final String gameName = "Minigame";

    public static final boolean resetWorlds = true;

    public static final int gameStartDelay = 20*60;
    public static final int gameResetDelay = 20*20;

    public static String getPlayersMetMessage(int minPlayers) {
        // when we get enough players to start the game countdown

        return ChatColor.GREEN + "We have " + String.valueOf(minPlayers) + " players, the game will start in " + Integer.valueOf(gameStartDelay / 20) + " seconds!";
    }

    public static String getTooFewMessage() {
        // when we don't have enough

        return ChatColor.GREEN + "We don't have enough players anymore, the game isn't starting until we get more players";
    }

    public static String getGameStartMessage() {
        // when the start countdown ends, and the game begins

        return ChatColor.GREEN + "The game is starting!";
    }

    public static String getPlayerJoinMessage(Player p, int players, int min, int max) {
        // when a player joins the game

        if (players < min) {
            return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " joined the game, " + String.valueOf(min - players) + " more needed to start!";
        } else if (players < max) {
            return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " joined the game, " + String.valueOf(max - players) + " slots remaining!";
        } else {
            return null;
        }
    }

    public static String getPlayerQuitMessage(Player p) {
        // when a player leaves the game (voluntarily)

        return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " left the game!";
    }

    public static String getPlayerWinMessage(Player p) {
        // when someone wins

        return ChatColor.AQUA + p.getName() + ChatColor.GREEN + " won the game!";
    }

    public static String getGameOverMessage() {
        return ChatColor.GREEN + "The game is now over!";
    }

    public static String getResetMessage() {
        // when the next game starts

        return ChatColor.GREEN + "The next game is starting!";
    }

    public static String getAutoaddedMessage() {
        // when a player is joined to the next game on reset

        return ChatColor.GREEN + "auto-adding you to this game, since you wanted to play!";
    }

    public static String getReloadMessage() {
        // sent to the players when the plugin is reloaded

        return ChatColor.RED + "The game was ended because the plugin was reloaded.";
    }

    public static String getNextGameMessage() {
        // when a player tries to join a going game

        return ChatColor.GREEN + "A game is currently going, you will be put in the next game.";
    }
}
