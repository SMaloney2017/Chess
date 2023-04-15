package com.chess.engine.board;

import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;
import com.chess.engine.player.DarkPlayer;
import com.chess.engine.player.LightPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.util.Color;
import com.chess.engine.util.GameUtilities;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board
{
	private final List<Tile> board;
	private final Collection<Piece> lightPieces;
	private final Collection<Piece> darkPieces;
	private final LightPlayer lightPlayer;
	private final DarkPlayer darkPlayer;

	private Board(Builder builder)
	{
		this.board = createGameBoard(builder);
		this.lightPieces = getActivePieces(this.board, Color.LIGHT);
		this.darkPieces = getActivePieces(this.board, Color.DARK);

		final Collection<Move> lightLegalMoves = getLegalMoves(this.lightPieces);
		final Collection<Move> darkLegalMoves = getLegalMoves(this.darkPieces);

		this.lightPlayer = new LightPlayer(this, lightLegalMoves, darkLegalMoves);
		this.darkPlayer = new DarkPlayer(this, lightLegalMoves, darkLegalMoves);
	}

	private static Collection<Piece> getActivePieces(final List<Tile> board, final Color color)
	{
		final List<Piece> pieces = new ArrayList<>();
		for (final Tile tile : board)
		{
			if (tile.isTileOccupied())
			{
				final Piece piece = tile.getPiece();
				if (piece.getColor() == color)
				{
					pieces.add(piece);
				}
			}
		}

		return ImmutableList.copyOf(pieces);
	}

	private static List<Tile> createGameBoard(final Builder builder)
	{
		final Tile[] tiles = new Tile[GameUtilities.NUM_TILES];
		for (int i = 0; i < GameUtilities.NUM_TILES; i++)
		{
			tiles[i] = Tile.newTile(i, builder.boardState.get(i));
		}

		return ImmutableList.copyOf(tiles);
	}

	public static Board newInitialBoardState()
	{
		final Builder builder = new Builder();

		builder.setPiece(new Rook(0, Color.DARK));
		builder.setPiece(new Knight(1, Color.DARK));
		builder.setPiece(new Bishop(2, Color.DARK));
		builder.setPiece(new Queen(3, Color.DARK));
		builder.setPiece(new King(4, Color.DARK));
		builder.setPiece(new Bishop(5, Color.DARK));
		builder.setPiece(new Knight(6, Color.DARK));
		builder.setPiece(new Rook(7, Color.DARK));
		builder.setPiece(new Pawn(8, Color.DARK));
		builder.setPiece(new Pawn(9, Color.DARK));
		builder.setPiece(new Pawn(10, Color.DARK));
		builder.setPiece(new Pawn(11, Color.DARK));
		builder.setPiece(new Pawn(12, Color.DARK));
		builder.setPiece(new Pawn(13, Color.DARK));
		builder.setPiece(new Pawn(14, Color.DARK));
		builder.setPiece(new Pawn(15, Color.DARK));

		builder.setPiece(new Pawn(48, Color.LIGHT));
		builder.setPiece(new Pawn(49, Color.LIGHT));
		builder.setPiece(new Pawn(50, Color.LIGHT));
		builder.setPiece(new Pawn(51, Color.LIGHT));
		builder.setPiece(new Pawn(52, Color.LIGHT));
		builder.setPiece(new Pawn(53, Color.LIGHT));
		builder.setPiece(new Pawn(54, Color.LIGHT));
		builder.setPiece(new Pawn(55, Color.LIGHT));
		builder.setPiece(new Rook(56, Color.LIGHT));
		builder.setPiece(new Knight(57, Color.LIGHT));
		builder.setPiece(new Bishop(58, Color.LIGHT));
		builder.setPiece(new Queen(59, Color.LIGHT));
		builder.setPiece(new King(60, Color.LIGHT));
		builder.setPiece(new Bishop(61, Color.LIGHT));
		builder.setPiece(new Knight(62, Color.LIGHT));
		builder.setPiece(new Rook(63, Color.LIGHT));

		builder.setInitiative(Color.LIGHT);

		return builder.build();
	}

	public Player getLightPlayer()
	{
		return this.lightPlayer;
	}

	public Player getDarkPlayer()
	{
		return this.darkPlayer;
	}

	public Collection<Piece> getLightPieces()
	{
		return this.lightPieces;
	}

	public Collection<Piece> getDarkPieces()
	{
		return this.darkPieces;
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < GameUtilities.NUM_TILES; i++)
		{
			final String text = this.board.get(i).toString();
			builder.append(String.format("%s", text));
			if ((i + 1) % GameUtilities.NUM_TILES_PER_ROW == 0)
			{
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	private Collection<Move> getLegalMoves(final Collection<Piece> pieces)
	{
		final List<Move> moves = new ArrayList<>();
		for (final Piece piece : pieces)
		{
			moves.addAll(piece.calculateLegalMoves(this));
		}

		return ImmutableList.copyOf(moves);

	}

	public Tile getTile(final int tileIndex)
	{
		return board.get(tileIndex);
	}

	public static class Builder
	{
		Map<Integer, Piece> boardState;
		Color initiative;

		public Builder()
		{
			this.boardState = new HashMap<>();
		}

		public Builder setPiece(final Piece piece)
		{
			this.boardState.put(piece.getPieceIndex(), piece);
			return this;
		}

		public Builder setInitiative(final Color color)
		{
			this.initiative = color;
			return this;
		}

		public Board build()
		{
			return new Board(this);
		}
	}
}
