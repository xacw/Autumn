package dev.xac.autumn.arena.menu;

import java.util.Map;

import dev.xac.autumn.util.menu.Button;
import dev.xac.autumn.util.menu.Menu;
import org.bukkit.entity.Player;

public class SelectArenaMenu extends Menu {

	@Override
	public String getTitle(Player player) {
		return "&6Select Arena";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		return super.getButtons();
	}

}
