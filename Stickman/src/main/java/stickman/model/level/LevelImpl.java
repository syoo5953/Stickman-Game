package stickman.model.level;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import stickman.config.ConfigurationProvider;
import stickman.config.LevelSettings;
import stickman.model.engine.EngineProvider;
import stickman.model.entity.Controllable;
import stickman.model.entity.Entity;
import stickman.model.entity.impl.AbstractEntity;
import stickman.model.entity.impl.HeroEntity;
import stickman.model.entity.spawner.EntitySpawner;
import stickman.model.entity.spawner.EntitySpawnerImpl;
import stickman.model.level.collision.CollisionHandler;

/** The implementation class of the Level interface. */
public class LevelImpl implements Level, Cloneable {

  private EntitySpawner entitySpawner;
  private CollisionHandler collisionHandler;

  private List<Entity> entities;
  private Controllable hero;

  private int levelNum;

  private int score;
  private double width;
  private double floorHeight;
  private Instant startTime;

  public LevelImpl(LevelSettings settings) {
    this.entitySpawner = new EntitySpawnerImpl();
    this.collisionHandler = new CollisionHandler();
    this.entities = new ArrayList<>();
    this.levelNum = settings.getLevelNum();
    this.width = settings.getWidth();
    this.floorHeight = settings.getFloorHeight();
    this.score = settings.getScore();
  }

  @Override
  public void start(ConfigurationProvider provider) {

    spawnHero(provider);
    spawnEntities(provider);
    populateScene(provider);
    startTime = Instant.now();
  }

  @Override
  public void finish(String outcome) {
    EngineProvider.getCurrentEngine().finish(outcome);
  }

  @Override
  public int getLevelNum() {
    return levelNum;
  }

  @Override
  public List<Entity> getEntities() {
    return this.entities;
  }

  public void setEntities(List<Entity> entities) {
    this.entities = entities;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getFloorHeight() {
    return this.floorHeight;
  }

  @Override
  public double getHeroX() {
    return hero.getXPos();
  }

  @Override
  public double getHeroY() {
    return hero.getYPos();
  }

  @Override
  public Instant getStartTime() {
    return startTime;
  }

  @Override
  public int getScore() {
    return score;
  }

  @Override
  public boolean jump() {
    return hero.setJumping(true);
  }

  @Override
  public boolean moveLeft() {
    return hero.setMovingLeft(true);
  }

  @Override
  public boolean moveRight() {
    return hero.setMovingRight(true);
  }

  @Override
  public boolean stopMoving() {
    hero.setMovingRight(false);
    hero.setMovingLeft(false);
    return true;
  }

  @Override
  public void tick() {
    entities.forEach(e -> e.move(this));
    collisionHandler.detectCollisions(this, hero);
  }

  private void spawnHero(ConfigurationProvider provider) {
    this.hero = entitySpawner.createHero(provider, this);
    entities.add(this.hero);
  }

  private void spawnEntities(ConfigurationProvider provider) {
    entities.addAll(entitySpawner.createEntities(provider, this));
  }

  private void populateScene(ConfigurationProvider provider) {
    entities.addAll(entitySpawner.createBackgroundEntities(provider, this));
  }

  public void setHero(HeroEntity hero) {
    this.hero = hero;
  }

  public Object clone() {
    Level level = null;

    try {
      level = (Level) super.clone();
      List<Entity> newEntities = new ArrayList<>();
      for (Entity entity : entities) {
        AbstractEntity abEntity = (AbstractEntity) entity;
        Entity newEntity = (Entity) abEntity.clone();
        if (newEntity instanceof HeroEntity) level.setHero((HeroEntity) newEntity);
        newEntities.add(newEntity);
      }
      level.setEntities(newEntities);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    return level;
  }
}
