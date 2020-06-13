package me.oliverHowe.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import me.oliverHowe.GameManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GUI extends Application {
    private static GUI instance;
    private Scene scene;
    private GameManager gameManager;
    private GameRoot gameRootObject;
    private MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    public static GUI getInstance() {
        return instance;
    }

    public static String generateURL(String path) {
        return Objects.requireNonNull(GUI.class.getClassLoader().getResource(path)).toExternalForm();
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        instance = this;
        TitleRoot titleRootObject = new TitleRoot();
        scene = new Scene(titleRootObject.getTitleRoot(), 900, 500);
        gameManager = new GameManager();
        initializePrimaryStage(primaryStage);
        scene.getStylesheets().add(generateURL("titleScene.css"));
        primaryStage.setScene(scene);
        primaryStage.show();
        createMediaPlayer();
    }

    public void setRoot(Pane root) {
        scene.setRoot(root);
    }

    private void createMediaPlayer() {
        @NotNull Media song = new Media(generateURL("music.mp3"));
        mediaPlayer = new MediaPlayer(song);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public <T extends Event> void addKeyListener(EventType<T> eventType, EventHandler<? super T> handler) {
        scene.addEventHandler(eventType, handler);
    }

    public void switchToGameScene() {
        gameRootObject = new GameRoot();
        setRoot(gameRootObject.getGameRootPane());
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

    public void addToGameRoot(ImageView image) {
        gameRootObject.addImageView(image);
    }
}
