//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Bottle Factory
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not
 * empty, Bottle objects are iterated over from the front to the back of the queue. No more Bottle
 * objects are returned by this iterator when all the Bottle objects are traversed (returned). This
 * Iterator iterates over any queue which implements the QueueADT<Bottle> interface. It uses the
 * QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 * 
 * @author seyeongoh
 *
 */
public class BottleQueueIterator implements Iterator<Bottle> {

  private QueueADT<Bottle> bottleQueue; // queue of bottles to be iterated over

  /**
   * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a
   * deep copy of the queue passed as input.
   * 
   * @param queue : The queue to iterate over, should implement the QueueADT interface.
   * @throws IllegalArgumentException : when queue is null
   */
  public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
    if (queue == null) {
      throw new IllegalArgumentException("queue is null");
    }
    this.bottleQueue = queue.copy();
  }

  /**
   * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is not
   * iterated over
   * 
   * @return boolean value (true if there is iteration, otherwise false)
   */
  public boolean hasNext() {
    return (!bottleQueue.isEmpty());
  }

  /**
   * Returns the next bottle in the iteration
   * 
   * @return Bottle The next bottle in the iteration
   * @throws NoSuchElementException - if there are no more elements in the iteration
   */
  public Bottle next() {
    if (!hasNext()) {
      throw new NoSuchElementException("No more bottles in the iteration");
    }
    return bottleQueue.dequeue();
  }
}
