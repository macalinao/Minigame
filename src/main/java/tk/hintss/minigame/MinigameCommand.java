package tk.hintss.minigame;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinigameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase(Statics.gameName)) {
            if (sender instanceof Player) {
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.GREEN + "Yes, hi");
                    return false;
                } else if (args[0].equalsIgnoreCase("join")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".play")) {
                        if (ServerManager.getInstance().getGameByName(args[1]) != null) {
                            ServerManager.getInstance().getGameByName(args[1]).addPlayer((Player) sender);
                        } else {
                            sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission to join a game!");
                    }
                } else if (args[0].equalsIgnoreCase("spectate")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".spectate")) {
                        if (ServerManager.getInstance().getGameByName(args[1]) != null) {
                            ServerManager.getInstance().getGameByName(args[1]).addSpectator((Player) sender);
                        } else {
                            sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission to spectate a game!");
                    }
                } else if (args[0].equalsIgnoreCase("newarena")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".map")) {
                        if (args.length == 4) {
                            try {
                                String name = args [1];
                                int minPlayers = Integer.valueOf(args[2]);
                                int maxPlayers = Integer.valueOf(args[3]);

                                if (ServerManager.getInstance().getGameByName(name) == null && ServerManager.getInstance().getArenaByName(name) == null) {
                                    ServerManager.getInstance().newArena(name, minPlayers, maxPlayers);
                                    sender.sendMessage(ChatColor.GREEN + "You successfull created the arena!");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "That arena already exists!");
                                }
                            } catch(NumberFormatException e) {
                                sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " newarena <arenaname> <minplayers> <maxplayers>");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " newarena <arenaname> <minplayers> <maxplayers>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission to make an arena!");
                    }
                } else if (args[0].equalsIgnoreCase("setminplayers")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".map")) {
                        if (args.length == 3) {
                            if (ServerManager.getInstance().getArenaByName(args[1]) != null) {
                                try {
                                    ServerManager.getInstance().getArenaByName(args[1]).setMinPlayers(Integer.parseInt(args[2]));
                                    sender.sendMessage(ChatColor.GREEN + "Successfully set minimum player count!");
                                } catch(NumberFormatException e) {
                                    sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setMinPlayers <arenaName> <minPlayers>");
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setMinPlayers <arenaName> <minPlayers>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission to change arena settings!");
                    }
                } else if (args[0].equalsIgnoreCase("setmaxplayers")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".map")) {
                        if (args.length == 3) {
                            if (ServerManager.getInstance().getArenaByName(args[1]) != null) {
                                try {
                                    ServerManager.getInstance().getArenaByName(args[1]).setMaxPlayers(Integer.parseInt(args[2]));
                                    sender.sendMessage(ChatColor.GREEN + "Successfully set max player count!");
                                } catch(NumberFormatException e) {
                                    sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setMaxPlayers <arenaName> <minPlayers>");
                                }
                            } else {
                                sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setMinPlayers <arenaName> <minPlayers>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission to change arena settings!");
                    }
                } else if (args[0].equalsIgnoreCase("setplayerspawn")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".map")) {
                        if (args.length == 2) {
                            if (ServerManager.getInstance().getArenaByName(args[1]) != null) {
                                ServerManager.getInstance().getArenaByName(args[1]).setPlayerSpawn(((Player) sender).getLocation());
                                sender.sendMessage(ChatColor.GREEN + "Successfully set player spawn!");
                            } else {
                                sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setPlayerSpawn <arena>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setPlayerSpawn <arena>");
                    }
                } else if (args[0].equalsIgnoreCase("setspectatorspawn")) {
                    if (sender.hasPermission(Statics.gameName.toLowerCase() + ".map")) {
                        if (args.length == 2) {
                            if (ServerManager.getInstance().getArenaByName(args[1]) != null) {
                                ServerManager.getInstance().getArenaByName(args[1]).setSpectatorSpawn(((Player) sender).getLocation());
                                sender.sendMessage(ChatColor.GREEN + "Successfully set spectator spawn!");
                            } else {
                                sender.sendMessage(ChatColor.RED + "That arena doesn't exist!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setSpectatorSpawn <arena>");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "/" + Statics.gameName + " setSpectatorSpawn <arena>");
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            }
            return true;
        } else {
            return false;
        }
    }
}
