package verlet;

import verlet.implementation.SphereRunnable;
import verlet.implementation.TwoSphereRunnable;

public interface VerletContainer {
    void addSphere(Sphere sphere);

    void removeSphere(Sphere sphere);

    void invokeSpheres(SphereRunnable runnable);
    void invokeSpheresWithSpheres(TwoSphereRunnable runnable);
}
