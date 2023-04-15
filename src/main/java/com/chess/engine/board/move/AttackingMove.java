package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public final class AttackingMove extends Move
{
	final Piece target;

	public AttackingMove(final Board board, final Piece piece, final int destinationIndex, final Piece target)
	{
		super(board, piece, destinationIndex);
		this.target = target;
	}
}
