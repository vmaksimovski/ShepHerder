public class Sheep {
    public double x, y;
    public double dX, dY;
    public boolean destroyed;

    public Sheep(double a, double b){
        x = a; y = b;
        dX = 0; dY = 0;
        destroyed = false;
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
        x += dX;
        y += dY;
        dX = dY = 0;
    }
}
