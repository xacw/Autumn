package dev.xac.autumn.arena;

import dev.xac.autumn.util.command.command.adapter.CommandTypeAdapter;

public class ArenaTypeAdapter implements CommandTypeAdapter {

	@Override
	public <T> T convert(String string, Class<T> type) {
		return type.cast(Arena.getByName(string));
	}

}

