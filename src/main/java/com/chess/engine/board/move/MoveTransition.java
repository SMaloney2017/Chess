package com.chess.engine.board.move;

import com.chess.engine.board.Board;

public class MoveTransition
{
	private final Board transitionBoard;
	private final Move move;
	private final MoveStatus status;

	public MoveTransition(final Board board, final Move move, final MoveStatus status)
	{
		this.transitionBoard = board;
		this.move = move;
		this.status = status;
	}
}
