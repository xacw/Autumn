package dev.xac.autumn.commands.event.user;

import dev.xac.autumn.event.game.EventGame;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = { "event clearcooldown", "event clearcd" }, permission = "praxi.admin.event")
public class EventClearCooldownCommand {

	public void execute(CommandSender sender) {
		EventGame.setCooldown(new Cooldown(0));
		sender.sendMessage(ChatColor.GREEN + "You cleared the event cooldown.");
	}

}
