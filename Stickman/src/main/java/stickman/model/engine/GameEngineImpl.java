package stickman.model.engine;

import java.util.ArrayList;
import java.util.List;
import stickman.config.ConfigurationProvider;
import stickman.model.Observer;
import stickman.model.level.Level;
import stickman.model.level.LevelImpl;
import stickman.model.level.LevelManager;
import stickman.model.level.LevelManagerImpl;
import stickman.model.runtimedata.SaveManager;
import stickman.model.runtimedata.ScoreManager;
import stickman.model.runtimedata.TimeManager;

/** The implementation class of the GameEngine interface. */
public class GameEngineImpl implements GameEngine {
  private ConfigurationProvider provider;
  private ScoreManager scoreManager;
  private LevelManager levelManager;
  private TimeManager timeManager;
  private List<Observer> observerList;

  public GameEngineImpl(String configPath) {
    observerList = new ArrayList<>();
    provider = new ConfigurationProvider(configPath);
    scoreManager = new ScoreManager(this);
    levelManager = new LevelManagerImpl(new LevelImpl(provider.getLevelData(0)), this);
    timeManager = new TimeManager();
    startLevel();
  }

  @Override
  public Level getCurrentLevel() {
    return this.levelManager.getCurrentLevel();
  }

  @Override
  public ScoreManager getScoreManager() {
    return this.scoreManager;
  }

  @Override
  public void addObserver(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observerList.remove(observer);
  }

  private void startLevel() {
    timeManager.setStartTimeToNow();
    levelManager.startLevel(provider, scoreManager);
  }

  @Override
  public boolean jump() {
    return levelManager.getCurrentLevel().jump();
  }

  @Override
  public boolean moveLeft() {
    return levelManager.getCurrentLevel().moveLeft();
  }

  @Override
  public boolean moveRight() {
    return levelManager.getCurrentLevel().moveRight();
  }

  @Override
  public boolean stopMoving() {
    return levelManager.getCurrentLevel().stopMoving();
  }

  @Override
  public void finish(String outcome) {
    if (outcome.equalsIgnoreCase("FLAG")) {
      if (levelManager.goToNextLevel(provider, scoreManager) == -1) endGame("CLEAR");
    } else if (outcome.equalsIgnoreCase("DEATH")) {
      endGame(outcome);
    }
  }

  private void endGame(String outcome) {
    levelManager.endGame(
        outcome, scoreManager.getTotalScore(), timeManager.getPlayTimeByPrettyTimeFormat());
  }

  @Override
  public void saveLevel() {
    SaveManager.save(levelManager.getCurrentLevel(), scoreManager);
  }

  @Override
  public void loadLevel() {
    SaveManager.load(timeManager, levelManager, scoreManager);
  }

  @Override
  public void tick() {
    for (Observer observer : observerList) {
      observer.update();
    }
  }
}
