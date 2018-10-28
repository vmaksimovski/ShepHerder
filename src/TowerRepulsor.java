package src;

import java.lang.Math;

public class TowerRepulsor extends Tower {
    public TowerRepulsor(double newX, double newY, double newPower) {
        super(newX, newY, newPower);
    }

    public double[] calculateForce(Sheep sheep){
    	double[] force = new double[2];
        force[0] = force[1] = 0.0;

        if(disabled){
            return force;
        }
        
        double r = Math.sqrt((sheep.y - this.y) * (sheep.y - this.y) + (sheep.x - this.x) * (sheep.x - this.x));
        double theta = Math.atan2((sheep.y - this.y), (sheep.x - this.x));
        force[0] += this.power/(r*Math.sqrt(r))*Math.cos(theta);
        force[1] += this.power/(r*Math.sqrt(r))*Math.sin(theta);

        return force;
    }
}
