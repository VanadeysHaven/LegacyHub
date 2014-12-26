package me.LegacyDev.Hub;

import org.bukkit.GameMode;
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
	public void onFlightAttempt(PlayerToggleFlightEvent event) {

		if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {

			event.getPlayer().setVelocity(event.getPlayer().getVelocity().add(new Vector(0,0.8,0)));
			event.getPlayer().setAllowFlight(false);
			event.setCancelled(true);

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
			}
		}
	}



}
