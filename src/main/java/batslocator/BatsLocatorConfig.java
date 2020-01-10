package batslocator;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("batslocator")
public interface BatsLocatorConfig extends Config
{
	enum DisplayMode
	{
		DOTS,
		NUMBERS
	}

	@ConfigItem(
		keyName = "unvisitedColor",
		name = "Unvisited chest color",
		description = "Configures the color of the unvisited chest dot and number",
		position = 0
	)
	default Color unvisitedColor()
	{
		return Color.magenta;
	}

	@ConfigItem(
		keyName = "batsColor",
		name = "Bats chest color",
		description = "Configures the color of the bats chest dot and number",
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
		position = 2
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
		position = 3
	)
	default int dotTransparency()
	{
		return 75;
	}

	@Range(
		max = 27
	)
	@ConfigItem(
		keyName = "dotSize",
		name = "Dot size",
		description = "Configures the size of the transparent dots, solid dots are one third larger",
		position = 4
	)
	default int dotSize()
	{
		return 9;
	}

	@ConfigItem(
		keyName = "displayMode",
		name = "Display mode",
		description = "Configures displaying chest states as dots or numbers",
		position = 5
	)
	default DisplayMode displayMode()
	{
		return DisplayMode.DOTS;
	}
}
