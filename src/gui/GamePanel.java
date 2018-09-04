package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

import game.Board;
import pieces.Piece;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	/** Constructs a GamePanel with a given number of rows and columns. */
	public GamePanel(int rows, int cols) {
		super(new GridLayout(rows, cols));
	}
	
	/** Constructs a GamePanel from a premade board of Pieces */
	public GamePanel(Board board) {
		super(new GridLayout(board.rows(), board.columns()));
		setBoard(board);
	}
	
	/** Change the board displayed on this GamePanel. */
	public void setBoard(Board board) {
		removeAll();
		GridLayout layout = (GridLayout)getLayout();
		layout.setRows(board.rows());
		layout.setColumns(board.columns());
		for (Piece p : board) {
			add(p);
		}
	}

}
