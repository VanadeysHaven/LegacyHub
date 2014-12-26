package me.LegacyDev.Hub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerVisibilty implements Listener {

	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	private final int cdtime = 3;
	private ArrayList<String> playerOff = new ArrayList<String>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		ItemStack pitemhand = p.getItemInHand();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(pitemhand.getItemMeta().getDisplayName().contains("ON")){

				boolean allowed = cooldown(p);
				if(allowed == true){

					playerOff.add(p.getName());
					lastUsage.put(p.getName(), System.currentTimeMillis());
					p.getInventory().setItem(1, SetInventory.playersOff());

					for (Player pl : Bukkit.getServer().getOnlinePlayers()){
						if(pl != p){
							p.getPlayer().hidePlayer(pl);
						}
					}

				}

			} else if (pitemhand.getItemMeta().getDisplayName().contains("OFF")){

				boolean allowed = cooldown(p);
				if(allowed == true){
					playerOff.remove(p.getName());
					lastUsage.put(p.getName(), System.currentTimeMillis());
					p.getInventory().setItem(1, SetInventory.playersOn());

					for (Player pl : Bukkit.getServer().getOnlinePlayers()){
						if(pl != p){
							p.getPlayer().showPlayer(pl);
						}
					}

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
				p.sendMessage("&cNot so fast my friend! Please wait for another &a" + timeLeft + " &cseconds.");
			} else {
				p.sendMessage("&cNot so fast my friend! Please wait for &a" + timeLeft + " &cmore second.");
			}
			return false;
		}

	}

}
