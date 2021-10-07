package stickman.model.level;

import stickman.config.ConfigurationProvider;
import stickman.config.LevelSettings;
import stickman.model.Observer;
import stickman.model.engine.GameEngine;
import stickman.model.runtimedata.ScoreManager;

public class LevelManagerImpl implements LevelManager, Observer {
  private Level currentLevel;

  public LevelManagerImpl(Level level, GameEngine model) {
    this.currentLevel = level;
    model.addObserver(this);
  }

  @Override
  public int goToNextLevel(ConfigurationProvider provider, ScoreManager scoreManager) {
    int nextLevelNum = currentLevel.getLevelNum() + 1;
    LevelSettings levelSettings = provider.getLevelData(nextLevelNum);
    if (levelSettings == null) {
      return -1;
    }
    currentLevel = new LevelImpl(levelSettings);
    startLevel(provider, scoreManager);
    return nextLevelNum;
  }

  @Override
  public void startLevel(ConfigurationProvider provider, ScoreManager scoreManager) {
    currentLevel.start(provider);
    scoreManager.setLevelscore(currentLevel.getScore());
  }

  @Override
  public void endGame(String outcome, int score, String timeFormat) {
    int repeat;
    if (outcome.equalsIgnoreCase("DEATH")) {
      System.out.println("\n=== YOU DIED! ===");
      System.out.println("Oops! You should be more careful next time.");
      repeat = 17;
    } else if (outcome.equalsIgnoreCase("CLEAR")) {
      System.out.println("\n=== YOU WON! ===");
      System.out.println("Congratulations! You finished the level.");
      System.out.println("Your score: " + score);
      repeat = 16;
    } else {
      System.out.println("OutCome is " + outcome);
      throw new IllegalArgumentException(
          "Why did you call this? I don't know why this game ends!!");
    }

    System.out.println("Your time: " + timeFormat);
    System.out.println("=".repeat(repeat));

    System.exit(0);
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  @Override
  public void loadLevelFromSave(Level level) {
    currentLevel = level;
  }

  @Override
  public void update() {
    currentLevel.tick();
  }
}
