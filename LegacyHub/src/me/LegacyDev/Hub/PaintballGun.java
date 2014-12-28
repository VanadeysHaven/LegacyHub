package me.LegacyDev.Hub;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

public class PaintballGun implements Listener {

	private Map<String, Long> lastUsage = new HashMap<String, Long>();
	public static Map<String, Integer> gunColor = new HashMap<String, Integer>();
	private final int cdtime = 10;

	@EventHandler
	public void onItemInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		ItemStack pItemHand = event.getPlayer().getItemInHand();

		if(pItemHand.getType() == Material.IRON_BARDING){
			if(!p.isSneaking()){
				boolean allowed = cooldown(p);
				if(allowed == true){
					Snowball sb = p.launchProjectile(Snowball.class);
					sb.setVelocity(p.getLocation().getDirection().multiply(1.8));
				} 
			} else {
				openColor(p);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSnowballHit(ProjectileHitEvent event){
		BlockIterator iterator = new BlockIterator(event.getEntity().getWorld(), event.getEntity().getLocation().toVector(), event.getEntity().getVelocity().normalize(), 0.0D, 4);
		Block hitBlock = null;
		LivingEntity shooter = event.getEntity().getShooter();

		if(event.getEntityType() == EntityType.SNOWBALL){
			if (shooter instanceof Player){
				Player p = (Player) shooter;

				while (iterator.hasNext()) {
					hitBlock = iterator.next();

					if (hitBlock.getTypeId() != 0){
						break;
					}
				}

				if(hitBlock.getType() != Material.SIGN && hitBlock.getType() != Material.STONE_PLATE && 
						!hitBlock.isLiquid() && hitBlock.getType() != Material.STAINED_CLAY) {
					hitBlock.getWorld().playEffect(hitBlock.getLocation(), Effect.STEP_SOUND, hitBlock.getTypeId());
					blockRegen(hitBlock.getTypeId(), hitBlock.getLocation(), hitBlock.getData());
					int color = gunColor.get(p.getName());
					hitBlock.setType(Material.STAINED_CLAY);
					hitBlock.setData((byte) color);
				}
			}
		}
	}


	@SuppressWarnings("deprecation")
	public void blockRegen(int mat, Location loc, byte meta){
		Bukkit.getWorld("world").getBlockAt(loc).setType(Material.STAINED_CLAY);
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable(){

			@Override
			public void run() {

				Block block = Bukkit.getWorld("world").getBlockAt(loc);
				block.setTypeId(mat);
				block.setData(meta);

			}

		}, 100L);


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
				p.sendMessage("§cGun reloading, please wait another §a" + timeLeft + " §cseconds.");
			} else {
				p.sendMessage("§cGun reloading Please wait for §a" + timeLeft + " §cmore second.");
			}
			return false;
		}

	}

	public void openColor(Player p){
		if(!gunColor.containsKey(p.getName())){
			gunColor.put(p.getName(), 11);
		}
		Inventory color = Bukkit.createInventory(null, 18, "§8§oSelect color");		
		int slot = 0;
		for(int i=0; i<16; i++){

			if(i >= 9){
				slot = i + 1;
			} else {
				slot = i;
			}

			if(gunColor.get(p.getName()) == i){
				Main.createDisplay(Material.STAINED_CLAY, 1, i, color, slot, null, "§7§oSelected");
			} else {
				if(p.hasPermission("lchub.cosmetic.gadget.paintball." + i) || i == 4 || i == 11 || i == 14){
					color.setItem(slot, new ItemStack(Material.STAINED_CLAY, 1, (byte) i));
				} else {
					color.setItem(slot, Main.noPerm());
				}

			}
			p.openInventory(color);
		}


	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){

		if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Select color"))
			return;

		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if(event.getCurrentItem().hasItemMeta()){
			return;
		}

		switch (event.getCurrentItem().getType()){
		case STAINED_CLAY:
			p.closeInventory();
			gunColor.put(p.getName(), (int) event.getCurrentItem().getDurability());
			break;
		default:
			break;
		}

	}
	
}


