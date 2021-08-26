package fr.lataverne.itemreward.items;

import fr.lataverne.itemreward.managers.CustomItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

import static fr.lataverne.itemreward.Helper.*;

public class BaseballBat extends CustomItem {
	public BaseballBat() {
		super(Material.WOODEN_SWORD);

		ItemMeta itemMeta = Objects.requireNonNull(this.getItemMeta(), "Item meta can't be null");

		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemMeta.addEnchant(Enchantment.KNOCKBACK, 20, true);

		if (configPathExists(this.getConfigPath() + ".displayName")) {
			itemMeta.setDisplayName(getStringInConfig(this.getConfigPath() + ".displayName", true));
		}

		if (configPathExists(this.getConfigPath() + ".lore")) {
			itemMeta.setLore(getStringListInConfig(this.getConfigPath() + ".lore", true));
		}

		this.setItemMeta(itemMeta);
	}

	public BaseballBat(ItemStack itemStack) {
		super(itemStack);
	}

	@Override
	public ECustomItem getCustomItemType() {
		return ECustomItem.BaseballBat;
	}

	@Override
	protected String getConfigPath() {
		return "item.baseballBat";
	}

	@Override
	protected void onInventoryClick(InventoryClickEvent e) {
		cantUseInCraft(e);
		cantRepairableAndEnchanted(e);

		InventoryType inventoryType = e.getInventory().getType();
		int rawSlot = e.getRawSlot();

		if (inventoryType == InventoryType.GRINDSTONE && rawSlot < 3) {
			e.setCancelled(true);
		}
	}
}
