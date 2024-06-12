package verlet.render;

import verlet.Scene;
import verlet.Sphere;
import verlet.VerletContainer;

import javax.swing.*;
import java.awt.*;

public class VerletPanel extends JPanel {

    private int zoom;
    private int orientationX;
    private int orientationY;

    private float minSize;
    private final VerletContainer container;
    private boolean drawSpheres,drawConstraints;
    public VerletPanel(VerletContainer container) {
        this(container,500,0,0);
    }
    protected VerletPanel(VerletContainer container, int zoom, int x, int y) {
        this.container = container;
        this.zoom = zoom;
        this.orientationX = x;
        this.orientationY = y;
        this.drawSpheres = true;
        this.drawConstraints = true;
    }

    public void setOrientationX(int orientationX) {
        this.orientationX = orientationX;
    }

    public int getOrientationY() {
        return orientationY;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getZoom() {
        return zoom;
    }

    public int getOrientationX() {
        return orientationX;
    }

    public void setOrientationY(int orientationY) {
        this.orientationY = orientationY;
    }

    public void setDrawSpheres(boolean drawSpheres) {
        this.drawSpheres = drawSpheres;
    }

    public void setDrawConstraints(boolean drawConstraints) {
        this.drawConstraints = drawConstraints;
    }

    public boolean isDrawSpheres() {
        return drawSpheres;
    }

    public boolean isDrawConstraints() {
        return drawConstraints;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateMinSize();

        if(drawSpheres) container.invokeSpheres((sphere -> {
            drawSphere(sphere,g);
        }));

        if(drawConstraints && container instanceof Scene) ((Scene)container).invokeConstraint(constraint -> ConstraintPainter.paintConstraint(constraint,this,g));
    }

    protected void drawSphere(Sphere sphere, Graphics g) {
        int radius = (int) translateSize(sphere.getRadius());
        g.fillOval(translateX(sphere.getX()) - radius,translateY(sphere.getY()) - radius, radius*2, radius*2);
    }

    public int translateX(double x) {
        return (int) (x*minSize + getWidth()/2 + orientationX);
    }
    public int translateY(double y) {
        return (int) -(y*minSize - getHeight()/2 + orientationY);
    }
    public int translateBackX(double x) {
        return (int) ((x - getWidth()/2)/minSize - orientationX/minSize);
    }
    public int translateBackY(double y) {
        return (int) -((y - getHeight()/2)/minSize + orientationY/minSize);
    }
    private void updateMinSize() {
        minSize = (float) Math.min(getWidth(), getHeight()) / zoom;
        //minSize = (float) ZOOM;
    }
    public float translateSize(float size) {
        return size * minSize;
    }
}
