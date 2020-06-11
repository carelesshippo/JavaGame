package me.oliverHowe.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import me.oliverHowe.GameManager;
import me.oliverHowe.Main;
import org.jetbrains.annotations.NotNull;

public class GameOverRoot {
    private final GameManager gameManager;
    private TilePane gameOverPane;
    private Label scoreAmountLabel;
    private Label highScoreAmountLabel;
    private Button playAgainButton;

    public GameOverRoot() {
        gameManager = Main.getGameManager();
        createGameOverPane();
    }

    public TilePane getGameOverPane() {
        return gameOverPane;
    }

    private void createGameOverPane() {
        gameOverPane = new TilePane();
        gameOverPane.setAlignment(Pos.CENTER);
        setBackground();
        initializeScoreLabels();
        initializePlayAgainButton();
        addAllElements();
    }

    private void addAllElements() {
        gameOverPane.getChildren().add(scoreAmountLabel);
        gameOverPane.getChildren().add(highScoreAmountLabel);
        gameOverPane.getChildren().add(playAgainButton);
    }

    private void setBackground() {
        gameOverPane.setBackground(generateBackground());
    }

    private void initializeScoreLabels() {
        scoreAmountLabel = new Label("Score: " + gameManager.getScore());
        highScoreAmountLabel = new Label("High Score: " + gameManager.getHighScore());
    }

    private void initializePlayAgainButton() {
        playAgainButton = new Button("Play Again");
        playAgainButton.setId("startButton");
        playAgainButton.setMaxSize(150, 100);
        playAgainButton.setPrefSize(125, 75);
        playAgainButton.setOnAction(event -> gameManager.startGameAgain());
    }

    @NotNull
    private Background generateBackground() {
        @NotNull BackgroundImage backgroundImage = new BackgroundImage(new Image(GUI.generateURL("backdrop.jpg")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(910, 510, false, false, false, false));
        return new Background(backgroundImage);
    }
}