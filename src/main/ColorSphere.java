package main;

import verlet.implementation.VerletSphere;

import java.awt.*;

public class ColorSphere extends VerletSphere {
    private Color color;
    public ColorSphere(double x, double y, float radius, Color color) {
        super();
        setAttributes(x,y,radius);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
