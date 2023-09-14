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
 * This class implements methods to checkin and checkout patients in a clinic or hospital.
 * 
 * The list of all patients who are checked in and not yet checked out is stored in an oversize
 * array defined by the array patients and its size. The array patients stores String objects
 * (patients names).
 * 
 * The array patients is an oversize array. This means it contains non null references from index 0
 * to index size-1. All references in the range of indexes size .. patients.length-1 MUST be null.
 *
 * When a patient is checked in, it is added to the end (index size) of the oversize array patients.
 * Patients are checked out at a specific visit date and so they are removed from the beginning
 * (index 0) of the oversize array patients.
 * 
 * When a patient is checked out, the information related to its visit (name + " " + date) is added
 * to the ArrayList pastVisits.
 *
 * All string comparisons or search are CASE-SENSITIVE.
 */
public class PatientsRegistration {

  private static ArrayList<String> pastVisits; // list of ALL patients past visits

  // The list of patients checked in and not yet checked out is defined by the below oversize array:
  private static String[] patients; // array storing the names of patients already checked in.
  private static int size; // number of patients already checked in and not yet checked out.

  // NOTE that patients and size are defined outside of any method. They are not provided as input
  // parameters to any of the methods defined below.

  // NO ADDITIONAL variables should be defined outside of any method.

  /**
   * Initializes the pastVisits static data field to an empty ArrayList of strings and the patients
   * array to an empty array which can store up to the provided capacity of String objects.
   * 
   * @param capacity maximum number of patients to be checked in and not yet checked out. We assume
   *                 that capacity is >= 0.
   */
  public static void init(int capacity) {
    // 1. TODO Complete the implementation of this method
    pastVisits = new ArrayList<String>();
    patients = new String[capacity];
    size = 0;

  }

