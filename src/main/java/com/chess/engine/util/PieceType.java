package com.chess.engine.util;

public enum PieceType
{
	PAWN("P"),
	KNIGHT("N"),
	BISHOP("B"),
	ROOK("R"),
	QUEEN("Q"),
	KING("K");

	private final String piece;

	PieceType(final String piece)
	{
		this.piece = piece;
	}

	@Override
	public String toString()
	{
		return this.piece;
	}
}
