package pieces;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Elbow extends Piece {
	
	public Elbow(int r, int c, boolean s) {
		super(r, c, s);
		setImage();
	}
	
	public boolean openingAt(int i) {
		return i == rotation || i == (rotation+1)%4;
	}
	
	protected void setImage() {
		String imgstring = "elbow_" + rotation + "_" + 
				(source ? "source" : (blue ? "blue" : "white"));
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + 
					"/images/" + imgstring + ".png"));
		} catch (IOException ioe) {
			System.err.println("Error getting image " + imgstring);
		}
		repaint();
	}
	
	public String toString() {
		if (blue) {
			switch (rotation) {
			case 0:
				return "╚";
			case 1:
				return "╔";
			case 2:
				return "╗";
			case 3:
				return "╝";
			default:
				return "";
			}
		} else {
			switch (rotation) {
			case 0:
				return "└";
			case 1:
				return "┌";
			case 2:
				return "┐";
			case 3:
				return "┘";
			default:
				return "";
			}
		}
	}

}
