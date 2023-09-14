//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

////////////////////////////////////////////////////////////////////////////////
//
// This file contains TWO classes PrintJob and WaitingLine. You will need to complete
// the implementation of these classes with respect to the provided requirements in
// the TODO tags for full credit.
//
// DO NOT make changes to the provided method signatures
// DO NOT make changes to the the fully implemented provided methods or constructors.
// String comparisons if applicable are CASE SENSITIVE.
//
// No variables outside of any method should be added to this class.
//
// No additional methods should be added to this class.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// A class definition and FOUR methods SHOULD be completed in this file:
// Definition of the class Item
// Item.equals()
// Item.compareTo()
// WaitingLine.recursiveShift()
// WaitingLine.testRemoveFirst()
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models PrintJob Objects. A PrintJob is a single file that has been transmitted to a
 * printer to be printed. A PrintJob is defined by the name of the file and the number of pages to
 * be printed. PrintJob objects are comparable with respect to the values of their number of pages
 * to be printed.
 *
 */
class PrintJob implements Comparable<PrintJob> { // TODO Define the PrintJob class to implement the
                                                 // Comparable interface.
  // A PrintJob MUST be compared to another PrintJob, ONLY.

  private String filename; // name of the file to be printed
  private int pageCount; // number of pages to be printed

  // PROVIDED CONSTRUCTOR
  /**
   * Creates a new PrintJob object with specific values for filename and pageCount
   * 
   * @param filename  name of the file associated with this print job and transmitted to the printer
   * @param pageCount number of pages to be printed
   */
  public PrintJob(String name, int pageCount) {
    this.filename = name;
    this.pageCount = pageCount;
  }

  // PROVIDED Item.toString method
  /**
   * Returns a String representation of this item formatted as "name($price)"
   * 
   * @return a String representation of this item
   */
  @Override
  public String toString() {
    return filename + "(" + pageCount + ")";
  }

  /**
   * Checks whether this PrintJob equals another object passed as input
   * 
   * @return true if other is instanceof PrintJob and has the exact same values for filename and
   *         pageCount as this PrintJob.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof PrintJob) {
      PrintJob otherPrintJob = (PrintJob) other;
      return this.filename.equals(otherPrintJob.filename)
          && this.pageCount == otherPrintJob.pageCount;
    }
    return false;
  }

  @Override
  public int compareTo(PrintJob other) {
    return this.pageCount - other.pageCount;
  }
}


/**
 * This class models WaitingLine objects which stores Comparable objects.
 *
 * @param <T> Type of elements stored in a WaitingLine object. A WaitingLine contains Comparable
 *            objects only.
 */
public class WaitingLine<T extends Comparable<T>> {

  private T[] data; // oversize array which contains the elements stored in this WaitingLine
  private int size; // total number of elements stored in this WaitingLine.

  // PROVIDED CONSTRUCTOR. DO NOT CHANGE ITS IMPLEMENTATION.
  /**
   * Creates a new WaitingLine with a specific capacity
   * 
   * @param capacity maximum number of elements to be stored in this WaitingLine object.
   */
  @SuppressWarnings("unchecked")
  public WaitingLine(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("invalid capacity");
    data = (T[]) new Comparable[capacity];
  }

  // PROVIDED addLast method
  /**
   * Adds a new object to the back (end) of this WaitingLine
   * 
   * @param newObject element to add. We assume that newObject is NOT null.
   */
  public void addLast(T newObject) {
    // Full waiting line: display error message
    if (size == data.length) {
      System.out.println("Full WaitingLine! No more elements can be added");
      return;
    }
    // append newObject to the end of the waiting line
    data[size] = newObject;
    size++;
  }


  /**
   * Removes the element at index 0 from the front (beginning) of this WaitingLine.
   * 
   */
  public void removeFirst() {
    // Empty waiting line: display error message
    if (size == 0) {
      System.out.println("Empty WaitingLine! No element can be removed!");
      return;
    }

    // Non-empty array:
    // Shift all the elements stored in the range 1.. size-1 of this waiting line one position to
    // the left
    recursiveShift(data, 1, size - 1);
    // set the element at index size-1 to null
    data[size - 1] = null;
    // decrement size
    size--;
  }


