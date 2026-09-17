package dev.xac.autumn.event.game.map;


import dev.xac.autumn.util.command.command.adapter.CommandTypeAdapter;

public class EventGameMapTypeAdapter implements CommandTypeAdapter {

	@Override
	public <T> T convert(String string, Class<T> type) {
		return type.cast(EventGameMap.getByName(string));
	}

}

