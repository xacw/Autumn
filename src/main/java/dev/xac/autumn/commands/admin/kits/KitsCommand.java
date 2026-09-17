package dev.xac.autumn.commands.admin.kits;

import dev.xac.autumn.kit.Kit;
import dev.xac.autumn.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "kits", permission = "praxi.admin.kit")
public class KitsCommand {

	public void execute(CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "Kits");

		for (Kit kit : Kit.getKits()) {
			sender.sendMessage(kit.getName());
		}
	}

}
