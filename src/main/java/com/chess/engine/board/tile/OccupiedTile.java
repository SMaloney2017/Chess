package com.chess.engine.board.tile;

import com.chess.engine.pieces.Piece;
import com.chess.engine.util.Color;

public class OccupiedTile extends Tile
{
	protected final Piece piece;

	protected OccupiedTile(int tileIndex, Piece piece)
	{
		super(tileIndex);
		this.piece = piece;
	}

	@Override
	public String toString()
	{
		return getPiece().getColor() == Color.DARK ?
			"\u001B[30m" + getPiece().toString() + "\u001B[0m" :
			"\u001B[37m" + getPiece().toString() + "\u001B[0m";
	}

	@Override
	public boolean isTileOccupied()
	{
		return true;
	}

	@Override
	public Piece getPiece()
	{
		return this.piece;
	}
}
