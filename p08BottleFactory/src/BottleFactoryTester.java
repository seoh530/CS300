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
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester {

  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {


    Bottle.resetBottleCounter();

    Bottle b1 = new Bottle("Blue");
    Bottle b2 = new Bottle("Pink");
    Bottle b3 = new Bottle("Blue");

    // CASE 1: test equals method, different serial number
    boolean expected = false;
    boolean actual = b1.equals(b3);
    if (expected != actual) {
      return false;
    }

    // CASE 2: test equals method, same serial number
    boolean expected2 = true;
    boolean actual2 = b3.equals(b3);
    if (expected2 != actual2) {
      return false;
    }

    // CASE 4: test toString method, original b1
    String expected3 = "SN1Blue:Empty:Open";
    String actual3 = b1.toString();
    if (!expected3.equals(actual3)) {
      return false;
    }

    // CASE 5: test toString method, Filled version of b3
    b3.fillBottle();
    String expected4 = "SN3Blue:Filled:Open";
    String actual4 = b3.toString();
    if (!expected4.equals(actual4)) {
      return false;
    }

    // CASE 6: test toString method, Filled and Capped version of b2
    b2.fillBottle();
    b2.sealBottle();
    String expected5 = "SN2Pink:Filled:Capped";
    String actual5 = b2.toString();
    if (!expected5.equals(actual5)) {
      return false;
    }

    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean linkedBottleQueueTester() {

    Bottle.resetBottleCounter();

    try {
      LinkedBottleQueue queue = new LinkedBottleQueue(0);
      return false;
    } catch (IllegalArgumentException e) {
    }


    try {
      LinkedBottleQueue queue = new LinkedBottleQueue(-1);
      return false;
    } catch (IllegalArgumentException e) {
    }
    /*
     * test enqueue, dequeue and peek methods using different scenarios 1) all methods on an empty
     * queue 2) all methods on a full queue 3) all methods on a partially filled queue 4) Verify
     * queue contents (using peek and size) after a sequence of enqueue-dequeue and dequeue-enqueue
     * 5) Enqueue until queue is full and dequeue until queue is empty
     */

    // CASE 1: all methods on an empty queue
    {
      Bottle.resetBottleCounter(); // reset the count

      LinkedBottleQueue emptyQueue = new LinkedBottleQueue(3);
      Bottle bottle = new Bottle("Blue");

      if (!emptyQueue.isEmpty()) {
        return false;
      }

      // 1-1 enqueue
      emptyQueue.enqueue(bottle);
      String expected = "SN1Blue:Empty:Open"; // we added one bottle
      if (!expected.equals(bottle.toString())) {
        return false;
      }
    }
    // 1-2 dequeue
    {
      LinkedBottleQueue emptyQueue = new LinkedBottleQueue(3);
      if (!emptyQueue.isEmpty()) {
        return false;
      }

      try {
        emptyQueue.dequeue();
        return false;
      } catch (NoSuchElementException e) {
      }
    }
    // 1-3 peek
    {
      int capacity = 5;
      LinkedBottleQueue emptyQueue = new LinkedBottleQueue(capacity);

      if (!emptyQueue.isEmpty()) {
        return false;
      }

      try {
        emptyQueue.peek();
        return false;
      } catch (NoSuchElementException e) {
      }
    }

    // CASE 2: all methods on a full queue
    Bottle.resetBottleCounter();

    {
      LinkedBottleQueue queue = new LinkedBottleQueue(2);

      queue.enqueue(new Bottle("Red"));
      queue.enqueue(new Bottle("Blue"));

      if (!queue.isFull()) {
        return false;
      }

      // 2-1 enqueue
      try {
        queue.enqueue(new Bottle("White"));
        return false;
      } catch (IllegalStateException e) {
      }

      // 2-2 dequeue
      String expected = "SN1Red";
      String actual = queue.dequeue().getSerialNumber();

      if (!expected.equals(actual)) {
        return false;
      }

      // 2-3 peek
      String expected2 = "SN2Blue";
      String actual2 = queue.peek().getSerialNumber();

      if (!expected2.equals(actual2)) {
        return false;
      }
    }

    // CASE 3: all methods on a partially filled queue
    Bottle.resetBottleCounter();

    {
      LinkedBottleQueue queue = new LinkedBottleQueue(3);

      queue.enqueue(new Bottle("Purple"));
      queue.enqueue(new Bottle("Gold"));

      // 3-1 enqueue
      Bottle b = new Bottle("Silver");
      queue.enqueue(b);

      String expected = "SN3Silver";
      if (!expected.equals(b.getSerialNumber())) {
        return false;
      }

      // 3-2 dequeue
      String expected2 = "SN1Purple";
      String actual2 = queue.dequeue().getSerialNumber();
      if (!expected2.equals(actual2)) {
        return false;
      }

      // 3-3 peek
      String expected3 = "SN2Gold";
      String actual3 = queue.peek().getSerialNumber();
      if (!expected3.equals(actual3)) {
        System.out.println("======");
        return false;
      }
    }

    // CASE 4: Verify queue contents after a sequence of enqueue-dequeue and dequeue-enqueue

    Bottle.resetBottleCounter();

    {
      LinkedBottleQueue queue = new LinkedBottleQueue(3);
      Bottle b1 = new Bottle("Gold");
      Bottle b2 = new Bottle("White");
      Bottle b3 = new Bottle("Purple");

      // 4-1 enqueue-dequeue
      queue.enqueue(b1);
      queue.enqueue(b2);

      int expected = 2;
      int actual = queue.size();
      if (expected != actual) {
        return false;
      }

      QueueADT<Bottle> copy = queue.copy();

      if (queue.size() != copy.size()) {
        return false;
      }

      // 4-2 dequeue-enqueue
      queue.dequeue();
      queue.enqueue(b3);
      queue.dequeue();

      int expected2 = 1;
      int actual2 = queue.size();
      if (expected2 != actual2) {
        return false;
      }

      QueueADT<Bottle> copy2 = queue.copy();

      if (queue.size() != copy2.size()) {
        return false;
      }
    }

    // CASE 5: Enqueue until queue is full and dequeue until queue is empty
    Bottle.resetBottleCounter();
    {
      // 5-1 enqueue until queue is full
      LinkedBottleQueue queue = new LinkedBottleQueue(3);

      queue.enqueue(new Bottle("Black"));
      queue.enqueue(new Bottle("Pink"));
      queue.enqueue(new Bottle("White"));


      QueueADT<Bottle> copy = queue.copy(); // copy the queue

      int expected = 3;
      int actual = queue.size();
      if (expected != actual) {
        return false;
      }

      if (queue.size() != copy.size()) {
        return false;
      }

      // 5-2 dequeue until queue is empty
      try {
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        return false;
      } catch (NoSuchElementException e) {

      }

      QueueADT<Bottle> copy2 = queue.copy();

      int expected2 = 0;
      int result2 = queue.size();
      if (expected2 != result2) {
        return false;
      }

      if (queue.size() != copy2.size()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {

    Bottle.resetBottleCounter();
    // CASE 1: all 3 methods on an empty queue

    try {
      CircularBottleQueue queue = new CircularBottleQueue(-1); // capacity not positive
      return false; // should have thrown an exception
    } catch (IllegalArgumentException e) {
      // expected exception
    }

    CircularBottleQueue queue = new CircularBottleQueue(3);

    // Test enqueue, dequeue, and peek methods
    if (!queue.isEmpty() || queue.size() != 0 || queue.isFull()) {
      System.out.println("case 1 failed");
      return false;
    }

    try {
      queue.enqueue(null); // null element
      return false;
    } catch (NullPointerException e) {
      // expected exception
    }


    // CASE 2: all 3 methods on a full queue
    // 2-1 enqueue case
    int capacity = 2;
    CircularBottleQueue queue2 = new CircularBottleQueue(capacity);
    Bottle b1 = new Bottle("Red");
    Bottle b2 = new Bottle("Blue");

    queue2.enqueue(b1);
    queue2.enqueue(b2);

    if (!queue2.isFull()) {
      System.out.println("queue2 is full");
      return false;
    }

    try {
      queue2.enqueue(new Bottle("Pink"));
      return false;
    } catch (IllegalStateException e) {
      // right track
    }

    // 2-2 dequeue case
    String expected = "SN1Red:Empty:Open";
    String actual = queue2.dequeue().toString();
    if (!expected.equals(actual)) {
      return false;
    }


    try {
      queue2.dequeue();
      queue2.dequeue(); // empty
      return false;
    } catch (NoSuchElementException e) {
      // right track
    }

    // 2-3 peek case
    Bottle.resetBottleCounter(); // reset the count

    CircularBottleQueue queue3 = new CircularBottleQueue(2);

    queue3.enqueue(new Bottle("Red"));
    queue3.enqueue(new Bottle("Blue"));

    String expected3 = "SN1Red";
    String actual3 = queue3.peek().getSerialNumber();
    if (!expected3.equals(actual3)) {
      return false;
    }

    // CASE 3: all methods on a partially filled queue
    Bottle.resetBottleCounter();

    CircularBottleQueue q4 = new CircularBottleQueue(3);

    q4.enqueue(new Bottle("Red"));
    q4.enqueue(new Bottle("Blue"));

    // 3-1 enqueue method
    Bottle b = new Bottle("Pink");
    q4.enqueue(b);
    String expected4 = "SN3Pink:Empty:Open";
    if (!expected4.equals(b.toString())) {
      return false;
    }

    // 3-2 dequeue method
    String expected5 = "SN1Red";
    String actual5 = q4.dequeue().getSerialNumber(); // dequeue and get the string
    if (!expected5.equals(actual5)) {
      return false;
    }

    // 3-3 peek method
    String expected6 = "SN2Blue";
    String actual6 = q4.peek().getSerialNumber(); // peek and get the string representation
    if (!expected6.equals(actual6)) {
      return false;
    }


    // CASE 4: Verify queue contents and size after a sequence of enqueue-dequeue and
    // dequeue-enqueue

    Bottle.resetBottleCounter();

    // 4-1 enqueue-dequeue
    CircularBottleQueue q5 = new CircularBottleQueue(3);

    queue.enqueue(new Bottle("Red"));
    queue.enqueue(new Bottle("Green"));

    int expected7 = 2;
    int actual7 = queue.size();
    if (expected7 != actual7) {
      return false;
    }

    QueueADT<Bottle> copied1 = queue.copy();
    if (queue.size() != copied1.size()) {
      return false;
    }

    queue.dequeue();

    // CASE 5: Enqueue until queue is full and dequeue until queue is empty
    Bottle.resetBottleCounter();

    CircularBottleQueue q6 = new CircularBottleQueue(2);

    q6.enqueue(new Bottle("Red"));
    q6.enqueue(new Bottle("Blue"));

    // 5-1 enqueue until full
    try {
      q6.enqueue(new Bottle("Black")); // full
      return false;
    } catch (IllegalStateException e) {
      // right track
    }

    // 5-2 dequeue until empty
    try {
      q6.dequeue();
      q6.dequeue();
      q6.dequeue(); // empty
      return false;
    } catch (NoSuchElementException e) {
      // right track
    }


    // CASE 6: test copy method
    QueueADT<Bottle> copied2 = queue.copy();
    // copy the queue after having three bottles dequeued
    // check if the copied queue has the same size as the original queue
    if (queue.size() != copied2.size()) {
      return false;
    }

    // I have used toString methods through CASE1 ~ CASE6
    return true;

  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {
    Bottle.resetBottleCounter();

    {
      try {

        LinkedBottleQueue lq = new LinkedBottleQueue(3);

        Bottle b1 = new Bottle("White");
        Bottle b2 = new Bottle("Gray");
        Bottle b3 = new Bottle("Blue");

        lq.enqueue(b1);
        lq.enqueue(b2);
        lq.enqueue(b3);

        Iterator<Bottle> iterator = lq.iterator();
        Bottle[] returnedBottles = new Bottle[3];

        int index = 0;

        while (iterator.hasNext()) {
          returnedBottles[index] = iterator.next();
          index++;
        }

        if (lq.size() != 3) {
          return false;
        }

        Bottle db1 = lq.dequeue();
        Bottle db2 = lq.dequeue();
        Bottle db3 = lq.dequeue();

        if (db1 != b1 || db2 != b2 || db3 != b3) {
          return false;
        }
      } catch (Exception e) {
        return false;
      }
    }

    /*
     * test 02: Create a CircularBottleQueue with at least n bottles and use the bottleQueueIterator
     * to traverse the queue. Verify if all the bottles are returned correctly
     */
    {
      try {
        CircularBottleQueue cq = new CircularBottleQueue(3);

        Bottle bottle1 = new Bottle("Red");
        Bottle bottle2 = new Bottle("Blue");
        Bottle bottle3 = new Bottle("Purple");

        cq.enqueue(bottle1);
        cq.enqueue(bottle2);
        cq.enqueue(bottle3);

        Iterator<Bottle> iterator = cq.iterator();
        Bottle[] returnedBottles = new Bottle[3];
        int index = 0;
        while (iterator.hasNext()) {
          returnedBottles[index] = iterator.next();
          index++;
        }

        // verify that the original queue remains intact after using the iterator
        if (cq.size() != 3) {
          return false;
        }
        // dequeue bottles from the original queue and verify that they match the enqueued bottles
        Bottle deb1 = cq.dequeue();
        Bottle deb2 = cq.dequeue();
        Bottle deb3 = cq.dequeue();

        if (deb1 != bottle1 || deb2 != bottle2 || deb3 != bottle3) {
          return false;
        }
      } catch (Exception e) { // unexpected exception
        return false;
      }
    }
    return true;

  }

  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out
        .println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }
}
