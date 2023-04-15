package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

public final class SignificantMove extends Move
{
	public SignificantMove(final Board board, final Piece piece, final int destinationIndex)
	{
		super(board, piece, destinationIndex);
	}
}
