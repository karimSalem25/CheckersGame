package Engine;

import java.util.ArrayList;
import entity.pieces.*;
import entity.player.*;
import exceptions.*;
import view.*;

public class Game implements PlayerListener {

	Player currentPlayer;
	Player opponentPlayer;
	Board board;
	GameListener listener;
	final int DIMENSION = 600;

	public Game(GameListener listener) {
		this.listener = listener;
		this.opponentPlayer = new Player(Color.WHITE, this);
		this.currentPlayer = new Player(Color.BLACK, this);

		ArrayList<Piece> thepiecesdone = new ArrayList<Piece>();

		Piece p1 = new Pawn(0, 0, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p1);
		Piece p2 = new Pawn(0, 2, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p2);
		Piece p3 = new Pawn(0, 4, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p3);
		Piece p4 = new Pawn(0, 6, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p4);
		Piece p5 = new Pawn(1, 1, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p5);
		Piece p6 = new Pawn(1, 3, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p6);

		Piece p7 = new Pawn(1, 5, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p7);
		Piece p8 = new Pawn(1, 7, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p8);
		Piece p9 = new Pawn(2, 0, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p9);
		Piece p10 = new Pawn(2, 2, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p10);
		Piece p11 = new Pawn(2, 4, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p11);
		Piece p12 = new Pawn(2, 6, Color.WHITE, opponentPlayer);
		thepiecesdone.add(p12);

		Piece p13 = new Pawn(7, 7, Color.BLACK, currentPlayer);
		thepiecesdone.add(p13);
		Piece p14 = new Pawn(7, 5, Color.BLACK, currentPlayer);
		thepiecesdone.add(p14);
		Piece p15 = new Pawn(7, 3, Color.BLACK, currentPlayer);
		thepiecesdone.add(p15);
		Piece p16 = new Pawn(7, 1, Color.BLACK, currentPlayer);
		thepiecesdone.add(p16);
		Piece p17 = new Pawn(6, 6, Color.BLACK, currentPlayer);
		thepiecesdone.add(p17);
		Piece p18 = new Pawn(6, 4, Color.BLACK, currentPlayer);
		thepiecesdone.add(p18);

		Piece p19 = new Pawn(6, 2, Color.BLACK, currentPlayer);
		thepiecesdone.add(p19);
		Piece p20 = new Pawn(6, 0, Color.BLACK, currentPlayer);
		thepiecesdone.add(p20);
		Piece p21 = new Pawn(5, 1, Color.BLACK, currentPlayer);
		thepiecesdone.add(p21);
		Piece p22 = new Pawn(5, 3, Color.BLACK, currentPlayer);
		thepiecesdone.add(p22);
		Piece p23 = new Pawn(5, 5, Color.BLACK, currentPlayer);
		thepiecesdone.add(p23);
		Piece p24 = new Pawn(5, 7, Color.BLACK, currentPlayer);
		thepiecesdone.add(p24);

		this.board = new Board(DIMENSION, thepiecesdone);
	}

	public final int getDimension() {
		return DIMENSION;
	}

	public ArrayList<Piece> getPieces() {
		return board.getPieces();
	}

	public void Play(Piece piece, Location destination) throws CheckersException {
		if (!(piece.getColor()).equals(currentPlayer.getColor()))
			throw new NotYourTurnException("Not Your Turn");

		if ((board.captureCapable(piece.getColor())).size() > 0
				&& !((board.captureCapable((piece.getColor()))).contains(piece)))
			throw new CaptureByPassException("Your color can attack");

		if (!((board.possiblePlays(piece)).contains(destination)))
			throw new InvalidMoveException("Invalid Move");

		if (board.movePiece(piece, destination) == true)
			endTurn();
		else if (board.canCapture(piece) == false)
			endTurn();
	}

	private void endTurn() {
		if (remainingpieces(currentPlayer.getColor()) == 0 || board.canMakeMove(currentPlayer.getColor()) == false)
			onLose(currentPlayer);

		if (remainingpieces(opponentPlayer.getColor()) == 0 || board.canMakeMove(opponentPlayer.getColor()) == false)
			onLose(opponentPlayer);

		Player temp;
		temp = currentPlayer;
		currentPlayer = opponentPlayer;
		opponentPlayer = temp;

	}

	public void onLose(Player player) {
		if (player.getColor() == Color.BLACK)
			listener.GameOver(Color.WHITE);
		else
			listener.GameOver(Color.BLACK);
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getOpponentPlayer() {
		return this.opponentPlayer;
	}

	public int remainingpieces(Color color) {
		int c = 0;
		for (int i = 0; i < (board.getPieces()).size(); i++) {
			if ((board.getPieces().get(i)).getColor() == color)
				c++;
		}
		return c;
	}

}