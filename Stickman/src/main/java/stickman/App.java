package stickman;

import javafx.application.Application;
import javafx.stage.Stage;
import stickman.model.engine.EngineProvider;
import stickman.model.engine.GameEngineImpl;
import stickman.view.GameManager;

/** The Application implementation for App */
public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    String s = "Java 11 sanity check";
    if (s.isBlank()) {
      throw new IllegalStateException(
          "You must be running Java 11+. You won't ever see this exception though"
              + " as your code will fail to compile on Java 10 and below.");
    }

    EngineProvider.setCurrentEngine(new GameEngineImpl("config.json"));
    GameManager window = new GameManager(640, 400);

    window.run();

    primaryStage.setTitle("Stickman");
    primaryStage.setScene(window.getScene());
    primaryStage.show();

    window.run();
  }
}
