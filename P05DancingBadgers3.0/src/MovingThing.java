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
 * This class models moving thing objects. A moving thing is defined by its speed and to which
 * direction it is facing (right or left).
 * 
 * @author seyeongoh
 *
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {
  protected int speed;
  protected boolean isFacingRight;

  /**
   * Creates a new MovingThing and sets its speed, image file, and initial x and y position. A
   * MovingThing object is initially facing right.
   * 
   * @param x             starting x-position of this MovingThing
   * @param y             starting y-position of this MovingThing
   * @param speed         movement speed of this MovingThing
   * @param imageFileName filename of the image of this MovingThing, for instance "name.png"
   */
  public MovingThing(float x, float y, int speed, String imageFileName) {
    super(x, y, imageFileName);
    this.speed = speed;
    this.isFacingRight = true;
  }

  /**
   * Draws this MovingThing at its current position. The implementation details of this method is
   * fully provided in the write-up of p05.
   */
  @Override
  public void draw() {
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(x, y);
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }


  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their
   * speeds.
   * 
   * @param other the MovingThing object to be compared.
   * @return zero if this object and other have the same speed, a negative integer if the speed of
   *         this moving object is less than the speed of other, and a positive integer otherwise.
   * 
   */
  public int compareTo(MovingThing other) {
    return Integer.compare(this.speed, other.speed);
  }
}
