import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Board implements ActionListener, MouseListener, KeyListener {
    private ArrayList<Sheep> sheep;
    private ArrayList<Tower> towers;
	public Renderer renderer;
    public int score = 0;
    public Random rand;

    public final int trailLength = 10;
	public final int WIDTH = 800, HEIGHT = 800;
	public int goalCenterX = WIDTH/2;
	public int goalCenterY = HEIGHT/2;
	public int goalRadius = 100;
	public int smallGoalRadius = 70;
	public int diameter = 10;
    
    public Board(int sheepSize){
    	renderer = new Renderer();
		JFrame jframe = new JFrame();
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);
		
		rand = new Random();
		sheep = new ArrayList<Sheep>();
		for(int i = 0; i < sheepSize; i++) {
			double randX = rand.nextDouble() * WIDTH;
			double randY = rand.nextDouble() * HEIGHT;
			sheep.add(new Sheep(randX, randY));
		}
		
		towers = new ArrayList<Tower>();
		towers.add(new TowerRepulsor(100.0, 100.0, 5000.0));
		towers.add(new TowerRepulsor(800.0, 800.0, 5000.0));

		Timer timer = new Timer(17, this);
		timer.start();		
    }

    public void addTower(int[] coordinates, int power){
        towers.add(new TowerRepulsor(coordinates[0], coordinates[1], power));
    }
    
	private void checkSheepGoal() {
		for(int i = 0; i < sheep.size(); i ++) {
			double x = sheep.get(i).x + diameter/4.0;
			double y = sheep.get(i).y + diameter/4.0;  //1/4 of the diameter
			double r = Math.sqrt((x-goalCenterX)*(x-goalCenterX) + (y-goalCenterY)*(y-goalCenterY));
			if(r<=smallGoalRadius && !sheep.get(i).destroyed) {
				sheep.get(i).destroyed = true;
				scoreUp();
			}
			//if sheep is in circle
				//if r < radius
			//get rid of sheep 
			//add score
		}

	}
	private void scoreUp() {
		score++;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.tick();
		renderer.repaint();
	}

	public void repaint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//paints background
		//everything else should come after this
		
		drawGoal(g);
		
		for(int i = 0; i < sheep.size(); i++) {
			for(int j = 0; j < sheep.get(i).trail.size() - 1; j++){
				g.drawLine((int) Math.round(sheep.get(i).trail.get(j)[0]), (int) Math.round(sheep.get(i).trail.get(j)[1]),
							(int) Math.round(sheep.get(i).trail.get(j + 1)[0]), (int) Math.round(sheep.get(i).trail.get(j + 1)[1]));
			}

			g.setColor(Color.blue);
			if(sheep.get(i).destroyed!=true){
				g.fillOval( (int) Math.round(sheep.get(i).x) , (int) Math.round(sheep.get(i).y), diameter, diameter);
			}
		}
		
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
	
    public void tick(){
		checkSheepGoal();
		
        for(int i = 0; i < sheep.size(); i++) {
            sheep.get(i).clearForce();
            for(int j = 0; j < towers.size(); j++){
                sheep.get(i).addForce(towers.get(j).calculateForce(sheep.get(i)));
            }
            sheep.get(i).applyForce();
        }
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		Tower newTower;

		if(SwingUtilities.isLeftMouseButton(e)){
			newTower = new TowerAttractor(x, y, 5000);
		} else if(SwingUtilities.isRightMouseButton(e)){
			newTower = new TowerRepulsor(x, y, 5000);
		} else {
			return;
		}

		if(towers.size() == 0) {
			towers.add(newTower);
		} else {
			towers.set(0, newTower);
		}
		System.out.println(String.valueOf(x) + " " + String.valueOf(y));		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		towers.remove(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
