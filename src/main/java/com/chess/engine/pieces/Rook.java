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
import java.util.Collection;
import java.util.List;

public class Rook extends Piece
{
	private final static int[] MOVE_OFFSETS_VECTOR_INDEXES = {-8, -1, 1, 8};

	public Rook(final int pieceIndex, final Color color)
	{
		super(PieceType.ROOK, pieceIndex, color);
	}

	private static boolean isFirstColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.FIRST_COLUMN[index] && ((offset == -1) || (offset == 8));
	}

	private static boolean isEighthColumnExclusion(final int index, final int offset)
	{
		return GameUtilities.EIGHTH_COLUMN[index] && ((offset == 8) || (offset == 1));
	}

	@Override
	public Collection<Move> calculateLegalMoves(final Board board)
	{
		final List<Move> legalMoves = new ArrayList<>();
		for (final int offset : MOVE_OFFSETS_VECTOR_INDEXES)
		{
			int destinationIndex = this.pieceIndex;
			while (GameUtilities.indexIsWithinBounds(destinationIndex))
			{

				if (isFirstColumnExclusion(this.pieceIndex, offset) ||
					isEighthColumnExclusion(this.pieceIndex, offset))
				{
					break;
				}

				destinationIndex += offset;
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
						break;
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	@Override
	public String toString()
	{
		return PieceType.ROOK.toString();
	}
}
