package dev.xac.autumn.commands.event.admin;

import dev.xac.autumn.util.command.command.CommandMeta;
import org.bukkit.entity.Player;

@CommandMeta(label = "events", permission = "praxi.event.host")
public class EventsCommand {

	public void execute(Player player) {
		player.sendMessage("WIP");
	}

}
