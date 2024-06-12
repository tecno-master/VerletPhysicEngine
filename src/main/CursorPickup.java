package main;

import verlet.Scene;
import verlet.Sphere;
import verlet.utils.VectorUtil;

public class CursorPickup {
    private static Sphere sphere;
    public static void grab(Scene scene, int x, int y) {
        scene.invokeSpheres((sphere -> {
            if(VectorUtil.length(x - sphere.getX(), y - sphere.getY()) < sphere.getRadius()*2) {
                CursorPickup.sphere = sphere;
            }
        }));
    }
    public static void release() {
        sphere = null;
    }
    public static void update(int x, int y) {
        if(sphere != null) {
            sphere.setX(sphere.getX()+(x-sphere.getX())/8);
            sphere.setY(sphere.getY()+(y-sphere.getY())/8);
        }
    }
}
