package me.oliverHowe.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import me.oliverHowe.GameManager;
import me.oliverHowe.Main;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GUI extends Application {
    static GUI instance;
    final int SPEED = 10;
    Scene scene;
    MediaPlayer mediaPlayer;
    TitleRoot titleRootObject;
    GameManager gameManager;
    GameRoot gameRootObject;

    public static GUI getInstance() {
        return instance;
    }

    public static String generateURL(String path) {
        return Objects.requireNonNull(GUI.class.getClassLoader().getResource(path)).toExternalForm();
    }

    public void setRoot(Pane root) {
        scene.setRoot(root);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        instance = this;
        Main.setGameManager();
        initializePrimaryStage(primaryStage);
        gameManager = Main.getGameManager();
        titleRootObject = new TitleRoot();
        scene = new Scene(titleRootObject.getTitleRoot(), 900, 500);
        scene.getStylesheets().add(generateURL("titleScene.css"));
        addKeyListener();
        primaryStage.setScene(scene);
        primaryStage.show();
        createMediaPlayer();
    }


    private void createMediaPlayer() {
        @NotNull Media song = new Media(generateURL("music.mp3"));
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void addKeyListener() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (!gameManager.isGameRunning()) {
                return;
            }
            if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT) {
                moveRight();
            }
            if (key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
                moveLeft();
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (!gameManager.isGameRunning()) {
                return;
            }
            if (key.getCode() == KeyCode.D || key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.A || key.getCode() == KeyCode.LEFT) {
                gameManager.setPlayerVelocity(0);
            }

        });
    }

    public void switchToGameScene() {
        gameRootObject = new GameRoot();
        setRoot(gameRootObject.getGameRootPane());
    }


    private void moveLeft() {
        gameManager.setPlayerVelocity(-SPEED);
    }

    private void moveRight() {
        gameManager.setPlayerVelocity(SPEED);
    }

    private void initializePrimaryStage(@NotNull Stage stage) {
        stage.setTitle("Fishing the Game 2");
        stage.getIcons().addAll(new Image(generateURL("fish.png")));
        stage.setResizable(false);
    }

    public void updateScoreLabel() {
        Platform.runLater(() -> {
            gameRootObject.getScoreLabel().setText("Score: " + gameManager.getScore());
            @NotNull AudioClip ding = new AudioClip(generateURL("score.mp3"));
            ding.play();
        });
    }

    public void switchToGameOverScene() {
        GameOverRoot gameOverRootObject = new GameOverRoot();
        setRoot(gameOverRootObject.getGameOverPane());
    }
}
