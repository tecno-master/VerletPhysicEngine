package verlet.implementation;

import verlet.Constraint;
import verlet.SceneConstraint;

public interface ConstraintRunnable {
    void run(Constraint constraint);
}
