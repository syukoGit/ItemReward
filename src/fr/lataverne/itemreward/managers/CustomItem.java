package fr.lataverne.itemreward.managers;

import fr.lataverne.itemreward.items.*;
import fr.lataverne.itemreward.items.potions.FlyPotion;
import fr.lataverne.itemreward.items.potions.PhantomPotion;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.NotImplementedException;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import static fr.lataverne.itemreward.Helper.*;

public abstract class CustomItem extends ItemStack {

	public enum ECustomItem {
		GoblinPickaxe,
		GiantBoots,
		UnbreakableHoe,
		MendingBook,
		FortuneBook,
		SilkTouchBook,
		FireworkRocket,
		RawBear,
		RawHorse,
		CookedBear,
		CookedHorse,
		ULU,
		IndianSpear,
		FlyPotion,
		PhantomPotion,
	}

	private static final String NBTTag = "CustomItemType";

	public CustomItem(Material material) {
		this(material, 1);
	}

	public CustomItem(Material material, int amount) {
		super(material, amount);

		this.init();
	}

	public CustomItem(ItemStack itemStack) {
		super(itemStack);
	}

	public static CustomItem getCustomItem(ECustomItem customItemType) {
		return getCustomItem(customItemType, 1);
	}

	public static CustomItem getCustomItem(ECustomItem customItemType, int level) {
		switch (customItemType) {
			case GoblinPickaxe:
				return new GoblinPickaxe();
			case GiantBoots:
				return new GiantBoots();
			case UnbreakableHoe:
				return new UnbreakableHoe();
			case MendingBook:
				return new MendingBook();
			case FortuneBook:
				return new FortuneBook();
			case SilkTouchBook:
				return new SilkTouchBook();
			case FireworkRocket:
				return new FireworkRocket();
			case RawBear:
				return new RawBear();
			case RawHorse:
				return new RawHorse();
			case CookedBear:
				return new CookedBear();
			case CookedHorse:
				return new CookedHorse();
			case ULU:
				return new ULU();
			case IndianSpear:
				return new IndianSpear();
			case FlyPotion:
				return new FlyPotion(level);
			case PhantomPotion:
				return new PhantomPotion();
			default:
				return null;
		}
	}

	public static CustomItem getCustomItem(ItemStack itemStack) {
		if (itemStack == null || itemStack.getItemMeta() == null || !hasNBT(itemStack, NBTTag)) {
			return null;
		}

		try {
			ECustomItem customItemType = ECustomItem.valueOf(getNBT(itemStack, NBTTag));

			switch (customItemType) {
				case GoblinPickaxe:
					return new GoblinPickaxe(itemStack);
				case GiantBoots:
					return new GiantBoots(itemStack);
				case UnbreakableHoe:
					return new UnbreakableHoe(itemStack);
				case MendingBook:
					return new MendingBook(itemStack);
				case FortuneBook:
					return new FortuneBook(itemStack);
				case SilkTouchBook:
					return new SilkTouchBook(itemStack);
				case FireworkRocket:
					return new FireworkRocket(itemStack);
				case RawBear:
					return new RawBear(itemStack);
				case RawHorse:
					return new RawHorse(itemStack);
				case CookedBear:
					return new CookedBear(itemStack);
				case CookedHorse:
					return new CookedHorse(itemStack);
				case ULU:
					return new ULU(itemStack);
				case IndianSpear:
					return new IndianSpear(itemStack);
				case FlyPotion:
					return new FlyPotion(itemStack);
				case PhantomPotion:
					return new PhantomPotion(itemStack);
				default:
					throw new NotImplementedException("Custom item type not implemented");
			}
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Bad NBT tag for the custom item type. Value: " + getNBT(itemStack, NBTTag));
		}
	}

	public static boolean useBlockBreakEvent(CustomItem customItem) {
		//noinspection SwitchStatementWithTooFewBranches
		switch (customItem.getCustomItemType()) {
			case GoblinPickaxe:
				return true;
			default:
				return false;
		}
	}

	public static boolean useBlockCookEvent(CustomItem customItem) {
		switch (customItem.getCustomItemType()) {
			case RawBear:
			case RawHorse:
				return true;
			default:
				return false;
		}
	}

	public static boolean useEntityDeathEvent(CustomItem customItem) {
		switch (customItem.getCustomItemType()) {
			case ULU:
			case IndianSpear:
				return true;
			default:
				return false;
		}
	}

	public static boolean useInventoryClickEvent(CustomItem customItem) {
		switch (customItem.getCustomItemType()) {
			case FortuneBook:
			case GiantBoots:
			case GoblinPickaxe:
			case IndianSpear:
			case MendingBook:
			case RawBear:
			case RawHorse:
			case SilkTouchBook:
			case ULU:
			case UnbreakableHoe:
				return true;
			default:
				return false;
		}
	}

	public static boolean usePlayerItemConsumeEvent(CustomItem customItem) {
		switch (customItem.getCustomItemType()) {
			case RawBear:
			case RawHorse:
			case CookedBear:
			case CookedHorse:
			case FlyPotion:
			case PhantomPotion:
				return true;
			default:
				return false;
		}
	}

	public static boolean usePlayerMoveEvent(CustomItem customItem) {
		//noinspection SwitchStatementWithTooFewBranches
		switch (customItem.getCustomItemType()) {
			case GiantBoots:
				return true;
			default:
				return false;
		}
	}

	public abstract ECustomItem getCustomItemType();

	protected abstract String getConfigPath();

	protected void onBlockBreak(BlockBreakEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	protected void onBlockCook(BlockCookEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	protected void onEntityDeath(EntityDeathEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	protected void onInventoryClick(InventoryClickEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	protected void onPlayerItemConsume(PlayerItemConsumeEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	protected void onPlayerMove(PlayerMoveEvent e) throws NotImplementedException {
		throw new NotImplementedException("Not implemented");
	}

	private void init() {
		addNBT(this, NBTTag, this.getCustomItemType().toString());
	}
}
