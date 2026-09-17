package dev.xac.autumn.commands.event.user;

import dev.xac.autumn.event.game.EventGame;
import dev.xac.autumn.event.impl.sumo.SumoEvent;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "event info")
public class EventInfoCommand {

	public void execute(Player player) {
		if (EventGame.getActiveGame() == null) {
			player.sendMessage(CC.RED + "There is no active event.");
			return;
		}

		EventGame game = EventGame.getActiveGame();

		player.sendMessage(CC.GOLD + CC.BOLD + "Event Information");
		player.sendMessage(CC.BLUE + "State: " + CC.YELLOW + game.getGameState().getReadable());
		player.sendMessage(CC.BLUE + "Players: " + CC.YELLOW + game.getRemainingPlayers() +
		                   "/" + game.getMaximumPlayers());

		if (game.getEvent() instanceof SumoEvent) {
			player.sendMessage(CC.BLUE + "Round: " + CC.YELLOW + game.getGameLogic().getRoundNumber());
		}
	}

}
