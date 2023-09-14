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
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and removed
 * with respect to the First In First Out (FIFO) scheduling policy.
 * 
 * @author seyeongoh
 *
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

  private LinkedNode<Bottle> front; // begin bottles in the linked list queue
  private LinkedNode<Bottle> back; // end bottles in the linked list queue
  private int size; // number of bottles in the queue
  private int capacity; // max number of bottles the queue can hold

  /**
   * Initializes the fields of this queue including its capacity. A newly created queue must be
   * empty, meaning that both its front and back are null and its size is zero.
   * 
   * @param capacity : Positive integer defining the max number of bottles the queue can hold
   * @throws IllegalArgumentException : when the capacity is not positive (meaning less or equal to
   *                                  zero).
   */
  public LinkedBottleQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be greater than 0");
    }
    front = null;
    back = null;
    size = 0;
    this.capacity = capacity;
  }

  /**
   * Checks and returns true if the queue is empty
   * 
   * @return true if size is zero, otherwise false
   */
  @Override
  public boolean isEmpty() {
    if (size == 0)
      return true;
    return false;
  }

  /**
   * Checks and returns true if the queue is full
   * 
   * @return true if size and capcity is same, otherwise false
   */
  @Override
  public boolean isFull() {
    if (size == capacity)
      return true;
    return false;
  }

  /**
   * Returns the number of bottles in the queue
   * 
   * @return size of the queue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Add a bottle to the end of the queue
   * 
   * 
   * @param bottle - bottle to add to the queue
   * @throws IllegalStateException : when queue is full
   * @throws NullPointerException  : when bottle to add is null
   */
  @Override
  public void enqueue(Bottle bottle) throws IllegalStateException, NullPointerException {
    if (bottle == null) {
      throw new NullPointerException("Bottle cannot be null");
    }
    if (isFull()) {
      throw new IllegalStateException("Queue is full");
    }
    LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
    if (isEmpty()) {
      front = newNode;
    } else {
      back.setNext(newNode);
    }
    back = newNode;
    size++;
  }

  /**
   * Removes and returns the first bottle in the queue
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException : when queue is empty
   */
  @Override
  public Bottle dequeue() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    Bottle removedbottle = front.getData();
    front = front.getNext();
    size--;
    if (isEmpty()) {
      back = null;
    }
    return removedbottle;
  }

  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException : When queue is empty
   */
  @Override
  public Bottle peek() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("Queue is empty.");
    }
    return front.getData();
  }

  /**
   * Returns a deep copy of this queue
   * 
   * @return deep copy of this queue
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copy = new LinkedBottleQueue(capacity);
    LinkedNode<Bottle> current = front;
    while (current != null) {
      Bottle originalBottle = current.getData();
      copy.enqueue(originalBottle);
      current = current.getNext();
    }
    return copy;
  }



  /**
   * Returns a string representation of the queue from the front to its back with the string
   * representation of each Bottle in a separate line. For instance, SN:Filled/Empty:Capped/Open ...
   * SN:Filled/Empty:Capped/Open
   * 
   * @return String in expected format, empty string when queue is empty
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    LinkedNode<Bottle> current = front;
    while (current != null) {
      sb.append(current.getData().toString());
      if (current.getNext() != null) {
        sb.append("\n");
      }
      current = current.getNext();
    }
    return sb.toString();
  }

  /**
   * Returns an iterator for traversing the queue's items
   * 
   * @return iterator to traverse the LinkedListQueue
   */
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this.copy());
  }
}
