package stickman.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import stickman.model.engine.EngineProvider;

/** A solid-color block background implementation of BackgroundDrawer. */
public class BlockedBackground implements BackgroundDrawer {

  private Rectangle sky;
  private Pane pane;

  @Override
  public void draw(Pane pane) {
    this.pane = pane;

    double width = pane.getWidth();
    double height = pane.getHeight();
    double floorHeight = EngineProvider.getCurrentEngine().getCurrentLevel().getFloorHeight();

    this.sky = new Rectangle(0, 0, width, height);
    sky.setFill(Paint.valueOf("LIGHTBLUE"));
    sky.setViewOrder(1000.0);

    this.pane.getChildren().addAll(sky);
  }

  @Override
  public void update(double xViewportOffset) {
    // do nothing since this is a static bg
  }
}