  /**
   * Checks a patient in. This method adds the patientName to the end of the oversize array defined
   * by the static variables (data fields) patients and size.
   * 
   * @param patientName patient name to be added to the list of patients checked in
   * @throws IllegalStateException if the patients array is FULL.
   */
  public static void checkin(String patientName) {
    // TODO implement this method
    // 2. Throw a new IllegalStateException if there is NO room to add patientName to the oversize
    // array patients.
    if (size >= patients.length) {
      throw new IllegalStateException("there is NO room to add patientName");
    }

    // 3. (The array patients is NOT FULL)
    // Appropriately add patientName to the end of the patients array and increment size
    patients[size] = patientName;
    size++;

  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Removes the patient at index 0 of the oversize array (patients, size) if there is one and adds
   * the removedPatientName + " " + date to the ArrayList of past visits. This method MUST maintain
   * the oversize array patients in its current order.
   * 
   * For instance, if the list of checked in patients is defined by:
   * 
   * patients: {"Mouna", "Michelle", "Ashley", null, null, null}, and size: 3,
   * 
   * checkout("2-13-2023") must remove "Mouna" from the patients array and add
   * 
   * "Mouna 2-13-2023" to the pastVisits ArrayList.
   * 
   * This method prints "EMPTY LIST" and does NOTHING else if the array patients is EMPTY
   * 
   * @param date string representing the checkout date. We assume that date is not null and that it
   *             is CORRECTLY formated as MM-DD-YYYY.
   * 
   */
  public static void checkout(String date) {
    // TODO implement this method

    // 4. if the array patients is empty, print "EMPTY LIST" and do nothing else
    if (size == 0) {
      System.out.println("EMPTY LIST");
      return;
    }

    // 5. if non-empty patients array
    // Add patients[0] + " " + date to the arraylist pastVisits
    pastVisits.add(patients[0] + " " + date);

    // remove the patient at index 0 while maintaining the current order of the patients array
    // and decrement size
    for (int i = 0; i < patients.length - 1; i++) {
      //patients[0] = patients[1]
      patients[i] = patients[i + 1];
    }
    patients[size - 1] = null;
    size--;

  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Returns the count of the past visits of a patient given their name. This method returns zero if
   * there is no strings matching this search criteria in the arraylist pastVisits.
   * 
   * @param patientName name of a given patient.
   * @return the number of past visits of the patient
   * @throws NullPointerException if patientName is null
   */
  public static int pastVisitsCount(String patientName) {
    // TODO implement this method
    // 6. Throw a new NullPointerException if patientName is null
    if (patientName == null) {
      throw new NullPointerException("patientName is null");
    }

    // 7. Traverse the pastVisits arraylist and count the number of past visits of a patient given
    // their name
    // [HINT] Note that the arraylist contains strings starting with or containing the patientName.
    int count = 0;
    for (String visit : pastVisits) {
      if (visit.contains(patientName)) {
        count++;
      }

    }

    // return the count of the past visits of the given patient

    return count; // default return statement added to resolve compiler errors. Feel free to change
                  // it.
  }

  /**
   * Ensures the correctness of PatientsRegistration.checkin() method when called to check a patient
   * in given their name. This tester considers a non-full oversize array of patients.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCheckinNonFullArray() {
    // TODO
    // 1. set the patients array to a non-full oversize array of Strings which already contains 3
    // names and set size to 3.
    // [HINT] DO NOT define local variables named patients or size. Use directly the static data
    // fields patients and size as follows.
    // patients = ...
    // size = ...
    patients = new String[] {"Ashley", "Kushagra", "Michelle", null};
    size = 3;

    // 2. define a local variable of type String and set it to a non-blank string. This represents
    // the name of the patient to checkin (patientName).
    String patientName = "Joe";

    // 3. Call PatientsRegistration.checkin(patientName) to checkin a new patient
    PatientsRegistration.checkin(patientName);

    // 4. define a local variable named expectedPatients of type String[] which contains the
    // expected contents of the patients array after checking patientName in
    String[] expectedPatients = {"Ashley", "Kushagra", "Michelle", "Joe"};

    // 4. Check for bugs.
    // Expected behavior: patientName should be added at the end of the array patients and size
    // should be 4.

    // [HINT] Use Arrays.deepEquals() method to compare whether two arrays are deeply equals.
    // You DO NOT need to check for unexpected exceptions in this tester.
    if (!Arrays.deepEquals(patients, expectedPatients) || size != 4) {
      return false;
    }

    // 5. This tester returns true if no bugs are detected
    return true; // default return statement added to resolve compiler errors. You can change it.
  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Ensures the correctness of PatientsRegistration.checkin() method when the patients array is
   * full.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCheckinFullArray() {
    try {
      // set the patients oversize array to a full array
      patients = new String[] {"Ashley", "Kushagra", "Michelle", "Preeti"};
      size = 4;
      // try to add a new patient to the patients array by checking them in
      try {
        PatientsRegistration.checkin("Mouna");
        // no exception thrown if the control goes here
        System.out
            .println("testCheckinFullArray() failed. No IllegalStateException thrown as expected.");
        return false;
      } catch (IllegalStateException e) {
        // expected exception caught!
      }
      // check that no changes were made to patients and size
      String[] expectedPatients = new String[] {"Ashley", "Kushagra", "Michelle", "Preeti"};
      int expectedSize = 4;
      if (size != expectedSize) {
        return false;
      }
      if (!Arrays.deepEquals(patients, expectedPatients)) {
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
   * Checks the correctness of init(), checkout() and pastVisitsCount() methods. This tester
   * considers checking out of a non-empty patients array.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testInitCheckoutAndPastVisitsCount() {
    try {
      { // checks the correctness of checkout
        init(10); // calls init

        // considers a non-empty patients array
        patients = new String[] {"Mouna", "Kushagra", "Mouna", "Preeti", null, null};
        size = 4;

        // call checkout("2-09-2023")
        checkout("02-09-2023");

        String expected = "Mouna 02-09-2023";
        String[] expectedPatients = new String[] {"Kushagra", "Mouna", "Preeti", null, null, null};
        int expectedSize = 3;

        // detect bugs
        if (!Arrays.deepEquals(patients, expectedPatients)) {
          return false;
        }

        if (size != expectedSize) {
          return false;
        }

        if (pastVisits == null || pastVisits.size() != 1 || !pastVisits.contains(expected)) {
          return false;
        }

        checkout("2-10-2023"); // checkout Kushagra

        checkout("2-13-2023"); // checkout Mouna

        // call pastVisitsCount
        if (pastVisitsCount("Mouna") != 2) {
          return false;
        }

        if (pastVisitsCount("Kushagra") != 1) {
          return false;
        }

        if (pastVisitsCount("Hobbes") != 0) {
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
    System.out.println("testCheckinNonFullArray(): " + testCheckinNonFullArray());
    System.out.println("testCheckinFullArray(): " + testCheckinFullArray());
    System.out
        .println("testInitCheckoutAndPastVisitsCount(): " + testInitCheckoutAndPastVisitsCount());
  }
}
