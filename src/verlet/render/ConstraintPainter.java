package verlet.render;

import verlet.Constraint;
import verlet.SceneConstraint;

import java.awt.*;

public class ConstraintPainter {
    public static void paintConstraint(Constraint constraint, VerletPanel verletPanel, Graphics g) {
        DrawableConstraint dc = findDrawableConstraint(constraint);
        if(dc == null) return;
        dc.cdr.run(constraint,verletPanel,g);
    }

    private static DrawableConstraint findDrawableConstraint(Constraint constraint) {
        for(DrawableConstraint dc : DrawableConstraint.values()) {
            if(dc.clazz == constraint.getClass()) return dc;
        }
        return null;
    }

    private enum DrawableConstraint {
        CircleAreaConstraint(verlet.constraint.CircleAreaConstraint.class, ((constraint1,verletPanel, g) -> {
            verlet.constraint.CircleAreaConstraint constraint = ((verlet.constraint.CircleAreaConstraint) constraint1);
            int radius = (int) verletPanel.translateSize(constraint.getRadius());
            g.drawOval(verletPanel.translateX(constraint.getX()) - radius,verletPanel.translateY(constraint.getY()) - radius, radius*2, radius*2);
        })),
        ;

        private final Class<?> clazz;
        private final ConstraintDrawRunnable cdr;
        DrawableConstraint(Class<?> clazz,ConstraintDrawRunnable cdr) {
            this.clazz = clazz;
            this.cdr = cdr;
        }
    }
    private interface ConstraintDrawRunnable {
        void run(Constraint constraint, VerletPanel verletPanel, Graphics g);
    }
}
