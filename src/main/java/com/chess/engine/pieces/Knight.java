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

public class Knight extends Piece
{
	private final static int[] MOVE_OFFSETS = {-17, -15, -10, -6, 6, 10, 15, 17};

	public Knight(final int pieceIndex, final Color color)
	{
		super(PieceType.KNIGHT, pieceIndex, color);
	}

	private static boolean isFirstColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.FIRST_COLUMN[index] && ((offset == -17) || (offset == -10) || (offset == 6) || (offset == 15));
	}

	private static boolean isSecondColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.SECOND_COLUMN[index] && ((offset == -10) || (offset == 6));
	}

	private static boolean isSeventhColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.SEVENTH_COLUMN[index] && ((offset == -6) || (offset == 10));
	}

	private static boolean isEighthColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.EIGHTH_COLUMN[index] && ((offset == -15) || (offset == -6) || (offset == 10) || (offset == 17));
	}

	@Override
	public List<Move> calculateLegalMoves(Board board)
	{
		final List<Move> legalMoves = new ArrayList<>();
		for (final int offset : MOVE_OFFSETS)
		{
			final int destinationIndex = this.pieceIndex + offset;
			if (GameUtilities.indexIsWithinBounds(destinationIndex))
			{
				if (isFirstColumnExclusion(this.pieceIndex, offset) ||
					isSecondColumnExclusion(this.pieceIndex, offset) ||
					isSeventhColumnExclusion(this.pieceIndex, offset) ||
					isEighthColumnExclusion(this.pieceIndex, offset))
				{
					continue;
				}

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
		return PieceType.KNIGHT.toString();
	}
}
