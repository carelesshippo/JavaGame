package me.oliverHowe;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameManager {
    private final GUI gui = GUI.getInstance();
    private final ArrayList<Fish> fishes = new ArrayList<>();
    private final ArrayList<Shark> sharks = new ArrayList<>();
    private Player player;
    private boolean gameRunning = false;
    private int maxSpeed = 10;
    private int minSpeed = 1;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getPlayerPosition() {
        return (int) player.getX();
    }

    public int getScore() {
        return player.getCurrentScore();
    }

    public void startGame() {
        gui.switchToGameScene();
        gameRunning = true;
        TickManager tickManager = new TickManager();
        tickManager.start();
    }

    public void startGameForFirstTime() {
        initializeAllCharacters();
        startGame();
    }

    private void initializeAllCharacters() {
        initializePlayer();
        initializeFishes();
        initializeSharks();
    }

    private void initializePlayer() {
        player = new Player();
    }

    private void initializeFishes() {
        fishes.clear();
        addFishes(3, false);
    }

    private void addFishes(int amount, boolean midGame) {
        for (int i = 0; i < amount; i++) {
            Fish fish = new Fish();
            if (midGame) {
                gui.addToGameRoot(fish.getFish());
            }
            fishes.add(fish);
        }
    }

    private void initializeSharks() {
        sharks.clear();
        addSharks(1, false);
    }

    private void addSharks(int amount, boolean midGame) {
        for (int i = 0; i < amount; i++) {
            Shark shark = new Shark();
            if (midGame) {
                gui.addToGameRoot(shark.getShark());
            }
            sharks.add(shark);
        }
    }

    public ImageView getPlayerPhoto() {
        return player.getBoat();
    }

    public void setPlayerVelocity(int velocity) {
        player.setVelocity(velocity);
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void endGame() {
        AudioClip lose = new AudioClip(GUI.generateURL("lose.mp3"));
        lose.play();
        gameRunning = false;
        gui.switchToGameOverScene();
    }

    @NotNull
    public ArrayList<ImageView> getSharkImages() {
        @NotNull ArrayList<ImageView> images = new ArrayList<>();
        for (@NotNull Shark f : sharks) {
            images.add(f.getShark());
        }
        return images;
    }

    @NotNull
    public ArrayList<ImageView> getFishImages() {
        @NotNull ArrayList<ImageView> images = new ArrayList<>();
        for (@NotNull Fish f : fishes) {
            images.add(f.getFish());
        }
        return images;
    }

    public void tick() {
        player.tick();
        for (@NotNull Shark shark : sharks) {
            shark.tick();
        }
        for (@NotNull Fish fish : fishes) {
            fish.tick();
        }
    }

    public void scored() {
        int newScore = player.getCurrentScore() + 1;
        if (newScore % 10 == 0) {
            addToSpeed(2);
        }
        if (newScore % 20 == 0) {
            addSharks(1, true);
        }
        player.setCurrentScore(newScore);
        gui.updateScoreLabel();
    }

    private void addToSpeed(int speed) {
        maxSpeed += speed;
        minSpeed += speed;
    }

    public int getHighScore() {
        return player.getHighScore();
    }

    public void startGameAgain() {
        initializeFishes();
        initializeSharks();
        resetAllPositions();
        startGame();
    }

    private void resetAllPositions() {
        player.resetPosition();
        for (Fish fish : fishes) {
            fish.resetPosition();
        }
        for (Shark shark : sharks) {
            shark.resetPosition();
        }
    }
}