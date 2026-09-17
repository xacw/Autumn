package dev.xac.autumn.commands.user.match;

import dev.xac.autumn.match.MatchSnapshot;
import dev.xac.autumn.match.menu.MatchDetailsMenu;
import dev.xac.autumn.util.command.command.CommandMeta;
import java.util.UUID;

import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "viewinv")
public class ViewInventoryCommand {

	public void execute(Player player, String id) {
		MatchSnapshot cachedInventory;

		try {
			cachedInventory = MatchSnapshot.getByUuid(UUID.fromString(id));
		} catch (Exception e) {
			cachedInventory = MatchSnapshot.getByName(id);
		}

		if (cachedInventory == null) {
			player.sendMessage(CC.RED + "Couldn't find an inventory for that ID.");
			return;
		}

		new MatchDetailsMenu(cachedInventory).openMenu(player);
	}

}
