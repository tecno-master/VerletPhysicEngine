package verlet.implementation;

import verlet.Constraint;
import verlet.Sphere;

public class VerletSphere implements Sphere {
    private double ax,ay;
    private double lx,ly;
    private double x,y;
    private float radius;
    protected VerletSphere() {}

    public void setAttributes(double x, double y, float radius) {
        this.ax = 0;
        this.ay = y;
        this.lx = x;
        this.ly = y;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void updatePosition(float dt) {

        // Calculate current velocity
        double vx = x - lx;
        double vy = y - ly;

        // Update last position
        lx = x;
        ly = y;

        // Update actual position
        x = x + vx + ax * dt * dt;
        y = y + vy + ay * dt * dt;

        // reset acceleration variable
        ax = 0;
        ay = 0;

    }

    public void collision(Sphere sphere) {
        // In case a subclass wants to overwrite
    }

    public void collision(Constraint constraint) {
        // In case a subclass wants to overwrite
    }

    @Override
    public void accelerate(double x, double y) {
        ax += x;
        ay += y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setOldX(double x) {
        this.lx = x;
    }

    @Override
    public void setOldY(double y) {
        this.ly = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getOldX() {
        return lx;
    }

    @Override
    public double getOldY() {
        return ly;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public float getWeight() {
        return getRadius();
    }
}
