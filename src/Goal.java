package src;

import java.awt.Color;
import java.awt.Graphics;

public class Goal {
	public int goalX;
	public int goalY;
	public int goalRadius; //bigger
	public int smallGoalRadius; //smaller
	
	public Goal(int goalX, int goalY, int goalRadius, int smallGoalRadius) {
		this.goalX = goalX;
		this.goalY = goalY;
		this.goalRadius = goalRadius;
		this.smallGoalRadius = smallGoalRadius;
	}
 	public void drawGoal(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(goalX-goalRadius, goalY-goalRadius, goalRadius*2, goalRadius*2);
		g.setColor(Color.WHITE);
		g.fillOval(goalX-smallGoalRadius, goalY-smallGoalRadius, smallGoalRadius*2, smallGoalRadius*2);
	}	

}
