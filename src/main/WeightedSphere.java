package main;

import verlet.implementation.VerletSphere;

public class WeightedSphere extends VerletSphere {
    private float weight;
    public WeightedSphere(double x, double y, float radius, float weight) {
        super();
        setAttributes(x,y,radius);
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return super.getWeight()*weight;
    }
}
