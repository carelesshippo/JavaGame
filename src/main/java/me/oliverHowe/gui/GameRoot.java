package me.oliverHowe.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import me.oliverHowe.GameManager;
import me.oliverHowe.Main;
import org.jetbrains.annotations.NotNull;


public class GameRoot {
    final GameManager gameManager;
    Label scoreLabel;
    Pane gameRootPane;

    public GameRoot() {
        gameManager = Main.getGameManager();
        createGameScenePane();
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Pane getGameRootPane() {
        return gameRootPane;
    }

    public void createGameScenePane() {
        gameRootPane = new Pane();
        gameRootPane.getChildren().add(gameManager.getSharkPhoto());
        gameRootPane.getChildren().add(gameManager.getPlayerPhoto());
        for (@NotNull ImageView f : gameManager.getFishImages()) {
            gameRootPane.getChildren().add(f);
        }

        scoreLabel = new Label("Score: 0");
        @NotNull Font font = new Font(20);
        scoreLabel.setFont(font);
        gameRootPane.getChildren().add(scoreLabel);

        gameRootPane.setBackground(generateBackground());
    }

    @NotNull
    private Background generateBackground() {
        @NotNull BackgroundImage backgroundImage = new BackgroundImage(new Image(GUI.generateURL("backdrop.jpg")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(910, 510, false, false, false, false));
        return new Background(backgroundImage);
    }

}
