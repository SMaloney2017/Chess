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

public class Pawn extends Piece
{
	private final static int[] MOVE_OFFSETS = {7, 8, 9, 16};

	public Pawn(final int pieceIndex, final Color color)
	{
		super(PieceType.PAWN, pieceIndex, color);
	}

	@Override
	public List<Move> calculateLegalMoves(Board board)
	{
		final List<Move> legalMoves = new ArrayList<>();
		for (final int offset : MOVE_OFFSETS)
		{
			final int destinationIndex = this.pieceIndex + (offset * this.color.getMagnitude());
			if (GameUtilities.indexIsWithinBounds(destinationIndex))
			{
				continue;
			}

			final Tile destinationTile = board.getTile(destinationIndex);
			if (destinationIndex == 8 && !destinationTile.isTileOccupied())
			{
				legalMoves.add(new SignificantMove(board, this, destinationIndex));
			}
			else if (destinationIndex == 16 && this.isFirstMove() &&
				(GameUtilities.SECOND_ROW[this.pieceIndex] && this.color == Color.DARK) ||
				(GameUtilities.SEVENTH_ROW[this.pieceIndex] && this.color == Color.LIGHT)
			)
			{
				final int skippedTileIndex = this.pieceIndex + (offset * this.color.getMagnitude());
				final Tile skippedTile = board.getTile(skippedTileIndex);

				if (!skippedTile.isTileOccupied() && !destinationTile.isTileOccupied())
				{
					legalMoves.add(new SignificantMove(board, this, destinationIndex));
				}
			}
			else if (destinationIndex == 7 &&
				!((GameUtilities.EIGHTH_COLUMN[this.pieceIndex] && this.color == Color.LIGHT) ||
					(GameUtilities.FIRST_COLUMN[this.pieceIndex] && this.color == Color.DARK))
			)
			{
				if (destinationTile.isTileOccupied())
				{
					final Piece target = destinationTile.getPiece();
					final Color occupancy = target.getColor();
					if (this.color != occupancy)
					{
						legalMoves.add(new AttackingMove(board, this, destinationIndex, target));
					}
				}
			}
			else if (destinationIndex == 9 &&
				!((GameUtilities.FIRST_COLUMN[this.pieceIndex] && this.color == Color.LIGHT) ||
					(GameUtilities.EIGHTH_COLUMN[this.pieceIndex] && this.color == Color.DARK))
			)
			{
				if (destinationTile.isTileOccupied())
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
		return PieceType.PAWN.toString();
	}
}
