import java.util.ArrayList;

public class Sheep {
    public double x, y;
    public ArrayList<Double[]> trail;
    public double dX, dY;
    public boolean destroyed;

    public Sheep(double a, double b){
        x = a; y = b;
        dX = 0; dY = 0;
        destroyed = false;
        trail = new ArrayList<Double[]>();
    }

    public void clearForce(){
        dX = dY = 0;
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
        lastPos[0] = x;
        lastPos[1] = y;
        trail.add(lastPos);
        if(trail.size() >= Runner.b.trailLength){
            trail.remove(0);
        }

        x += dX;
        y += dY;
        dX = dY = 0;
    }
}
