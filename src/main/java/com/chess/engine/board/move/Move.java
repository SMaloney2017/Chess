package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public abstract class Move
{
	final Board board;
	final Piece piece;
	final int destinationIndex;

	Move(final Board board, final Piece piece, final int destinationIndex)
	{
		this.board = board;
		this.piece = piece;
		this.destinationIndex = destinationIndex;
	}
}
