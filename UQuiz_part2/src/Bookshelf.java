//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// TODO fill the above UNIT QUIZ FILE HEADER out!!!

import java.util.Arrays;
import java.util.ArrayList;
// NO additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit.
//
// When creating new exception objects, including books within these objects
// is optional.
//
// No variables outside of any method should be added to this class.
//
// No additional methods should be added to this class.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE
//
////////////////////////////////////////////////////////////////////////////////

/**
 * This class implements methods to manage adding and removing books within a bookshelf.
 * 
 * The list of books is stored in an oversize array defined by the array books and its size.
 * 
 * The array books is an oversize array. This means it contains non null references from index 0 to
 * index size-1. All references in the range of indexes size .. books.length-1 MUST be null.
 *
 * New books are added to the end (index size) of the oversize array books. Books are removed from
 * index 1 of the oversize array books.
 * 
 * A user can search for books with a specific key and the search output is stored in an ArrayList.
 * 
 * All string comparisons or search are CASE-SENSITIVE.
 */
public class Bookshelf {
  // The list of books is defined by the below oversize array:
  private static String[] books; // array storing the titles of books in a bookshelf
  private static int size; // size of the array books.

  // NOTE that books and size are defined outside of any method. They are not provided as input
  // parameters to any of the methods defined below.

  /**
   * Initializes the books array to an array of String objects with the provided capacity. The books
   * array must contain the string "sample" at index 0 after it is created.
   * 
   * @param capacity maximum number of books to be stored in the books array.
   * @throws IllegalArgumentException if capacity is zero or negative
   */
  public static void init(int capacity) {
    // TODO Complete the implementation of this method
    // 1. Throw a new IllegalArgumentException if capacity is zero or negative
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    // 2. Initialize the books array as described in the javadocs comments
    books = new String[capacity];
    // Be sure to set the element at index 0 of books to the string "sample"
    books[0] = "sample";
    // Be sure to initialize the size of the array books to one.
    size = 1;
  }

  /**
   * Adds a book to the end (index size) of the oversize array books.
   * 
   * @param toAdd book to add. We assume that toAdd is not null.
   * @throws IllegalStateException if the oversize array books is FULL.
   */
  public static void add(String toAdd) {
    // TODO implement this method
    // 3. Throw a new IllegalStateException if the oversize array books is full
    if (size >= books.length) {
      throw new IllegalStateException("Array is full");
    }

    // 4. (The array books is NOT FULL)
    // Appropriately add toAdd to the end of the array books and increment size
    books[size] = toAdd;
    size++;
  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)


  /**
   * Removes the book stored at index 1 of the oversize array books. This operation should maintain
   * the order of precedence of the strings stored in the array books.
   * 
   * This method prints "EMPTY bookshelf" if the oversize books contains only one book and does
   * nothing else.
   */
  public static void remove() {
    // TODO
    // 5. Prints "EMPTY bookshelf" if the books array contains only ONE book and then return.
    if (size == 1) {
      System.out.println("EMPTY bookshelf");
      return;
    }
    // 6. (Non-empty books array)
    // Removes the book at index 1 of the books array and decrement size. This operation MUST
    // maintain the books array in its current order.
    for (int i = 1; i < books.length - 1; i++) {
      books[i] = books[i + 1];
    }
    size--;
  }



