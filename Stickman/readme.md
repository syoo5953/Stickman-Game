# Stickman

### This readme file is modified by Sungki Yoo in 03/11/2019.

### Project Information
Stickman is written in Java and adheres to the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

### How to Run
This project requires [Java 11.0.4](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
) or above, and [Gradle 5.6.1](https://gradle.org/releases/) or above.

To run the application, navigate to the root of the project folder and execute `gradle run` in a terminal.
In its current state, the application should only be executed with `gradle run`. In the future, an executable file is planned.

In order to run inbuilt tests, use `gradle build` or `gradle test`.

Please note that the program cannot be launched via `gradle build run` or similar because of the test runner limitations.

### Configuration
Almost every aspect of the game can be configured. However, the configuration file has a rigorous format which must be adhered to for program execution to begin.

If you wish to edit this configuration, your json file should be placed in `src/main/resources/`, and the filename should be passed in the creation of the GameEngine at line 27 in `App.java`. The default configuration name is `config.json`.

If any configuration keys are missing, or the configuration keys do not contain a valid value, the application will fall back on default vaules.

The configuration file must contain the following elements at a minimum - even if the values are blank, the program requires the structure for execution:

```json
{
  "count": {},
  "levels": {
    "0": {
       "settings": {},
       "entities": {}
    },
    "1": {
       "settings": {},
       "entities": {}
    }...
  } 
}
```

After this, the rest of the configuration file is completely up to user discretion.

#### The `settings` Object

`width` (double)
- Determines the size of the level.
- Used to limit the spawning of ground tiles.
- Possible values: any double value
- Default: `1100.0`

`floorHeight` (double)
- Determines the floor height of the level
- Used to determine where ground tiles should be placed on the y axis.
- Possible values: any double value
- Default: `350.0`

#### The `entities` Object

The entities option may contain any of 5 different objects. Apart from the `hero` and `flag` sections, any number of child objects can be defined in each section.

```json
"entities": {

  "hero": {},
  "flag": {},
  "clouds": {},
  "enemies": {},
  "platforms": {}

}
```

Entities can be defined dynamically by adding a child object to any section. Within each section, each child object must have a unique name, for example:

```json
...
"clouds": {
  
  "cloud-1": {
    "xPos": 100.0,
    "yPos": 200.0,
    "velocity": 2.5
  },

  "cloud-2": {
    "xPos": 50.0,
    "yPos": 60.0,
    "velocity": -3.9
  },

  "yetanothercloud": {
    "xPos": ?,
    "yPos": ?,
    "velocity": ?
  }


},
...
```

##### Global Parameters

All child objects can take the following parameters:

`xPos` (double)
- Determines the spawn x-position of the entity
- Possible values: any double

`yPos` (double)
- Determines the spawn y-position of the entity
- Possible values: any double

##### Other Parameters

`hero` (child object):
- `size` (string)
    - the size of the hero
    - possible values: `tiny`, `normal`, `large`, `giant` in increasing order of size
    - default: `normal`
   
- `lives` (string)
    - the amount of lives that the hero will have
    - possible values: any integer
    - default: `3`

`cloud` (child object):
- `velocity` (double)
    - the cloud's velocity
    - possible values: any double. negative values will make the cloud move in the opposite direction.
    - default: `2.0`
    
`enemy` (child object):
- `strategy` (string)
    - the enemy's movement strategy
    - possible values: `passive` - the enemy does nothing; `hostile` - the enemy targets the hero; `shy` - the enemy runs from the hero.
    - default: `passive`
    

#### Example Configuration

```json
"levels": {

  "settings": {
    "width": 1100.0,
    "floorHeight": 350.0
  },

  "entities": {

    "hero": {
      "size": "tiny",
      "xPos": 20.0,
      "lives": 3
    },
 
    "flag": {
      "xPos": 700.0,
      "yPos": 0.0
    },

    "enemies": {

      "enemy-1": {
        "xPos": 300.0,
        "strategy": "passive"
      },

      "enemy-2": {
        "xPos": 500.0,
        "strategy": "hostile"
      },

      "enemy-3": {
        "xPos": 600.0,
        "strategy": "shy"
      }     
        
    }      


  }

}
```

The number of levels and its entities can be added by adding more level in 'config.file'. Also you need to change the count value
to indicate the number of levels you are going to have.

Example:

"count": value in int,

"levels" {

   "0": {}

   "1":{}

   "2":{}

   and so on...
}

### Documentation
Javadocs can be generated for the program by executing `gradle javadoc`, and then retrieving or accessing the Javadocs at `build/docs`.
