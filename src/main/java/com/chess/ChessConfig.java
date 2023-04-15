package com.chess;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("Chess")
public interface ChessConfig extends Config
{
	@ConfigSection(
		name = "General",
		description = "General Settings",
		position = 0
	)
	String generalSettings = "generalSettings";

	@ConfigSection(
		name = "Game",
		description = "Game Settings",
		position = 0
	)
	String gameSettings = "gameSettings";

	@ConfigSection(
		name = "Board",
		description = "Board Options",
		position = 0
	)
	String boardSettings = "boardSettings";

	@ConfigItem(
		keyName = "challengePlayer",
		name = "Challenge",
		description = "Toggle \"Challenge Player\" option in players' right-click menus",
		position = 1,
		section = generalSettings
	)
	default boolean challengePlayer()
	{
		return true;
	}
}
