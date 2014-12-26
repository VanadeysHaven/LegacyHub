package me.LegacyDev.Hub;

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

public class Preferences implements Listener {

	public static ArrayList<String> playerOff = new ArrayList<String>();
	public static ArrayList<String> doubleJumpOff = new ArrayList<String>();
	public static ArrayList<String> punchOff = new ArrayList<String>();
	
	public int clockItem = 3;
	public int punchItem = 4;
	public int doubleItem = 5;
	public int clockSwitch = clockItem + 9;
	public int punchSwitch = punchItem + 9;
	public int doubleSwitch = doubleItem + 9;

	public static ItemStack clock(){
		ItemStack clock = new ItemStack(Material.WATCH);
		ItemMeta clockMeta = clock.getItemMeta();
		ArrayList<String> clockLore = new ArrayList<String>();
		clockLore.add("§7§oToggles if you can see other players.");
		clockLore.add("§7§oStaff members can't be hidden.");
		clockMeta.setLore(clockLore);
		clockMeta.setDisplayName("§bPlayer visibility");
		clock.setItemMeta(clockMeta);
		return clock;
	}

	public static ItemStack feather(){
		ItemStack feather = new ItemStack(Material.FEATHER);
		ItemMeta featherMeta = feather.getItemMeta();
		ArrayList<String> featherLore = new ArrayList<String>();
		featherLore.add("§7§oToggles if you can double jump.");
		featherMeta.setLore(featherLore);
		featherMeta.setDisplayName("§bDouble jump");
		feather.setItemMeta(featherMeta);
		return feather;
	}

	public static ItemStack clockOn(){
		ItemStack clockOn = new ItemStack(Material.INK_SACK,1 ,(byte)10);
		ItemMeta clockOnMeta = clockOn.getItemMeta();
		clockOnMeta.setDisplayName("§bPlayer visibility §8» §aON");
		ArrayList<String> clockOnLore = new ArrayList<String>();
		clockOnLore.add("§7§oClick to toggle.");
		clockOnMeta.setLore(clockOnLore);
		clockOn.setItemMeta(clockOnMeta);
		return clockOn;
	}

	public static ItemStack clockOff(){
		ItemStack clockOff = new ItemStack(Material.INK_SACK,1 ,(byte)8);
		ItemMeta clockOffMeta = clockOff.getItemMeta();
		clockOffMeta.setDisplayName("§bPlayer visibility §8» §cOFF");
		ArrayList<String> clockOffLore = new ArrayList<String>();
		clockOffLore.add("§7§oClick to toggle.");
		clockOffMeta.setLore(clockOffLore);
		clockOff.setItemMeta(clockOffMeta);
		return clockOff;
	}

	public static ItemStack doubleOn(){
		ItemStack doubleOn = new ItemStack(Material.INK_SACK,1 ,(byte)10);
		ItemMeta doubleOnMeta = doubleOn.getItemMeta();
		doubleOnMeta.setDisplayName("§bDouble jump §8» §aON");
		ArrayList<String> doubleOnLore = new ArrayList<String>();
		doubleOnLore.add("§7§oClick to toggle.");
		doubleOnMeta.setLore(doubleOnLore);
		doubleOn.setItemMeta(doubleOnMeta);
		return doubleOn;
	}

	public static ItemStack doubleOff(){
		ItemStack doubleOff = new ItemStack(Material.INK_SACK,1 ,(byte)8);
		ItemMeta doubleOffMeta = doubleOff.getItemMeta();
		doubleOffMeta.setDisplayName("§bDouble jump §8» §cOFF");
		ArrayList<String> doubleOffLore = new ArrayList<String>();
		doubleOffLore.add("§7§oClick to toggle.");
		doubleOffMeta.setLore(doubleOffLore);
		doubleOff.setItemMeta(doubleOffMeta);
		return doubleOff;
	}
	
	public static ItemStack punchOn(){
		ItemStack punchOn = new ItemStack(Material.INK_SACK,1 ,(byte)10);
		ItemMeta punchOnMeta = punchOn.getItemMeta();
		punchOnMeta.setDisplayName("§bPunching §8» §aON");
		ArrayList<String> punchOnLore = new ArrayList<String>();
		punchOnLore.add("§7§oClick to toggle.");
		punchOnMeta.setLore(punchOnLore);
		punchOn.setItemMeta(punchOnMeta);
		return punchOn;
	}

