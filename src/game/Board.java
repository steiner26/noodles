package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import pieces.Elbow;
import pieces.Endcap;
import pieces.Line;
import pieces.Piece;
import pieces.Tshape;

public class Board implements Iterable<Piece> {
	
	protected Piece[][] myboard;
	private int rows;
	private int cols;
	private Piece source;

	public Board(Piece[][] board, int sourcerow, int sourcecol) {
		assert board.length > 1 && board[0].length > 1 : 
			"Board is not of a valid size";
		myboard = board;
		rows = board.length;
		cols = board[0].length;
		source = board[sourcerow][sourcecol];
	}
	
	/** Returns the number of rows in this Board. */
	public int rows() {return rows;}
	
	/** Returns the number of columns in this Board. */
	public int columns() {return cols;}
	
	/** Returns the source Piece of this board. */
	public Piece source() {return source;}
	
	/** Returns the neighboring Pieces of a piece. */
	ArrayList<Piece> neighbors(Piece p) {
		ArrayList<Piece> result = new ArrayList<Piece>();
		Piece top = myboard[Math.max(p.row()-1, 0)][p.column()];
		if (!top.equals(p)) result.add(top);
		Piece right = myboard[p.row()][Math.min(p.column()+1, rows-1)];
		if (!right.equals(p)) result.add(right);
		Piece left = myboard[p.row()][Math.max(p.column()-1, 0)];
		if (!left.equals(p)) result.add(left);
		Piece bottom = myboard[Math.min(p.row()+1, cols-1)][p.column()];
		if (!bottom.equals(p)) result.add(bottom);
		return result;
	}
	
	public String toString() {
		String result = "";
		for (Piece[] row : myboard) {
			String rowstring = "";
			for (Piece p : row) {
				rowstring += p.toString();
//				rowstring += p.posString() + " ";
//				rowstring += p.rotation();
//				rowstring += p.isSource() ? 1 : 0;
			}
			result += rowstring + "\n";
		}
		return result;
	}
	
	@Override
	public Iterator<Piece> iterator() {
		return new BoardIterator(this);
	}
	
	/** Creates a new Board from a .csv file. The first row should contain the 
	 * number of rows, columns and coordinates of the source piece. The
	 * following rows contain the data for the board, using the words 
	 * "endcap", "elbow", "line" and "tshape" to describe the pieces. */
	public static Board boardFromCSV(Path p) {
		BufferedReader br;
		String line ;
		try {
			br = Files.newBufferedReader(p);

			//read the first line, which contains info about the board
			line = br.readLine();
			String[] data = line.split(",");
			int rows = Integer.parseInt(data[0]);
			int cols = Integer.parseInt(data[1]);
			int sourcerow = Integer.parseInt(data[2]);
			int sourcecol = Integer.parseInt(data[3]);

			//construct the board from the csv
			Piece[][] board = new Piece[rows][];
			for (int i=0; i<rows; i++) board[i] = new Piece[cols];
			for (int i=0; i<rows; i++) {
				line = br.readLine();
				String[] pieces = line.split(",");
				for (int j=0; j<cols; j++) {
					Piece newpiece = null;
					boolean source = (sourcerow == i && sourcecol == j);
					switch (pieces[j]) {
					case "endcap":
						newpiece = new Endcap(i, j, source);
						break;
					case "elbow":
						newpiece = new Elbow(i, j, source);
						break;
					case "line":
						newpiece = new Line(i, j, source);
						break;
					case "tshape":
						newpiece = new Tshape(i, j, source);
						break;
					}
					board[i][j] = newpiece;
				}
			}
			return new Board(board, sourcerow, sourcecol);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

final class BoardIterator implements Iterator<Piece> {
	
	private Board board;
	private int currentrow;
	private int currentcol;
	
	BoardIterator(Board b) {
		board = b;
	}

	@Override
	public boolean hasNext() {
		return currentrow < board.rows();
	}

	@Override
	public Piece next() {
		Piece toreturn = board.myboard[currentrow][currentcol];
		currentcol = (currentcol + 1 ) % board.columns();
		if (currentcol == 0) currentrow++;
		return toreturn;
	}
	
}
