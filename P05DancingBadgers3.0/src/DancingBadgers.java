//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Dancing Badgers 3.0
// Course: CS 300 Spring 2023
//
// Author: Seyeong Oh
// Email: soh87@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;

/**
 * This is the main class for the p05 Dancing Bangers III program
 * 
 * @author seyeongoh
 *
 */
public class DancingBadgers extends PApplet {

  /**
   * Driver method to run this graphic application
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
  }

  private static processing.core.PImage backgroundImage;
  private static int badgersCountMax;
  private boolean danceShowOn;
  private static Random randGen;
  private static ArrayList<Thing> things;
  // array storing badgers dance show steps
  private static DanceStep[] badgersDanceSteps = new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN, DanceStep.LEFT, DanceStep.RIGHT,
      DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  // array storing the positions of the dancing badgers at the start of the dance show
  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};


  /**
   * Sets the size of the display window of this graphic application
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /**
   * Sets the title and defines the initial environment properties of this graphic application. This
   * method initializes all the data fields defined in this class.
   */
  @Override
  public void setup() {
    // displays the title of the screen
    this.getSurface().setTitle("P5 Dancing Badgers");
    // sets the alignment of the text
    this.textAlign(3, 3);
    // interprets the x and y position of an image to its center
    this.imageMode(3);
    // confirms that this screen is "focused", meaning it is active and will accept mouse and
    // keyboard input.
    this.focused = true;
    // Initialize the random number generator
    randGen = new Random();
    // Load the background image
    backgroundImage = this.loadImage("images" + File.separator + "background.png");
    badgersCountMax = 5;

    Thing.setProcessing(this);
    things = new ArrayList<Thing>();
    things.add(new Thing(50, 50, "target.png")); // idx 0
    things.add(new Thing(750, 550, "target.png")); // idx 1
    things.add(new Thing(750, 50, "shoppingCounter.png")); // idx 2
    things.add(new Thing(50, 550, "shoppingCounter.png")); // idx 3


    StarshipRobot robot1 = new StarshipRobot(things.get(0), things.get(2), 3);
    things.add(robot1);// idx4
    StarshipRobot robot2 = new StarshipRobot(things.get(1), things.get(3), 5);
    things.add(robot2); // idx 5

    Basketball ball1 = new Basketball(50, 300);
    things.add(ball1);// idx 6
    Basketball ball2 = new Basketball(750, 300);
    things.add(ball2); // idx 7

  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  @Override
  public void draw() {
    background(color(255, 218, 185));
    image(backgroundImage, width / 2, height / 2);

    for (int i = 0; i < things.size(); i++) {
      things.get(i).draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse. This method iterates through the
   * list of things. If the mouse is over a Clickable object, it calls its mousePressed method, then
   * returns.
   */
  @Override
  public void mousePressed() {
    for (Thing thing : things) {
      if (thing instanceof Clickable && thing.isMouseOver()) {
        ((Clickable) thing).mousePressed();
        return;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released. This method calls the mousePressed()
   * method on every Clickable object stored in the things list.
   */
  @Override
  public void mouseReleased() {
    for (Thing thing : things) {
      if (thing instanceof Clickable && thing.isMouseOver()) {
        ((Clickable) thing).mouseReleased();
      }
    }
  }

  /**
   * Gets the number of Badger objects present in the basketball arena
   * 
   * @return numBadger the number of Badger objects present in the basketball arena
   */
  public int badgersCount() {
    int numBadger = 0;
    for (Thing thing : things) {
      if (thing instanceof Badger) {
        numBadger++;
      }
    }
    return numBadger;
  }

  /**
   * Sets the badgers start dance positions. The start dance positions of the badgerss are provided
   * in the startDancePositions array. The array startDancePositions contains badgersCountMax dance
   * positions. If there are fewer Badger objects in the basketball arena, they will be assigned the
   * first positions.
   */
  private void setStartDancePositions() {
    for (int i = 0; i < badgersCount(); i++) {
      things.remove(i + 8);
      Badger newBadger =
          new Badger(startDancePositions[i][0], startDancePositions[i][1], badgersDanceSteps);
      things.add(i + 8, newBadger);
    }
  }

  /**
   * Callback method called each time the user presses a key.
   * 
   * b-key: If the b-key is pressed and the danceShow is NOT on, a new badger is added to the list
   * of things. Up to badgersCountMax can be added to the basketball arena.
   * 
   * c-key: If the c-key is pressed, the danceShow is terminated (danceShowOn set to false), and ALL
   * MovingThing objects are removed from the list of things. This key removes MovingThing objects
   * ONLY.
   * 
   * d-key: This key starts the dance show if the danceShowOn was false, and there is at least one
   * Badger object in the basketball arena. Starting the dance show, sets the danceShowOn to true,
   * sets the start dance positions of the Badger objects, and calls the startDancing() method on
   * every Badger object present in the list of things. Pressing the d-key when the danceShowOn is
   * true or when there are no Badger objects present in the basketball arena has no effect.
   * 
   * r-key: If the danceShow is NOT on and the d-key is pressed when the mouse is over a Badger
   * object, this badger is removed from the list of things. If the mouse is over more than one
   * badger, the badger at the lowest index position will be deleted.
   * 
   * s-key: If the s-key is pressed, the dancdShow is terminated and all the Badger objects stop
   * dancing. Pressing the s-key does not remove any thing.
   */
  @Override
  public void keyPressed() {
    switch (Character.toUpperCase(key)) {
      case 'B': // make badger
        if (badgersCount() < badgersCountMax && !danceShowOn) {
          things
              .add(new Badger(randGen.nextInt(width), randGen.nextInt(height), badgersDanceSteps));
        }
        break;

      case 'R': // delete the badger being pressed
        for (int i = 0; i < things.size(); i++) {
          if (things.get(i) instanceof Badger && things.get(i).isMouseOver() && !danceShowOn) {
            things.remove(i);
          }
        }
        break;

      case 'C': // clear all badger and startship
        danceShowOn = false;
        int index = 0;
        while (index < things.size()) {
          if (things.get(index) instanceof MovingThing) {
            things.remove(index);
          } else
            index++;
        }
        break;

      case 'D': // make badgers dance
        if (badgersCount() > 0 && !danceShowOn) {
          danceShowOn = true;
          setStartDancePositions();
          for (Thing thing : things) {
            if (thing instanceof Badger) {
              ((Badger) thing).startDancing();
            }
          }
        }
        break;

      case 'S': // make badgers stop dancing
        danceShowOn = false;
        for (Thing thing : things) {
          if (thing instanceof Badger) {
            ((Badger) thing).stopDancing();
          }
        }
        break;
    }
  }
}