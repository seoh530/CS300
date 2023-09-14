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
import java.util.Random;
import java.io.File;
import processing.core.PImage;

/**
 * This class is developing a graphical application to simulate badgers dance show on a basketball
 * arena. In this P02, there are 6 methods and 1 main methods. Each method works for creating
 * basketball area window and badgers when I pressed 'b' and removes badgers when I press'r'.
 *
 * @author Seyeong Oh
 */
public class DancingBadgers {
  // PImage object that represents the background image
  private static PImage backgroundImage;
  // perfect size array storing the badgers present in this application
  private static Badger[] badgers;
  // Generator of random numbers
  private static Random randGen;

  /**
   * set up the application display window.
   */
  public static void setup() {
    randGen = new Random();
    // load the image of the background
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    // the array badgers which can store up to 5 Badger references (initializing as null)
    badgers = new Badger[5];
  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    // given background color
    Utility.background(Utility.color(255, 218, 185));
    // Draw the background image to the center of the screen
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);

    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null)
        badgers[i].draw();
    }
  }

  /**
   * Checks if the mouse is over a specific Badger whose reference is provided as input parameter.
   * 
   * @param Badger reference to a specific Badger object
   * @return true if the mouse is over the specific Badger object passed as input (i.e. over the
   *         image of the Badger), false otherwise
   */
  public static boolean isMouseOver(Badger Badger) {

    if (Utility.mouseX() < Badger.getX() + Badger.image().width / 2
        && Utility.mouseX() > Badger.getX() - Badger.image().width / 2
        && Utility.mouseY() < Badger.getY() + Badger.image().height / 2
        && Utility.mouseY() > Badger.getY() - Badger.image().height / 2) {
      return true;
    }
    return false;
  }

  /**
   * Callback method called each time the user presses the mouse.
   */
  public static void mousePressed() {
    // only the Badger stored at the lowest index array will be dragged
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] != null && isMouseOver(badgers[i])) {
        badgers[i].startDragging();
        break;
      }
    }
  }

  /**
   * Callback method called each time the user releases the mouse.
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.length; i++) {
      if (badgers[i] == null)
        continue;
      else
        badgers[i].stopDragging();

    }
  }


  /**
   * Callback method called each time the mouse is released.
   */
  public static void keyPressed() {
    float xPos = (float) randGen.nextInt(Utility.width());
    float yPos = (float) randGen.nextInt(Utility.height());

    if (Utility.key() == 'b' || Utility.key() == 'B') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] == null) {
          badgers[i] = new Badger(xPos, yPos);
          break;
        }
      }
    } else if (Utility.key() == 'r' || Utility.key() == 'R') {
      for (int i = 0; i < badgers.length; i++) {
        if (badgers[i] != null && isMouseOver(badgers[i])) {
          badgers[i] = null;
          break;
        }
      }
    }
  }

  /**
   * This is the main method that makes DancingBadger program run.
   */
  public static void main(String[] args) {
    Utility.runApplication();

  }

}


