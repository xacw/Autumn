package dev.xac.autumn.commands.admin.kits;

import dev.xac.autumn.kit.Kit;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "kit setloadout", permission = "praxi.kit.setloadout")
public class KitSetLoadoutCommand {

	public void execute(Player player, Kit kit) {
		if (kit == null) {
			player.sendMessage(CC.RED + "A kit with that name does not exist.");
			return;
		}

		kit.getKitLoadout().setArmor(player.getInventory().getArmorContents());
		kit.getKitLoadout().setContents(player.getInventory().getContents());
		kit.save();

		player.sendMessage(CC.GREEN + "You updated the kit's loadout.");
	}

}
