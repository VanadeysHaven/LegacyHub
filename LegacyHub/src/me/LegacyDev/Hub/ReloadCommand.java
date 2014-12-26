package me.LegacyDev.Hub;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getLabel().equalsIgnoreCase("lchubreload")){
			if(p.hasPermission("lchub.reload")){
				p.performCommand("reloader reload LegacyHub");
			} else {
				p.sendMessage("§cYou do not have permission to do this.");
			}
		}
		return false;
	}
}
