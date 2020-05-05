package me.oliverHowe;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import me.oliverHowe.gui.GUI;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameManager {
    final ArrayList<Fish> fishes = new ArrayList<>();
    GUI gui = GUI.getInstance();
    private Player player;
    private Shark shark;
    private boolean gameRunning = false;

    public GameManager() {
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
        initializeShark();
    }

    public void initializePlayer() {
        player = new Player();
    }

    public void initializeFishes() {
        fishes.add(new Fish());
        fishes.add(new Fish());
        fishes.add(new Fish());
    }

    public void initializeShark() {
        shark = new Shark();
    }

    public ImageView getSharkPhoto() {
        return shark.getShark();
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
    public ArrayList<ImageView> getFishImages() {
        @NotNull ArrayList<ImageView> images = new ArrayList<>();
        for (@NotNull Fish f : fishes) {
            images.add(f.getFish());
        }
        return images;
    }

    public void tick() {
        player.tick();
        shark.tick();
        for (@NotNull Fish f : fishes) {
            f.tick();
        }
    }

    public void scored() {
        try {
            player.setCurrentScore(player.getCurrentScore() + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gui.updateScoreLabel();
    }

    public int getHighScore() {
        return player.getHighScore();
    }

    public void startGameAgain() {
        resetAllPositions();
        startGame();
    }

    private void resetAllPositions() {
        player.resetPosition();
        for (Fish f : fishes) {
            f.resetPosition();
        }
        shark.resetSharkLocationAndSpeed();
    }
}
