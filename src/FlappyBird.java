import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {

	public static FlappyBird flappyBird;

	public final int WIDTH = 800, HEIGHT = 800;
	public static final int points = 100;
	double[] forceX = new double[points];
	double[] forceY = new double[points];
	public Renderer renderer;

	public ArrayList<Double> pY;
	public ArrayList<Double> pX;

	public int ticks;

	public Random rand;

	public FlappyBird() {
		JFrame jframe = new JFrame();
		// Timer timer = new Timer(20, this);
		Timer timer = new Timer(7, this);

		renderer = new Renderer();
		rand = new Random();
		
		pY = new ArrayList<Double>();
		pX = new ArrayList<Double>();
		for(int i = 0; i < points; i++) {
			pX.add(rand.nextDouble() * WIDTH);
			pY.add(rand.nextDouble() * HEIGHT);
		}
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		



		timer.start();
	}
	ArrayList<Double> repulsorX = new ArrayList<Double>();
	ArrayList<Double> repulsorY = new ArrayList<Double>();

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0;i < points; i++) {
			forceX[i] = 0;
			forceY[i] = 0;
		}
		for(int i = 0; i < repulsorY.size(); i++) {
			for(int j = 0; j < points; j++) {
				double r = Math.sqrt((pY.get(j) - repulsorY.get(i)) * (pY.get(j) - repulsorY.get(i)) + (pX.get(j) - repulsorX.get(i)) * (pX.get(j) - repulsorX.get(i)));
				double theta = Math.atan2(pY.get(j) - repulsorY.get(i), pX.get(j) - repulsorX.get(i));
				//forceY[j] += 200000/(r*r)*Math.sin(theta);
				//forceX[j] += 200000/(r*r)*Math.cos(theta);
				forceY[j] += 5000.0/(r*Math.sqrt(r))*Math.sin(theta);
				forceX[j] += 5000.0/(r*Math.sqrt(r))*Math.cos(theta);
				//console.log(r);
			}
		}
		for(int i = 0; i < points; i++) {
			pY.set(i, pY.get(i) + 0.3*forceY[i]);
			pX.set(i, pX.get(i) + 0.3*forceX[i]);
			//the value 0.5 is multiplied in as well to lessen the effect of the repulsor
		}
		
		renderer.repaint();
	}

	public void repaint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.blue);
		
		for(int i = 0; i < points; i++) {
			g.fillOval( (int) Math.round(pX.get(i)) , (int) Math.round(pY.get(i)), 10, 10);
		}
//		g.setColor(Color.orange);
//		g.fillRect(0, HEIGHT - 120, WIDTH, 120);
	}

	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX();
		int y = e.getY();
		if(repulsorY.size() == 0) {
			repulsorY.add(0.0);
			repulsorX.add(0.0);
		}
		repulsorX.set(0, Double.valueOf(x));
		repulsorY.set(0, Double.valueOf(y));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

}
