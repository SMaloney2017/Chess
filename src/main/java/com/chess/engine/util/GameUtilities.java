package com.chess.engine.util;

public class GameUtilities
{
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	public static final boolean[] FIRST_COLUMN = initializeColumn(1);
	public static final boolean[] SECOND_COLUMN = initializeColumn(2);
	public static final boolean[] SEVENTH_COLUMN = initializeColumn(7);
	public static final boolean[] EIGHTH_COLUMN = initializeColumn(8);

	public static final boolean[] SECOND_ROW = initializeRow(8);
	public static final boolean[] SEVENTH_ROW = initializeRow(48);

	private GameUtilities()
	{
		throw new RuntimeException("You cannot instantiate GameUtilities()");
	}

	private static boolean[] initializeColumn(int x)
	{
		final boolean[] column = new boolean[NUM_TILES];
		do
		{
			column[x] = true;
			x += NUM_TILES_PER_ROW;
		} while (x < NUM_TILES);
		return column;
	}

	private static boolean[] initializeRow(int x)
	{
		final boolean[] row = new boolean[NUM_TILES];
		do
		{
			row[x] = true;
			x++;
		} while (x % NUM_TILES_PER_ROW != 0);
		return row;
	}

	public static boolean indexIsWithinBounds(final int index)
	{
		return index >= 0 && index < NUM_TILES;
	}
}
