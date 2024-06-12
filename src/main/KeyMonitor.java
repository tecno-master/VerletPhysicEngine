package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeyMonitor implements KeyListener {
    private HashMap<Integer, Boolean> keyStates;
    private Map<Integer, ArrayList<Runnable>> runnables;

    public KeyMonitor() {
        keyStates = new HashMap<>();
        this.runnables = new HashMap<>();
    }

    public void addKeyListener(int key, Runnable runnable) {
        if(runnables.containsKey(key)) runnables.get(key).add(runnable);
        else runnables.put(key, new ArrayList<Runnable>(){{add(runnable);}});
    }

    public boolean isKeyPressed(int keyCode) {
        return keyStates.getOrDefault(keyCode, false);
    }

    public void releaseKey(int keyCode) {
        keyStates.put(keyCode, false);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyStates.put(e.getKeyCode(), true);
        invokeRunnable(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keyStates.put(e.getKeyCode(), false);
    }

    private void invokeRunnable(int key) {
        if(runnables.containsKey(key)) {
            for(Runnable runnable : runnables.get(key)) {
                runnable.run();
            }
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }
}


