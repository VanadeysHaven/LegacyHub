package me.LegacyDev.Hub.DisguisesCustomization;

import me.LegacyDev.Hub.Main;
import me.LegacyDev.Hub.Menus.DisguiseMenu;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has been created on 6-1-2015 at 19:10 by cooltimmetje.
 */
public class Slime implements Listener {

    public static int egg = 0;
    public static int sizeItem = 8;
    public static int sizeSwitch = sizeItem + 9;

    public static Map<Integer, Material> sizes = new HashMap<Integer, Material>();
    public static Map<String, Integer> sizePlayer = new HashMap<String, Integer>();


    public static void openSlime(Player p) {
        if(sizes.isEmpty()){
            sizes.put(1, Material.IRON_INGOT);
            sizes.put(2, Material.GOLD_INGOT);
            sizes.put(3, Material.DIAMOND);
        }

        Inventory inv = Bukkit.createInventory(null, 36, "&8&oSlime Customization".replace('&', '§'));

        Main.createDisplay(Material.MONSTER_EGG, 1, 0 , inv, egg, "&aSelected disguise &8» &bSlime", null);

        Main.createDisplay(Material.SLIME_BALL, 1, 0, inv, sizeItem, "&aSlime size",  "&7&oSets the size of the slime.");
        if(!sizePlayer.containsKey(p.getName())) {
            sizePlayer.put(p.getName(), 1);
        }
        Main.createDisplay(sizes.get(sizePlayer.get(p.getName())), 1, 0, inv, sizeSwitch, "&bSlime size &8» &b" + sizePlayer.get(p.getName()), Main.select);

        for(int i=27; i<36; i++){
            Main.createDisplay(Material.STAINED_CLAY, 1, 13, inv, i, "&aClick to confirm", null);
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack curItem = event.getCurrentItem();
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Slime customization"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(!event.getCurrentItem().hasItemMeta()){
            return;
        }

        if(event.getSlot() == sizeSwitch) {
            openSize(p);
        } else if(curItem.getType() == Material.STAINED_CLAY){
            activateDisguise(p, sizePlayer.get(p.getName()));
        }
    }

    private void activateDisguise(Player p, Integer size) {
        MobDisguise dis = new MobDisguise(DisguiseType.SLIME);
        SlimeWatcher watcher = (SlimeWatcher) dis.getWatcher();
        watcher.setSize(size);
        watcher.setCustomName(p.getDisplayName());
        watcher.setCustomNameVisible(true);
        DisguiseAPI.disguiseToAll(p, dis);
        p.sendMessage(Main.disguiseMsgPart1 + "slime" + Main.disguiseMsgPart2);
        DisguiseMenu.disCooldown(p);
        p.closeInventory();
    }

    private void openSize(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "&8&oSlime size".replace('&', '§'));

        for (int i = 1; i < 4; i++) {
            int slot = i + 2;
            Main.createDisplay(sizes.get(i), 1, 0, inv, slot, null, null);
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onSizeClick(InventoryClickEvent event){
        ItemStack curItem = event.getCurrentItem();
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Slime size"))
            return;

        Player p = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(event.getCurrentItem().hasItemMeta()){
            return;
        } else if(event.getCurrentItem().getType() == Material.IRON_INGOT) {
            sizePlayer.put(p.getName(), 1);
        } else if(curItem.getType() == Material.GOLD_INGOT){
            sizePlayer.put(p.getName(), 2);
        } else if(curItem.getType() == Material.DIAMOND){
            sizePlayer.put(p.getName(), 3);
        }
        openSlime(p);
    }

}
