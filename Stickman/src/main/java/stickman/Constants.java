package stickman;

/** The Constants class. */
public class Constants {

  public static final double HERO_DEFAULT_WIDTH = 17;
  public static final double HERO_DEFAULT_HEIGHT = 30;
  public static final double CLOUD_WIDTH = 80;
  public static final double CLOUD_HEIGHT = 20;
  public static final double ENEMY_WIDTH = 36;
  public static final double ENEMY_HEIGHT = 18;
  public static final double PLATFORM_WIDTH = 130;
  public static final double PLATFORM_HEIGHT = 15;
  public static final double FLAG_WIDTH = 26;
  public static final double FLAG_HEIGHT = 50;
  public static final double TILE_SIDE = 70;
  public static final double MAX_SAVE = 1;
  public static final int KILL_SCORE = 100;
  public static final int SCORE_LOOSE_RATE = 1;

  private Constants() {
    throw new IllegalArgumentException("Static class. Don't call the constructor");
  }
}
