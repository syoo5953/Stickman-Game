package stickman.view;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import stickman.model.engine.EngineProvider;
import stickman.model.engine.GameEngine;

/** A listener and handler for Keyboard input */
class KeyboardInputHandler {

  private final SoundHandler soundHandler;
  private boolean left = false;
  private boolean right = false;
  private Set<KeyCode> pressedKeys = new HashSet<>();

  KeyboardInputHandler() {

    this.soundHandler = new SoundHandler("/media/");

    soundHandler.registerSound("jump", "jump.wav");
  }

  /**
   * Listens for and handles Key press events.
   *
   * @param keyEvent the Key being pressed.
   */
  void handlePressed(KeyEvent keyEvent) {
    GameEngine model = EngineProvider.getCurrentEngine();
    if (pressedKeys.contains(keyEvent.getCode())) {
      return;
    }
    pressedKeys.add(keyEvent.getCode());

    if (keyEvent.getCode().equals(KeyCode.Q)) {
      model.saveLevel();
    }

    if (keyEvent.getCode().equals(KeyCode.S)) {
      model.loadLevel();
    }

    if (keyEvent.getCode().equals(KeyCode.UP)) {
      if (model.jump()) {
        soundHandler.playSound("jump");
      }
    }

    if (keyEvent.getCode().equals(KeyCode.LEFT)) {
      left = true;
    } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
      right = true;
    } else {
      return;
    }

    if (left) {
      if (right) {
        model.stopMoving();
      } else {
        model.moveLeft();
      }
    } else {
      model.moveRight();
    }
  }

  /**
   * Listens for and handles Key release events.
   *
   * @param keyEvent the key being released.
   */
  void handleReleased(KeyEvent keyEvent) {
    GameEngine model = EngineProvider.getCurrentEngine();
    pressedKeys.remove(keyEvent.getCode());

    if (keyEvent.getCode().equals(KeyCode.LEFT)) {
      left = false;
    } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
      right = false;
    } else {
      return;
    }

    if (!(right || left)) {
      model.stopMoving();
    } else if (right) {
      model.moveRight();
    } else {
      model.moveLeft();
    }
  }
}
