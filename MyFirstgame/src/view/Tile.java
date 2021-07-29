package view;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import entity.pieces.Location;

public class Tile extends JButton{
	Location target;
	
	public Tile(Location location) {
		this.target = location;
		
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBorderPainted(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}	
	public Location getnewLocation() {
		return this.target;
	}

}