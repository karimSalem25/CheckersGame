package entity.pieces;
import java.util.ArrayList;
import entity.player.*;

public abstract class Piece {
	private Location location;
	private Color color;
	private PieceListener listener;
	
	public Piece(Location location, Color color, PieceListener listener) 
	{
		this.location = location;
		this.color = color;
		this.listener = listener;
	}
	public Piece(int row, int col, Color color, PieceListener listener)
	{
		this.location = new Location(row,col);
		this.color = color;
		this.listener = listener;
	}
	public Color getColor() {
		return color;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public int getCol() {
		return this.location.getCol();
	}
	public int getRow() {
		return this.location.getRow();
	}
	
	public void Captured() 
	{
		//onPieceCaptured(listener);
	}
	
	public King upgrade() {
		King TheKing = new King(this.location, this.color , this.listener);
		return TheKing;
	}
	
	public abstract ArrayList<Location> possibleMoves();
	
	public boolean equals(Object obj)
	{
		Location newObj = (Location) obj;
		return newObj.equals(this.location);
	}

}
