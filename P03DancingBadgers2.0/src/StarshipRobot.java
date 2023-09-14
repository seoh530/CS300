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

/**
 * This class models Starship Robot objects delivering food to badger fans
 *
 */
public class StarshipRobot {
  private static processing.core.PApplet processing;
  private processing.core.PImage image;
  private int speed; // movement speed of the StarshipRobot
  private float x; // x-position of the StarshipRobot
  private float y; // y-position of the StarshupRobot
  private Thing source;
  private Thing destination;


  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The (x,y) position of
   * this StarshipRobot is set to the (x,y) position of source.
   * 
   * @param source:      Thing object representing the start point of this StarshipRobot
   * @param destination: Thing object representing the destination point of this StarshipRobot
   * @param speed:       movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {

    this.source = source;
    this.destination = destination;
    this.speed = speed;

    this.x = source.getX(); // initialize x of its source
    this.y = source.getY(); // initialize y of its source

    this.image = processing.loadImage("images" + File.separator + "starshipRobot.png");
  }

  /**
   * Returns a reference to the image of this starship robot
   * 
   * @return the image of type PImage of the starship robot object
   */
  public processing.core.PImage image() {
    return this.image;
  }


  /**
   * Returns the x-position of this thing in the display window
   * 
   * @return the X coordinate of the thing position
   * 
   */
  public float getX() {
    return this.x;
  }

  /**
   * Returns the y-position of this thing in the display window
   * 
   * @return the y-position of the thing
   * 
   */
  public float getY() {
    return this.y;
  }

  /**
   * Sets the x-position of this thing in the display window
   * 
   * @param x: the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this thing in the display window
   * 
   * @param y: the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Sets the PApplet object display window where this StarshipRobot will be drawn. The processing
   * PApplet data field is set to Badger.processing since Badger and StarshipRobot objects are going
   * to be displayed to the same screen.
   */
  public static void setProcessing() {
    StarshipRobot.processing = Badger.getProcessing();
  }

  /**
   * Draws this StarshipRobot to the display window while it is in action delivering food
   */
  public void draw() {
    processing.image(image, x, y);
    go();

  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing: a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   */
  public boolean isOver(Thing thing) {
    // Get the dimensions of this StarshipRobot's image
    float robotWidth = this.image().width;
    float robotHeight = this.image().height;
    // Calculate the coordinates of the rectangle that represents this StarshipRobot's image
    float robotMinX = this.getX() - robotWidth / 2;
    float robotMinY = this.getY() - robotHeight / 2;
    float robotMaxX = robotMinX + robotWidth;
    float robotMaxY = robotMinY + robotHeight;
    // Get the dimensions of the other Thing's image
    float thingWidth = thing.image().width;
    float thingHeight = thing.image().height;
    // Calculate the coordinates of the rectangle that represents the other Thing's image
    float rightMinX = thing.getX() - thingWidth / 2;
    float rightMinY = thing.getY() - thingHeight / 2;
    float leftMaxX = rightMinX + thingWidth;
    float leftMaxY = rightMinY + thingHeight;
    // Check if the two rectangles overlap
    if ((robotMinX < leftMaxX) && (rightMinX < robotMaxX) && (robotMinY < leftMaxY)
        && (rightMinY < robotMaxY))
      return true;// when robot is over the Thing object
    return false;
  }


  /**
   * Helper method to move this StarshipRobot towards its destination
   */
  private void moveTowardsDestination() {
    float dx = destination.getX() - this.x;
    float dy = destination.getY() - this.y;
    int d = (int) Math.sqrt(dx * dx + dy * dy);

    float newX = this.x + (speed * dx / d);
    float newY = this.y + (speed * dy / d);

    this.x = newX;
    this.y = newY;
  }

  /**
   * Implements the behavior of this StarshipRobot going back-and-forth between its source and
   * destination.
   */
  public void go() {
    if (isOver(destination)) {
      // if we've reached the destination, swap source and destination
      Thing temp = destination;
      destination = source;
      source = temp;
    } else {
      // move towards the destination
      moveTowardsDestination();
    }
  }

}
