package tocsv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Imgtocsv {
	
	public static void main(String[] args) {
		toCSV("IMG_0739.png", 5, 5);
	}
	
	public static void toCSV(String imgname, int rows, int cols) {
		
		//Get the imgae file and create a subimage for just the board
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + "/" + imgname));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		BufferedImage boardimg = image.getSubimage(50, 342, 650, 650);
		System.out.println(boardimg);
		
		//Create a new csv for the board
		File file = new File(System.getProperty("user.dir") +  "/game0739.csv");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		//Create a PrintWriter to write to the csv
		Path out = file.toPath();
		PrintWriter pr = null;
		try {
			pr = new PrintWriter(Files.newBufferedWriter(out), true);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		//Scan through the image and create the lines to be written
		int background = boardimg.getRGB(1, 1);
		int sourcerow = 0; 
		int sourcecol = 0;
		String[] lines = new String[rows];
		for (int i=0; i<rows; i++) {
			String rowline = "";
			int y = 65 + (i*130);
			for (int j=0; j<cols; j++) {
				int x = 65 + (j*130);
				boolean top = boardimg.getRGB(x, y-60) != background;
				boolean right = boardimg.getRGB(x+60, y) != background;
				boolean bottom = boardimg.getRGB(x, y+60) != background;
				boolean left = boardimg.getRGB(x-60, y) != background;
				rowline += decodePiece(top, right, bottom, left) + ",";
				if (boardimg.getRGB(x, y) == background) {
					sourcerow = i;
					sourcecol = j;
				}
			}
			lines[i] = rowline;
		}
		
		//Write to the csv
		pr.println(rows + "," + cols + "," + sourcerow + "," + sourcecol + ",");
		for (String lin : lines) {
			pr.println(lin);
		}
		pr.close();
		
	}
	
	
	private static String decodePiece(boolean... args) {
		assert args.length == 4;
		int sum = 0;
		for (boolean b : args) {
			sum += b ? 1 : 0;
		}
		if (sum == 1) {return "endcap";}
		else if (sum == 3) {return "tshape";}
		else if ((args[0] && args[2]) || (args[1] && args[3])) {return "line";}
		else {return "elbow";}
	}
	
	
}
