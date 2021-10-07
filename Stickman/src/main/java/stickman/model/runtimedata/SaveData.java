package stickman.model.runtimedata;

import java.time.Instant;
import stickman.model.level.Level;

public class SaveData { // Memento Class
  private Level level;
  private Instant savedTime;
  private int totalScore;
  private int levelScore;

  public SaveData(Level level, Instant savedTime, int totalScore, int levelScore) {
    this.level = level;
    this.savedTime = savedTime;
    this.totalScore = totalScore;
    this.levelScore = levelScore;
  }

  public Level getLevel() {
    return level;
  }

  public Instant getSavedTime() {
    return savedTime;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public int getLevelScore() {
    return levelScore;
  }
}
