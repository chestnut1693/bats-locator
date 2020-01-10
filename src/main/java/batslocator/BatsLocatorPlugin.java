package batslocator;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

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
	private BatsLocatorConfig config;

	@Provides
	BatsLocatorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BatsLocatorConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
	}

	@Override
	protected void shutDown() throws Exception
	{
	}
}
