package com.chess.engine.board.tile;

import com.chess.engine.pieces.Piece;

public class EmptyTile extends Tile
{
	protected EmptyTile(int tileIndex)
	{
		super(tileIndex);
	}

	@Override
	public String toString()
	{
		return "-";
	}

	@Override
	public boolean isTileOccupied()
	{
		return false;
	}

	@Override
	public Piece getPiece()
	{
		return null;
	}
}
