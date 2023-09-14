//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
////////////////////////////////////////////////////////////////////////////////

// TODO fill the above UNIT QUIZ FILE HEADER out!!!

import java.util.Arrays;
import java.util.ArrayList;
import java.util.NoSuchElementException;
// NO additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class. You will need to complete the implementation
// this class with respect to the provided requirements in the TODO tags for
// full credit.
//
// When creating new exception objects, including messages within these objects
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
 * This class implements methods to manage messages of type String within a message box.
 * 
 * The list of all messages is stored in an oversize array defined by the array messages and its
 * size.
 * 
 * The array messages is an oversize array. This means it contains non null references from index 0
 * to index size-1. All references in the range of indexes size .. messages.length-1 MUST be null.
 *
 * New messages are added to index 0 of the oversize array messages. Messages are removed from the
 * end of the oversize array messages (index size-1).
 * 
 * Important messages which contain a specific key are stored in an ArrayList named
 * importantMessages.
 * 
 * All string comparisons or search are CASE-SENSITIVE.
 */
public class MessageBox {

  private static ArrayList<String> importantMessages; // list of all the important messages

  // The list of unread messages is defined by the below oversize array:
  private static String[] messages; // array storing the subjects of unread messages received on a
                                    // message box.
  private static int size; // size of the array messages.

  // NOTE that messages and size are defined outside of any method. They are not provided as input
  // parameters to any of the methods defined below.

  // NO ADDITIONAL variables should be defined outside of any method.

  /**
   * Initializes the importantMessages to an ArrayList of strings which contains the string "test"
   * and initializes the messages array to an empty array which can store up to the provided
   * capacity of String objects.
   * 
   * @param capacity maximum number of messages to be stored in the messages array. We assume that
   *                 capacity is a non-zero positive integer.
   */
  public static void init(int capacity) {
    // 1. TODO Complete the implementation of this method
    importantMessages = new ArrayList<String>();
    messages = new String[capacity];
    size = 0;
  }

  /**
   * Adds a message to index 0 of the array messages. This method should maintain the order of the
   * messages already stored in the array. This method prints the error message "FULL BOX" if the
   * array messages is full.
   * 
   * @param toAdd message to add
   * @throws IllegalArgumentException if toAdd is null.
   */
  public static void add(String toAdd) {
    // TODO implement this method
    // 2. Throw a new IllegalArgumentException if toAdd is null
    if(toAdd == null) {
      throw new IllegalArgumentException();
    }

    // 3. Print "FULL BOX" if messages is full and do nothing else
    if(size >= messages.length) {
      System.out.println("FULL BOX");
      return;
    }


    // 4. (The array messages is NOT FULL)
    // Appropriately add toAdd to the beginning (index 0) of the messages array and increment size
    for(int i = size - 1; i >= 0; i--) {
      messages[i+1] = messages[i];
    }
    size++;


  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)


  /**
   * Removes and returns the message stored at the end of the oversize array messages.
   * 
   * @return the message that was stored at the end of the oversize array messages.
   * @throws NoSuchElementException if the oversize array defined by messages and size is empty.
   */
  public static String remove() {
    // TODO
    // 5. Throw a new NoSuchElementException if the oversize array messages is empty

    // 6. (Non-empty messages array)
    // save the last message into a local variable to return
    // set the last message within the array messages to null
    // decrement size
    // return the saved message

    return null; // a default return statement added to resolve compiler errors. You can change it.
  }



