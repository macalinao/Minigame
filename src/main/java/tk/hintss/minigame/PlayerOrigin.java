package tk.hintss.minigame;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerOrigin {
    private final Location originLoc;
    private final ItemStack[] inv;
    private final ItemStack[] armor;

    public PlayerOrigin(Player player) {
        this.originLoc = player.getLocation();
        this.inv = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();

        player.getInventory().clear();
    }

    public void restore(Player player) {
        player.getInventory().clear();

        player.teleport(originLoc);
        player.getInventory().setContents(inv);
        player.getInventory().setArmorContents(armor);
    }
}
