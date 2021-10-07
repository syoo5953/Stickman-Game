package stickman.view;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import stickman.model.engine.EngineProvider;
import stickman.model.runtimedata.ScoreManager;

public class TextDrawer implements UIDrawer { // draw text in scene. used for Score

  private Pane pane;
  private Text totalText;
  private Text levelText;

  @Override
  public void draw(Pane pane) {
    this.pane = pane;
    totalText = new Text();
    levelText = new Text();
    totalText.setFont(new Font(20));
    levelText.setFont(new Font(20));
    this.pane.getChildren().addAll(totalText, levelText);
  }

  @Override
  public void update(double xViewportOffset, double yViewportOffset) {
    ScoreManager scoreManager = EngineProvider.getCurrentEngine().getScoreManager();
    totalText.setText("total : " + scoreManager.getTotalScore());
    levelText.setText("level : " + scoreManager.getLevelscore());
    totalText.setX(10);
    totalText.setY(18);
    levelText.setX(10);
    levelText.setY(40);
  }
}
