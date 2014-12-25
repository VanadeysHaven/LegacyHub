package me.LegacyDev.Hub;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{ //Extending JavaPlugin so that Bukkit knows its the main class...
	private static Plugin plugin;
	private long loadTime;

	public void onEnable() {
		startLoad();
		plugin = this;

		getLogger().info("[Hub] Registering Events...");
		registerEvents(this, new JoinQuitEvent(), new CompassMenu());

		//		getLogger().info("[Hub] Registering Commands...");
		//		getCommand("tp").setExecutor(new TPCommand());

		//		getLogger().info("[Hub] Hooking into APIs...");

		stopLoad();
	}

	public void onDisable() {
		plugin = null; //To stop memory leeks
		getServer().resetRecipes();
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

	static Block spawn = Bukkit.getServer().getWorld("Hub").getBlockAt(120, 100, -65);
	//TODO set spawn coords



}