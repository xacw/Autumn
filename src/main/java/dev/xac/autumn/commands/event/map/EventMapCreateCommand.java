package dev.xac.autumn.commands.event.map;

import dev.xac.autumn.event.game.map.EventGameMap;
import dev.xac.autumn.event.game.map.impl.SpreadEventGameMap;
import dev.xac.autumn.event.game.map.impl.TeamEventGameMap;
import dev.xac.autumn.util.command.command.CPL;
import dev.xac.autumn.util.command.command.CommandMeta;
import dev.xac.autumn.util.CC;
import org.bukkit.entity.Player;

@CommandMeta(label = "event map create", permission = "praxi.admin.event")
public class EventMapCreateCommand {

	public void execute(Player player, @CPL("mapName") String mapName, @CPL("mapType") String mapType) {
		if (EventGameMap.getByName(mapName) != null) {
			player.sendMessage(CC.RED + "An event map with that name already exists.");
			return;
		}

		EventGameMap gameMap;

		if (mapType.equalsIgnoreCase("TEAM")) {
			gameMap = new TeamEventGameMap(mapName);
		} else if (mapType.equalsIgnoreCase("SPREAD")) {
			gameMap = new SpreadEventGameMap(mapName);
		} else {
			player.sendMessage(CC.RED + "That event map type is not valid. Pick either \"TEAM\" or \"SPREAD\"!");
			return;
		}

		gameMap.save();

		EventGameMap.getMaps().add(gameMap);

		player.sendMessage(CC.GREEN + "You successfully created the event map \"" + mapName + "\".");
	}

}
