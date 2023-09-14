//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Dancing Badgers 2.0
// Course: CS 300 Spring 2023
//
// Author: Seyeong Oh
// Email: soh87@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Jiho Han
// Partner Email: jhan292@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _O_ Write-up states that pair programming is allowed for this assignment.
// _O_ We have both read and understand the course Pair Programming Policy.
// _O_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.util.Random;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // backgound image
  private static ArrayList<Badger> badgers;
  private static ArrayList<Thing> things;
  private static ArrayList<StarshipRobot> robots;
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax; // maximum number of badgers


  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    randGen = new Random();
    badgersCountMax = 5; // initialize the badger number

    Thing.setProcessing();
    things = new ArrayList<Thing>();
    things.add(new Thing(50, 50, "target.png")); // idx 0
    things.add(new Thing(750, 550, "target.png")); // idx 1
    things.add(new Thing(750, 50, "shoppingCounter.png")); // idx 2
    things.add(new Thing(50, 550, "shoppingCounter.png")); // idx 3

    StarshipRobot.setProcessing();
    robots = new ArrayList<StarshipRobot>();
    // create first StarshipRobot
    robots.add(new StarshipRobot(things.get(2), things.get(0), 3));
    // create second StarshipRobot
    robots.add(new StarshipRobot(things.get(3), things.get(1), 3));
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {

    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);


    for (int i = 0; i < things.size(); i++) {
      things.get(i).draw();
    }

    for (int i = 0; i < robots.size(); i++) {
      robots.get(i).draw();
    }

    for (int i = 0; i < badgers.size(); i++) {
      badgers.get(i).draw();
    }

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.size(); i++) {
      if (badgers.get(i).isMouseOver()) {
        badgers.get(i).startDragging();
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.size(); i++) {
      badgers.get(i).stopDragging();
    }
  }


  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        if (badgers.size() < badgersCountMax) {
          badgers
              .add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
        }
        break;

      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i).isMouseOver()) {
            badgers.remove(i);
            break;
          }
        }
        break;
    }
  }
}
