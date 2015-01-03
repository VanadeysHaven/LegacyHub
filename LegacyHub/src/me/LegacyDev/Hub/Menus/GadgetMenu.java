package me.LegacyDev.Hub.Menus;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.Gadgets.PaintballGun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GadgetMenu implements Listener {

	public static void openGadget(Player p) {
		Inventory gadgets = Bukkit.createInventory(null, 54, "§8§oGadgets");

		if(p.hasPermission("lchub.cosmetic.gadget.paintball")){
			Main.createDisplay(Material.IRON_BARDING, 1, 0, gadgets, 0, "&bPaintball Gun &8» &aClick to select", "&7&oRight click to fire, shift+right click to select color.");
		} else {
			gadgets.setItem(0, Main.noPerm());
		}

		p.openInventory(gadgets);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){

		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Gadgets"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		switch (event.getCurrentItem().getType()){
		case IRON_BARDING:
			p.closeInventory();
			p.getInventory().setItem(6, Main.createItem(Material.IRON_BARDING, "&bPaintball gun &8» &3Right click to shoot" , "&7&oShift+right click to select color."));
			if(!PaintballGun.gunColor.containsKey(p.getName())){
				PaintballGun.gunColor.put(p.getName(), 11);
			}
			break;
			//		case SKULL_ITEM:
			//			break;
			//		case PISTON_BASE:
			//			GadgetMenu.openGadget(p);
			//			break;
			//		case IRON_CHESTPLATE:
			//			break;
		default:
			break;
		}

	}
}
