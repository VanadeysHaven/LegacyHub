package me.LegacyDev.Hub.Menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassMenu implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		ItemStack pItemHand = event.getPlayer().getInventory().getItemInHand();
		
		if(pItemHand.getType().equals(Material.COMPASS)){
			if(pItemHand.hasItemMeta() == true){
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
					openCompass(p);
				}
			}
		}
	}

	public void openCompass(Player p) {
		Inventory compass = Bukkit.createInventory(null, 9, "§8Server Selector");
		
		ItemStack survival = new ItemStack(Material.GRASS);
		ItemMeta survivalMeta = survival.getItemMeta();
		ArrayList<String> survivalLore = new ArrayList<String>();
		survivalLore.add("§7§oPlay survival.");
		//TODO Placeholder message...
		survivalMeta.setDisplayName("§bSurvival §8» §3Click to connect.");
		survivalMeta.setLore(survivalLore);
		survival.setItemMeta(survivalMeta);
		compass.setItem(0, survival);
		
		ItemStack games = new ItemStack(Material.SLIME_BALL);
		ItemMeta gamesMeta = games.getItemMeta();
		ArrayList<String> gamesLore = new ArrayList<String>();
		gamesLore.add("§7§oPlay games.");
		//TODO Placeholder message...
		gamesMeta.setDisplayName("§bGames §8» §3Click to connect.");
		gamesMeta.setLore(gamesLore);
		games.setItemMeta(gamesMeta);
		compass.setItem(1, games);
		
		p.openInventory(compass);
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		
		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Server Selector"))
			return;
		
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		switch (event.getCurrentItem().getType()){
		case GRASS:
			p.sendMessage("TODO » Survival");
			//TODO Insert teleport here.
			p.closeInventory();
			break;
		case SLIME_BALL:
			p.sendMessage("TODO » Games");
			//TODO Insert teleport here.
			p.closeInventory();
			break;
		default:
			break;
		}

	}
}
