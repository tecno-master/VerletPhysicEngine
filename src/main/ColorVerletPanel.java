package main;

import verlet.Sphere;
import verlet.VerletContainer;
import verlet.render.VerletPanel;

import java.awt.*;

public class ColorVerletPanel extends VerletPanel {
    public ColorVerletPanel(VerletContainer container) {
        super(container);
    }

    @Override
    protected void drawSphere(Sphere sphere, Graphics g) {
        Color c = g.getColor();
        if(sphere instanceof ColorSphere) g.setColor(((ColorSphere)sphere).getColor());
        super.drawSphere(sphere,g);
        g.setColor(c);
    }
}
