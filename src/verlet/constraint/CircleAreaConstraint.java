package verlet.constraint;

import verlet.SceneConstraint;
import verlet.Sphere;
import verlet.implementation.VerletSphere;
import verlet.utils.VectorUtil;

public class CircleAreaConstraint implements SceneConstraint {
    private final double x,y;
    private final float radius;
    public CircleAreaConstraint(double x, double y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void apply(Sphere sphere) {

        double dx = sphere.getX() - x;
        double dy = sphere.getY() - y;

        double length = VectorUtil.length(dx,dy) + sphere.getRadius();

        if(length <= radius) return;

        sphere.setX(dx / length * radius + x);
        sphere.setY(dy / length * radius + y);

        if(sphere instanceof VerletSphere) ((VerletSphere)sphere).collision(this);

    }
}
