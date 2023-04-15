package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.AttackingMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.move.SignificantMove;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.util.Color;
import com.chess.engine.util.GameUtilities;
import com.chess.engine.util.PieceType;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	private final static int[] MOVE_OFFSETS = {-9, -8, -7, -1, 1, 7, 8, 9};

	public King(final int pieceIndex, final Color color)
	{
		super(PieceType.KING, pieceIndex, color);
	}

	private static boolean isFirstColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.FIRST_COLUMN[index] && ((offset == -9) || (offset == -1) || (offset == 7));
	}

	private static boolean isEighthColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.EIGHTH_COLUMN[index] && ((offset == -7) || (offset == 1) || (offset == 9));
	}

	@Override
	public List<Move> calculateLegalMoves(Board board)
	{
		final List<Move> legalMoves = new ArrayList<>();
		for (final int offset : MOVE_OFFSETS)
		{
			if (isFirstColumnExclusion(this.pieceIndex, offset) ||
				isEighthColumnExclusion(this.pieceIndex, offset))
			{
				continue;
			}

			final int destinationIndex = this.pieceIndex + offset;
			if (GameUtilities.indexIsWithinBounds(destinationIndex))
			{
				final Tile destinationTile = board.getTile(destinationIndex);
				if (!destinationTile.isTileOccupied())
				{
					legalMoves.add(new SignificantMove(board, this, destinationIndex));
				}
				else
				{
					final Piece target = destinationTile.getPiece();
					final Color occupancy = target.getColor();
					if (this.color != occupancy)
					{
						legalMoves.add(new AttackingMove(board, this, destinationIndex, target));
					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}

	@Override
	public String toString()
	{
		return PieceType.KING.toString();
	}
}
