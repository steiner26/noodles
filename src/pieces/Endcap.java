package pieces;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Endcap extends Piece {
	
	public Endcap(int r, int c, boolean s) {
		super(r, c, s);
		setImage();
	}
	
	protected void setImage() {
		String imgstring = "endcap_" + rotation + "_" + 
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
		return i == rotation;
	}

	public String toString() {
		if (blue) {
			switch (rotation) {
			case 0:
				return "ü";
			case 1:
				return "{";
			case 2:
				return "ñ";
			case 3:
				return "}";
			default:
				return "";
			}
		} else {
			switch (rotation) {
			case 0:
				return "u";
			case 1:
				return "[";
			case 2:
				return "n";
			case 3:
				return "]";
			default:
				return "";
			}
		}
	}

}