  /**
   * Searches for books containing a given key within the books oversize array. The search result
   * should be returned in an ArrayList.
   * 
   * @param key search key. We assume that key is not null.
   */
  public static ArrayList<String> search(String key) {
    // TODO implement this method
    // 7. Define a local variable of type ArrayList<String> and initialize it to an empty ArrayList
    // of strings.
    ArrayList<String> result = new ArrayList<String>();
    // 8. Traverse the oversize array books and add any string which contains the provided key to
    // the
    // to the arraylist.
    for (int i = 0; i < size; i++) {
      if (books[i].contains(key)) {
        result.add(books[i]);
      }
    }
    // 9. Return the arraylist


    // No changes should be made to the array books or to its size

    return result; // default return statement added to resolve compiler errors. You can change it.

  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Ensures the correctness of Bookshelf.add() method when called to add a book to the books array
   * given its title. This tester considers a non-full oversize array of books.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddNonFullArray() {
    // TODO
    // 1. set the books array to a non-full oversize array of Strings which already contains 3
    // books (including "sample") and set size to 3.
    // [HINT] DO NOT define local variables named books or size. Use directly the static data
    // fields books and size as follows.
    // books = ...
    // size = ...
    books = new String[] {"sample","book1","book2",null} ;
    size = 3;

    // 2. define a local variable of type String and set it to a non-blank string. This represents
    // the title of the book to add.
    String newBookTitle = "Book 3";

    // 3. Call Bookshelf.add() to add the new book

    Bookshelf.add(newBookTitle);
    // 4. define a local variable named expectedBooks of type String[] which contains the
    // expected contents of the array books after adding the new book
    String[] expectedBooks = {"sample", "Book 1", "Book 2", "Book 3"};

    // 4. Check for bugs.
    // Expected behavior: The new book should be added at the end of the array books and size
    // should be 4.

    // [HINT] Use Arrays.deepEquals() method to compare whether two arrays are deeply equals.
    // You DO NOT need to check for unexpected exceptions in this tester.
    if (Arrays.deepEquals(books, expectedBooks) || size == 4) {
      return true;
    }

    // 5. This tester returns true if no bugs are detected


    return false; // default return statement added to resolve compiler errors. You can change it.
  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Ensures the correctness of Bookshelf.add() method when the books array is full.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddFullArray() {
    try {
      // set the books oversize array to a full array
      books = new String[] {"sample", "Algrithmics", "Java Programming", "Programming in Python"};
      size = 4;
      // try to add a new book to the books array
      try {
        Bookshelf.add("Smile Big");
        // no exception thrown if the control goes here
        System.out
            .println("testAddFullArray() failed. No IllegalStateException thrown as expected.");
        return false;
      } catch (IllegalStateException e) {
        // expected exception caught!
      }
      // check that no changes were made to books and size
      String[] expectedBooks =
          new String[] {"sample", "Algrithmics", "Java Programming", "Programming in Python"};
      int expectedSize = 4;
      if (size != expectedSize) {
        return false;
      }
      if (!Arrays.deepEquals(books, expectedBooks)) {
        return false;
      }

    } catch (Exception e) {
      // a non-expected exception caught
      e.printStackTrace();
      return false;
    }

    return true; // NO bugs detected
  }

  /**
   * Checks the correctness of init(), remove() and search() methods. This tester considers removing
   * from a books array whose size is greater or equal to two.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInitRemoveSearch() {
    try {
      { // checks the correctness of init
        init(10);

        if (books == null || books.length != 10 || size != 1) {
          return false;
        }

        if (books[0] == null || !books[0].equals("sample")) {
          return false;
        }
      }

      { // checks the correctness of search

        // considers a books array whose size is greater or equal to 2
        books = new String[] {"sample", "Algrithmics", "Java Programming", "Programming in Python",
            null, null};
        size = 4;

        // call search
        ArrayList<String> searchList = search("Programming");

        // detect bugs
        if (searchList == null || searchList.size() != 2) {
          return false;
        }

        if (!searchList.contains("Java Programming")) {
          return false;
        }

        if (!searchList.contains("Programming in Python")) {
          return false;
        }

        // checks the correctness of remove
        remove();

        String[] expectedBooks =
            new String[] {"sample", "Java Programming", "Programming in Python", null, null, null};
        int expectedSize = 3;

        if (size != expectedSize) {
          return false;
        }

        if (!Arrays.deepEquals(books, expectedBooks)) {
          return false;
        }
      }

    } catch (Exception e) {
      // a non-expected exception caught
      e.printStackTrace();
      return false;
    }

    return true; // NO bugs detected
  }

  /**
   * Main method to call the test methods
   * 
   * @param args - input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddNonFullArray(): " + testAddNonFullArray());
    System.out.println("testAddFullArray(): " + testAddFullArray());
    System.out.println("testInitRemoveSearch(): " + testInitRemoveSearch());
  }

}
