package me.LegacyDev.Hub;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerVisibilty implements Listener {

	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	private final int cdtime = 3;

	@EventHandler
	public void onItemInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		ItemStack pitemhand = p.getItemInHand();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(pitemhand.getItemMeta().getDisplayName().contains("ON")){

				boolean allowed = cooldown(p);
				if(allowed == true){

					Preferences.playerOff.add(p.getName());
					lastUsage.put(p.getName(), System.currentTimeMillis());
					p.getInventory().setItem(1, SetInventory.playersOff());

					hidePlayers(p);

				}

			} else if (pitemhand.getItemMeta().getDisplayName().contains("OFF")){

				boolean allowed = cooldown(p);
				if(allowed == true){
					Preferences.playerOff.remove(p.getName());
					lastUsage.put(p.getName(), System.currentTimeMillis());
					p.getInventory().setItem(1, SetInventory.playersOn());

					showPlayers(p);

				}
			}

		} 
	}

	public boolean cooldown(Player p){

		long lastUsed = 0;
		if(lastUsage.containsKey(p.getName())){
			lastUsed = lastUsage.get(p.getName());
		}
		int cdmillis = cdtime * 1000;
		if(System.currentTimeMillis() - lastUsed >= cdmillis){	
			return true;
		} else {
			int timeLeft = (int) (cdtime - ((System.currentTimeMillis() - lastUsed) / 1000));
			if(timeLeft != 1){
				p.sendMessage("§cNot so fast my friend! Please wait for another §a" + timeLeft + " §cseconds.");
			} else {
				p.sendMessage("§cNot so fast my friend! Please wait for §a" + timeLeft + " §cmore second.");
			}
			return false;
		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent event){
		Player pjoin = event.getPlayer();

		for(Player pl : Bukkit.getServer().getOnlinePlayers()){
			if(pl != pjoin){
				if (Preferences.playerOff.contains(pl.getName())){
					if(!pjoin.hasPermission("lchub.hideexempt")){
						pl.hidePlayer(pjoin);
					}
				} else {
					pl.showPlayer(pjoin);
				}
			} else {
				if(Preferences.playerOff.contains(pjoin)){
					if(!pjoin.hasPermission("lchub.admin")){
						pjoin.getInventory().setItem(1, SetInventory.playersOff());
					}
				}
			}
		}


	}

	@SuppressWarnings("deprecation")
	public static void hidePlayers(Player p){
		for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			if(pl != p){
				if(!pl.hasPermission("lchub.hideexempt")){
					p.getPlayer().hidePlayer(pl);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void showPlayers(Player p){
		for (Player pl : Bukkit.getServer().getOnlinePlayers()){
			if(pl != p){
				if(!pl.hasPermission("lchub.hideexempt")){
					p.getPlayer().showPlayer(pl);
				}
			}
		}
	}

}
