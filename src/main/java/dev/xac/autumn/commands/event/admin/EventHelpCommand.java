package dev.xac.autumn.commands.event.admin;

import dev.xac.autumn.Autumn;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "event", "event help" })
public class EventHelpCommand {

	public void execute(Player player) {
		for (String line : Autumn.get().getMainConfig().getStringList("EVENT.HELP")) {
			player.sendMessage(CC.translate(line));
		}
	}

}
