package verlet;

import verlet.implementation.VerletGrid;

public interface Solver {
    void enableCollisions(boolean b);
    void setGrid(VerletGrid grid);
    void setGravity(double x, double y);
    void setSubSteps(int subSteps);
    void step(float dt);
}
