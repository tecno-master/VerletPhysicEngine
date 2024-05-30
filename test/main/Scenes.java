package main;

import verlet.Scene;
import verlet.Sphere;
import verlet.Verlet;
import verlet.constraint.CircleAreaConstraint;
import verlet.constraint.LinkConstraint;
import verlet.constraint.StaticPositionConstraint;

import java.awt.event.KeyEvent;

public class Scenes {
    public static void get(int index, Scene scene, KeyMonitor km) {

        if(index == 0) {
            km.addKeyListener(KeyEvent.VK_SPACE, () -> {
                scene.addSphere(Verlet.createSphere(180,0,(float)Math.random()*15+5));
            });

            scene.addConstraint(new CircleAreaConstraint(0,0,200));
        }

        if(index == 1) {

            Sphere lastSphere = Verlet.createSphere(0,0,10);
            scene.addSphere(lastSphere);
            scene.addConstraint(new StaticPositionConstraint(lastSphere));

            for(int i = 1; i < 10; i++) {
                Sphere sphere = Verlet.createSphere(i*10*2,0,10);
                scene.addSphere(sphere);
                scene.addConstraint(new LinkConstraint(lastSphere,sphere));
                lastSphere = sphere;
            }

        }

    }
}
