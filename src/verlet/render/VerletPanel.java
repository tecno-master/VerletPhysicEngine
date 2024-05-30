package verlet.render;

import verlet.Scene;
import verlet.VerletContainer;

import javax.swing.*;
import java.awt.*;

public class VerletPanel extends JPanel {

    private final static int ZOOM = 500;
    private final static int ORIENTATION_X = 0;
    private final static int ORIENTATION_Y = 0;

    private float minSize;

    private final VerletContainer container;
    public VerletPanel(VerletContainer container) {
        this.container = container;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateMinSize();

        container.invokeSpheres((sphere -> {
            int radius = (int) translateSize(sphere.getRadius());
            g.fillOval(translateX(sphere.getX()) - radius,translateY(sphere.getY()) - radius, radius*2, radius*2);
        }));

        if(container instanceof Scene) ((Scene)container).invokeConstraint(constraint -> ConstraintPainter.paintConstraint(constraint,this,g));
    }

    public int translateX(double x) {
        return (int) (x*minSize + getWidth()/2 + ORIENTATION_X);
    }
    public int translateY(double y) {
        return (int) -(y*minSize - getHeight()/2 + ORIENTATION_Y);
    }
    public int translateBackX(double x) {
        return (int) ((x - getWidth()/2)/minSize);
    }
    public int translateBackY(double y) {
        return (int) -((y - getHeight()/2)/minSize);
    }
    private void updateMinSize() {
        minSize = (float) Math.min(getWidth(), getHeight()) / ZOOM;
        //minSize = (float) ZOOM;
    }
    public float translateSize(float size) {
        return size * minSize;
    }
}
