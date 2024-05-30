package verlet;

import verlet.Constraint;

public interface StaticConstraint extends Constraint {
    void apply();
}
