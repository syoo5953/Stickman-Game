package stickman.model;

public
interface Observer { // Observer Pattern, It is with GameEngine.tick, ScoreManager, LevelManager now
  void update();
}
