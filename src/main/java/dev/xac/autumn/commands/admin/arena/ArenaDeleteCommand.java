package dev.xac.autumn.commands.admin.arena;

import dev.xac.autumn.arena.Arena;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "arena delete", permission = "praxi.admin.arena")
public class ArenaDeleteCommand {

	public void execute(Player player, Arena arena) {
		if (arena != null) {
			arena.delete();

			player.sendMessage(CC.GOLD + "Deleted arena \"" + arena.getName() + "\"");
		} else {
			player.sendMessage(CC.RED + "An arena with that name does not exist.");
		}
	}

}
