package me.oliverHowe.gui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import me.oliverHowe.GameManager;
import org.jetbrains.annotations.NotNull;


public class GameRoot {
    final GameManager gameManager;
    Label scoreLabel;
    final Pane gameRootPane = new Pane();

    public GameRoot() {
        gameManager = GUI.getInstance().getGameManager();
        createGameScenePane();
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Pane getGameRootPane() {
        return gameRootPane;
    }

    public void createGameScenePane() {
        gameRootPane.getChildren().clear();
        gameRootPane.getChildren().add(gameManager.getPlayerPhoto());

        for (@NotNull ImageView fishImage : gameManager.getFishImages()) {
            gameRootPane.getChildren().add(fishImage);
        }
        for (@NotNull ImageView sharkImage : gameManager.getSharkImages()) {
            gameRootPane.getChildren().add(sharkImage);
        }

        scoreLabel = new Label("Score: 0");
        @NotNull Font font = new Font(20);
        scoreLabel.setFont(font);
        gameRootPane.getChildren().add(scoreLabel);

        gameRootPane.setBackground(generateBackground());
    }

    public void addImageView(ImageView image) {
        Platform.runLater(() -> gameRootPane.getChildren().add(image));
    }


    @NotNull
    private Background generateBackground() {
        @NotNull BackgroundImage backgroundImage = new BackgroundImage(new Image(GUI.generateURL("backdrop.jpg")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(910, 510, false, false, false, false));
        return new Background(backgroundImage);
    }

}
