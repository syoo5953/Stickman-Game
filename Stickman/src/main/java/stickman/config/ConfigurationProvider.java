package stickman.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stickman.config.parser.LevelSettingsParser;

/** A data storage object for storing Level and Entity data */
public class ConfigurationProvider {

  private JSONObject configObject;
  private List<LevelSettings> levels;

  public ConfigurationProvider(String configPath) {

    try (Reader fileReader =
        new InputStreamReader(getClass().getResourceAsStream("/" + configPath))) {
      configObject = (JSONObject) new JSONParser().parse(fileReader);
      levels = LevelSettingsParser.parseLevels(configObject);
      fileReader.close();
    } catch (NullPointerException | IOException | ParseException e) {
      System.out.println("Error: Configuration file missing or malformed.");
      System.out.println("Exiting Program.");
      throw new IllegalArgumentException("Missing or malformed configuration file");
    }
  }

  /**
   * Gets the LevelSettings object, or Creates the LevelSettings object if non-existent.
   *
   * @return the created/stored LevelSettings object.
   */
  public LevelSettings getLevelData(int levelNum) {

    if (levelNum >= levels.size()) {
      return null;
    }
    return levels.get(levelNum);
  }
}
