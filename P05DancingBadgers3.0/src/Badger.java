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
 * This class models a Badger object in the P05 Dancing Badgers III programming assignment
 * 
 * @author seyeongoh
 *
 */
public class Badger extends MovingThing implements Clickable {

  private boolean isDragging;
  private boolean isDancing;
  private static int oldMouseX;
  private static int oldMouseY;
  private DanceStep[] danceSteps;
  private int stepIndex;
  // stores the next dance (x, y) position of this Badger. nextDancePosition[0]: x-position
  // nextDancePosition[1]: y-position
  private float[] nextDancePosition;

  /**
   * Creates a new Badger object positioned at a specific position of the display window and whose
   * moving speed is 2. When created, a new badger is not dragging and is not dancing. This
   * constructor also sets the danceSteps of the created Badger to the one provided as input and
   * initializes stepIndex to 1.
   * 
   * @param x          x-position of this Badger object within the display window
   * @param y          y-position of this Badger object within the display window
   * @param danceSteps perfect-size array storing the dance steps of this badger
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    this.danceSteps = danceSteps;
    this.isDancing = false;
    this.isDragging = false;
    oldMouseX = 0;
    oldMouseY = 0;
    this.stepIndex = 1;
    this.nextDancePosition = new float[] {x, y};
  }

  /**
   * Draws this badger to the display window. Also, this method: - calls the drag() behavior if this
   * Badger is dragging - calls the dance() behavior if this Badger is dancing To draw the badger to
   * the screen, think of using partial overriding (super.draw()) as the image of the Badger is not
   * directly visible here.
   */
  @Override
  public void draw() {
    // Call super class draw method to draw the badger image
    super.draw();
    // Call drag method if this Badger is dragging
    if (isDragging) {
      drag();
    }
    // Call dance method if this Badger is dancing
    if (isDancing) {
      dance();
    }

  }

  /**
   * Checks whether this badger is being dragged
   * 
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Helper method to drag this Badger object to follow the mouse moves
   */
  private void drag() {
    int newMouseX = processing.mouseX;
    int newMouseY = processing.mouseY;
    // Calculate the distance between the old and new mouse position
    int dx = newMouseX - oldMouseX;
    int dy = newMouseY - oldMouseY;
    // Move the badger to the new position
    x += dx;
    y += dy;
    // Update old mouse position to the current mouse position
    if (x > 0)
      x = Math.min(x, processing.width);
    else
      x = 0;
    if (y > 0)
      y = Math.min(y, processing.height);
    else
      y = 0;
    oldMouseX = newMouseX;
    oldMouseY = newMouseY;
  }

  /**
   * Starts dragging this badger
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    drag();
  }

  /**
   * Stops dragging this badger
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Defines the behavior of this Badger when it is clicked. If the mouse is over this badger and
   * this badger is NOT dancing, this method starts dragging this badger.
   */
  public void mousePressed() {
    if (isMouseOver()) {
      if (!isDancing) {
        startDragging();
      }
    }
  }

  /**
   * Defines the behavior of this Badger when the mouse is released. If the mouse is released, this
   * badger stops dragging.
   */
  public void mouseReleased() {
    stopDragging();
  }

  /**
   * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks
   * whether this Badger is facing right and updates the isFacingRight data field accordingly. After
   * making one move dance, a badger is facing right if the x-move towards its next dance position
   * is positive, otherwise, it is facing left.
   * 
   * @return true if this Badger almost reached its next dance position, meaning that the distance
   *         to its next dance position is less than 2 times its speed. Otherwise, return false.
   */
  private boolean makeMoveDance() {
    // Calculate the distance to the next dance position
    float dx = nextDancePosition[0] - x;
    float dy = nextDancePosition[1] - y;
    // Calculate the distance and direction based on dx and dy
    float distance = (float) Math.sqrt(dx * dx + dy * dy);
    float directionX = dx / distance;
    float directionY = dy / distance;
    // Calculate the distance based on speed and direction
    float amountX = directionX * speed;
    float amountY = directionY * speed;
    // add amountX and amountY to badger's position
    x += amountX;
    y += amountY;

    isFacingRight = (amountX > 0);
    // Check if the badger almost reached the next dance position
    return (distance < speed * 2);
  }

  /**
   * Implements the dance behavior of this Badger. This method prompts the Badger to make one move
   * dance. If the makeMoveDance method call returns true (meaning the badger almost reached its
   * nextDancePosition), this method MUST: - update its next dance position (see
   * DanceStep.getPositionAfter()), - increment the stepIndex. The danceSteps array is a circular
   * indexing array. The stepIndex should be incremented by one and then wrapped around with respect
   * to the length of the array.
   */
  private void dance() {
    // Move the badger one step towards its next dance position
    boolean isAlmostThere = makeMoveDance();
    // If the badger is almost at its next dance position, update its next dance position and
    // increment the step index
    if (isAlmostThere) {
      DanceStep nextStep = danceSteps[(stepIndex + 1) % danceSteps.length];
      // Update the next dance position
      nextDancePosition = nextStep.getPositionAfter(x, y);
      // Increment the step index and wrap it around with respect to the length of the danceSteps
      // array
      stepIndex = (stepIndex + 1) % danceSteps.length;
    }
  }

  /**
   * Prompts this badger to start dancing. This method: - updates the isDancing data field - stops
   * dragging this badger - sets stepIndex to zero - Resets the nextDancePosition
   */
  public void startDancing() {
    isDragging = false;
    stepIndex = 0;
    // Initialize nextDancePosition with the position after the first step of the dance
    float[] positionAfterFirstStep = this.danceSteps[0].getPositionAfter(this.x, this.y);
    this.nextDancePosition[0] = positionAfterFirstStep[0];
    this.nextDancePosition[1] = positionAfterFirstStep[1];
    this.isDancing = true;

  }

  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    this.isDancing = false;
  }

}
