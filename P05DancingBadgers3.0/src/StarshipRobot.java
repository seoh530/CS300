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

/**
 * This class models StarshipRobot objects which can be triggered to move or do some actions.
 * 
 * @author seyeongoh
 *
 */
public class StarshipRobot extends MovingThing {

  private Thing source;
  private Thing destination;

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The start point of
   * this this StarshipRobot is set to the (x,y) position of source. When created, a StarshipRobot
   * object must face its destination. [HINT] checking whether source.x is less than destination.x
   * can help determining whether a starship robot is facing right or not.
   * 
   * @param source      Thing object representing the start point of this StarshipRobot
   * @param destination Thing object representing the destination point of this StarshipRobot
   * @param speed       movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    // MovingThing constructor
    super(source.x, source.y, speed, "starshipRobot.png");
    this.source = source;
    this.destination = destination;
    // Determine if the robot is facing right or left based on the x positions of the source and
    // destination
    this.isFacingRight = (source.x < destination.x);

  }

  /**
   * Draws this StarshipRobot to the display window while it is in motion delivering food. This
   * method first prompts this StarshipRobot to go. Then, it draws it to the display window.
   */
  @Override
  public void draw() {
    go();
    super.draw();
  }

  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns
   *         false.
   */
  public boolean isOver(Thing thing) {
    // Get the dimensions of this StarshipRobot's image
    float robotWidth = this.image().width;
    float robotHeight = this.image().height;
    // Calculate the coordinates of the rectangle that represents this StarshipRobot's image
    float robotMinX = this.x - robotWidth / 2;
    float robotMinY = this.y - robotHeight / 2;
    float robotMaxX = robotMinX + robotWidth;
    float robotMaxY = robotMinY + robotHeight;
    // Get the dimensions of the other Thing's image
    float thingWidth = thing.image().width;
    float thingHeight = thing.image().height;
    // Calculate the coordinates of the rectangle that represents the other Thing's image
    float rightMinX = thing.x - thingWidth / 2;
    float rightMinY = thing.y - thingHeight / 2;
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
    float dx = destination.x - this.x;
    float dy = destination.y - this.y;
    int d = (int) Math.sqrt(dx * dx + dy * dy);

    float newX = this.x + (speed * dx / d);
    float newY = this.y + (speed * dy / d);

    this.x = newX;
    this.y = newY;
  }

  /**
   * Implements the action of this StarshipRobot. By default, an StarshipRobot object moves
   * back-and-forth between its source and destination. If the starship robot is over its
   * destination, this method: - switches the source and destination, - switches the value of
   * isFacingRight to its opposite (!isFacingRight), so that the starship robot faces the opposite
   * direction.
   * 
   */
  public void go() {
    // Switch source and destination
    moveTowardsDestination();
    // when the starship robot reach the destination
    if (this.isOver(destination) && !this.isOver(source)) {
      Thing temp = source;
      source = destination;
      destination = temp;

      // Switch the direction the robot is facing
      isFacingRight = !isFacingRight;
    }
  }
}
