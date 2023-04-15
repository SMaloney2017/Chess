package com.chess.ui;

import com.chess.engine.board.Board;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import net.runelite.client.ui.ColorScheme;

public class ChessPluginPanel extends net.runelite.client.ui.PluginPanel
{
	public Board board;

	public ChessPluginPanel(Board board)
	{
		this.board = board;

		/* Layout & Style */
		setBackground(ColorScheme.DARKER_GRAY_COLOR);

		JPanel layout = new JPanel();
		layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));
		layout.setBackground(ColorScheme.DARKER_GRAY_COLOR);

		add(layout);
	}

	public void initializeGame(Board board)
	{

	}
}
