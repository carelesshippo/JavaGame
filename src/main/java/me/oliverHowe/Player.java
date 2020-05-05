package me.oliverHowe;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.oliverHowe.gui.GUI;

public class Player {
    private int highScore;
    private int currentScore = 0;
    private int velocity;
    private double xValue;
    private ImageView boat;

    public Player() {
        highScore = 0;
        velocity = 0;
        createSelf();
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public double getX() {
        return xValue;
    }

    private void setX(double xValue) {
        if (xValue >= 780) {
            return;
        }
        if (xValue <= -20) {
            return;
        }
        this.xValue = xValue;
        boat.setX(this.xValue);
    }

    public ImageView getBoat() {
        return boat;
    }

    private void createSelf() {
        boat = new ImageView(new Image(GUI.generateURL("boat.png")));
        boat.setFitWidth(150);
        boat.setFitHeight(150);
        boat.setY(325);
        boat.setX(375);
        xValue = 375;
    }


    public int getHighScore() {
        return highScore;
    }

    private void setHighScore(int highScore) throws Exception {
        if (highScore < 0) {
            throw new Exception("A score cannot be negative!");
        }
        this.highScore = highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) throws Exception {
        if (currentScore < 0) {
            throw new Exception("A score cannot be negative!");
        }
        this.currentScore = currentScore;
        if (theScoreIsGreaterThanHighScore()) {
            highScore = this.currentScore;
        }
    }

    public void tick() {
        setX(xValue + velocity);
    }

    private boolean theScoreIsGreaterThanHighScore() {
        return currentScore > highScore;
    }

    public void resetPosition() {
        boat.setY(325);
        boat.setX(375);
        xValue = 375;
        currentScore = 0;
    }
}
