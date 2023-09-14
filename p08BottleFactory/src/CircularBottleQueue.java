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

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 * 
 * @author seyeongoh
 *
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

  private Bottle[] bottles;
  private int front;
  private int back;
  private int size;

  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows: the bottles
   * oversize array to an empty array of Bottle objects whose length is the input capacity, its size
   * to zero, and both its front and back to -1.
   * 
   * @param capacity : defining the number of bottles the queue can hold
   * @throws IllegalArgumentException : when capacity is not positive
   */
  public CircularBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    bottles = new Bottle[capacity];
    front = -1;
    back = -1;
    size = 0;
  }

  /**
   * Returns an iterator to traverse the queue
   * 
   * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());
  }

  /**
   * Checks and returns true if the queue is empty
   * 
   * @return boolean value
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks and returns true if the queue is full
   * 
   * @return boolean value
   */
  @Override
  public boolean isFull() {
    return size == bottles.length;
  }

  /**
   * Returns the number of bottles in the queue
   * 
   * @return size of the queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Add a bottle to the end of the queue
   * 
   * @return bottle - Bottle to add to the queue
   * @throws IllegalStateException - when queue is full
   * @throws NullPointerException  - when bottle to add is null
   */
  @Override
  public void enqueue(Bottle element) throws IllegalStateException, NullPointerException {
    if (element == null) {
      throw new NullPointerException("Bottle cannot be null");
    }
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    if (isEmpty()) {
      front = 0;
      back = 0;
    } else {
      back = (back + 1) % bottles.length;
    }
    bottles[back] = element;
    size++;
  }

  /**
   * Removes and returns the first bottle in the queue.
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException - when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    Bottle bottle = bottles[front];
    bottles[front] = null; // set the dequeued element to null to avoid memory leaks
    if (front == back) { // queue becomes empty after dequeueing
      front = -1;
      back = -1;
    } else {
      front = (front + 1) % bottles.length;
    }
    size--;
    return bottle;
  }

  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException - when queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    return bottles[front];
  }

  /**
   * Returns a deep copy of this Queue
   * 
   * @return a deep copy of the queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    CircularBottleQueue copyQueue = new CircularBottleQueue(bottles.length);
    // deep copy the queue to the new queue (copiedQueue)
    System.arraycopy(bottles, 0, copyQueue.bottles, 0, bottles.length);
    copyQueue.front = front;
    copyQueue.back = back;
    copyQueue.size = size;

    return copyQueue;
  }

  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ...
   * SN:Filled/Empty:Capped/Open The string should not contain a newline character at the end.
   * 
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Bottle b : this) {
      sb.append(b.toString()).append("\n");
    }
    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1); // Remove the last newline character
    }
    return sb.toString();
  }
}
