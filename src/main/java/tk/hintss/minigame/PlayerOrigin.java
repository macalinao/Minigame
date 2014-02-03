package tk.hintss.minigame;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerOrigin {
    // stores data about where players were and what they had before they joined/spectated a game

    private final String name;
    private final Location originLoc;
    private final ItemStack[] inv;
    private final ItemStack[] armor;

    public PlayerOrigin(Player player) {
        // stores the player's data

        this.name = player.getName();
        this.originLoc = player.getLocation();
        this.inv = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
    }

    public void restore(Player player) {
        // gives back all the player's stuff and teleports them back to where they were before they joined

        player.getInventory().clear();

        player.teleport(originLoc);
        player.getInventory().setContents(inv);
        player.getInventory().setArmorContents(armor);
    }
}
