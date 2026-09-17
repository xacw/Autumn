package dev.xac.autumn.party.menu;

import dev.xac.autumn.party.PartyEvent;
import dev.xac.autumn.profile.Profile;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import dev.xac.autumn.util.CC;
import dev.xac.autumn.util.ItemBuilder;
import dev.xac.autumn.util.menu.Button;
import dev.xac.autumn.util.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PartyEventSelectEventMenu extends Menu {

	@Override
	public String getTitle(Player player) {
		return "&a&lSelect an event";
	}

	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = new HashMap<>();
		buttons.put(3, new SelectEventButton(PartyEvent.FFA));
		buttons.put(5, new SelectEventButton(PartyEvent.SPLIT));
		return buttons;
	}

	@AllArgsConstructor
	private class SelectEventButton extends Button {

		private PartyEvent partyEvent;

		@Override
		public ItemStack getButtonItem(Player player) {
			return new ItemBuilder(partyEvent == PartyEvent.FFA ? Material.QUARTZ : Material.REDSTONE)
					.name("&a&l" + partyEvent.getName())
					.build();
		}

		@Override
		public void clicked(Player player, ClickType clickType) {
			Profile profile = Profile.getByUuid(player.getUniqueId());

			if (profile.getParty() == null) {
				player.sendMessage(CC.RED + "You are not in a party.");
				return;
			}

			new PartyEventSelectKitMenu(partyEvent).openMenu(player);
		}

	}

}
