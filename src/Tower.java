package SHEPherd;

abstract class Tower {
    double x, y;
    boolean disabled;
    double power;

    public Tower(double newX, double newY, double newPower){
        x = newX;
        y = newY;
        power = newPower;
        disabled = false;
    }

    public abstract double[] calculateForce(Sheep sheep);
}
