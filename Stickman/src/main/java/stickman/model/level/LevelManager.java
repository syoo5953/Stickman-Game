package stickman.model.level;

import stickman.config.ConfigurationProvider;
import stickman.model.runtimedata.ScoreManager;

public interface LevelManager { // Manage Levels

  void startLevel(ConfigurationProvider provider, ScoreManager scoreManager);

  void endGame(String outcome, int score, String timeFormat);

  int goToNextLevel(ConfigurationProvider provider, ScoreManager scoreManager);

  Level getCurrentLevel();

  void loadLevelFromSave(Level level);
}
