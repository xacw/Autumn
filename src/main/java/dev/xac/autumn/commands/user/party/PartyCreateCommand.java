package dev.xac.autumn.commands.user.party;

import dev.xac.autumn.Locale;
import dev.xac.autumn.party.Party;
import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.profile.ProfileState;
import dev.xac.autumn.profile.hotbar.Hotbar;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = { "p create", "party create" })
public class PartyCreateCommand {

	public void execute(Player player) {
		if (player.hasMetadata("frozen")) {
			player.sendMessage(CC.RED + "You cannot create a party while frozen.");
			return;
		}

		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getParty() != null) {
			player.sendMessage(CC.RED + "You already have a party.");
			return;
		}

		if (profile.getState() != ProfileState.LOBBY) {
			player.sendMessage(CC.RED + "You must be in the lobby to create a party.");
			return;
		}

		profile.setParty(new Party(player));

		Hotbar.giveHotbarItems(player);

		player.sendMessage(Locale.PARTY_CREATE.format());
	}

}
