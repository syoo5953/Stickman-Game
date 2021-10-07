package stickman.model.runtimedata;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import stickman.Constants;
import stickman.model.level.Level;
import stickman.model.level.LevelManager;

public class SaveManager { // CareTaker Class
  static Queue<SaveData> saveDatas = new LinkedList<>();

  // private SaveManager(){
  // throw new IllegalArgumentException("Static class. Don't call the constructor");
  // }

  public static void save(Level level, ScoreManager scoreManager) {
    SaveData saveData =
        new SaveData(
            (Level) level.clone(),
            Instant.now(),
            scoreManager.getTotalScore(),
            scoreManager.getLevelscore());
    if (saveDatas.size() >= Constants.MAX_SAVE) {
      saveDatas.poll();
      saveDatas.add(saveData);
    } else {
      saveDatas.add(saveData);
    }
  }

  public static void load(
      TimeManager timeManager, LevelManager levelManager, ScoreManager scoreManager) {
    if (saveDatas.isEmpty()) {
      return;
    }
    SaveData data = saveDatas.poll();
    timeManager.updateActualPlayTime();
    levelManager.loadLevelFromSave(data.getLevel());
    scoreManager.restoreFromSave(data);
  }
}
