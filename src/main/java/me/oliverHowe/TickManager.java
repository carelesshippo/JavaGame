package me.oliverHowe;

public class TickManager extends Thread {

    private static TickManager instance;
    private final GameManager gameManager = Main.getGameManager();
    private int ticks;
    private int elapsedTime;

    public static TickManager getInstance() {
        return instance;
    }

    public void run() {
        instance = this;
        elapsedTime = 0;
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
            elapsedTime++;
        }
    }
}
