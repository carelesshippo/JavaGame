package me.oliverHowe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Shark {
    final int spawnHeight = -90;
    final GameManager gameManager;
    private ImageView self;
    int speed;


    public Shark() {
        gameManager = GUI.getInstance().getGameManager();
        setSpeed(gameManager.getMinSpeed(), gameManager.getMaxSpeed());
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
        } while (speed < minimum);
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
            resetPosition();
        }
        if (self.getY() > 350 && self.getY() < 425) {
            checkCollision();
        }
    }

    private void checkCollision() {
        int p = gameManager.getPlayerPosition();
        if (self.getX() > p && self.getX() < p + 150) {
            resetPosition();
            gameOver();
            return;
        }
        if (self.getX() + 75 > p && self.getX() + 75 < p + 75) {
            resetPosition();
            gameOver();
        }

    }

    private void gameOver() {
        gameManager.endGame();
    }

    public void resetPosition() {
        self.setY(spawnHeight);
        generateSpawnLocation();
        setSpeed(gameManager.getMinSpeed(), gameManager.getMaxSpeed());
    }
}
