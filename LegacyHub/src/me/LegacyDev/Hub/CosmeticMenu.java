package me.LegacyDev.Hub;

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

public class CosmeticMenu implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		ItemStack pItemHand = event.getPlayer().getItemInHand();
		
		if(pItemHand.getType() == Material.CHEST && pItemHand.hasItemMeta() == true){
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
				openCosmetic(p);
				event.setCancelled(true);
			}
		}
		
	}

	public void openCosmetic(Player p) {
	Inventory cosmetic = Bukkit.createInventory(null, 27, "§8§oCosmetic menu");
	
	Main.createDisplay(Material.BONE, 1, 0, cosmetic, 10, "&bPets &8» &cComing Soon", "&7&oChoose a little friend that will follow you around!");
	Main.createDisplay(Material.SKULL_ITEM, 1, 0, cosmetic, 12, "&bDisguises &8» &cComing Soon", "&7&oMutate yourself into any mob!");
	Main.createDisplay(Material.PISTON_BASE, 1, 0, cosmetic, 14, "&bGadgets &8» &aClick to open", "&7&oGet some kind of new technology, not responsible if it blows up.");
	Main.createDisplay(Material.IRON_CHESTPLATE, 1, 0, cosmetic, 16, "&bWardrobe &8» &cComing Soon", "&7&oUpgrade your swag to level 9000!");
	
	p.openInventory(cosmetic);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		
		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Cosmetic menu"))
			return;
		
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta()){
			return;
		}

		switch (event.getCurrentItem().getType()){
//		case BONE:
//			break;
//		case SKULL_ITEM:
//			break;
		case PISTON_BASE:
			GadgetMenu.openGadget(p);
			break;
//		case IRON_CHESTPLATE:
//			break;
		default:
			break;
		}

	}

}
