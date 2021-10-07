package stickman.model.runtimedata;

import java.time.Duration;
import java.time.Instant;

public class TimeManager { // Manage StartTime
  private Instant gameStartTime;

  public String getPlayTimeByPrettyTimeFormat() {
    Duration elapsed = Duration.between(gameStartTime, Instant.now());
    return elapsed.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
  }

  public void setStartTimeToNow() {
    gameStartTime = Instant.now();
  }

  public void updateActualPlayTime() {
    gameStartTime.plusMillis(Duration.between(gameStartTime, Instant.now()).toMillis());
  }
}
