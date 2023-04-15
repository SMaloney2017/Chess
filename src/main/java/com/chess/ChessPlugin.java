package com.chess;

import com.chess.engine.board.Board;
import com.chess.ui.ChessPluginPanel;
import com.google.inject.Provides;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.swing.SwingUtilities;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.Player;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(name = "Chess", description = "Play chess while you bank stand.", tags = {"Chess"})
public class ChessPlugin extends Plugin
{
	private static NavigationButton navigationButton;
	public ChessPluginPanel panel;
	public Board board;

	@Inject
	private Client client;
	@Inject
	private ChessConfig config;
	@Inject
	private ClientToolbar clientToolbar;
	@Inject
	private Provider<MenuManager> menuManager;

	@Provides
	ChessConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ChessConfig.class);
	}

	@Override
	protected void startUp()
	{
		this.board = Board.newInitialBoardState();
		System.out.println(board);

		this.panel = new ChessPluginPanel(board);

		navigationButton = NavigationButton.builder().tooltip("Chess").icon(ImageUtil.loadImageResource(ChessPlugin.class, "icon.png")).priority(5).panel(panel).build();
		clientToolbar.addNavigation(navigationButton);

		if (config.challengePlayer() && client != null)
		{
			menuManager.get().addPlayerMenuItem("Challenge");
		}
	}

	@Override
	protected void shutDown()
	{
		clientToolbar.removeNavigation(navigationButton);
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		if ((event.getType() != MenuAction.CC_OP.getId() && event.getType() != MenuAction.CC_OP_LOW_PRIORITY.getId()) || !config.challengePlayer())
		{
			return;
		}

		String username = Text.toJagexName(Text.removeTags(event.getTarget()).toLowerCase().trim());

		final String option = event.getOption();
		final int componentId = event.getActionParam1();
		final int groupId = WidgetInfo.TO_GROUP(componentId);

		if (groupId == WidgetInfo.FRIENDS_LIST.getGroupId() && option.equals("Delete") || groupId == WidgetInfo.FRIENDS_CHAT.getGroupId() && (option.equals("Add ignore") || option.equals("Remove friend")) || groupId == WidgetInfo.CHATBOX.getGroupId() && (option.equals("Add ignore") || option.equals("Message")) || groupId == WidgetInfo.IGNORE_LIST.getGroupId() && option.equals("Delete") || (componentId == WidgetInfo.CLAN_MEMBER_LIST.getId() || componentId == WidgetInfo.CLAN_GUEST_MEMBER_LIST.getId()) && (option.equals("Add ignore") || option.equals("Remove friend")) || groupId == WidgetInfo.PRIVATE_CHAT_MESSAGE.getGroupId() && (option.equals("Add ignore") || option.equals("Message")) || groupId == WidgetID.GROUP_IRON_GROUP_ID && (option.equals("Add friend") || option.equals("Remove friend") || option.equals("Remove ignore")))
		{
			client.createMenuEntry(-2).setOption("Challenge").setTarget(event.getTarget()).setType(MenuAction.RUNELITE).setIdentifier(event.getIdentifier()).onClick(e -> sendChallenge(username));
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (event.getMenuAction() == MenuAction.RUNELITE_PLAYER && event.getMenuOption().equals("Challenge"))
		{
			Player player = client.getCachedPlayers()[event.getId()];
			if (player == null)
			{
				return;
			}

			String username = player.getName();
			sendChallenge(username);
		}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{

	}

	public void sendChallenge(String username)
	{
		SwingUtilities.invokeLater(() -> {
			/* select nav-button to open Temple plugin */
			if (!navigationButton.isSelected())
			{
				navigationButton.getOnSelect().run();
			}
			/* select ranks-tab */
			panel.initializeGame(board);
		});
	}
}
