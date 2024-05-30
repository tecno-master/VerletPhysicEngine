package main;

import jpl.JPL;
import verlet.Scene;
import verlet.Verlet;
import verlet.Solver;
import verlet.implementation.VerletGrid;
import verlet.render.VerletPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Main {
    private static int mouseX,mouseY;
    public static void main(String[] args) {

        mouseX = mouseY = 0;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        KeyMonitor km = new KeyMonitor();
        frame.addKeyListener(km);

        Scene scene = Verlet.createScene();

        Scenes.get(1,scene,km);
        Solver solver = Verlet.createSolver(scene);
        solver.setSubSteps(8);
        solver.setGrid(new VerletGrid(500,500,40));

        VerletPanel panel = new VerletPanel(scene);
        frame.add(panel);

        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CursorPickup.grab(scene, panel.translateBackX(e.getX()), panel.translateBackY(e.getY()));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                CursorPickup.release();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        JPL.start();
        JPL.getScheduler().setTickRate(60);

        JPL.getScheduler().scheduleRepeatingTask(() -> {

            CursorPickup.update(panel.translateBackX(mouseX), panel.translateBackY(mouseY));

            solver.step(0.02f);
            panel.repaint();

        });

    }
}