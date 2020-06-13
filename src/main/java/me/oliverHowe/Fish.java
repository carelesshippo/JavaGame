package me.oliverHowe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Fish {
    private final int spawnHeight = -90;
    private ImageView self;
    private int speed;
    private final GameManager gameManager;

    public Fish() {
        gameManager = GUI.getInstance().getGameManager();
        setSpeed(gameManager.getMinSpeed(), gameManager.getMaxSpeed());
        createSelf();
    }

    public ImageView getFish() {
        return self;
    }

    private void createSelf() {
        self = new ImageView(new Image(GUI.generateURL("fish.png")));
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
        int playerPosition = gameManager.getPlayerPosition();
        if (self.getX() > playerPosition && self.getX() < playerPosition + 150) {
            resetPosition();
            score();
            return;
        }
        if (self.getX() + 75 > playerPosition && self.getX() + 75 < playerPosition + 75) {
            resetPosition();
            score();
        }

    }

    private void score() {
        gameManager.scored();
    }

    public void resetPosition() {
        self.setY(spawnHeight);
        generateSpawnLocation();
        setSpeed(gameManager.getMinSpeed(), gameManager.getMaxSpeed());
    }
}
