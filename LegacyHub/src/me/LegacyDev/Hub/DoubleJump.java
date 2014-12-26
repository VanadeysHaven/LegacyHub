package me.LegacyDev.Hub;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {

	@EventHandler
	public void onFlightAttempt(PlayerToggleFlightEvent event){
		Player p = event.getPlayer();

		if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {

			Vector jump = event.getPlayer().getLocation().getDirection().multiply(0.4).setY(1.1);
			p.setVelocity(event.getPlayer().getVelocity().add(jump));
			p.setAllowFlight(false);
			event.setCancelled(true);
			p.setFallDistance(3000);
			p.setExp(0);
			p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 100, 1);
			
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void dmg(final EntityDamageEvent event) {
		Entity e = event.getEntity();
		if(e instanceof Player) {
			if(event.getCause() == DamageCause.FALL){
				Player p = (Player)e;
				event.setCancelled(true);
				p.setAllowFlight(true);
				p.setExp(1);
			}
		}
	}



}
