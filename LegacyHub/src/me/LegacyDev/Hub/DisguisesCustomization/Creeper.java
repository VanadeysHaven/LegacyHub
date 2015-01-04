package me.LegacyDev.Hub.DisguisesCustomization;

import java.util.ArrayList;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.Menus.DisguiseMenu;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.CreeperWatcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Creeper implements Listener {

	private ArrayList<String> isCharged = new ArrayList<String>();
	private ArrayList<String> isFat = new ArrayList<String>();

	public static int chargedIcon = 6;
	public static int chargedSwitch = chargedIcon + 9;
	public static int explodingIcon = 7;
	public static int explodingSwitch = explodingIcon + 9;

	public static void openCreeper(Player p){
		Inventory inv = Bukkit.createInventory(null, 36, "§8§oCreeper customization");

		Main.createDisplay(Material.MONSTER_EGG, 1, 50, inv, 0, "&aSelected disguise &8» &bCreeper", null);

		Main.createDisplay(Material.BLAZE_ROD, 1, 0, inv, chargedIcon, "&aCharged Creeper", 
				"&7&oSets if the glow of a charged creeper will be visible.");
		Main.createDisplay(Material.INK_SACK, 1, 8, inv, chargedSwitch, "&bCharged Creeper &8» &cOFF", Main.toggle);

		Main.createDisplay(Material.TNT, 1, 0, inv, explodingIcon, "&aFat Creeper", 
				"&7&oSets if your Creeper looks like if it is going to explode.");
		Main.createDisplay(Material.INK_SACK, 1, 8, inv, explodingSwitch, "&bFat Creeper &8» &cOFF", Main.toggle);
		
		for(int i=27; i<36; i++){
			Main.createDisplay(Material.STAINED_CLAY, 1, 13, inv, i, "&aClick to confirm", null);
		}

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		ItemStack curItem = event.getCurrentItem();
		Inventory inv = event.getInventory();
		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Creeper customization"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		if(event.getCurrentItem().getType() == Material.INK_SACK){
			if(curItem.getItemMeta().getDisplayName().contains("Charged")){
				if(curItem.getItemMeta().getDisplayName().contains("OFF")){
					isCharged.add(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 10, inv, chargedSwitch, "&bCharged Creeper &8» &aON", Main.toggle);
				} else if(curItem.getItemMeta().getDisplayName().contains("ON")){
					isCharged.remove(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 8, inv, chargedSwitch, "&bCharged Creeper &8» &cOFF", Main.toggle);
				}
			} else if(curItem.getItemMeta().getDisplayName().contains("Fat")){
				if(curItem.getItemMeta().getDisplayName().contains("OFF")){
					isFat.add(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 10, inv, explodingSwitch, "&bFat Creeper &8» &aON", Main.toggle);
				} else if(curItem.getItemMeta().getDisplayName().contains("ON")){
					isFat.remove(p.getName());
					Main.createDisplay(Material.INK_SACK, 1, 8, inv, explodingSwitch, "&bFat Creeper &8» &cOFF", Main.toggle);
				}
			}
		} else if(curItem.getType() == Material.STAINED_CLAY){
			MobDisguise dis = new MobDisguise(DisguiseType.CREEPER);
			CreeperWatcher watcher = (CreeperWatcher) dis.getWatcher();
			if(isCharged.contains(p.getName())){
				watcher.setPowered(true);
				isCharged.remove(p.getName());
			}
			if(isFat.contains(p.getName())){
				watcher.setIgnited(true);
				isFat.remove(p.getName());
			}
			watcher.setCustomName(p.getDisplayName());
			watcher.setCustomNameVisible(true);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(Main.disguiseMsgPart1 + "creeper" + Main.disguiseMsgPart2);
			DisguiseMenu.disCooldown(p);
			p.closeInventory();
			return;
		} else {
			return;
		}
	}

}
