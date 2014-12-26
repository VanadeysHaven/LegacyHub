package me.LegacyDev.Hub;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetInventory implements CommandExecutor {

	public static ItemStack compass(){
		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta compassMeta = compass.getItemMeta();
		compassMeta.setDisplayName("§bServer selector §8» §3Right Click");
		ArrayList<String> compassLore = new ArrayList<String>();
		compassLore.add("§7§oView all available servers.");
		compassMeta.setLore(compassLore);
		compass.setItemMeta(compassMeta);
		return compass;
	}

	public static ItemStack playersOn(){
		ItemStack playerOn = new ItemStack(Material.INK_SACK,1 ,(byte)10);
		ItemMeta playerOnMeta = playerOn.getItemMeta();
		playerOnMeta.setDisplayName("§bPlayer visibilty §8» §aON");
		ArrayList<String> playerOnLore = new ArrayList<String>();
		playerOnLore.add("§7§oToggle player visibilty.");
		playerOnMeta.setLore(playerOnLore);
		playerOn.setItemMeta(playerOnMeta);
		return playerOn;
	}

	public static ItemStack playersOff(){
		ItemStack playerOff = new ItemStack(Material.INK_SACK,1 ,(byte)8);
		ItemMeta playerOffMeta = playerOff.getItemMeta();
		playerOffMeta.setDisplayName("§bPlayer visibilty §8» §cOFF");
		ArrayList<String> playerOffLore = new ArrayList<String>();
		playerOffLore.add("§7§oToggle player visibilty.");
		playerOffMeta.setLore(playerOffLore);
		playerOff.setItemMeta(playerOffMeta);
		return playerOff;
	}

	public static ItemStack cosmetic(){
		ItemStack cosmetic = new ItemStack(Material.CHEST,1);
		ItemMeta cosmeticMeta = cosmetic.getItemMeta();
		cosmeticMeta.setDisplayName("§bCosmetic Menu §8» §3Right Click");
		ArrayList<String> cosmeticLore = new ArrayList<String>();
		cosmeticLore.add("§7§oView all cosmetic items.");
		cosmeticMeta.setLore(cosmeticLore);
		cosmetic.setItemMeta(cosmeticMeta);
		return cosmetic;
	}

	public static void setInventory(Player p){

		p.getInventory().clear();
		p.getInventory().setItem(0, compass());
		p.getInventory().setItem(1, playersOn());
		p.getInventory().setItem(8, cosmetic());

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getLabel().equalsIgnoreCase("resetinv")){
			if(p.hasPermission("lchub.admin")){
				setInventory(p);
				p.sendMessage("§aInv resetted");
				//TODO Placeholder message...
				if(PlayerVisibilty.playerOff.contains(p.getName())){
					p.getInventory().setItem(1, playersOff());
				}
			}
		}
		return false;
	}
	
}