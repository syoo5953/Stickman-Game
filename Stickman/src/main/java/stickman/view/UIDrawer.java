package stickman.view;

import javafx.scene.layout.Pane;

public interface UIDrawer {
  public void draw(Pane pane);

  public void update(double xViewportOffset, double yViewportOffset);
}
