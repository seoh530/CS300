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

/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display
 * window of a graphic application.
 * 
 * @author seyeongoh
 *
 */
public class Thing {

  protected static processing.core.PApplet processing;
  private processing.core.PImage image;
  protected float x;
  protected float y;

  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x             x-position of this thing in the display window
   * @param y             y-position of this thing in the display window
   * @param imageFilename filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFilename) {
    this.x = x; // x: x-position of this Thing
    this.y = y; // y: y-position of this Thing
    this.image = processing.loadImage("images" + File.separator + imageFilename);
  }

  /**
   * Draws this thing to the display window at its current (x,y) position
   */
  public void draw() {
    processing.image(image, x, y);
  }

  /**
   * Sets the PApplet object display window where this Thing object will be drawn
   * 
   * @param processing PApplet object that represents the display window
   */
  public static void setProcessing(processing.core.PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return this.image;
  }

  /**
   * Checks if the mouse is over this Thing object
   * 
   * @return true if the mouse is over this Thing, otherwise returns false.
   */
  public boolean isMouseOver() {
    int thingWidth = this.image().width;
    int thingHeight = this.image().height;

    float left = this.x - (thingWidth / 2);
    float right = this.x + (thingWidth / 2);
    float top = this.y - (thingHeight / 2);
    float bottom = this.y + (thingHeight / 2);

    return (processing.mouseX >= left && processing.mouseX <= right && processing.mouseY >= top
        && processing.mouseY <= bottom);
  }
}
