package src;

import java.util.ArrayList;

public class Sheep {
    public double x, y;
    public ArrayList<Double[]> trail;
    public double dX, dY;
    public boolean destroyed;
    public double diameter = 10;

    public Sheep(double a, double b){
        x = a; y = b;
        dX = 0; dY = 0;
        destroyed = false;
        trail = new ArrayList<Double[]>();
    }

    public void clearForce(){
//        dX = dY = 0;
    }

    public void addForce(double fX, double fY){
        dX += fX;
        dY += fY;
    }  
    
    public void addForce(double[] f){
    	this.addForce(f[0], f[1]);
    }

    public void applyForce(){
    	
    	Double[] lastPos = new Double[2];
        lastPos[0] = x + diameter/2 ;
        lastPos[1] = y + diameter/2;
        trail.add(lastPos);
        if(trail.size() >= Runner.b.trailLength){
            trail.remove(0);
        }
        
        x += dX;
        y += dY;
        dX *= 0.98851402035289613535686750493829;
        dY *= 0.98851402035289613535686750493829;
//        dX = dY = 0;
    }
    
    public boolean isInGoal (Goal goal) {
    	double x_1 = x + diameter/4.0;
    	double y_1 = y + diameter/4.0;
		double r = Math.sqrt((x_1-goal.goalX)*(x_1-goal.goalX) + (y_1-goal.goalY)*(y_1-goal.goalY));
		if(r<=goal.smallGoalRadius && !destroyed) {
				destroyed = true;
				return true;
				//scoreUp();
		}   	
    	return false;
    }
}
