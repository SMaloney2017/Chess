package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.util.Color;
import java.util.Collection;

public class LightPlayer extends Player
{

	public LightPlayer(Board board, Collection<Move> lightLegalMoves, Collection<Move> darkLegalMoves)
	{
		super(board, lightLegalMoves, darkLegalMoves);
	}

	@Override
	public Collection<Piece> getActivePieces()
	{
		return this.board.getLightPieces();
	}

	@Override
	public Color getColor()
	{
		return Color.LIGHT;
	}

	@Override
	public Player getOpponent()
	{
		return this.board.getDarkPlayer();
	}
}
