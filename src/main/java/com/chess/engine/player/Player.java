package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.move.MoveTransition;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.util.Color;
import com.chess.engine.util.PieceType;
import java.util.Collection;

public abstract class Player
{
	protected final Board board;
	protected final King king;
	protected final Collection<Move> legalMoves;

	Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentLegalMoves)
	{
		this.board = board;
		this.king = getKing();
		this.legalMoves = legalMoves;
	}

	private King getKing()
	{
		for (final Piece piece : getActivePieces())
		{
			if (piece.getPieceType() == PieceType.KING)
			{
				return (King) piece;
			}
		}
		throw new RuntimeException("Error::Illegal Board State");
	}

	public boolean isLegalMove(final Move move)
	{
		return this.legalMoves.contains(move);
	}

	public boolean isInCheck()
	{
		return false;
	}

	public boolean isInCheckMate()
	{
		return false;
	}

	public boolean isInStalemate()
	{
		return false;
	}

	public boolean hasCastled()
	{
		return false;
	}

	public MoveTransition makeMove(final Move move)
	{
		return null;
	}

	public abstract Collection<Piece> getActivePieces();

	public abstract Color getColor();

	public abstract Player getOpponent();

}
