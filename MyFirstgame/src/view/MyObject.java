package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import entity.pieces.*;

public class MyObject extends JButton {
	public Piece piece;
	private ImageIcon icon;
	private Location location;

	public MyObject(Piece p, Location location) {
		this.location = location;
		this.piece = p;

		if((piece.getColor()).equals(entity.pieces.Color.BLACK))
			this.icon = new ImageIcon((GUI.class.getResource("black_man.png"))); 
		else
			this.icon = new ImageIcon((GUI.class.getResource("white_man.png")));

	    this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setBackground(new Color(89,192,221));
		this.setIcon(resizeIcon(this.icon, 65,65));
	}

	public static Icon resizeIcon(ImageIcon icon, int resizedW, int resizedH) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedW, resizedH, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public Location getnewLocation() {
		return this.location;
	}

	public Location setnewLocation(Location location) {
		return this.location = location;
	}
}
