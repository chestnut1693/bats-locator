package batslocator;

import static batslocator.BatsLocator.CLOSED;
import static batslocator.BatsLocator.OPENED_POISON_OR_BATS;
import static batslocator.BatsLocator.OPENED_WITHOUT_GRUBS;
import static batslocator.BatsLocator.OPENED_WITH_GRUBS;
import static batslocator.BatsLocator.POISON_SPLAT;
import static batslocator.BatsLocator.TROUGH;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.GraphicsObject;
import net.runelite.api.Varbits;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Bats Locator",
	description = "Helps locating bats in the thieving room of Chambers of Xeric",
	tags = {"finder", "thieving"},
	enabledByDefault = false
)
public class BatsLocatorPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private BatsLocatorConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private BatsOverlay overlay;

	@Getter
	private BatsLocator batsLocator;

	@Getter
	private boolean inRaidChambers;

	@Provides
	BatsLocatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BatsLocatorConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
		clientThread.invokeLater(() -> checkRaidPresence(true));
		batsLocator = new BatsLocator(client);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
		inRaidChambers = false;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (!event.getGroup().equals("raids"))
		{
			return;
		}

		clientThread.invokeLater(() -> checkRaidPresence(true));
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		checkRaidPresence(false);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		GameObject gameObject = event.getGameObject();
		switch (gameObject.getId())
		{
			case TROUGH:
				batsLocator.troughSpawnEvent(gameObject);
				break;
			case CLOSED:
			case OPENED_POISON_OR_BATS:
			case OPENED_WITHOUT_GRUBS:
			case OPENED_WITH_GRUBS:
				batsLocator.chestSpawnEvent(gameObject);
				break;
		}
	}

	@Subscribe
	public void onGraphicsObjectCreated(GraphicsObjectCreated event)
	{
		if (inRaidChambers)
		{
			GraphicsObject graphicsObject = event.getGraphicsObject();
			if (graphicsObject.getId() == POISON_SPLAT)
			{
				batsLocator.poisonSplatEvent(WorldPoint.fromLocal(client, graphicsObject.getLocation()));
			}
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (inRaidChambers)
		{
			batsLocator.gameTickEvent();
		}
	}

	private void checkRaidPresence(boolean force)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		boolean setting = client.getVar(Varbits.IN_RAID) == 1;

		if (force || inRaidChambers != setting)
		{
			//A new instance is created when leaving the raid chambers instead of entering the raid chambers.
			//Entering the raid chambers will change the IN_RAID varbit but game objects spawn before the varbit change.
			if (!setting)
			{
				batsLocator = new BatsLocator(client);
			}

			inRaidChambers = setting;
		}
	}
}
