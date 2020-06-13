package me.oliverHowe.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;

public class TitleRoot {
    private StackPane titleRoot;

    public TitleRoot() {
        createTitlePane();
    }

    public Pane getTitleRoot() {
        return titleRoot;
    }

    private void createTitlePane() {
        titleRoot = new StackPane();
        setBackground();
        @NotNull Button startButton = new Button("Start Game");
        startButton.setMaxSize(150, 100);
        startButton.setPrefSize(125, 75);
        startButton.setId("startButton");
        startButton.setOnAction(event -> GUI.getInstance().getGameManager().startGameForFirstTime());
        titleRoot.getChildren().add(startButton);
    }

    private void setBackground() {
        titleRoot.setBackground(generateBackground());
    }

    @NotNull
    private Background generateBackground() {
        @NotNull BackgroundImage backgroundImage = new BackgroundImage(new Image(GUI.generateURL("titleBackground.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(910, 510, false, false, false, false));
        return new Background(backgroundImage);
    }
}
