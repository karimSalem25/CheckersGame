package entity.pieces;
public class Location {
	private int row;
	private int column;
	
	public Location(int row, int col) 
	{
		this.row = row;
		this.column = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return column;
	}
	
	public void setCol(int col) {
		this.column = col;
	}
	
	public String toString () {
		return "Row " + this.row + "column " + this.column;
		
	}
	public boolean equals(Object obj)
	{
		Location newObj = (Location) obj;
		return (newObj.row == (this.row) && newObj.column == (this.column));
	}

	public void setLocation(Location location) 
	{
		this.row = location.getRow();
		this.column = location.getCol();
	}

}