	public static ItemStack punchOff(){
		ItemStack punchOff = new ItemStack(Material.INK_SACK,1 ,(byte)8);
		ItemMeta punchOffMeta = punchOff.getItemMeta();
		punchOffMeta.setDisplayName("§bPunching §8» §cOFF");
		ArrayList<String> punchOffLore = new ArrayList<String>();
		punchOffLore.add("§7§oClick to toggle.");
		punchOffMeta.setLore(punchOffLore);
		punchOff.setItemMeta(punchOffMeta);
		return punchOff;
	}
	
	public static ItemStack punchNoStaff(){
		ItemStack punchNoStaff = new ItemStack(Material.IRON_FENCE,1);
		ItemMeta punchNoStaffMeta = punchNoStaff.getItemMeta();
		punchNoStaffMeta.setDisplayName("§bPunching §8» §4§oNot Available");
		ArrayList<String> punchNoStaffLore = new ArrayList<String>();
		punchNoStaffLore.add("§7§oThis is only avaiable for staff members");
		punchNoStaffMeta.setLore(punchNoStaffLore);
		punchNoStaff.setItemMeta(punchNoStaffMeta);
		return punchNoStaff;
	}
	
	public static ItemStack punch(){
		ItemStack punch = new ItemStack(Material.TNT);
		ItemMeta punchMeta = punch.getItemMeta();
		ArrayList<String> punchLore = new ArrayList<String>();
		punchLore.add("§7§oToggles if you can be punched");
		punchMeta.setLore(punchLore);
		punchMeta.setDisplayName("§bPunching");
		punch.setItemMeta(punchMeta);
		return punch;
	}

	@EventHandler
	public void onItemInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getItemInHand().getType() == Material.REDSTONE_COMPARATOR){
				if(p.getItemInHand().hasItemMeta()){
					event.setCancelled(true);
					openPrefs(p);
				}

			}
		}
	}

	public void openPrefs(Player p){
		Inventory prefs = Bukkit.createInventory(null, 18, "§8§oYour preferences");

		prefs.setItem(clockItem, clock());
		prefs.setItem(punchItem, punch());
		prefs.setItem(doubleItem, feather());

		if(playerOff.contains(p.getName())){
			prefs.setItem(clockSwitch, clockOff());
		} else {
			prefs.setItem(clockSwitch, clockOn());
		}

		if(doubleJumpOff.contains(p.getName())){
			prefs.setItem(doubleSwitch, doubleOff());
		} else {
			prefs.setItem(doubleSwitch, doubleOn());
		}
		
		if(p.hasPermission("lchub.punch.canbe")){
			if(punchOff.contains(p.getName())){
				prefs.setItem(punchSwitch, punchOff());
			} else {
				prefs.setItem(punchSwitch, punchOn());
			}
		} else {
			prefs.setItem(punchSwitch, punchNoStaff());
		}

		p.openInventory(prefs);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){

		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Your preferences"))
			return;

		Player p = (Player) event.getWhoClicked();
		ItemStack clickedItem = event.getCurrentItem();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		if(clickedItem.getType() == Material.INK_SACK){
			if(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).contains("visibility")){
				if(clickedItem.getItemMeta().getDisplayName().contains("OFF")){
					event.getInventory().setItem(clockSwitch, clockOn());
					p.getInventory().setItem(1, SetInventory.playersOn());
					playerOff.remove(p.getName());
					PlayerVisibilty.showPlayers(p);
				} else if(clickedItem.getItemMeta().getDisplayName().contains("ON")) {
					event.getInventory().setItem(clockSwitch, clockOff());
					p.getInventory().setItem(1, SetInventory.playersOff());
					playerOff.add(p.getName());
					PlayerVisibilty.hidePlayers(p);
				}
			} else if(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).contains("Double jump")){
				if(clickedItem.getItemMeta().getDisplayName().contains("OFF")){
					event.getInventory().setItem(doubleSwitch, doubleOn());
					p.setAllowFlight(true);
					p.setExp(1);
					doubleJumpOff.remove(p.getName());
				} else if(clickedItem.getItemMeta().getDisplayName().contains("ON")) {
					event.getInventory().setItem(doubleSwitch, doubleOff());
					p.setAllowFlight(false);
					p.setExp(0);
					doubleJumpOff.add(p.getName());
				}
			} else if(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).contains("Punching")){
				if(clickedItem.getItemMeta().getDisplayName().contains("OFF")){
					punchOff.remove(p.getName());
					event.getInventory().setItem(punchSwitch, punchOn());
				} else if(clickedItem.getItemMeta().getDisplayName().contains("ON")) {
					punchOff.add(p.getName());
					event.getInventory().setItem(punchSwitch, punchOff());
				}
			}
		}
	}
}