package pieces;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Piece extends JPanel {
	
	protected boolean blue;
	protected boolean source;
	protected int rotation;	
	protected int row;
	protected int col;
	protected BufferedImage image;
	
	/** Constructor for a Piece, used as a base constructor for all subclasses. */
	public Piece(int row, int column, boolean source) {
		this.source = source;
		blue = source;
		rotation = new Random().nextInt(4);
		this.row = row;
		col = column;
	}
	
	/** Returns the row of this Piece */
	public int row() {return row;}
	
	/** Returns the column of this Piece*/
	public int column() {return col;}
	
	/** Returns the color of this piece. Possible values are Game.BLUE (true) 
	 * and Game.WHITE (false). */
	public boolean color() {return blue;}
	
	/** Returns whether this Piece is a source. */
	public boolean isSource() {return source;}
	
	/** Returns the rotation of this Piece. Values range from 0 to 3. */
	public int rotation() {return rotation;}
	
	/** Returns whether this Piece has an opening at a certain side. Parameter
	 * side should be one of Game.SIDE_TOP, Game.SIDE_RIGHT, Game.SIDE_BOTTOM 
	 * and Game.SIDE_LEFT, corresponding to numbers 0-3 respectively. */
	public abstract boolean openingAt(int side);
	
	/** Set the current image of this Piece based on its type, rotation 
	 * and color. */
	protected abstract void setImage();
	
	/** Rotate this piece by 90˚ clockwise and update its image to match
	 * its new state. */
	public void rotate() {
		rotation = (rotation + 1) % 4; 
		setImage();
	}
	
	/** Set the color of this Piece to the value of parameter newcolor. 
	 * Possible values of newcolor are Game.BLUE (true) and Game.WHITE (false). */
	public void setColor(boolean newcolor) {
		if (newcolor != blue) {
			blue = newcolor; 
			setImage();
		}
	}
	
	/** Returns a string representing the position of this Piece on the board 
	 * in the format of (row, column). */
	public String posString() {
		return "(" + row() + ", " + column() + ")";
		}
	
	public boolean equals(Object ob) {
		if (!getClass().isInstance(ob)) {
			return false;
		}
		Piece p = (Piece) ob;
		return this.row() == p.row() && this.column() == p.column();
	}
	
	public int hashCode() {
		return row+col;	
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
}
