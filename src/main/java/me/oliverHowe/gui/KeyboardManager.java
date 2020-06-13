package me.oliverHowe.gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import me.oliverHowe.GameManager;

public class KeyboardManager {
    private final GUI gui = GUI.getInstance();
    private final GameManager gameManager = gui.getGameManager();
    private boolean leftArrowDown = false;
    private boolean rightArrowDown = false;
    private boolean aDown = false;
    private boolean dDown = false;

    public KeyboardManager() {
        gui.addKeyListener(KeyEvent.KEY_PRESSED, (key) -> {
            if (!gameManager.isGameRunning()) {
                return;
            }
            if (key.getCode() == KeyCode.D) {
                dDown = true;
            }
            if (key.getCode() == KeyCode.A) {
                aDown = true;
            }
            if (key.getCode() == KeyCode.LEFT) {
                leftArrowDown = true;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                rightArrowDown = true;
            }
        });
        gui.addKeyListener(KeyEvent.KEY_RELEASED, (key) -> {
            if (key.getCode() == KeyCode.D) {
                dDown = false;
            }
            if (key.getCode() == KeyCode.A) {
                aDown = false;
            }
            if (key.getCode() == KeyCode.LEFT) {
                leftArrowDown = false;
            }
            if (key.getCode() == KeyCode.RIGHT) {
                rightArrowDown = false;
            }
        });
    }

    public boolean isLeftArrowDown() {
        return leftArrowDown;
    }

    public boolean isRightArrowDown() {
        return rightArrowDown;
    }

    public boolean isaDown() {
        return aDown;
    }

    public boolean isdDown() {
        return dDown;
    }
}
