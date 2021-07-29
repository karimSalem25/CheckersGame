package view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import Engine.Game;
import entity.pieces.Location;
import entity.pieces.Pawn;
import exceptions.CheckersException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI implements GameListener, ActionListener {
	private MyObject LastClickedPiece;
	private Game game;
	private String title;
	private Tile theoldtile;
	private static JFrame Frame;
	private ArrayList<Tile> Tiles = new ArrayList<>();
	private ArrayList<MyObject> objects = new ArrayList<>();
	private final int col = 600;
	private final int row = 600;

	public GUI(String title) {
		this.title = title;
		this.game = new Game(this);
		Create();
	}

	public void Create() {
		Color mynewColor = new Color(235, 39, 39);
		Color mynewColor2 = new Color(200, 200, 200);

		Frame = new JFrame(title);
		Frame.setLayout(new GridLayout(8, 8));
		Frame.setSize(row, col);
		Frame.setLocationRelativeTo(null);
		Frame.getContentPane();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setVisible(true);
		Frame.setResizable(false);
		Frame.getRootPane().setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.white));

		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {

				Location l = new Location(i, j);
				Tile tile = new Tile(l);
				tile.addActionListener(this);
				Tiles.add(tile);

				if ((i + j) % 2 == 0) {
					tile.setBackground(mynewColor2);
				} else
					tile.setBackground(mynewColor);

				Frame.add(tile);

				for (int k = 0; k < game.getPieces().size(); k++) {

					if (game.getPieces().get(k).getLocation().getRow() == i
							&& game.getPieces().get(k).getLocation().getCol() == j) {
						Location loc = new Location(i, j);
						MyObject object = new MyObject(game.getPieces().get(k), loc);
						object.addActionListener(this);
						objects.add(object);
						object.setOpaque(true);
						tile.add(object);
					}
				}

			}
		}

	}

	public void playSound(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public Tile getTileAt(Location l) {
		for (int i = 0; i < this.Tiles.size(); i++) {
			if ((Tiles.get(i).getnewLocation()).getRow() == l.getRow()
					&& (Tiles.get(i).getnewLocation()).getCol() == l.getCol())
				return Tiles.get(i);
		}
		return null;
	}

	public MyObject getObjectAt(Location l) {
		for (int i = 0; i < this.objects.size(); i++) {
			if ((objects.get(i).getnewLocation()).getRow() == l.getRow()
					&& (objects.get(i).getnewLocation()).getCol() == l.getCol())
				return objects.get(i);
		}
		return null;
	}

	public void GameOver(entity.pieces.Color color) {
		if (color.equals(entity.pieces.Color.BLACK)) {
			playSound("WinSound.wav");
			JOptionPane.showMessageDialog(Frame, ("BLACK WINS"));
			System.exit(0);
		} else {
			playSound("WinSound.wav");
			JOptionPane.showMessageDialog(Frame, ("WHITE WINS"));
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		new GUI("Checkers");
		Frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		Object obj = event.getSource();

		if (LastClickedPiece == null) {
			LastClickedPiece = (MyObject) obj;
			theoldtile = getTileAt(LastClickedPiece.getnewLocation());
		}

		else {
			Tile thenewtile = (Tile) obj;
			try {
				game.Play(LastClickedPiece.piece, thenewtile.getnewLocation());

				if ((thenewtile.getnewLocation().getRow() == LastClickedPiece.getnewLocation().getRow() + 2
						&& (thenewtile.getnewLocation().getCol() == LastClickedPiece.getnewLocation().getCol() + 2))) {
					MyObject victim = (getObjectAt(new Location(LastClickedPiece.getnewLocation().getRow() + 1,
							LastClickedPiece.getnewLocation().getCol() + 1)));

					Tile victimtile = (getTileAt(new Location(LastClickedPiece.getnewLocation().getRow() + 1,
							LastClickedPiece.getnewLocation().getCol() + 1)));

					victimtile.remove(victim);
					objects.remove(victim);
					Frame.repaint();
					Frame.revalidate();
				}

				if ((thenewtile.getnewLocation().getRow() == LastClickedPiece.getnewLocation().getRow() + 2
						&& (thenewtile.getnewLocation().getCol() == LastClickedPiece.getnewLocation().getCol() - 2))) {
					MyObject victim = (getObjectAt(new Location(LastClickedPiece.getnewLocation().getRow() + 1,
							LastClickedPiece.getnewLocation().getCol() - 1)));

					Tile victimtile = (getTileAt(new Location(LastClickedPiece.getnewLocation().getRow() + 1,
							LastClickedPiece.getnewLocation().getCol() - 1)));

					victimtile.remove(victim);
					objects.remove(victim);
					Frame.repaint();
					Frame.revalidate();
				}

				if ((thenewtile.getnewLocation().getRow() == LastClickedPiece.getnewLocation().getRow() - 2
						&& (thenewtile.getnewLocation().getCol() == LastClickedPiece.getnewLocation().getCol() + 2))) {
					MyObject victim = (getObjectAt(new Location(LastClickedPiece.getnewLocation().getRow() - 1,
							LastClickedPiece.getnewLocation().getCol() + 1)));

					Tile victimtile = (getTileAt(new Location(LastClickedPiece.getnewLocation().getRow() - 1,
							LastClickedPiece.getnewLocation().getCol() + 1)));

					victimtile.remove(victim);
					objects.remove(victim);
					Frame.repaint();
					Frame.revalidate();
				}

				if ((thenewtile.getnewLocation().getRow() == LastClickedPiece.getnewLocation().getRow() - 2
						&& (thenewtile.getnewLocation().getCol() == LastClickedPiece.getnewLocation().getCol() - 2))) {
					MyObject victim = (getObjectAt(new Location(LastClickedPiece.getnewLocation().getRow() - 1,
							LastClickedPiece.getnewLocation().getCol() - 1)));

					Tile victimtile = (getTileAt(new Location(LastClickedPiece.getnewLocation().getRow() - 1,
							LastClickedPiece.getnewLocation().getCol() - 1)));

					victimtile.remove(victim);
					objects.remove(victim);
					Frame.repaint();
					Frame.revalidate();
				}

				if (thenewtile.getnewLocation().getRow() == 7
						&& (LastClickedPiece.piece.getColor().equals(entity.pieces.Color.WHITE))
						&& LastClickedPiece.piece instanceof Pawn) {
					LastClickedPiece.setIcon(
							MyObject.resizeIcon(new ImageIcon(GUI.class.getResource("white_cro.png")), 65, 65));
					LastClickedPiece.piece = game.getPieces().get(game.getPieces().size() - 1);
				}
				if (thenewtile.getnewLocation().getRow() == 0
						&& (LastClickedPiece.piece.getColor().equals(entity.pieces.Color.BLACK))
						&& LastClickedPiece.piece instanceof Pawn) {
					LastClickedPiece.setIcon(MyObject
							.resizeIcon(new ImageIcon(GUI.class.getResource("checkers-king-png-11.png")), 65, 65));
					LastClickedPiece.piece = game.getPieces().get(game.getPieces().size() - 1);
				}

				thenewtile.add(LastClickedPiece);
				theoldtile.remove(getObjectAt(LastClickedPiece.getnewLocation()));
				LastClickedPiece.setnewLocation(thenewtile.getnewLocation());
				playSound("ClickSound.wav");
				LastClickedPiece = null;
				Frame.repaint();
				Frame.revalidate();
			}

			catch (CheckersException e) {
				playSound("errorSound.wav");
				JOptionPane.showMessageDialog(Frame, e.getMessage());
				LastClickedPiece = null;
			}

		}
	}
}
