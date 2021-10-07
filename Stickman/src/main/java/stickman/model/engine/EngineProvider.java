package stickman.model.engine;

public class EngineProvider {
  private static GameEngine currentEngine;

  private EngineProvider() {
    throw new IllegalArgumentException("Static class. Don't call the constructor");
  }

  public static GameEngine getCurrentEngine() {
    return currentEngine;
  }

  public static void setCurrentEngine(GameEngine engine) {
    currentEngine = engine;
  }
}
