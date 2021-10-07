package stickman.model.runtimedata;

import java.time.Duration;
import java.time.Instant;
import stickman.Constants;
import stickman.model.Observer;
import stickman.model.engine.GameEngine;

public class ScoreManager implements Observer { // Manage Scores
  private int totalScore;
  private int levelscore;
  private long miliTime;
  private Instant lastTime;

  public ScoreManager(GameEngine model) {
    lastTime = Instant.now();
    totalScore = 0;
    levelscore = 0;
    model.addObserver(this);
  }

  public void plusScore(int score) {
    levelscore += score;
    totalScore += score;
  }

  public void minusScore(int score) {
    levelscore -= score;
    totalScore -= score;
    if (levelscore <= 0) levelscore = 0;
    if (totalScore <= 0) totalScore = 0;
  }

  public int getLevelscore() {
    return levelscore;
  }

  public void setLevelscore(int startScore) {
    levelscore = startScore;
    totalScore += startScore;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public void restoreFromSave(SaveData data) {
    levelscore = data.getLevelScore();
    totalScore = data.getTotalScore();
  }

  @Override
  public void update() {
    if (miliTime < 1000) {
      Duration term = Duration.between(lastTime, Instant.now());
      miliTime += term.toMillis();
      lastTime = Instant.now();
    } else {
      minusScore(Constants.SCORE_LOOSE_RATE);
      miliTime = 0;
    }
  }
}
