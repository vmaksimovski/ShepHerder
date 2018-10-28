abstract class Tower {
    double x, y;
    double power;

    public Tower(double newX, double newY, double newPower){
        x = newX;
        y = newY;
        power = newPower;
    }

    public abstract double[] calculateForce(Sheep sheep);
}
