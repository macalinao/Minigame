package tk.hintss.minigame;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerOrigin {
    private final String name;

    // isPlayer stores if the player joined the game as a player (and not a spectator)
    // if a game ends and this is true, they are auto-added to the next game
    private boolean isPlayer;

    private final Location originLoc;

    private final double health;
    private final int food;
    private final float saturation;

    private final ItemStack[] inv;
    private final ItemStack[] armor;

    private final int exp;

    public PlayerOrigin(Player player, boolean isPlayer) {
        this.name = player.getName();

        this.isPlayer = isPlayer;

        this.originLoc = player.getLocation();

        this.health = player.getHealth();
        this.food = player.getFoodLevel();
        this.saturation = player.getSaturation();

        this.inv = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();

        player.getInventory().clear();

        this.exp = player.getTotalExperience();

        player.setTotalExperience(0);
    }

    public void restore(Player player) {
        if (player.getName().equals(name)) {
            player.getInventory().clear();

            player.teleport(originLoc);

            player.setHealth(health);
            player.setFoodLevel(food);
            player.setSaturation(saturation);

            player.getInventory().setContents(inv);
            player.getInventory().setArmorContents(armor);

            player.setTotalExperience(exp);
        }
    }

    public String getName() {
        return name;
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
