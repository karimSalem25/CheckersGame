package Engine;

import java.util.ArrayList;
import entity.pieces.*;

public class Board {

	int dimension;
	private ArrayList<Piece> pieces;

	public Board(int dimension, ArrayList<Piece> pieces) {
		this.dimension = dimension;
		this.pieces = pieces;
	}

	public Piece getPieceAt(Location location) {
		for (int i = 0; i < pieces.size(); i++) {
			if ((pieces.get(i)).getLocation().equals(location))
				return pieces.get(i);
		}
		return null;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public ArrayList<Location> possiblePlays(Piece piece) {

		Location DRight = new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() + 1);
		Location DRightCapture = new Location(piece.getLocation().getRow() + 2, piece.getLocation().getCol() + 2);
		Location DLeft = new Location(piece.getLocation().getRow() + 1, piece.getLocation().getCol() - 1);
		Location DLeftCapture = new Location(piece.getLocation().getRow() + 2, piece.getLocation().getCol() - 2);

		Location URight = new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() + 1);
		Location URightCapture = new Location(piece.getLocation().getRow() - 2, piece.getLocation().getCol() + 2);
		Location ULeft = new Location(piece.getLocation().getRow() - 1, piece.getLocation().getCol() - 1);
		Location ULeftCapture = new Location(piece.getLocation().getRow() - 2, piece.getLocation().getCol() - 2);

		ArrayList<Location> Plays = new ArrayList<Location>();

		if (canCapture(piece) == false && piece instanceof Pawn && piece.getColor() == Color.WHITE) {
			if (DRight.getRow() < 8 && DRight.getCol() < 8 && getPieceAt(DRight) == null)
				Plays.add(DRight);

			if (DLeft.getRow() < 8 && DLeft.getCol() >= 0 && getPieceAt(DLeft) == null)
				Plays.add(DLeft);

		}
		if (canCapture(piece) == false && piece instanceof Pawn && piece.getColor() == Color.BLACK) {

			if (URight.getRow() >= 0 && URight.getCol() < 8 && getPieceAt(URight) == null)
				Plays.add(URight);

			if (ULeft.getRow() >= 0 && ULeft.getCol() >= 0 && getPieceAt(ULeft) == null)
				Plays.add(ULeft);

		}

		if (canCapture(piece) == false && piece instanceof King)

		{
			if (URight.getRow() >= 0 && URight.getCol() < 8 && getPieceAt(URight) == null)
				Plays.add(URight);

			if (ULeft.getRow() >= 0 && ULeft.getCol() >= 0 && getPieceAt(ULeft) == null)
				Plays.add(ULeft);

			if (DRight.getRow() < 8 && DRight.getCol() < 8 && getPieceAt(DRight) == null)
				Plays.add(DRight);

			if (DLeft.getRow() < 8 && DLeft.getCol() >= 0 && getPieceAt(DLeft) == null)
				Plays.add(DLeft);

		}

