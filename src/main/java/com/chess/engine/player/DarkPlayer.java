package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.util.Color;
import java.util.Collection;

public class DarkPlayer extends Player
{
	public DarkPlayer(Board board, Collection<Move> lightLegalMoves, Collection<Move> darkLegalMoves)
	{
		super(board, darkLegalMoves, lightLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces()
	{
		return this.board.getDarkPieces();
	}

	@Override
	public Color getColor()
	{
		return Color.DARK;
	}

	@Override
	public Player getOpponent()
	{
		return this.board.getLightPlayer();
	}
}
