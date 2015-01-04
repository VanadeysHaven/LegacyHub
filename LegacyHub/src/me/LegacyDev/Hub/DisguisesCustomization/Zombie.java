package me.LegacyDev.Hub.DisguisesCustomization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.Menus.DisguiseMenu;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher.SlotType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.ZombieWatcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Zombie implements Listener {

	public static ArrayList<String> isBaby = new ArrayList<String>();
	public static ArrayList<String> isVillager = new ArrayList<String>();
	public static Map<String, Material> helmet = new HashMap<String, Material>();
	public static Map<String, Material> chestplate = new HashMap<String, Material>();
	public static Map<String, Material> leggings = new HashMap<String, Material>();
	public static Map<String, Material> boots = new HashMap<String, Material>();
	public static Map<String, Material> held = new HashMap<String, Material>();

	public static int egg = 0;
	public static int villagerItem = 2;
	public static int villagerSwitch = villagerItem + 9;
	public static int babyItem = 3;
	public static int babySwitch = babyItem + 9;
	public static int helmetItem = 4;
	public static int helmetSwitch = helmetItem + 9;
	public static int chestplateItem = 5;
	public static int chestplateSwitch = chestplateItem + 9;
	public static int leggingItem = 6;
	public static int leggingSwitch = leggingItem + 9;
	public static int bootsItem = 7;
	public static int bootsSwitch = bootsItem + 9;
	public static int heldItem = 8;
	public static int heldSwitch = heldItem + 9;

	public static void openZombie(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "§8§oZombie customization");

		Main.createDisplay(Material.MONSTER_EGG, 1, 54, inv, egg, "&aSelected disguise &8» &bZombie", null);

		Main.createDisplay(Material.EMERALD, 1, 0, inv, villagerItem, "&aVillager zombie", "&7&oSets if the disguise will be a villager");
		if(!isVillager.contains(p.getName())){
			Main.createDisplay(Material.INK_SACK, 1, 8, inv, villagerSwitch, "&bVillager zombie &8» &cOFF", Main.toggle);
		} else {
			Main.createDisplay(Material.INK_SACK, 1, 10, inv, villagerSwitch, "&bVillager zombie &8» &aON", Main.toggle);
		}


		Main.createDisplay(Material.SEEDS, 1, 0, inv, babyItem, "&aBaby zombie", "&7&oSets if the disguise will be a baby");
		if(!isBaby.contains(p.getName())){
			Main.createDisplay(Material.INK_SACK, 1, 8, inv, babySwitch, "&bBaby zombie &8» &cOFF", Main.toggle);
		} else {
			Main.createDisplay(Material.INK_SACK, 1, 10, inv, babySwitch, "&bBaby zombie &8» &aON", Main.toggle);
		}

		Main.createDisplay(Material.LEATHER_HELMET, 1, 0, inv, helmetItem, "&aHelmet", "&7&oSets the helmet the disguise will be wearing");
		if (!helmet.containsKey(p.getName())) {
			Main.createDisplay(Material.GLASS, 1, 0, inv, helmetSwitch, "&cNothing selected.", Main.select);
		} else {
			Main.createDisplay(helmet.get(p.getName()), 1, 0, inv, helmetSwitch, null, Main.select);
		}

		Main.createDisplay(Material.LEATHER_CHESTPLATE, 1, 0, inv, chestplateItem, "&aChestplate", "&7&oSets the chestplate the disguise will be wearing");
		if (!chestplate.containsKey(p.getName())) {
			Main.createDisplay(Material.GLASS, 1, 0, inv, chestplateSwitch, "&cNothing selected.", Main.select);
		} else {
			Main.createDisplay(chestplate.get(p.getName()), 1, 0, inv, chestplateSwitch, null, Main.select);
		}

		Main.createDisplay(Material.LEATHER_LEGGINGS, 1, 0, inv, leggingItem, "&aLeggings", "&7&oSets the leggings the disguise will be wearing");
		if (!leggings.containsKey(p.getName())) {
			Main.createDisplay(Material.GLASS, 1, 0, inv, leggingSwitch,"&cNothing selected.", Main.select);
		} else {
			Main.createDisplay(leggings.get(p.getName()), 1, 0, inv, leggingSwitch, null, Main.select);
		}

		Main.createDisplay(Material.LEATHER_BOOTS, 1, 0, inv, bootsItem, "&aBoots", "&7&oSets the boots the disguise will be wearing");
		if (!boots.containsKey(p.getName())) {
			Main.createDisplay(Material.GLASS, 1, 0, inv, bootsSwitch, "&cNothing selected", Main.select);
		} else {
			Main.createDisplay(boots.get(p.getName()), 1, 0, inv, bootsSwitch, null, Main.select);
		}

		Main.createDisplay(Material.WOOD_SWORD, 1, 0, inv, heldItem, "&aHeld item", "&7&oSets the item the disguise will be holding");
		if (!held.containsKey(p.getName())) {
			Main.createDisplay(Material.GLASS, 1, 0, inv, heldSwitch, "&cNothing selected", Main.select);
		} else {
			Main.createDisplay(held.get(p.getName()), 1, 0, inv, heldSwitch, null, Main.select);
		}

		for (int i = 27; i < 36; i++) {
			Main.createDisplay(Material.STAINED_CLAY, 1, 13, inv, i, "&aClick to confirm", null);
		}

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		Inventory inv = event.getInventory();
		if (!ChatColor.stripColor(event.getInventory().getName())
				.equalsIgnoreCase("Zombie customization"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!event.getCurrentItem().hasItemMeta()) {
			return;
		}

		if (event.getCurrentItem().getType() == Material.INK_SACK) {
			if (curItem.getItemMeta().getDisplayName().contains("Baby")) {
				if (curItem.getItemMeta().getDisplayName().contains("OFF")) {
					isBaby.add(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 10, inv,babySwitch, "&bBaby Zombie &8» &aON", Main.toggle);
				} else if (curItem.getItemMeta().getDisplayName().contains("ON")) {
					isBaby.remove(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 8, inv, babySwitch, "&bBaby Zombie &8» &cOFF", Main.toggle);
				}
			} else if(curItem.getItemMeta().getDisplayName().contains("Villager")){
				if(curItem.getItemMeta().getDisplayName().contains("OFF")){
					isVillager.add(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 10, inv, villagerSwitch, "&bVillager zombie &8» &aON", Main.toggle);
				} else if(curItem.getItemMeta().getDisplayName().contains("ON")){
					isVillager.remove(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 8, inv, villagerSwitch, "&bVillager zombie &8» &cOFF", Main.toggle);
				}
			}
		} else if (event.getSlot() >= helmetSwitch && event.getSlot() <= heldSwitch) {
			if (event.getSlot() == helmetSwitch) {
				openHelmet(p);
			} else if (event.getSlot() == chestplateSwitch) {
				openChestplate(p);
			} else if (event.getSlot() == leggingSwitch) {
				openLegging(p);
			} else if (event.getSlot() == bootsSwitch) {
				openBoots(p);
			} else if (event.getSlot() == heldSwitch) {
				openHeld(p);
			}
		} else if (curItem.getType() == Material.STAINED_CLAY) {
			MobDisguise dis = new MobDisguise(DisguiseType.ZOMBIE);
			ZombieWatcher watcher = (ZombieWatcher) dis.getWatcher();
			watcher.setItemStack(SlotType.HELMET, new ItemStack(helmet.get(p.getName())));
			helmet.remove(p.getName());
			watcher.setItemStack(SlotType.CHESTPLATE, new ItemStack(chestplate.get(p.getName())));
			chestplate.remove(p.getName());
			watcher.setItemStack(SlotType.LEGGINGS, new ItemStack(leggings.get(p.getName())));
			leggings.remove(p.getName());
			watcher.setItemStack(SlotType.BOOTS, new ItemStack(boots.get(p.getName())));
			boots.remove(p.getName());
			watcher.setItemStack(SlotType.HELD_ITEM, new ItemStack(held.get(p.getName())));
			held.remove(p.getName());
			watcher.setBaby(isBaby.contains(p.getName()));
			isBaby.remove(p.getName());
			watcher.setVillager(isVillager.contains(p.getName()));
			isVillager.remove(p.getName());
			watcher.setCustomName(p.getDisplayName());
			watcher.setCustomNameVisible(true);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(Main.disguiseMsgPart1 + "Zombie" + Main.disguiseMsgPart2);
			DisguiseMenu.disCooldown(p);
			p.closeInventory();
			return;
		} else {
			return;
		}
	}

	private void openHelmet(Player p) {
		Inventory inv = Bukkit.createInventory(null, 18, "§8§oZombie Helmet");

		Main.createDisplay(Material.LEATHER_HELMET, 1, 0, inv, 0, null, null);
		Main.createDisplay(Material.CHAINMAIL_HELMET, 1, 0, inv, 1, null, null);
		Main.createDisplay(Material.IRON_HELMET, 1, 0, inv, 2, null, null);
		Main.createDisplay(Material.GOLD_HELMET, 1, 0, inv, 3, null, null);
		Main.createDisplay(Material.DIAMOND_HELMET, 1, 0, inv, 4, null, null);

		Main.createDisplay(Material.TNT, 1, 0, inv, 9, null, null);
		Main.createDisplay(Material.GLOWSTONE, 1, 0, inv, 10, null, null);
		Main.createDisplay(Material.WORKBENCH, 1, 0, inv, 11, null, null);
		Main.createDisplay(Material.DIAMOND_BLOCK, 1, 0, inv, 12, null, null);
		Main.createDisplay(Material.ICE, 1, 0, inv, 13, null, null);

		Main.createDisplay(Material.STAINED_GLASS, 1, 14, inv, 8, "&cCancel", null);
		Main.createDisplay(Material.GLASS, 1, 0, inv, 17, "&cClear", null);

		p.openInventory(inv);
	}

	@EventHandler
	public void onHelmetClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Zombie Helmet"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!curItem.hasItemMeta()) {
			helmet.put(p.getName(), curItem.getType());
			openZombie(p);
		} else {
			if (curItem.getType() == Material.STAINED_GLASS) {
				openZombie(p);
			} else {
				helmet.remove(p.getName());
				openZombie(p);
			}
		}
	}

	private void openChestplate(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9,
				"§8§oZombie Chestplate");

		Main.createDisplay(Material.LEATHER_CHESTPLATE, 1, 0, inv, 0, null, null);
		Main.createDisplay(Material.CHAINMAIL_CHESTPLATE, 1, 0, inv, 1, null, null);
		Main.createDisplay(Material.IRON_CHESTPLATE, 1, 0, inv, 2, null, null);
		Main.createDisplay(Material.GOLD_CHESTPLATE, 1, 0, inv, 3, null, null);
		Main.createDisplay(Material.DIAMOND_CHESTPLATE, 1, 0, inv, 4, null, null);

		Main.createDisplay(Material.STAINED_GLASS, 1, 14, inv, 8, "&cCancel", null);
		Main.createDisplay(Material.GLASS, 1, 0, inv, 7, "&cClear", null);

		p.openInventory(inv);
	}

	@EventHandler
	public void onChestplateClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Zombie Chestplate"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!curItem.hasItemMeta()) {
			chestplate.put(p.getName(), curItem.getType());
			openZombie(p);
		} else {
			if (curItem.getType() == Material.STAINED_GLASS) {
				openZombie(p);
			} else {
				chestplate.remove(p.getName());
				openZombie(p);
			}
		}
	}

	private void openLegging(Player p) {
		Inventory inv = Bukkit
				.createInventory(null, 9, "§8§oZombie Leggings");

		Main.createDisplay(Material.LEATHER_LEGGINGS, 1, 0, inv, 0, null, null);
		Main.createDisplay(Material.CHAINMAIL_LEGGINGS, 1, 0, inv, 1, null, null);
		Main.createDisplay(Material.IRON_LEGGINGS, 1, 0, inv, 2, null, null);
		Main.createDisplay(Material.GOLD_LEGGINGS, 1, 0, inv, 3, null, null);
		Main.createDisplay(Material.DIAMOND_LEGGINGS, 1, 0, inv, 4, null, null);

		Main.createDisplay(Material.STAINED_GLASS, 1, 14, inv, 8, "&cCancel", null);
		Main.createDisplay(Material.GLASS, 1, 0, inv, 7, "&cClear", null);

		p.openInventory(inv);

	}

	@EventHandler
	public void onLeggingClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		if (!ChatColor.stripColor(event.getInventory().getName()) .equalsIgnoreCase("Zombie Leggings"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!curItem.hasItemMeta()) {
			leggings.put(p.getName(), curItem.getType());
			openZombie(p);
		} else {
			if (curItem.getType() == Material.STAINED_GLASS) {
				openZombie(p);
			} else {
				leggings.remove(p.getName());
				openZombie(p);
			}
		}
	}

	private void openBoots(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "§8§oZombie Boots");

		Main.createDisplay(Material.LEATHER_BOOTS, 1, 0, inv, 0, null, null);
		Main.createDisplay(Material.CHAINMAIL_BOOTS, 1, 0, inv, 1, null, null);
		Main.createDisplay(Material.IRON_BOOTS, 1, 0, inv, 2, null, null);
		Main.createDisplay(Material.GOLD_BOOTS, 1, 0, inv, 3, null, null);
		Main.createDisplay(Material.DIAMOND_BOOTS, 1, 0, inv, 4, null, null);

		Main.createDisplay(Material.STAINED_GLASS, 1, 14, inv, 8, "&cCancel", null);
		Main.createDisplay(Material.GLASS, 1, 0, inv, 7, "&cClear", null);

		p.openInventory(inv);
	}

	@EventHandler
	public void onBootsClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		if (!ChatColor.stripColor(event.getInventory().getName()) .equalsIgnoreCase("Zombie Boots"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!curItem.hasItemMeta()) {
			boots.put(p.getName(), curItem.getType());
			openZombie(p);
		} else {
			if (curItem.getType() == Material.STAINED_GLASS) {
				openZombie(p);
			} else {
				boots.remove(p.getName());
				openZombie(p);
			}
		}
	}

	private void openHeld(Player p) {
		Inventory inv = Bukkit.createInventory(null, 18,
				"§8§oZombie Held Item");

		Main.createDisplay(Material.WOOD_SWORD, 1, 0, inv, 0, null, null);
		Main.createDisplay(Material.STONE_SWORD, 1, 0, inv, 1, null, null);
		Main.createDisplay(Material.IRON_SWORD, 1, 0, inv, 2, null, null);
		Main.createDisplay(Material.GOLD_SWORD, 1, 0, inv, 3, null, null);
		Main.createDisplay(Material.DIAMOND_SWORD, 1, 0, inv, 4, null, null);
		Main.createDisplay(Material.BOW, 1, 0, inv, 5, null, null);

		Main.createDisplay(Material.DIAMOND, 1, 0, inv, 9, null, null);
		Main.createDisplay(Material.COAL, 1, 0, inv, 10, null, null);
		Main.createDisplay(Material.SLIME_BALL, 1, 0, inv, 11, null, null);
		Main.createDisplay(Material.COOKIE, 1, 0, inv, 12, null, null);
		Main.createDisplay(Material.FISHING_ROD, 1, 0, inv, 13, null, null);
		Main.createDisplay(Material.CAKE, 1, 0, inv, 14, null, null);

		Main.createDisplay(Material.STAINED_GLASS, 1, 14, inv, 8, "&cCancel", null);
		Main.createDisplay(Material.GLASS, 1, 0, inv, 17, "&cClear", null);

		p.openInventory(inv);
	}

	@EventHandler
	public void onHeldClick(InventoryClickEvent event) {
		ItemStack curItem = event.getCurrentItem();
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Zombie Held Item"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (!curItem.hasItemMeta()) {
			held.put(p.getName(), curItem.getType());
			openZombie(p);
		} else {
			if (curItem.getType() == Material.STAINED_GLASS) {
				openZombie(p);
			} else {
				held.remove(p.getName());
				openZombie(p);
			}
		}
	}

}
