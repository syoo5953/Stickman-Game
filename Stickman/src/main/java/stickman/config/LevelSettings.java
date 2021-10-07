package stickman.config;

import org.json.simple.JSONObject;

/** The configuration containing all user-defined settings. */
public class LevelSettings {

  private JSONObject entityData;
  private double width;
  private double floorHeight;
  private int levelNum;
  private int score;

  public LevelSettings(
      JSONObject entityData, double width, double floorHeight, int score, int levelNum) {
    this.entityData = entityData;
    this.width = width;
    this.floorHeight = floorHeight;
    this.levelNum = levelNum;
    this.score = score;
  }

  /**
   * Get the stored Entity data.
   *
   * @return a JSONObject containing Entity data.
   */
  public JSONObject getEntityData() {
    return entityData;
  }

  /**
   * Get the level's floor height.
   *
   * @return the floor height.
   */
  public double getFloorHeight() {
    return floorHeight;
  }

  /**
   * Get the level's width.
   *
   * @return the width.
   */
  public double getWidth() {
    return width;
  }

  public int getLevelNum() {
    return levelNum;
  }

  public int getScore() {
    return score;
  }
}
