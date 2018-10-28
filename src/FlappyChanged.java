/*import java.awt.Color;
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
//GETTING RID OF IT
public class FlappyChanged implements ActionListener, MouseListener, KeyListener {

	public static FlappyChanged FlappyChanged;

	public static Board board;
	public final int WIDTH = 800, HEIGHT = 800;
	public static final int points = 100;
	public static final int diameter = 10;
	Sheep[] sheepArr = new Sheep[points];

	public int ticks;

	public Random rand;
	

	public FlappyChanged() {
		JFrame jframe = new JFrame();
		// Timer timer = new Timer(20, this);
		Timer timer = new Timer(17, this);

		renderer = new Renderer();
		rand = new Random();
		
		for(int i = 0; i < points; i++) {
			
			//pX.add(rand.nextDouble() * WIDTH);
			//pY.add(rand.nextDouble() * HEIGHT);
			double randX = rand.nextDouble() * WIDTH;
			double randY = rand.nextDouble() * HEIGHT;
			sheepArr[i] = new Sheep(randX, randY);
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
	ArrayList<TowerRepulsor> repulsor = new ArrayList<TowerRepulsor>();

	@Override
	public void actionPerformed(ActionEvent e) {
		board.tick();
		renderer.repaint();
	}

	public void repaint(Graphics g) {
		
		
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//paints background
		//everything else should come after this
		
		
		
		drawGoal(g);
		g.setColor(Color.blue);
		
		for(int i = 0; i < sheepArr.length; i++) {
			if(sheepArr[i].destroyed==true)
				g.fillOval( (int) Math.round(sheepArr[i].x) , (int) Math.round(sheepArr[i].y), diameter, diameter);
		}
//		g.setColor(Color.orange);
//		g.fillRect(0, HEIGHT - 120, WIDTH, 120);
		
		drawScore(g);
	}

	private void drawScore(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("SanSerif", Font.PLAIN, 20));
		g.drawString("score is: " + score, 0, 20);
		
	}

	private void drawGoal(Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillOval(goalCenterX-goalRadius, goalCenterY-goalRadius, goalRadius*2, goalRadius*2);
		g.setColor(Color.WHITE);
		g.fillOval(goalCenterX-smallGoalRadius, goalCenterY-smallGoalRadius, smallGoalRadius*2, smallGoalRadius*2);
		
	}

	public static void main(String[] args) {
		FlappyChanged = new FlappyChanged();
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		
//		int x = e.getX();
//		int y = e.getY();
//		if(repulsor.size() == 0) {
//			repulsor.add(new TowerRepulsor(x, y, 5000));
//		} else {
//			repulsor.set(0, new TowerRepulsor(x, y, 5000));
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//	}
//
//	@Override
//	public void keyTyped(KeyEvent e) {
//
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//
//	}

}*/
