package tk.hintss.minigame;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerOrigin {
    // stores data about where players were and what they had before they joined/spectated a game

    private final String name;
    // isPlayer stores if the player joined the game as a player (and not a spectator)
    // if a game ends and this is true, they are auto-added to the next game
    private boolean isPlayer;
    private final Location originLoc;
    private final ItemStack[] inv;
    private final ItemStack[] armor;
    private final int exp;

    public PlayerOrigin(Player player, boolean isPlayer) {
        // stores the player's data

        this.name = player.getName();
        this.isPlayer = isPlayer;
        
        this.originLoc = player.getLocation();
        this.inv = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
        this.exp = player.getTotalExperience();
    }

    public void restore(Player player) {
        // gives back all the player's stuff and teleports them back to where they were before they joined

        player.getInventory().clear();

        player.teleport(originLoc);
        player.getInventory().setContents(inv);
        player.getInventory().setArmorContents(armor);
        player.setTotalExperience(exp);
    }
}
