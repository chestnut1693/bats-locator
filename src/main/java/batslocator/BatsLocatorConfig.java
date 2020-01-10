package batslocator;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("batslocator")
public interface BatsLocatorConfig extends Config
{
	@ConfigItem(
		keyName = "batsColor",
		name = "Bats chest color",
		description = "Configures the color of the bats chest dot and number.",
		position = 1
	)
	default Color batsColor()
	{
		return Color.white;
	}

	@ConfigItem(
		keyName = "poisonColor",
		name = "Poison chest color",
		description = "Configures the color of the poison chest dot and number",
		position = 1
	)
	default Color poisonColor()
	{
		return Color.green;
	}

	@Range(
		max = 255
	)
	@ConfigItem(
		keyName = "dotTransparency",
		name = "Dot transparency",
		description = "Configures the transparency of the chest dots that are not likely to contain poison or bats",
		position = 2
	)
	default int dotTransparency()
	{
		return 75;
	}
}
