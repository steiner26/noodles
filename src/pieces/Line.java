package pieces;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Line extends Piece {

	public Line(int r, int c, boolean s) {
		super(r, c, s);
		setImage();
	}
	
	protected void setImage() {
		String imgstring = "line_" + rotation%2 + "_" + 
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
		return i == rotation || i == (rotation+2)%4;
	}

	public String toString() {
		if (blue) {
			switch (rotation%2) {
			case 0:
				return "║";
			case 1:
				return "═";
			default:
				return "";
			}
		} else {
			switch (rotation%2) {
			case 0:
				return "│";
			case 1:
				return "─";
			default:
				return "";
			}
		}
	}

}
