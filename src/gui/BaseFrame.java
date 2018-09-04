package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BaseFrame extends JFrame {
	
	private GamePanel gp;
	
	/** Constructs a new default BaseFrame with no central panel */
	public BaseFrame() {
		//null is a placeholder for the layout of the baseframe
		super("Noodles", null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 500));
		setVisible(true);
	}
	
	/** Constructs a new BaseFrame with a premade GamePanel */
	public BaseFrame(GamePanel panel) {
		super("Noodles", null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 500));
		gp = panel;
		getContentPane().add(gp);
		pack();
		repaint();
		setVisible(true);
	}
	
	
	public void setPanel(GamePanel newpanel) {
		getContentPane().remove(gp);
		getContentPane().add(newpanel);
		gp = newpanel;
		pack();
		repaint();
	}

}
