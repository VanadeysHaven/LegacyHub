package me.LegacyDev.Hub;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

	@SuppressWarnings("deprecation")
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		
		if (!p.hasPermission("lchub.admin")){
			SetInventory.setInventory(p);
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setSaturation(8F);
			p.setLevel(0);
			p.setExp(0F);
			event.setJoinMessage(null);
		} else {
			event.setJoinMessage(p.getName() + " §ejoined the hub.");
			//TODO Placeholder message...
		}
//		p.teleport(Main.spawn.getLocation());

	}

	public void onPlayerLeave(PlayerQuitEvent event){
		Player p = event.getPlayer();
	
		if(!p.hasPermission("lchub.admin")){
			event.setQuitMessage(null);
		} else {
			event.setQuitMessage(p.getName() + " §eleft the hub.");
			//TODO Placeholder message...
		}
		
	}
}
