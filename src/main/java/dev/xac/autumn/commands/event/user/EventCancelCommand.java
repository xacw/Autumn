package dev.xac.autumn.commands.event.user;

import dev.xac.autumn.event.game.EventGame;
import dev.xac.autumn.util.command.command.CommandMeta;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@CommandMeta(label = "event cancel", permission = "praxi.admin.event")
public class EventCancelCommand {

	public void execute(CommandSender sender) {
		if (EventGame.getActiveGame() != null) {
			EventGame.getActiveGame().getGameLogic().cancelEvent();
		} else {
			sender.sendMessage(ChatColor.RED + "There is no active event.");
		}
	}

}
