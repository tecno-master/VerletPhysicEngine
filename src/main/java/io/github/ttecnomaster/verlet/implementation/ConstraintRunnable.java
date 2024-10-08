package io.github.ttecnomaster.verlet.implementation;

import io.github.ttecnomaster.verlet.Constraint;
import io.github.ttecnomaster.verlet.Scene;

/**
 * This interface is used for running a Runnable with a Constraint as a parameter.
 * This is primarily used by the {@link Scene#invokeConstraints(ConstraintRunnable)} method.
 * As a functional interface this should be used with lambda only.
 *
 * @author tecno-master
 * @see Scene
 * @version 1.0.0
 */
@FunctionalInterface
public interface ConstraintRunnable {
    /**
     * When called passes a Constraint parameter. run executes whatever the interface was created for
     *
     * @param constraint The Constraint that gets called on the run method
     */
    void run(Constraint constraint);
}
