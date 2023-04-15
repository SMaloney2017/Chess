package com.chess.engine.board.tile;

import com.chess.engine.pieces.Piece;
import com.chess.engine.util.GameUtilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public abstract class Tile
{
	private static final Map<Integer, EmptyTile> cacheOfAllEmptyTiles = createEmptyTileSet();
	protected final int tileIndex;

	protected Tile(int tileIndex)
	{
		this.tileIndex = tileIndex;
	}

	private static Map<Integer, EmptyTile> createEmptyTileSet()
	{
		Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		for (int i = 0; i < GameUtilities.NUM_TILES; i++)
		{
			emptyTileMap.put(i, new EmptyTile(i));
		}

		return Collections.unmodifiableMap(emptyTileMap);
	}

	public static Tile newTile(final int tileIndex, final Piece piece)
	{
		return piece != null ? new OccupiedTile(tileIndex, piece) : cacheOfAllEmptyTiles.get(tileIndex);
	}

	public abstract boolean isTileOccupied();

	public abstract Piece getPiece();
}
