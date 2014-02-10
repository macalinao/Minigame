package tk.hintss.minigame.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import tk.hintss.minigame.ServerManager;
import tk.hintss.minigame.Statics;

public class SignListener implements Listener {
    @EventHandler
    public void onClickSign(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.SIGN) {
                Sign sign = (Sign) event.getClickedBlock().getState();

                if (sign.getLine(0).equalsIgnoreCase("[" + Statics.gameName + "]")) {
                    if (sign.getLine(1).equalsIgnoreCase("join")) {
                        if (event.getPlayer().hasPermission(Statics.gameName.toLowerCase() + ".play")) {
                            if (ServerManager.getInstance().getGameByName(sign.getLine(2)) != null) {
                                ServerManager.getInstance().getGameByName(sign.getLine(2)).addPlayer(event.getPlayer());
                            } else {
                                event.getPlayer().sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to join a game!");
                        }
                    } else if (sign.getLine(1).equalsIgnoreCase("spectate")) {
                        if (event.getPlayer().hasPermission(Statics.gameName.toLowerCase() + ".spectate")) {
                            if (ServerManager.getInstance().getGameByName(sign.getLine(2)) != null) {
                                ServerManager.getInstance().getGameByName(sign.getLine(2)).addSpectator(event.getPlayer());
                            } else {
                                event.getPlayer().sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to spectate a game!");
                        }
                    }
                }
            }
        }
    }
}
