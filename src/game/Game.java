package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;

import gui.GamePanel;
import pieces.Piece;

public class Game {

	static final int SIDE_TOP = 0;
	static final int SIDE_RIGHT = 1;
	static final int SIDE_BOTTOM = 2;
	static final int SIDE_LEFT = 3;

	static final boolean BLUE = true;
	static final boolean WHITE = false;

	private Board board;

	public Game(int rows, int cols) {

	}

	/** Returns a new Game from a premade Board. */
	public Game(Board b) {
		board = b;
		initialize();
	}

	/** Initialize the game by adding the mouse listeners to the Pieces and 
	 * changing the color of any Piece connected to the source to blue. */
	private void initialize() {
		checkPath();
		for (Piece p : board) {
			p.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					p.rotate();
					checkPath();
				}
			});
		}
	}

	/** Creates a new game from a .csv file. The first row should contain the 
	 * number of rows, columns and coordinates of the source piece. The
	 * following rows contain the data for the board, using the words 
	 * "endcap", "elbow", "line" and "tshape" to describe the pieces. */
	public static Game gameFromCSV(Path p) {
		return new Game(Board.boardFromCSV(p));
	}

	/** Returns a new GamePanel from this Game. */
	public GamePanel createPanel() {
		return new GamePanel(board);
	}

	/** Change the color of any Piece connected to the source to blue, and all
	 * other Pieces to white. */
	private void checkPath() {
		LinkedList<Piece> toprocess = new LinkedList<Piece>(); 
		HashSet<Piece> path = new HashSet<Piece>();
		toprocess.add(board.source());
		path.add(board.source());
		while (!toprocess.isEmpty()) {
			Piece current = toprocess.pop();
			for (Piece neighbor : board.neighbors(current)) {
				if (!path.contains(neighbor) && connected(current, neighbor)) {
					neighbor.setColor(BLUE);
					toprocess.add(neighbor);
					path.add(neighbor);
				}
			}
		}
		HashSet<Piece> offpath = allPieces();
		offpath.removeAll(path);
		if (offpath.isEmpty()) {
			//THE GAME IS OVER!!
		} else {
			for (Piece p : offpath) {
				p.setColor(WHITE);
			}
		}
	}

	/** Returns a Set with all Pieces in the board of this game. */
	private HashSet<Piece> allPieces() {
		HashSet<Piece> result = new HashSet<Piece>();
		for (Piece p : board) {
			result.add(p);
		}
		return result;
	}

	/** Returns the opposite side a given side */
	private int opposite(int i) {
		return (i+2)%4;
	}

	/** Returns whether or not two pieces are connected. 
	 * Parameter side is the side of p1 on which p2 neighbors it. */
	private boolean connected(Piece p1, Piece p2) {
		int side = touchingSide(p1, p2);
		return p1.openingAt(side) && p2.openingAt(opposite(side));
	}

	/** Returns the side of the first piece on which the second piece neighbors it. */
	private int touchingSide(Piece p1, Piece p2) {
		assert Math.abs(p1.row()-p2.row()) == 1 ^ Math.abs(p1.column()-p2.column()) == 1 : 
			"These pieces are not touching: " + p1.posString() + " & " + p2.posString();
		if (p1.row()-p2.row() == 1) {
			return SIDE_TOP;
		} else if (p1.row()-p2.row() == -1) {
			return SIDE_BOTTOM;
		} else if (p1.column()-p2.column() == 1) {
			return SIDE_LEFT;
		} else {
			return SIDE_RIGHT;
		}
	}

	public String toString() {
		return board.toString();
	}

}
