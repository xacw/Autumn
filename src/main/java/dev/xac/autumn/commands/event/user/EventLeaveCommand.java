package dev.xac.autumn.commands.event.user;

import dev.xac.autumn.event.game.EventGame;
import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.profile.ProfileState;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "event leave")
public class EventLeaveCommand {

	public void execute(Player player) {
		Profile profile = Profile.getByUuid(player.getUniqueId());

		if (profile.getState() == ProfileState.EVENT) {
			EventGame.getActiveGame().getGameLogic().onLeave(player);
		} else {
			player.sendMessage(CC.RED + "You are not in an event.");
		}
	}

}
