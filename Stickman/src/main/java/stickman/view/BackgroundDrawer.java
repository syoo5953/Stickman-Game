package stickman.view;

import javafx.scene.layout.Pane;

/** The BackgroundDrawer interface */
public interface BackgroundDrawer {

  /**
   * Draws a background texture onto the game window.
   *
   * @param pane the Pane being used for display.
   */
  void draw(Pane pane);

  /**
   * Shift the background on the x-axis
   *
   * @param xViewportOffset the amount of x-displacement.
   */
  void update(double xViewportOffset);
}
