package me.oliverHowe;

import javafx.application.Application;
import me.oliverHowe.gui.GUI;

public class Main {

    private static GameManager gameManager;

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static void setGameManager() {
        Main.gameManager = new GameManager();
    }

    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }

}