  /**
   * Traverses the oversize array messages and adds any message which contains the specified key to
   * the arraylist of important messages.
   * 
   * @param key search key to identify important messages. We assume that key is not null.
   */
  public static void filterImportantMessages(String key) {
    // TODO implement this method

    // 7. Traverse the oversize array messages and add any message which contains the provided key
    // to the arraylist importantMessages.

    // No changes should be made to the array messages or to its size


  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Ensures the correctness of MessageBox.remove method. This tester considers a non-empty oversize
   * array messages.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveNonEmptyArray() {
    // TODO
    // 1. set the messages array to a non-empty oversize array of Strings which already contains 3
    // messages and set size to 3.
    // [HINT] DO NOT define local variables named messages or size. Use directly the static data
    // fields messages and size as follows.
    // messages = ...
    // size = ...
    messages = new String[] {"hello","World","!",null};
    size = 3;

    // 2. Call MessageBox.remove() and save the returned string in a local variable
    String removeMsg = MessageBox.remove();

    // 3. define a local variable names expected of type String which contains the expected String
    // to return by the remove() method call.
    String expectedMsg = "!";  

    // 4. define a local variable named expectedMessages of type String[] which contains the
    // expected contents of the messages array after removing the last message.
    String [] expectedMessages = new String[] {"hello","World",null};


    // 5. Check for bugs.
    // Expected behavior: The message that was at the end of the array should be appropriately 
    // removed and returned, and size should be 2
    if(!Arrays.deepEquals(messages, expectedMessages) && size != 2) {
      return true;
    }


    // [HINT] Use Arrays.deepEquals() method to compare whether two arrays are deeply equals.
    // You DO NOT need to check for unexpected exceptions in this tester.

    // 5. This tester returns true if no bugs are detected


    return false; // default return statement added to resolve compiler errors. You can change it.
  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Ensures the correctness of MessageBox.remove() method when the messages array is empty.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testRemoveEmptyArray() {
    try {
      // set the messages oversize array to an empty array
      messages = new String[10];
      size = 0;
      // try to call remove method
      try {
        MessageBox.remove();
        // no exception thrown if the control goes here
        System.out.println(
            "testRemoveEmptyArray() failed. No NoSuchElementException thrown as expected.");
        return false;
      } catch (NoSuchElementException e) {
        // expected exception caught!
      }
      // check that no changes were made to messages array and size
      String[] expectedMessages = new String[10];
      int expectedSize = 0;
      if (size != expectedSize) {
        return false;
      }
      if (!Arrays.deepEquals(messages, expectedMessages)) {
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
   * Checks the correctness of init(), add() and filterImportantMessages() methods. This tester
   * considers adding to a non-full messages array.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInitAddMessageFilterImportantMessages() {
    try {
      { // checks the correctness of init()
        init(10);
        if (importantMessages == null || importantMessages.size() != 1
            || !importantMessages.contains("test")) {
          return false;
        }
        if(messages == null || messages.length != 10) {
          return false;
        }

        // checks the correctness of add()
        // considers a non-full messages array
        messages = new String[] {"Welcome to cs300 sp23", "cs300 p02 released",
            "Assistance requested", "Meeting", "cs300 p01 grades and feedback", null, null};
        size = 5;

        // call add("winter carnival")
        add("winter carnival");

        String[] expectedMessages =
            new String[] {"winter carnival", "Welcome to cs300 sp23", "cs300 p02 released",
                "Assistance requested", "Meeting", "cs300 p01 grades and feedback", null};
        int expectedSize = 6;

        // detect bugs
        if (!Arrays.deepEquals(messages, expectedMessages)) {
          return false;
        }

        if (size != expectedSize) {
          return false;
        }

        // checks the correctness of filterImportantMessages
        filterImportantMessages("cs300");
        if (importantMessages == null || importantMessages.size() != 4
            || !importantMessages.contains("test")
            || !importantMessages.contains("Welcome to cs300 sp23")
            || !importantMessages.contains("cs300 p02 released")
            || !importantMessages.contains("cs300 p01 grades and feedback")) {
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
    System.out.println("testRemoveNonEmptyArray(): " + testRemoveNonEmptyArray());
    System.out.println("testRemoveEmptyArray(): " + testRemoveEmptyArray());
    System.out.println("testInitAddMessageFilterImportantMessages(): "
        + testInitAddMessageFilterImportantMessages());
  }
}
