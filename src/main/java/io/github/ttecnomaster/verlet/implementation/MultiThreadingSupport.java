package io.github.ttecnomaster.verlet.implementation;

import io.github.ttecnomaster.verlet.Solver;
import io.github.ttecnomaster.verlet.VerletContainer;

/**
 * Implementing MultiThreadingSupport into a class means it supports
 * the usage of multi-threading. Currently {@link VerletContainer}
 * and {@link VerletGrid} support multi-threading. Implementing the {@link MultiThreadingSupport#solveCollisionPartition(int, int, TwoSphereRunnable)}
 * method means, that individual threads can access and call it in order to solve the correct partition part spheres.
 * Most implementations use a split up "for loop".
 * Solver threads are ran by {@link VerletSolverThread}.
 *
 * @author tecno-master
 * @see VerletSolverThread
 * @see VerletContainer
 * @see VerletGrid
 * @version 1.0.0
 */
public interface MultiThreadingSupport {
    /**
     * Implementing this method means,
     * that individual threads can access and call it in order to solve the correct partition part spheres.
     * Most implementations use a split up "for loop".
     * @param partitionIndex The index of the partition. Is it the first part? Is it the last part?
     * @param partitionCount The total amount of partitions/threads. Can be specified by {@link Solver#setMultiThreading(int)}
     * @param runnable The runnable that should run on two spheres which are tested for a collision
     */
    void solveCollisionPartition(int partitionIndex, int partitionCount, TwoSphereRunnable runnable);
}
