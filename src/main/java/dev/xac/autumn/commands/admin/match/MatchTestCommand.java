package dev.xac.autumn.commands.admin.match;

import dev.xac.autumn.Locale;
import dev.xac.autumn.match.Match;
import dev.xac.autumn.match.participant.MatchGamePlayer;
import dev.xac.autumn.participant.GameParticipant;
import dev.xac.autumn.util.command.command.CommandMeta;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

@CommandMeta(label = "match test", permission = "praxi.admin")
public class MatchTestCommand {

	public void execute(Player player) {
		List<GameParticipant<MatchGamePlayer>> list = Arrays.asList(
				new GameParticipant<>(new MatchGamePlayer(UUID.randomUUID(), "Test1")),
				new GameParticipant<>(new MatchGamePlayer(UUID.randomUUID(), "Test2")),
				new GameParticipant<>(new MatchGamePlayer(UUID.randomUUID(), "Test3")),
				new GameParticipant<>(new MatchGamePlayer(UUID.randomUUID(), "Test4")));

		BaseComponent[] components = Match.generateInventoriesComponents(
				Locale.MATCH_END_WINNER_INVENTORY.format("s"), list);

		player.spigot().sendMessage(components);
	}

}
