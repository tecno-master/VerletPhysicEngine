package main;

import verlet.Scene;
import verlet.Sphere;
import verlet.Verlet;
import verlet.constraint.CircleAreaConstraint;
import verlet.constraint.LinkConstraint;
import verlet.constraint.StaticPositionConstraint;

import java.awt.*;
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

        if(index == 2) {

            km.addKeyListener(KeyEvent.VK_1, () -> {
                int size = 5;
                double x = 0;
                double y = 100;
                Sphere[][] spheres = new Sphere[size][size];
                Color color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
                for(int i = 0; i < spheres.length; i++) {
                    for(int j = 0; j < spheres[0].length; j++) {
                        spheres[i][j] = new ColorSphere(x+i*5*2,y+j*5*2,5,color);
                        scene.addSphere(spheres[i][j]);
                    }
                }
                for(int i = 0; i < spheres.length; i++) {
                    for(int j = 0; j < spheres[0].length; j++) {
                        if(i+1 < spheres.length) scene.addConstraint(new LinkConstraint(spheres[i][j],spheres[i+1][j]));
                        if(j+1 < spheres.length) scene.addConstraint(new LinkConstraint(spheres[i][j],spheres[i][j+1]));
                    }
                }
            });

            km.addKeyListener(KeyEvent.VK_2, () -> {
                int size = 5;
                double x = 0;
                double y = 100;
                Sphere[][] spheres = new Sphere[size][size];
                Color color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
                for(int i = 0; i < spheres.length; i++) {
                    for(int j = 0; j < spheres[0].length; j++) {
                        spheres[i][j] = new ColorSphere(x+i*5*2,y+j*5*2,5,color);
                        scene.addSphere(spheres[i][j]);
                    }
                }
            });
            km.addKeyListener(KeyEvent.VK_3, () -> {
                Sphere lastSphere = new WeightedSphere(0,0,20,4);
                scene.addSphere(lastSphere);

                for(int i = 1; i < 13; i++) {
                    Sphere sphere = Verlet.createSphere(i*10*2,0,8);
                    scene.addSphere(sphere);
                    scene.addConstraint(new LinkConstraint(lastSphere,sphere));
                    lastSphere = sphere;
                }
            });

            double sx = -200;
            double sy = -100;
            int count = 50;
            float size = (float) (Math.abs(sx)*2/count/2);

            Sphere lastSphere = Verlet.createSphere(sx,sy, size);
            scene.addConstraint(new StaticPositionConstraint(lastSphere));
            scene.addSphere(lastSphere);
            for(int i = 1; i < count; i++) {
                Sphere sphere = Verlet.createSphere(i*size*2+sx,sy,size);
                scene.addSphere(sphere);
                scene.addConstraint(new LinkConstraint(lastSphere,sphere));
                lastSphere = sphere;
            }
            scene.addConstraint(new StaticPositionConstraint(lastSphere));

        }

    }
}
