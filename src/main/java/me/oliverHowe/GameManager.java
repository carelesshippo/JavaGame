package me.oliverHowe;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import me.oliverHowe.gui.GUI;
import me.oliverHowe.gui.KeyboardManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GameManager {
    private final int DEFAULTMAXSPEED = 10;
    private final int DEFAULTMINSPEED = 1;
    private final int STARTINGSHARKS = 1;
    private final int STARTINGFISH = 3;
    private final int NEWSHARKS = 20;
    private final int NEWSPEED = 10;
    private final int SPEEDINTERVAL = 2;
    private final int SHARKINTERVAL = 1;
    private final int PLAYERSPEED = 10;
    private final GUI gui = GUI.getInstance();
    private final ArrayList<Fish> fishes = new ArrayList<>();
    private final ArrayList<Shark> sharks = new ArrayList<>();
    private Player player;
    private boolean gameRunning = false;
    private int maxSpeed = DEFAULTMAXSPEED;
    private int minSpeed = DEFAULTMINSPEED;
    private KeyboardManager inputDetector;

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
        inputDetector = new KeyboardManager();
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
        addFishes(STARTINGFISH, false);
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
        addSharks(STARTINGSHARKS, false);
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

    private void setPlayerVelocity(int velocity) {
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

    private boolean right() {
        return inputDetector.isdDown() || inputDetector.isRightArrowDown();
    }

    private boolean left() {
        return inputDetector.isaDown() || inputDetector.isLeftArrowDown();
    }

    public void tick() {
        setPlayerVelocity(0);
        if (left() && right()) {
            setPlayerVelocity(0);
        } else if (left()) {
            setPlayerVelocity(-PLAYERSPEED);
        } else if (right()) {
            setPlayerVelocity(PLAYERSPEED);
        }
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
        if (newScore % NEWSPEED == 0) {
            addToSpeed(SPEEDINTERVAL);
        }
        if (newScore % NEWSHARKS == 0) {
            addSharks(SHARKINTERVAL, true);
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
        maxSpeed = DEFAULTMAXSPEED;
        minSpeed = DEFAULTMINSPEED;
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