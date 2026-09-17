package dev.xac.autumn;

import com.bizarrealex.aether.Aether;
import com.bizarrealex.aether.AetherOptions;
import dev.xac.autumn.arena.Arena;
import dev.xac.autumn.arena.ArenaListener;
import dev.xac.autumn.arena.ArenaType;
import dev.xac.autumn.arena.ArenaTypeAdapter;
import dev.xac.autumn.arena.ArenaTypeTypeAdapter;
import dev.xac.autumn.commands.admin.general.SetSpawnCommand;
import dev.xac.autumn.commands.admin.arena.ArenaAddKitCommand;
import dev.xac.autumn.commands.admin.arena.ArenaCreateCommand;
import dev.xac.autumn.commands.admin.arena.ArenaDeleteCommand;
import dev.xac.autumn.commands.admin.arena.ArenaGenHelperCommand;
import dev.xac.autumn.commands.admin.arena.ArenaGenerateCommand;
import dev.xac.autumn.commands.admin.arena.ArenaRemoveKitCommand;
import dev.xac.autumn.commands.admin.arena.ArenaSaveCommand;
import dev.xac.autumn.commands.admin.arena.ArenaSelectionCommand;
import dev.xac.autumn.commands.admin.arena.ArenaSetSpawnCommand;
import dev.xac.autumn.commands.admin.arena.ArenaStatusCommand;
import dev.xac.autumn.commands.admin.arena.ArenasCommand;
import dev.xac.autumn.commands.user.gamer.SuicideCommand;
import dev.xac.autumn.essentials.Essentials;
import dev.xac.autumn.event.Event;
import dev.xac.autumn.event.EventTypeAdapter;
import dev.xac.autumn.commands.event.admin.EventAdminCommand;
import dev.xac.autumn.commands.event.admin.EventHelpCommand;
import dev.xac.autumn.commands.event.admin.EventSetLobbyCommand;
import dev.xac.autumn.event.game.EventGameListener;
import dev.xac.autumn.commands.event.user.EventCancelCommand;
import dev.xac.autumn.commands.event.user.EventClearCooldownCommand;
import dev.xac.autumn.commands.event.user.EventForceStartCommand;
import dev.xac.autumn.commands.event.user.EventHostCommand;
import dev.xac.autumn.commands.event.user.EventInfoCommand;
import dev.xac.autumn.commands.event.user.EventJoinCommand;
import dev.xac.autumn.commands.event.user.EventLeaveCommand;
import dev.xac.autumn.commands.event.admin.EventsCommand;
import dev.xac.autumn.event.game.map.EventGameMap;
import dev.xac.autumn.event.game.map.EventGameMapTypeAdapter;
import dev.xac.autumn.commands.event.map.EventMapCreateCommand;
import dev.xac.autumn.commands.event.map.EventMapDeleteCommand;
import dev.xac.autumn.commands.event.map.EventMapSetSpawnCommand;
import dev.xac.autumn.commands.event.map.EventMapStatusCommand;
import dev.xac.autumn.commands.event.map.EventMapsCommand;
import dev.xac.autumn.commands.event.admin.EventAddMapCommand;
import dev.xac.autumn.commands.event.admin.EventRemoveMapCommand;
import dev.xac.autumn.commands.event.vote.EventMapVoteCommand;
import dev.xac.autumn.commands.admin.match.MatchTestCommand;
import dev.xac.autumn.commands.user.match.ViewInventoryCommand;
import dev.xac.autumn.commands.user.duels.DuelAcceptCommand;
import dev.xac.autumn.commands.user.duels.DuelCommand;
import dev.xac.autumn.commands.user.duels.RematchCommand;
import dev.xac.autumn.kit.Kit;
import dev.xac.autumn.kit.KitTypeAdapter;
import dev.xac.autumn.commands.admin.kits.KitCreateCommand;
import dev.xac.autumn.commands.admin.kits.KitGetLoadoutCommand;
import dev.xac.autumn.commands.admin.kits.KitsCommand;
import dev.xac.autumn.commands.admin.kits.KitSetLoadoutCommand;
import dev.xac.autumn.kit.KitEditorListener;
import dev.xac.autumn.match.Match;
import dev.xac.autumn.commands.user.match.SpectateCommand;
import dev.xac.autumn.commands.user.match.StopSpectatingCommand;
import dev.xac.autumn.match.MatchListener;
import dev.xac.autumn.party.Party;
import dev.xac.autumn.commands.user.party.PartyChatCommand;
import dev.xac.autumn.commands.user.party.PartyCloseCommand;
import dev.xac.autumn.commands.user.party.PartyCreateCommand;
import dev.xac.autumn.commands.user.party.PartyDisbandCommand;
import dev.xac.autumn.commands.user.party.PartyHelpCommand;
import dev.xac.autumn.commands.user.party.PartyInfoCommand;
import dev.xac.autumn.commands.user.party.PartyInviteCommand;
import dev.xac.autumn.commands.user.party.PartyJoinCommand;
import dev.xac.autumn.commands.user.party.PartyKickCommand;
import dev.xac.autumn.commands.user.party.PartyLeaveCommand;
import dev.xac.autumn.commands.user.party.PartyOpenCommand;
import dev.xac.autumn.party.PartyListener;
import dev.xac.autumn.profile.Profile;
import dev.xac.autumn.commands.donater.FlyCommand;
import dev.xac.autumn.profile.ProfileListener;
import dev.xac.autumn.profile.hotbar.Hotbar;
import dev.xac.autumn.commands.user.settings.ToggleDuelRequestsCommand;
import dev.xac.autumn.commands.user.settings.ToggleScoreboardCommand;
import dev.xac.autumn.commands.user.settings.ToggleSpectatorsCommand;
import dev.xac.autumn.queue.QueueListener;
import dev.xac.autumn.queue.QueueThread;
import dev.xac.autumn.scoreboard.ScoreboardAdapter;
import dev.xac.autumn.util.InventoryUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;
import lombok.Getter;
import dev.xac.autumn.util.command.Honcho;
import dev.xac.autumn.util.config.BasicConfigurationFile;
import dev.xac.autumn.util.menu.MenuListener;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class Autumn extends JavaPlugin {

	private static Autumn autumn;

	@Getter private BasicConfigurationFile mainConfig;
	@Getter private BasicConfigurationFile arenasConfig;
	@Getter private BasicConfigurationFile kitsConfig;
	@Getter private BasicConfigurationFile eventsConfig;
	@Getter private MongoDatabase mongoDatabase;
	@Getter private Honcho honcho;
	@Getter private Essentials essentials;

	@Override
	public void onEnable() {
		autumn = this;
		getServer().getPluginManager().registerEvents(new MenuListener(), this);
		honcho = new Honcho(this);
		mainConfig = new BasicConfigurationFile(this, "config");
		arenasConfig = new BasicConfigurationFile(this, "arenas");
		kitsConfig = new BasicConfigurationFile(this, "kits");
		eventsConfig = new BasicConfigurationFile(this, "events");
		this.essentials = new Essentials(this);
		loadMongo();

		Hotbar.init();
		Kit.init();
		Arena.init();
		Profile.init();
		Match.init();
		Party.init();
		Event.init();
		EventGameMap.init();

		new Aether(this, new ScoreboardAdapter(), new AetherOptions().hook(true));
		new QueueThread().start();

		getHoncho().registerTypeAdapter(Arena.class, new ArenaTypeAdapter());
		getHoncho().registerTypeAdapter(ArenaType.class, new ArenaTypeTypeAdapter());
		getHoncho().registerTypeAdapter(Kit.class, new KitTypeAdapter());
		getHoncho().registerTypeAdapter(EventGameMap.class, new EventGameMapTypeAdapter());
		getHoncho().registerTypeAdapter(Event.class, new EventTypeAdapter());

		Arrays.asList(
				new ArenaAddKitCommand(),
				new ArenaRemoveKitCommand(),
				new ArenaCreateCommand(),
				new ArenaDeleteCommand(),
				new ArenaGenerateCommand(),
				new ArenaGenHelperCommand(),
				new ArenaSaveCommand(),
				new ArenasCommand(),
				new ArenaSelectionCommand(),
				new ArenaSetSpawnCommand(),
				new ArenaStatusCommand(),
				new DuelCommand(),
				new DuelAcceptCommand(),
				new EventAdminCommand(),
				new EventHelpCommand(),
				new EventCancelCommand(),
				new EventClearCooldownCommand(),
				new EventForceStartCommand(),
				new EventHostCommand(),
				new EventInfoCommand(),
				new EventJoinCommand(),
				new EventLeaveCommand(),
				new EventSetLobbyCommand(),
				new EventMapCreateCommand(),
				new EventMapDeleteCommand(),
				new EventMapsCommand(),
				new EventMapSetSpawnCommand(),
				new EventMapStatusCommand(),
				new EventMapVoteCommand(),
				new EventAddMapCommand(),
				new EventRemoveMapCommand(),
				new EventsCommand(),
				new RematchCommand(),
				new SpectateCommand(),
				new StopSpectatingCommand(),
				new FlyCommand(),
				new SetSpawnCommand(),
				new PartyChatCommand(),
				new PartyCloseCommand(),
				new PartyCreateCommand(),
				new PartyDisbandCommand(),
				new PartyHelpCommand(),
				new PartyInfoCommand(),
				new PartyInviteCommand(),
				new PartyJoinCommand(),
				new PartyKickCommand(),
				new PartyLeaveCommand(),
				new PartyOpenCommand(),
				new KitCreateCommand(),
				new KitGetLoadoutCommand(),
				new KitSetLoadoutCommand(),
				new KitsCommand(),
				new ViewInventoryCommand(),
				new MatchTestCommand(),
				new ToggleScoreboardCommand(),
				new ToggleSpectatorsCommand(),
				new ToggleDuelRequestsCommand(),
				new SuicideCommand()
		).forEach(command -> getHoncho().registerCommand(command));

		Arrays.asList(
				new KitEditorListener(),
				new PartyListener(),
				new ProfileListener(),
				new PartyListener(),
				new MatchListener(),
				new QueueListener(),
				new ArenaListener(),
				new EventGameListener()
		).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));

		Arrays.asList(
				Material.WORKBENCH,
				Material.STICK,
				Material.WOOD_PLATE,
				Material.WOOD_BUTTON,
				Material.SNOW_BLOCK
		).forEach(InventoryUtil::removeCrafting);

		// Set the difficulty for each world to HARD
		// Clear the droppedItems for each world
		getServer().getWorlds().forEach(world -> {
			world.setDifficulty(Difficulty.HARD);
			getEssentials().clearEntities(world);

		});
	}

	@Override
	public void onDisable() {
		Match.cleanup();
	}

	private void loadMongo() {
		if (mainConfig.getBoolean("MONGO.AUTHENTICATION.ENABLED")) {
			mongoDatabase = new MongoClient(
					new ServerAddress(
							mainConfig.getString("MONGO.HOST"),
							mainConfig.getInteger("MONGO.PORT")
					),
					MongoCredential.createCredential(
							mainConfig.getString("MONGO.AUTHENTICATION.USERNAME"),
							mainConfig.getString("MONGO.AUTHENTICATION.ADMIN"), mainConfig.getString("MONGO.AUTHENTICATION.PASSWORD").toCharArray()
					),
					MongoClientOptions.builder().build()
			).getDatabase(mainConfig.getString("MONGO.DATABASE"));
		} else {
			mongoDatabase = new MongoClient(mainConfig.getString("MONGO.HOST"), mainConfig.getInteger("MONGO.PORT"))
					.getDatabase(mainConfig.getString("MONGO.DATABASE"));
		}
	}

	public static Autumn get() {
		return autumn;
	}

}
