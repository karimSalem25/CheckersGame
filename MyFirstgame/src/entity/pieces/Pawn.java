package entity.pieces;
import java.util.ArrayList;

import entity.player.*;
public class Pawn extends Piece {

	public Pawn(Location location, Color color, PieceListener listener) {
		super(location, color, listener);	
	}
	
	public Pawn(int row, int col, Color color, PieceListener listener) {
		super(row, col, color, listener);
		this.setLocation(new Location(row,col));
	}
	
	public ArrayList<Location> possibleMoves() 
	{
		ArrayList<Location> Moves = new ArrayList<Location>();
		
		if(this.getColor() == Color.WHITE) {
		    Location move1 = new Location(this.getRow() + 1, this.getCol() + 1);
		    if( move1.getRow() < 8 && move1.getCol() < 8)
		    	Moves.add(move1);
		    
		    Location move2 = new Location(this.getRow() + 1, this.getCol() - 1);
		    if(move2.getRow() < 8 && move2.getCol() >= 0)
		    	Moves.add(move2);
		    }
		
		if(this.getColor() == Color.BLACK){
			 Location move3 = new Location(this.getRow() -1, this.getCol() -1);
			 if(move3.getRow()>= 0 && move3.getCol() >= 0)
				 Moves.add(move3);
			 
			 Location move4 = new Location(this.getRow() -1, this.getCol() +1);
			 if(move4.getRow()>= 0 && move4.getCol() < 8)
				 Moves.add(move4);
		}
		return Moves;
	}
	
	public boolean equals(Object obj)
	{
		if( obj instanceof King)
			return false;
		
		Pawn newObj = (Pawn) obj;
		return (newObj.getLocation()).equals(this.getLocation()) && (newObj.getColor()) == (this.getColor());
	}
	
	public String toString() {
		return "location" + this.getLocation() + "color "+ this.getColor();
	}

}