		if (canCapture(piece) == true && piece instanceof Pawn) {

			if (piece.getColor() == Color.BLACK) {

				if (ULeftCapture.getRow() >= 0 && ULeftCapture.getCol() >= 0 && getPieceAt(ULeft) != null
						&& !(getPieceAt(ULeft).getColor().equals(piece.getColor())) && getPieceAt(ULeftCapture) == null)
					Plays.add(ULeftCapture);

				if (URightCapture.getRow() >= 0 && URightCapture.getCol() < 8 && getPieceAt(URight) != null
						&& !(getPieceAt(URight).getColor().equals(piece.getColor()))
						&& getPieceAt(URightCapture) == null)
					Plays.add(URightCapture);
			}

			if (piece.getColor() == Color.WHITE) {

				if (DRightCapture.getRow() < 8 && DRightCapture.getCol() < 8 && getPieceAt(DRight) != null
						&& !(getPieceAt(DRight).getColor().equals(piece.getColor()))
						&& getPieceAt(DRightCapture) == null)
					Plays.add(DRightCapture);

				if (DLeftCapture.getRow() < 8 && DLeftCapture.getCol() >= 0 && getPieceAt(DLeft) != null
						&& !(getPieceAt(DLeft).getColor().equals(piece.getColor())) && getPieceAt(DLeftCapture) == null)
					Plays.add(DLeftCapture);

			}
		}
		if (canCapture(piece) == true && piece instanceof King) {

			if (DRightCapture.getRow() < 8 && DRightCapture.getCol() < 8 && getPieceAt(DRight) != null
					&& !(getPieceAt(DRight).getColor().equals(piece.getColor())) && getPieceAt(DRightCapture) == null)
				Plays.add(DRightCapture);

			if (DLeftCapture.getRow() < 8 && DLeftCapture.getCol() >= 0 && getPieceAt(DLeft) != null
					&& !(getPieceAt(DLeft).getColor().equals(piece.getColor())) && getPieceAt(DLeftCapture) == null)
				Plays.add(DLeftCapture);

			if (ULeftCapture.getRow() >= 0 && ULeftCapture.getCol() >= 0 && getPieceAt(ULeft) != null
					&& !(getPieceAt(ULeft).getColor().equals(piece.getColor())) && getPieceAt(ULeftCapture) == null)
				Plays.add(ULeftCapture);

			if (URightCapture.getRow() >= 0 && URightCapture.getCol() < 8 && getPieceAt(URight) != null
					&& !(getPieceAt(URight).getColor().equals(piece.getColor())) && getPieceAt(URightCapture) == null)
				Plays.add(URightCapture);

		}
		return Plays;
	}

	public boolean movePiece(Piece piece, Location destination) {

		if (destination.getRow() == 0 && piece.getColor() == Color.BLACK && piece instanceof Pawn) {
			pieces.remove(piece);
			piece = piece.upgrade();
			pieces.add(piece);
		}

		if (destination.getRow() == 7 && piece.getColor() == Color.WHITE && piece instanceof Pawn) {
			pieces.remove(piece);
			piece = piece.upgrade();
			pieces.add(piece);
		}

		if ((destination).equals(new Location((piece.getRow() + 2), (piece.getCol() - 2)))) {
			Location capturedpiece = new Location(piece.getRow() + 1, piece.getCol() - 1);
			getPieceAt(capturedpiece).Captured();
			pieces.remove(getPieceAt(capturedpiece));
			piece.setLocation(destination);
			return false;
		}

		if ((destination).equals(new Location((piece.getRow() + 2), (piece.getCol() + 2)))) {
			Location capturedpiece = new Location(piece.getRow() + 1, piece.getCol() + 1);
			getPieceAt(capturedpiece).Captured();
			pieces.remove(getPieceAt(capturedpiece));
			piece.setLocation(destination);
			return false;
		}
		if ((destination).equals(new Location((piece.getRow() - 2), (piece.getCol() - 2)))) {
			Location capturedpiece = new Location(piece.getRow() - 1, piece.getCol() - 1);
			getPieceAt(capturedpiece).Captured();
			pieces.remove(getPieceAt(capturedpiece));
			piece.setLocation(destination);
			return false;
		}
		if ((destination).equals(new Location((piece.getRow() - 2), (piece.getCol() + 2)))) {
			Location capturedpiece = new Location(piece.getRow() - 1, piece.getCol() + 1);
			getPieceAt(capturedpiece).Captured();
			pieces.remove(getPieceAt(capturedpiece));
			piece.setLocation(destination);
			return false;
		}
		piece.setLocation(destination);
		return true;
	}

	public ArrayList<Piece> captureCapable(Color color) {
		ArrayList<Piece> captures = new ArrayList<Piece>();

		for (int i = 0; i < pieces.size(); i++) {
			if (canCapture(pieces.get(i)) == true && ((pieces.get(i)).getColor()).equals(color))
				captures.add(pieces.get(i));
		}
		return captures;
	}

	public boolean canCapture(Piece p) {

		Location DRight = new Location(p.getLocation().getRow() + 1, p.getLocation().getCol() + 1);
		Location DRightCapture = new Location(p.getLocation().getRow() + 2, p.getLocation().getCol() + 2);
		Location DLeft = new Location(p.getLocation().getRow() + 1, p.getLocation().getCol() - 1);
		Location DLeftCapture = new Location(p.getLocation().getRow() + 2, p.getLocation().getCol() - 2);

		Location URight = new Location(p.getLocation().getRow() - 1, p.getLocation().getCol() + 1);
		Location URightCapture = new Location(p.getLocation().getRow() - 2, p.getLocation().getCol() + 2);
		Location ULeft = new Location(p.getLocation().getRow() - 1, p.getLocation().getCol() - 1);
		Location ULeftCapture = new Location(p.getLocation().getRow() - 2, p.getLocation().getCol() - 2);

		if (p instanceof King) {
			if (DRightCapture.getRow() < 8 && DRightCapture.getCol() < 8 && getPieceAt(DRight) != null
					&& !(getPieceAt(DRight).getColor().equals(p.getColor())) && getPieceAt(DRightCapture) == null)
				return true;

			if (DLeftCapture.getRow() < 8 && DLeftCapture.getCol() >= 0 && getPieceAt(DLeft) != null
					&& !(getPieceAt(DLeft).getColor().equals(p.getColor())) && getPieceAt(DLeftCapture) == null)
				return true;

			if (ULeftCapture.getRow() >= 0 && ULeftCapture.getCol() >= 0 && getPieceAt(ULeft) != null
					&& !(getPieceAt(ULeft).getColor().equals(p.getColor())) && getPieceAt(ULeftCapture) == null)
				return true;

			if (URightCapture.getRow() >= 0 && URightCapture.getCol() < 8 && getPieceAt(URight) != null
					&& !(getPieceAt(URight).getColor().equals(p.getColor())) && getPieceAt(URightCapture) == null)
				return true;
		}

		if (p instanceof Pawn) {

			if (p.getColor() == Color.BLACK) {

				if (ULeftCapture.getRow() >= 0 && ULeftCapture.getCol() >= 0 && getPieceAt(ULeft) != null
						&& !(getPieceAt(ULeft).getColor().equals(p.getColor())) && getPieceAt(ULeftCapture) == null)
					return true;

				if (URightCapture.getRow() >= 0 && URightCapture.getCol() < 8 && getPieceAt(URight) != null
						&& !(getPieceAt(URight).getColor().equals(p.getColor())) && getPieceAt(URightCapture) == null)
					return true;

			}
			if (p.getColor() == Color.WHITE)

			{
				if (DRightCapture.getRow() < 8 && DRightCapture.getCol() < 8 && getPieceAt(DRight) != null
						&& !(getPieceAt(DRight).getColor().equals(p.getColor())) && getPieceAt(DRightCapture) == null)
					return true;

				if (DLeftCapture.getRow() < 8 && DLeftCapture.getCol() >= 0 && getPieceAt(DLeft) != null
						&& !(getPieceAt(DLeft).getColor().equals(p.getColor())) && getPieceAt(DLeftCapture) == null)
					return true;
			}
		}

		return false;

	}

	public boolean canMakeMove(Color color) {
		for (int i = 0; i < (this.pieces).size(); i++) {
			if ((this.pieces.get(i).getColor() == color))
				if ((possiblePlays(this.pieces.get(i))) != null)
					return true;
		}
		return false;
	}
}