  /**
   * Generic RECURSIVE method to shift one position to the left of all the elements stored in the
   * range firstIndex .. lastIndex, inclusive, of the array defined by data.
   *
   * @param data       reference to the oversize array. We assume that the oversize array is NOT
   *                   empty, and it contains no null references in the range of indexes firstIndex
   *                   .. lastIndex.
   * @param firstIndex begin index of the portion of the array to be shifted. We assume that
   *                   startIndex is valid and is greater or equal to 1.
   * @param lastIndex  last index of the portion of the array to be shifted. We assume that
   *                   lastIndex is valid.
   */
  public static <T> void recursiveShift(T[] data, int firstIndex, int lastIndex) {
    // TODO Complete the implementation of this RECURSIVE method
    // NO LOOP must be used to implement this method.
    // Using a loop ensures losing all the points of the manual grading part of the WaitingLine
    // class.

    // TODO base case: the portion of the array to be shifted is empty -> nothing to do
    // [HINT] The portion of the array to be shifted is empty if firstIndex > lastIndex
    if (firstIndex > lastIndex) {
      return;
    }

    // TODO recursive case: non-empty portion of the array to be shifted.
    // shift the element at firstIndex one position to the left
    if (firstIndex > 0) {
      data[firstIndex - 1] = data[firstIndex];
    }


    // use recursion to shift the remaining elements one position to the left
    recursiveShift(data, firstIndex + 1, lastIndex);
  }

  // PROVIDED WaitingLine.toString method
  /**
   * Returns a String representation of this WaitingLine
   * 
   * @return a String representation of this WaitingLine
   */
  public String toString() {
    String s = "size: " + size + "\n";
    s += "WaitingLine: ";
    for (int i = 0; i < size; i++) {
      s += data[i] + " ";
    }
    return s.trim();
  }

  // PROVIDED demo
  /**
   * A demo -- creates a waiting line of Integers and sorts it.
   */
  public static void demo() {
    // Create a waiting line of integers and add 5 integers to its contents.
    WaitingLine<Integer> integers = new WaitingLine<Integer>(5);
    integers.addLast(10);
    integers.addLast(2);
    integers.addLast(3);
    integers.addLast(5);
    integers.addLast(1);

    // print the waiting line of integers
    System.out.println(integers);
    // Expected Output:
    // size: 5
    // WaitingLine: 10 2 3 5 1

    // sort the array belonging to the waiting line of integers and print the sorted waiting line
    Arrays.sort(integers.data);// can be safely called if the input array is full
    System.out.println("WaitingLine sorted!");
    System.out.println(integers);
    // Expected Output:
    // size: 5
    // WaitingLine: 1 2 3 5 10

    // Remove the first two integers by calling the removeFirst() method
    integers.removeFirst(); // 1 should be removed!
    System.out.println(integers);
    // Expected Output:
    // size: 4
    // WaitingLine: 2 3 5 10

    integers.removeFirst(); // 2 should be removed!
    System.out.println(integers);
    // Expected Output:
    // size: 3
    // WaitingLine: 3 5 10
  }

  // TODO Complete the implementation of the below tester method
  /**
   * Checks for the correctness of WaitingLine.removeFirst() method. This tester does not consider
   * removing from an empty waiting line.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveFirst() {
    try {
      // TODO Create 4 PrintJob objects of different pageCount values
      PrintJob job1 = new PrintJob("p1", 10);
      PrintJob job2 = new PrintJob("p2", 20);
      PrintJob job3 = new PrintJob("p3", 30);
      PrintJob job4 = new PrintJob("p4", 40);

      // TODO Create a WaitingLine object whose capacity is greater or equal to 5 and which contains
      // elements of type PrintJob.
      WaitingLine<PrintJob> line = new WaitingLine<>(5);

      // TODO Call addLast to append the 4 created items
      line.addLast(job1);
      line.addLast(job2);
      line.addLast(job3);
      line.addLast(job4);

      // You do NOT need to sort the waiting line
      // TODO Call removeFirst to remove the print job at index 0 of the waiting line.
      // If the waiting line was not sorted, the PrintJob to be removed should be the first added
      // one.
      line.removeFirst();

      // TODO Define an array named expected of type PrintJob[] having the same capacity as the
      // waiting line and which contains the expected contents of the array data of the waiting line
      // after calling removeFirst
      PrintJob[] expected = {job2, job3, job4, null, null};

      // TODO Check for bugs - return false each time a bug is detected.
      // Expected behavior1: The size of the waiting line must be 3
      if (line.size != 3) {
        return false;
      }

      // Expected behavior2: The contents of the array data of the waiting line must equals the
      // expected. Use Arrays.deepEquals() method to check whether two are deeply equals or not.
      if (!Arrays.deepEquals(line.data, expected)) {
        return false;
      }

    } catch (Exception e) {// an unexpected exception caught
      e.printStackTrace();
      return false;
    }

    return true; // Test passes - No bug detected

  }


  /**
   * Main method
   * 
   * @param args list of inputs if any
   */
  public static void main(String[] args) {
    System.out.println("testRemoveFirst(): " + (testRemoveFirst() ? "PASS" : "FAIL"));
    System.out.println("\n=========== DEMO ============");
    demo();
  }


}
