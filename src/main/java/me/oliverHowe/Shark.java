package me.oliverHowe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Shark {
    final int spawnHeight = -90;
    final GameManager gameManager;
    ImageView self;
    int speed;

    public Shark() {
        gameManager = Main.getGameManager();
        setSpeed(0, 10);
        createSelf();
    }

    public ImageView getShark() {
        return self;
    }

    private void createSelf() {
        self = new ImageView(new Image(GUI.generateURL("shark.png")));
        self.setFitHeight(75);
        self.setFitWidth(75);
        generateSpawnLocation();
    }

    private void setSpeed(int minimum, int max) {
        do {
            speed = new Random().nextInt(max);
        } while (speed < minimum + 1);
    }

    private void generateSpawnLocation() {
        @NotNull Random random = new Random();
        int x = random.nextInt(900);
        self.setX(x);
        self.setY(spawnHeight);
    }

    public void tick() {
        self.setY(self.getY() + speed);
        if (self.getY() > 550) {
            resetSharkLocationAndSpeed();
        }
        if (self.getY() > 350 && self.getY() < 425) {
            checkCollision();
        }
    }

    private void checkCollision() {
        int p = gameManager.getPlayerPosition();
        if (self.getX() > p && self.getX() < p + 150) {
            resetSharkLocationAndSpeed();
            gameOver();
            return;
        }
        if (self.getX() + 75 > p && self.getX() + 75 < p + 75) {
            resetSharkLocationAndSpeed();
            gameOver();
        }

    }

    private void gameOver() {
        gameManager.endGame();
    }

    public void resetSharkLocationAndSpeed() {
        self.setY(spawnHeight);
        generateSpawnLocation();
        setSpeed(0, 10);
    }
}
