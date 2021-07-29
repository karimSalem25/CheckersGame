package entity.pieces;
import java.util.ArrayList;

import entity.player.*;

public class King extends Piece {

	public King(Location location, Color color, PieceListener listen) 
	{
		super(location, color, listen);
	}
	
	public King(int row, int column, Color color, PieceListener listen) {
		
		super(row, column, color, listen);
	}
	
	public boolean equals(Object obj) 
	{
		if( obj instanceof Pawn)
			return false;
		
		King newObj = (King) obj;
		return newObj.getLocation().equals(this.getLocation()) && newObj.getColor() == (this.getColor());
	}
	public ArrayList<Location> possibleMoves() 
	{
		ArrayList<Location> Moves = new ArrayList<Location>();

		    Location move1 = new Location(this.getRow() + 1, this.getCol() +1);
		    if(move1.getRow() < 8 && move1.getCol() < 8)
		    	Moves.add(move1);
		    
		    Location move2 = new Location(this.getRow() + 1, this.getCol() -1);
		    if(move2.getRow() < 8 && move2.getCol()>= 0)
		    	Moves.add(move2);
		    
		    Location move3 = new Location(this.getRow() -1, this.getCol() -1);
		    if(move3.getRow() >= 0 && move3.getCol() >= 0)
		    	Moves.add(move3);
		    
		    Location move4 = new Location(this.getRow() -1, this.getCol() +1);
		    if(move4.getRow() >= 0 && move4.getCol() < 8)
		    	Moves.add(move4);
		    
		    return Moves;
	}
}
