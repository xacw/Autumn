package dev.xac.autumn.commands.user.match;

import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.profile.ProfileState;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "stopspectating")
public class StopSpectatingCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getState() == ProfileState.FIGHTING && profile.getMatch().getGamePlayer(player).isDead()) {
			profile.getMatch().getGamePlayer(player).setDisconnected(true);
			profile.setState(ProfileState.LOBBY);
			profile.setMatch(null);
		} else if (profile.getState() == ProfileState.SPECTATING) {
			profile.getMatch().removeSpectator(player);
		} else {
			player.sendMessage(CC.RED + "You are not spectating a match.");
		}
	}

}
