package me.oliverHowe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Fish {
    private final int SPAWNHEIGHT = -90;
    private ImageView self;
    private int speed;
    private GameManager gameManager;

    public Fish() {
        gameManager = Main.getGameManager();
        setSpeed(0, 10);
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
        } while (speed < minimum + 1);
    }

    private void generateSpawnLocation() {
        @NotNull Random random = new Random();
        int x = random.nextInt(900);
        self.setX(x);
        self.setY(SPAWNHEIGHT);
    }

    public void tick() {
        self.setY(self.getY() + speed);
        if (self.getY() > 550) {
            resetFish();
        }
        if (self.getY() > 350 && self.getY() < 425) {
            checkCollision();
        }
    }

    private void checkCollision() {
        int playerPosition = gameManager.getPlayerPosition();
        if (self.getX() > playerPosition && self.getX() < playerPosition + 150) {
            resetFish();
            score();
            return;
        }
        if (self.getX() + 75 > playerPosition && self.getX() + 75 < playerPosition + 75) {
            resetFish();
            score();
        }

    }

    private void score() {
        gameManager.scored();
    }

    private void resetFish() {
        self.setY(SPAWNHEIGHT);
        generateSpawnLocation();
        setSpeed(0, 10);
    }
}
