package me.LegacyDev.Hub;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class StaffPunch implements Listener {

	private Map<String, Long> lastPuncher = new HashMap<String, Long>();
	private Map<String, Long> lastPunched = new HashMap<String, Long>();
	private final int cdPunch = 30;

	@EventHandler
	public void playerDamagePlayer(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player && event.getEntity() instanceof Player){
			Player puncher = (Player) event.getDamager();
			Player punched = (Player) event.getEntity();
			event.setCancelled(true);

			if(puncher.hasPermission("lchub.punch.can")){
				if(punched.hasPermission("lchub.punch.canbe")){
					if(!Preferences.punchOff.contains(punched.getName())){
						boolean allowedPuncher = cooldownPuncher(puncher);
						if(allowedPuncher == true){
							boolean allowedPunched = cooldownPunched(punched, puncher);
							if(allowedPunched == true){
								if(!Preferences.punchOff.contains(punched.getName())){
									lastPuncher.put(puncher.getName(), System.currentTimeMillis());
									lastPunched.put(punched.getName(), System.currentTimeMillis());
									punched.setVelocity(new Vector(0,3,0));
									Bukkit.getWorld("world").playSound(punched.getLocation(), Sound.EXPLODE, 100, 1);
									ParticleEffect.EXPLOSION_HUGE.display(3, 3, 3, 1, 10, puncher.getLocation(), 20);
									Bukkit.broadcastMessage("§a" + puncher.getName() + " §3punched §a" + punched.getName() + " §3into the sky!");
								}
							}
						}
					} else {
						punched.sendMessage("§cThis staff member has punching turned off.");
					}
				}
			}
		}
	}

	public boolean cooldownPuncher(Player p){

		long lastPunch = 0;
		if(lastPuncher.containsKey(p.getName())){
			lastPunch = lastPuncher.get(p.getName());
		}
		int cdmillis = cdPunch * 1000;
		if(System.currentTimeMillis() - lastPunch >= cdmillis){	
			return true;
		} else {
			int timeLeft = (int) (cdPunch - ((System.currentTimeMillis() - lastPunch) / 1000));
			if(timeLeft != 1){
				p.sendMessage("§cStaff members do have feelings, please let them be alone for another §a" + timeLeft + " §cseconds.");
			} else {
				p.sendMessage("§cStaff members do have feelings, please let them be alone for §a" + timeLeft + " §cmore second.");
			}
			return false;
		}
	}

	public boolean cooldownPunched(Player punched, Player puncher){

		long lastPunch = 0;
		if(lastPunched.containsKey(punched.getName())){
			lastPunch = lastPunched.get(punched.getName());
		}
		int cdmillis = cdPunch * 1000;
		if(System.currentTimeMillis() - lastPunch >= cdmillis){	
			return true;
		} else {
			int timeLeft = (int) (cdPunch - ((System.currentTimeMillis() - lastPunch) / 1000));
			if(timeLeft != 1){
				puncher.sendMessage("§cThis staff member still has some pain from the last punch, you can hurt this staff again in §a" + timeLeft + " §cseconds. >=D");
			} else {
				puncher.sendMessage("§cThis staff member still has some pain from the last punch, you can hurt this staff again in §a" + timeLeft + " §csecond. >=D");
			}
			return false;
		}
	}


}
