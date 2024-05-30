package verlet;

import verlet.implementation.VerletGrid;

public interface Solver {
    void setGrid(VerletGrid grid);
    void setSubSteps(int subSteps);
    void step(float dt);
}
