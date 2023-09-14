//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

////////////////////////////////////////////////////////////////////////////////
//
// This file contains TWO classes Item and Shelf. You will need to complete the
// implementation of these classes with respect to the provided requirements in
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
// Shelf.recursiveShift()
// Shelf.testAddFirst()
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models Item Objects. Items are comparable with respect to the values of their prices.
 *
 */
class Item implements Comparable<Item> {

  private String name; // name of this item
  private int price; // price of this item

  // PROVIDED CONSTRUCTOR
  /**
   * Creates a new Item object with specific name and price
   * 
   * @param name  name of this item. We assume the name is not null and not blank
   * @param price price of this item. We assume price is a positive integer
   */
  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  // PROVIDED Item.toString method
  /**
   * Returns a String representation of this item formatted as "name($price)"
   * 
   * @return a String representation of this item
   */
  @Override
  public String toString() {
    return name + "($" + price + ")";
  }

  /**
   * Checks whether this Item equals another object passed as input
   * 
   * @return true if other is instanceof Item and has the exact same values for name and price as
   *         this Item.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Item) {
      Item items = (Item) other;
      if (this.name.equals(items.name) && this.price == items.price)
        return true;
    }
    return false;
  }

  // TODO Override/Implement the Comparable.compareTo method.
  // Items are compared with respect to their prices.
  // Item.compareTo method MUST take an input parameter of type Item
  // The compareTo method compares this item to the other item passed as input
  // It returns zero if this item and the other item have the same value of price, a negative
  // integer if the price of this item is less than the price of the other input Item and, a
  // positive integer otherwise.
  // For instance, an item with price 3 is less than an item with price 10
  @Override
  public int compareTo(Item other) {
    // if(this.price < other.price) --> negative
    // if(this.price > other.price) --> positive
    return this.price - other.price;
  }
}


/**
 * This class models Shelf objects which store comparable objects
 *
 * @param <T> Type of elements stored in a Shelf object. A Shelf contains Comparable objects only.
 */
public class Shelf<T extends Comparable<T>> {

  private T[] data; // oversize array which contains the elements stored in this Shelf
  private int size; // total number of elements stored in this Shelf.

  // PROVIDED CONSTRUCTOR. DO NOT CHANGE ITS IMPLEMENTATION.
  /**
   * Creates a new Shelf with a specific capacity
   * 
   * @param capacity maximum number of elements to be stored in this Shelf object.
   */
  @SuppressWarnings("unchecked")
  public Shelf(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("invalid capacity");
    data = (T[]) new Comparable[capacity];
  }

  // PROVIDED addFirst method
  /**
   * Adds a new object to the beginning (index 0) of this Shelf
   * 
   * @param newObject element to add. We assume that newObject is NOT null.
   */
  public void addFirst(T newObject) {
    // Full array: display error message
    if (size == data.length) {
      System.out.println("Full Shelf! No more elements can be added");
      return;
    }
    // Non-full array:
    // Shift all the elements stored in the array one position to the right
    recursiveShift(data, size);
    // add newItem to index 0 of the array
    data[0] = newObject;
    // increment size
    size++;
  }

  /**
   * Generic RECURSIVE method to shift all elements stored in the oversize array (data, size) one
   * position to the right.
   *
   * @param data reference to the oversize array. We assume that the oversize array is NOT full
   * @param size size of the input array data
   */
  public static <T> void recursiveShift(T[] data, int size) {
    // TODO Complete the implementation of this RECURSIVE method
    // NO LOOP must be used to implement this method.
    // Using a loop ensures losing all the points of the manual grading part of the Shelf class.
    // Note that the case of a full array should not be considered. This method is called only when
    // the input array is NOT full.

    // TODO base case: empty array -> nothing to do
    if (size == 0) {
      return;
    }

    // TODO recursive case: non-empty array.
    // shift the LAST element one position to the right
    data[size] = data[size - 1];

    // use recursion to shift the remaining elements one position to the right
    recursiveShift(data, size - 1);
  }

  // PROVIDED Shelf.toString method
  /**
   * Returns a String representation of this Shelf
   * 
   * @return a String representation of this Shelf
   */
  public String toString() {
    String s = "size: " + size + "\n";
    s += "Shelf: ";
    for (int i = 0; i < size; i++) {
      s += data[i] + " ";
    }
    return s.trim();
  }

  // PROVIDED demo
  /**
   * A demo -- creates a shelf of Integers and sorts it.
   */
  public static void demo() {
    // Create a shelf of integers and add 5 integers to its contents.
    Shelf<Integer> integers = new Shelf<Integer>(5);
    integers.addFirst(1);
    integers.addFirst(5);
    integers.addFirst(3);
    integers.addFirst(2);
    integers.addFirst(10);

    // print the shelf of integers
    System.out.println(integers);
    // Expected Output:
    // size: 5
    // Shelf: 10 2 3 5 1

    // sort the array belonging to the shelf of integers and print the sorted shelf
    Arrays.sort(integers.data);// can be safely called if the input array is full
    System.out.println("Shelf sorted!");
    System.out.println(integers);
    // Expected Output:
    // size: 5
    // Shelf: 1 2 3 5 10

  }

  // TODO Complete the implementation of the below tester method
  /**
   * Checks for the correctness of Shelf.addFirst() method. This tester does not consider adding to
   * a full shelf.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFirst() {
    try {
      // TODO Create 4 Item objects of different prices
      Item item1 = new Item("i1", 23);
      Item item2 = new Item("i2", 45);
      Item item3 = new Item("i3", 14);
      Item item4 = new Item("i4", 25);

      // TODO Create a Shelf object whose capacity is greater or equal to 5 and which contains
      // elements
      // of type Item.
      Shelf<Item> shelf = new Shelf<>(5);

      // TODO Call addFirst to add the 4 created items
      shelf.addFirst(item1);
      shelf.addFirst(item2);
      shelf.addFirst(item3);
      shelf.addFirst(item4);

      // TODO Define an array named expected of type Item[] having the same capacity as the shelf
      // and
      // which contains the expected contents of the array data of the shelf after adding the 4
      // items.
      Item[] expected = {item4, item3, item2, item1, null};

      // TODO Check for bugs - return false each time a bug is detected.
      // Expected behavior1: The size of the shelf must be 4
      if (shelf.size != 4) {
        System.out.println("The size of the shelf must be 4");
        return false;
      }

      // Expected behavior2: The contents of the array data of the shelf must equals the
      // expected. Use Arrays.deepEquals() method to check whether two are deeply equals or not.
      if (!Arrays.deepEquals(shelf.data, expected)) {
        System.out.println("actual and expected output must equal");
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
    System.out.println("testAddFirst(): " + (testAddFirst() ? "PASS" : "FAIL"));
    System.out.println("\n=========== DEMO ============");
    demo();
  }
}
