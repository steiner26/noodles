package pieces;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Tshape extends Piece {

	public Tshape(int r, int c, boolean s) {
		super(r, c, s);
		setImage();
	}
	
	protected void setImage() {
		String imgstring = "tshape_" + rotation + "_" + 
				(source ? "source" : (blue ? "blue" : "white"));
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + 
					"/images/" + imgstring + ".png"));
		} catch (IOException ioe) {
			System.err.println("Error getting image " + imgstring);
		}
		repaint();
	}

	public boolean openingAt(int i) {
		return i == rotation || i == (rotation+1)%4 || i == (rotation+2)%4;
	}

	public String toString() {
		if (blue) {
			switch (rotation) {
			case 0:
				return "╠";
			case 1:
				return "╦";
			case 2:
				return "╣";
			case 3:
				return "╩";
			default:
				return "";
			}
		} else {
			switch (rotation) {
			case 0:
				return "├";
			case 1:
				return "┬";
			case 2:
				return "┤";
			case 3:
				return "┴";
			default:
				return "";
			}
		}
	}

}
