package game;

import java.nio.file.Path;
import java.nio.file.Paths;

import gui.BaseFrame;

public class Controller {
	
	public static void main(String[] args) {
		Path p = Paths.get("game0739.csv");
		BaseFrame frame = new BaseFrame(Game.gameFromCSV(p).createPanel());
	}
	
	/** Basic structure for the controller class:
	 * 
	 * This class will act as the listener for the BaseFrame and load levels
	 * when they are clicked on. Once fully impemented, the BaseFrame will
	 * start with a level selector in the center, and when clicked will
	 * call a method in this class to create a new GamePanel and add
	 * it to the BaseFrame by calling GameFrame.setPanel(). 
	 * 
	 * The controller has no need to know any information about the current 
	 * game, except when it is done (which might be a challenge). One solution
	 * could be to have the Game have the controller as a field, and the only
	 * public methods in Controller is to load the next level and to go back
	 * to the menu. */
	
	
	
	/** Various notes for the game solver:
	 * 
	 * -If an endcap has a set piece facing it, it must always connect to it
	 * -If and endcap has 3 surrounding set piece not facing it, it will face 
	 * in the fourth direction.
	 * 
	 * 
	 * -If an elbow has two set pieces on two adjecant sides and cannot
	 * connected to either, then its position can be set.
	 * -If an elbow has a set piece facing it and a set piece not facing it on 
	 * an adjecant side, its position can be set.
	 * -If an elbow has 2 adjecant set pieces facing it, its position can be set.
	 * 
	 * 
	 * -If a line has a set piece next to it, it can always be set.
	 * if that piece can connect to the line, then rotate the line to connect
	 * if it cannot connect, turn the line to not connect.
	 * 
	 * 
	 * -If a tshape has a set piece not facing it, it must face away from it.
	 * -If an elbow has 3 adjecant set pieces facing it, its position can be set.
	 *
	 * 
	 * */
 	
}
