package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.util.Color;
import com.chess.engine.util.PieceType;
import java.util.Collection;

public abstract class Piece
{
	protected final int pieceIndex;
	protected final Color color;
	protected final boolean isFirstMove;
	protected final PieceType pieceType;

	protected Piece(final PieceType pieceType, final int pieceIndex, final Color color)
	{
		this.pieceType = pieceType;
		this.pieceIndex = pieceIndex;
		this.color = color;
		this.isFirstMove = false;
	}

	public abstract Collection<Move> calculateLegalMoves(final Board board);

	public Color getColor()
	{
		return this.color;
	}

	public boolean isFirstMove()
	{
		return this.isFirstMove;
	}

	public int getPieceIndex()
	{
		return this.pieceIndex;
	}

	public PieceType getPieceType()
	{
		return this.pieceType;
	}
}
