package dev.xac.autumn.commands.user.party;

import dev.xac.autumn.party.PartyPrivacy;
import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "p close", "party close", "p lock", "party lock" })
public class PartyCloseCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getParty() == null) {
			player.sendMessage(CC.RED + "You do not have a party.");
			return;
		}

		if (!profile.getParty().getLeader().equals(player)) {
			player.sendMessage(CC.RED + "You are not the leader of your party.");
			return;
		}

		profile.getParty().setPrivacy(PartyPrivacy.CLOSED);
	}

}
