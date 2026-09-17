package dev.xac.autumn.commands.user.party;

import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "p info", "party info" })
public class PartyInfoCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getParty() == null) {
			player.sendMessage(CC.RED + "You do not have a party.");
			return;
		}

		profile.getParty().sendInformation(player);
	}

}
