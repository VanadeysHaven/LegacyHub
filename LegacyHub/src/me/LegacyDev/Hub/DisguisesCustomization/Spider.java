package me.LegacyDev.Hub.DisguisesCustomization;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.Menus.DisguiseMenu;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Spider implements Listener {

	public static void openSpider(Player p){
		Inventory inv = Bukkit.createInventory(null, 18, "§8§oSpider Customization");

		Main.createDisplay(Material.MONSTER_EGG, 1, 52, inv, 4, "&aSelected disguise &8» &bSpider", null);

		for(int i=9; i<18; i++){
			Main.createDisplay(Material.STAINED_CLAY, 1, 13, inv, i, "&aClick to confirm", null);
		}
		
		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		ItemStack curItem = event.getCurrentItem();
		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Spider customization"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		if(curItem.getType() == Material.STAINED_CLAY){
			MobDisguise dis = new MobDisguise(DisguiseType.SPIDER);
			LivingWatcher watcher = (LivingWatcher) dis.getWatcher();
			watcher.setCustomName(p.getDisplayName());
			watcher.setCustomNameVisible(true);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(Main.disguiseMsgPart1 + "spider" + Main.disguiseMsgPart2);
			DisguiseMenu.disCooldown(p);
			p.closeInventory();
			return;
		} else {
			return;
		}
	}

}
