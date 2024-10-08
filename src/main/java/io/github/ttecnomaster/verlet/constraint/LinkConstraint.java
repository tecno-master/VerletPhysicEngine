package io.github.ttecnomaster.verlet.constraint;

import io.github.ttecnomaster.verlet.Sphere;
import io.github.ttecnomaster.verlet.StaticConstraint;
import io.github.ttecnomaster.verlet.utils.VectorUtil;
import io.github.ttecnomaster.verlet.Constraint;
import io.github.ttecnomaster.verlet.Scene;

/**
 * The LinkConstraint is a type of {@link StaticConstraint} and links together two Spheres.
 * Those Spheres will always be together as lang as the Constraint exists in the {@link Scene}.
 * You can link multiple Spheres together forming all kinds of shapes (form a box with links).
 * It calculates the {@link VectorUtil#length(double, double)} between those two Spheres, subtracts the combined radius and moves Spheres depending on the outcome.
 *
 * @author tecno-master
 * @see Constraint
 * @see StaticConstraint
 * @version 1.0.0
 */
public class LinkConstraint implements StaticConstraint {
    private final Sphere sphere1,sphere2;
    private final double distance;

    /**
     * Defines the two Spheres that should be linked together.
     * Sets the target distance to the combined radius, as that would physically link two Spheres together.
     *
     * @param sphere1 The first Sphere that should be linked
     * @param sphere2 The second Sphere that should be linked
     */
    public LinkConstraint(Sphere sphere1, Sphere sphere2) {
        this(sphere1,sphere2,sphere1.getRadius()+sphere2.getRadius());
    }

    /**
     * Defines the two Spheres that should be linked together.
     * Allows to customize the target distance between the two Spheres.
     * By default, the target distance is the combined radius.
     *
     * @param sphere1 The first Sphere that should be linked
     * @param sphere2 The second Sphere that should be linked
     * @param distance The target distance the Spheres should be holding
     */
    public LinkConstraint(Sphere sphere1, Sphere sphere2, double distance) {
        this.sphere1 = sphere1;
        this.sphere2 = sphere2;
        this.distance = distance;
    }

    /**
     * A LinkConstraint requires two Spheres in order to function.
     * Those two spheres will then be linked together.
     * This method returns the first sphere.
     * @return The first Sphere linked to the other sphere.
     */
    public Sphere getSphere1() {
        return sphere1;
    }

    /**
     * A LinkConstraint requires two Spheres in order to function.
     * Those two spheres will then be linked together.
     * This method returns the second sphere.
     * @return The second Sphere linked to the other sphere.
     */
    public Sphere getSphere2() {
        return sphere2;
    }

    /**
     * Ensures that the two Spheres hold their target distance.
     * If not then move them together/apart.
     * It calculates the {@link VectorUtil#length(double, double)} between those two Spheres, subtracts the combined radius and moves Spheres depending on the outcome.
     * Also accounts weight and velocity. The heavier Sphere will have a bigger impact.
     */
    @Override
    public void apply() {
        double dx = sphere1.getX() - sphere2.getX();
        double dy = sphere1.getY() - sphere2.getY();
        final double dist = VectorUtil.length(dx,dy);
        dx /= dist;
        dy /= dist;
        final double delta = this.distance - dist;

        float weightDiff = sphere1.getWeight() / (sphere1.getWeight() + sphere2.getWeight());
        applyNewPosition(sphere1,dx * delta*(1-weightDiff),dy * delta*(1-weightDiff));
        applyNewPosition(sphere2,- dx * delta*(weightDiff),- dy * delta*(weightDiff));
    }

    private void applyNewPosition(Sphere sphere, double x, double y) {
        sphere.setX(sphere.getX() + x);
        sphere.setY(sphere.getY() + y);
    }
}
