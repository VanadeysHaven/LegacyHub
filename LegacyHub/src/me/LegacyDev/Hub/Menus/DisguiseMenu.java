package me.LegacyDev.Hub.Menus;

import java.util.HashMap;
import java.util.Map;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.DisguisesCustomization.Creeper;
import me.LegacyDev.Hub.DisguisesCustomization.Skeleton;
import me.LegacyDev.Hub.DisguisesCustomization.Spider;
import me.LegacyDev.Hub.DisguisesCustomization.Zombie;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DisguiseMenu implements Listener {

	public static Map<Integer, String> mobList = new HashMap<Integer, String>();

	public static void openDisguise(Player p) {
		Inventory disguises = Bukkit.createInventory(null, 27, "§8§oDisguises");
		int slot = 0;
		if(mobList.isEmpty()){
			mobList.put(50, "Creeper");
			mobList.put(51, "Skeleton");
			mobList.put(52, "Spider");
			mobList.put(54, "Zombie");
			mobList.put(55, "Slime");
			mobList.put(56, "Ghast");
			mobList.put(57, "Zombie Pigman");
			mobList.put(58, "Enderman");
			mobList.put(59, "Cave Spider");
			mobList.put(60, "Silverfish");
			mobList.put(62, "Magma Cube");
			mobList.put(65, "Bat");
			mobList.put(66, "Witch");
			mobList.put(90, "Pig");
			mobList.put(91, "Sheep");
			mobList.put(92, "Cow");
			mobList.put(93, "Chicken");
			mobList.put(94, "Squid");
			mobList.put(95, "Wolf");
			mobList.put(96, "Mooshroom");
			mobList.put(98, "Ocelot");
			mobList.put(100, "Horse");
			mobList.put(120, "Villager");
		}


		for(int i=50; i<121; i++){

			if(mobList.containsKey(i)){
				if(p.hasPermission("lchub.cosmetic.disguise." + i)){
					if(slot >= 18){
						Main.createDisplay(Material.MONSTER_EGG, 1, i, disguises, slot + 2, "§b" + mobList.get(i), null);
					} else {
						Main.createDisplay(Material.MONSTER_EGG, 1, i, disguises, slot, "§b" + mobList.get(i), null);
					}
				} else {
					if(slot >= 18){
						disguises.setItem(slot + 2, Main.noPerm());
					} else { 
						disguises.setItem(slot, Main.noPerm());
					}
				}

				slot = slot + 1;
			}

		}

		p.openInventory(disguises);

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		ItemStack curItem = event.getCurrentItem();
		int curItemID = event.getCurrentItem().getDurability();

		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Disguises"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(!event.getCurrentItem().hasItemMeta() || curItem.getType() == Material.STAINED_GLASS_PANE){
			return;
		}

		String disguiseMsg = Main.disguiseMsgPart1 + mobList.get(curItemID) + Main.disguiseMsgPart2;
		//TODO Placeholder Message...
		
		if(curItemID == 50){
			Creeper.openCreeper(p);
			return;
		} else if (curItemID == 51) {
			Skeleton.openSkeleton(p);
			return;		
		} else if (curItemID == 52) {
			Spider.openSpider(p);
			return;
		} else if (curItemID == 54) {
			Zombie.openZombie(p);
			return;		
		} else if (curItemID == 55) {
			MobDisguise dis = new MobDisguise(DisguiseType.SLIME);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization			
		} else if (curItemID == 56) {
			MobDisguise dis = new MobDisguise(DisguiseType.GHAST);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 57) {
			MobDisguise dis = new MobDisguise(DisguiseType.PIG_ZOMBIE);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization			
		} else if (curItemID == 58) {
			MobDisguise dis = new MobDisguise(DisguiseType.ENDERMAN);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			//TODO Add customization
			return;		
		} else if (curItemID == 59) {
			MobDisguise dis = new MobDisguise(DisguiseType.CAVE_SPIDER);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;		
		} else if (curItemID == 60) {
			MobDisguise dis = new MobDisguise(DisguiseType.SILVERFISH);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 62) {
			MobDisguise dis = new MobDisguise(DisguiseType.MAGMA_CUBE);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 65) {
			MobDisguise dis = new MobDisguise(DisguiseType.BAT);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 66) {
			MobDisguise dis = new MobDisguise(DisguiseType.WITCH);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 90) {
			MobDisguise dis = new MobDisguise(DisguiseType.PIG);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 91) {
			MobDisguise dis = new MobDisguise(DisguiseType.SHEEP);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 92) {
			MobDisguise dis = new MobDisguise(DisguiseType.COW);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 93) {
			MobDisguise dis = new MobDisguise(DisguiseType.CHICKEN);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 94) {
			MobDisguise dis = new MobDisguise(DisguiseType.SQUID);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 95) {
			MobDisguise dis = new MobDisguise(DisguiseType.WOLF);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 96) {
			MobDisguise dis = new MobDisguise(DisguiseType.MUSHROOM_COW);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
		} else if (curItemID == 98) {
			MobDisguise dis = new MobDisguise(DisguiseType.OCELOT);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 100) {
			MobDisguise dis = new MobDisguise(DisguiseType.HORSE);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else if (curItemID == 120) {
			MobDisguise dis = new MobDisguise(DisguiseType.VILLAGER);
			DisguiseAPI.disguiseToAll(p, dis);
			p.sendMessage(disguiseMsg);
			disCooldown(p);
			p.closeInventory();
			return;
			//TODO Add customization
		} else {
			return;
		}

	}

	public static void disCooldown(Player p) {
		// TODO Create cooldown
		
	}

}




