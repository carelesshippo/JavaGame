package me.oliverHowe;

import me.oliverHowe.gui.GUI;

public class TickManager extends Thread {
    private final GameManager gameManager = GUI.getInstance().getGameManager();
    private int ticks;

    public void run() {
        ticks = 0;
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60D;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (gameManager.isGameRunning()) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                delta--;
            }
        }
    }

    private void tick() {
        gameManager.tick();
        ticks++;
        if (ticks >= 60) {
            ticks = 0;
        }
    }
}
