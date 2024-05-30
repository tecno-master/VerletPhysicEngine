package verlet;

import verlet.implementation.ConstraintRunnable;

public interface Scene extends VerletContainer {
    void addConstraint(Constraint constraint);

    void removeConstraint(Constraint constraint);

    void invokeConstraint(ConstraintRunnable runnable);

}
