package src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Shape;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {

	public GameWindow() {
		JFrame frame = new JFrame("Graphics Test");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(600, 600);
	    frame.setLocationRelativeTo(null);

	    frame.setVisible(true);
	    
	    ImageIcon playImage = new ImageIcon("src/startbutton260260.jpg");
	    JButton jb = new JButton("play", playImage);
    	jb.setBounds(200, 200, 260, 260);
    	frame.add(jb);
	}
	
	public static void main(String args[]) {
		GameWindow gw = new GameWindow();
		
	}
}
