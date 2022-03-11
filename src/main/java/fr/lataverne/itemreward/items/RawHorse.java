package fr.lataverne.itemreward.items;

import fr.lataverne.itemreward.Helper;
import fr.lataverne.itemreward.api.objects.CustomItem;
import fr.lataverne.itemreward.managers.ECustomItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RawHorse extends CustomItem {

    public RawHorse(int amount) {
        super(Material.BEEF, amount);

        ItemMeta itemMeta = Objects.requireNonNull(this.getItemMeta(), "Item meta can't be null");

        itemMeta.setCustomModelData(1);

        if (Helper.configPathExists(this.getConfigPath() + ".displayName")) {
            itemMeta.setDisplayName(Helper.getStringInConfig(this.getConfigPath() + ".displayName", true));
        }

        if (Helper.configPathExists(this.getConfigPath() + ".lore")) {
            itemMeta.setLore(Helper.getStringListInConfig(this.getConfigPath() + ".lore", true));
        }

        this.setItemMeta(itemMeta);
    }

    public RawHorse(ItemStack itemStack) {
        super(itemStack);
    }

    @Override
    public ECustomItem getCustomItemType() {
        return ECustomItem.RawHorse;
    }

    @Override
    public void onBlockCook(@NotNull BlockCookEvent e) {
        switch (e.getBlock().getType()) {
            case CAMPFIRE, SOUL_CAMPFIRE -> e.setResult(new CookedHorse(1));
            default -> e.setCancelled(true);
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        Helper.cantCooked(e);
    }

    @Override
    public void onPlayerItemConsume(@NotNull PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();

        player.setFoodLevel(Math.min(player.getFoodLevel() + 3, 20));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 3));
    }

    @Override
    protected String getConfigPath() {
        return "item.rawHorse";
    }
}
