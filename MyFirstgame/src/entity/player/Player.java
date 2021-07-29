package entity.player;
import Engine.*;
import entity.pieces.Color;

public class Player implements PieceListener {
	private Color color;
	private int remainingPieces;
	private PlayerListener listener;
	
	public Player(Color color, PlayerListener listener) {
		
		this.color=color;
		this.listener = listener;
		this.remainingPieces = 12;
	}

	public Color getColor() {
		return color;
	}
	
	public void onPieceCaptured() {
		this.remainingPieces = this.remainingPieces - 1;
	}
}
