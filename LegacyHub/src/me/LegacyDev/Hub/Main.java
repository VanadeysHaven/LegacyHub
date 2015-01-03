package me.LegacyDev.Hub;

import java.util.ArrayList;

import me.LegacyDev.Hub.Commands.ReloadCommand;
import me.LegacyDev.Hub.Core.DoubleJump;
import me.LegacyDev.Hub.Core.JoinQuitEvent;
import me.LegacyDev.Hub.Core.PlayerVisibilty;
import me.LegacyDev.Hub.Core.SetInventory;
import me.LegacyDev.Hub.Core.StaffPunch;
import me.LegacyDev.Hub.DisguisesCustomization.Creeper;
import me.LegacyDev.Hub.Gadgets.PaintballGun;
import me.LegacyDev.Hub.Menus.CompassMenu;
import me.LegacyDev.Hub.Menus.CosmeticMenu;
import me.LegacyDev.Hub.Menus.DisguiseMenu;
import me.LegacyDev.Hub.Menus.GadgetMenu;
import me.LegacyDev.Hub.Menus.Preferences;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{ //Extending JavaPlugin so that Bukkit knows its the main class...
	private static Plugin plugin;
	private long loadTime;

	public void onEnable() {
		startLoad();
		plugin = this;

		getLogger().info("[Hub] Registering Events...");
		registerEvents(this, new JoinQuitEvent(), new CompassMenu(), new PlayerVisibilty(), new DoubleJump(), new Preferences(),
				new StaffPunch(), new CosmeticMenu(), new GadgetMenu(), new PaintballGun(), new DisguiseMenu(), new Creeper());

		getLogger().info("[Hub] Registering Commands...");
		getCommand("resetinv").setExecutor(new SetInventory());
		getCommand("lchubreload").setExecutor(new ReloadCommand());

		getLogger().info("[Hub] Hooking into APIs...");
		if (getServer().getPluginManager().getPlugin("Reloader") != null && getServer().getPluginManager().getPlugin("Reloader").isEnabled()){
			getLogger().info("[Hub] Successfully hooked into Reloader!");
		} else {
			getLogger().warning("[Hub] Failed to hook into Reloader, disabling plugin!");
			getPluginLoader().disablePlugin(this);
		}
		if (getServer().getPluginManager().getPlugin("LibsDisguises") != null && getServer().getPluginManager().getPlugin("LibsDisguises").isEnabled()){
			getLogger().info("[Hub] Successfully hooked into LibsDisguises!");
		} else {
			getLogger().warning("[Hub] Failed to hook into LibsDisguises, disabling plugin!");
			getPluginLoader().disablePlugin(this);
		}

		stopLoad();
		Bukkit.broadcastMessage("§aHub plugin enabled!");
		//TODO Placeholder message...
	}

	public void onDisable() {
		plugin = null; //To stop memory leeks
		getServer().resetRecipes();
		Bukkit.broadcastMessage("§cHub plugin disabled!");
		//TODO Placeholder message...
	}


	//Much eaisier then registering events in 10 diffirent methods
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	//To access the plugin variable from other classes
	public static Plugin getPlugin() {
		return plugin;
	}



	//	public static String TPTag = "§8[§cTP§8] §a";

	public void startLoad(){
		loadTime = System.currentTimeMillis();
	}

	public void stopLoad(){
		loadTime = System.currentTimeMillis() - loadTime;
		getLogger().info("[Hub] Plugin enabled! (" + loadTime + "ms)");
	}

	public static void createDisplay(Material material, int amount, int data, Inventory inv, int Slot, String name, String lore) {
		ItemStack item = new ItemStack(material, amount, (byte) data);
		ItemMeta meta = item.getItemMeta();
		if(!(name == null)){
			meta.setDisplayName(name.replace('&', '§'));
		}
		if(!(lore == null)){
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add(lore.replace('&', '§'));
			meta.setLore(Lore);
		}
		item.setItemMeta(meta);

		inv.setItem(Slot, item);
	}

	public static ItemStack createItem(Material material, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name.replace('&', '§'));
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore.replace('&', '§'));
		meta.setLore(Lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack noPerm() {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 14);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§cNo Permission");
		item.setItemMeta(meta);
		return item;
	}
}