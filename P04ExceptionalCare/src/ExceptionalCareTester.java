//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Eceptional Care
// Course: CS 300 Spring 2023
//
// Author: Seyeong Oh
// Email: soh87@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Jiho Han
// Partner Email: jhan292@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _O_ Write-up states that pair programming is allowed for this assignment.
// _O_ We have both read and understand the course Pair Programming Policy.
// _O_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This class is testing methods of UrgentCareAdmissions class are working well according to
 * instructions. Also, this class test Empty and Full patientList for testing the empty and full
 * list exceptions working well.
 * 
 * @author seyeongoh
 *
 */
public class ExceptionalCareTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for testing an
   * instantiable class. This method verifies the correctness of your PatientRecord class.
   * 
   * In this test, we create two PatientRecords with different information and use the accessor
   * methods to verify that both contain the correct information and have the correct String
   * representation.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testPatientRecord() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    // (1) create two PatientRecords with different, valid input
    // no exceptions should be thrown, so let's be safe:
    PatientRecord test1 = null, test2 = null;
    try {
      test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
      test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
    } catch (Exception e) {
      return false;
    }

    // (2) verify their data fields:
    {
      // CASE_NUMBER
      int expected1 = 21701;
      int expected2 = 32102;
      if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2)
        return false;
    }
    {
      // triage
      int expected1 = PatientRecord.YELLOW;
      int expected2 = PatientRecord.GREEN;
      if (test1.getTriage() != expected1 || test2.getTriage() != expected2)
        return false;
    }
    {
      // gender
      char expected1 = 'M';
      char expected2 = 'X';
      if (test1.getGender() != expected1 || test2.getGender() != expected2)
        return false;
    }
    {
      // age
      int expected1 = 17;
      int expected2 = 21;
      if (test1.getAge() != expected1 || test2.getAge() != expected2)
        return false;
    }
    {
      // orderOfArrival
      int expected1 = 1;
      int expected2 = 2;
      if (test1.getArrivalOrder() != expected1 || test2.getArrivalOrder() != expected2)
        return false;
    }
    {
      // hasBeenSeen - try the mutator too
      if (test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
      test1.seePatient();
      if (!test1.hasBeenSeen() || test2.hasBeenSeen())
        return false;
    }

    // (3) verify their string representations
    {
      String expected1 = "21701: 17M (YELLOW)";
      String expected2 = "32102: 21X (GREEN)";
      if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2))
        return false;
    }

    // (4) finally, verify that the constructor throws an exception for an invalid triage value
    try {
      new PatientRecord('F', 4, -17);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalArgumentException e) {
      // correct exception type, but it should have a message:
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) {
      // incorrect exception type
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * This method tests the behavior of the ExceptionalCareAdmission class and its constructor method
   * with two scenarios. 1. veryfy a normal, valid input constructor call does NOT throw an
   * exception 2. verigy that the object has size 0, is not full, has no seen patients, and
   * toString() is an empty string We assume for the purposes of this method that the
   * ExceptionalCareAdmissions constructor and PatientRecord constructor are working properly.
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testAdmissionsConstructorValid() {
    PatientRecord.resetCounter();
    // (1)
    try {
      ExceptionalCareAdmissions test1 = new ExceptionalCareAdmissions(2);

      // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
      // its toString() is an empty string
      if (test1.size() != 0) {
        return false; // the size should be 0
      }
      if (test1.isFull()) {
        return false; // the object should not be full
      }
      if (test1.getNumberSeenPatients() != 0) {
        return false; // the object should have no seen patients
      }
      if (!test1.toString().isEmpty()) {
        System.out.println(test1.toString());
        return false; // the toString() method should return an empty string
      }
    } catch (IllegalArgumentException e) {
      return false;
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * This method tests the behavior of the throw IllegalArgumentException. this method tests one
   * scenario: verify that calling the constructor with capacity <= 0 causes an
   * IllegalArgumentException We assume for the purposes of this method that the
   * ExceptionalCareAdmissions constructor and PatientRecord constructor are working properly.
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testAdmissionsConstructorError() {
    PatientRecord.resetCounter();
    // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
    try {
      PatientRecord[] test3 = new PatientRecord[0];
    } catch (IllegalArgumentException e) {
      System.out.println("exception here");
      return false;
    }
    return true;
  }

  /**
   * This method tests the behavior of addPatient method. It tests three scenarios: 1. add a new
   * patient to an empty list and compare toString() values. 2. add a new patient to the end of the
   * list and verify it. 3. add a new patient to the begginning of the list and veriy it.
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testAddPatientValid() {
    // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
    // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
    ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
    PatientRecord patient1 = new PatientRecord('M', 18, PatientRecord.RED);
    admissions.addPatient(patient1, 0);
    String expected1 = "21801: 18M (RED)";
    String actual1 = admissions.toString();
    if (!expected1.equals(actual1)) {
      return false;
    }
    // (2) add a new patient to the end of the list
    PatientRecord patient2 = new PatientRecord('F', 22, PatientRecord.GREEN);
    admissions.addPatient(patient2, admissions.size());
    String expected2 = "21801: 18M (RED)\n12202: 22F (GREEN)";
    String actual2 = admissions.toString();
    if (!expected2.equals(actual2)) {
      return false;
    }
    // (3) add a new patient to the beginning of the list
    PatientRecord patient3 = new PatientRecord('X', 17, PatientRecord.YELLOW);
    admissions.addPatient(patient3, 0);
    String expected3 = "31703: 17X (YELLOW)\n21801: 18M (RED)\n12202: 22F (GREEN)";
    String actual3 = admissions.toString();
    if (!expected3.equals(actual3)) {
      return false;
    }
    return true;
  }

  /**
   * This test method is provided for you in its entirety, to give you a model for verifying a
   * method which throws exceptions. This method tests addPatient() with two different, full
   * patientsList arrays; one contains seen patients and one does not.
   * 
   * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor and
   * PatientRecord constructor are working properly.
   * 
   * This method must NOT allow ANY exceptions to be thrown from the tested method.
   * 
   * @author hobbes
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testAddPatientError() {
    // FIRST: reset the patient counter so this tester method can be run independently
    PatientRecord.resetCounter();

    ////// (1) a full Admissions object that contains no seen patients

    // create a small admissions object and fill it with patients. i'm filling the list
    // in decreasing order of triage, so the addPatient() method has to do the least
    // amount of work.
    ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
    full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

    // saving one patient in a local variable so we can mark them "seen" later
    PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
    full.addPatient(seenPatient, 2);

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception, but for this method we expect a specific
      // error message so we have one more step to verify:
      String message = e.getMessage();
      String expected = "Cannot admit new patients";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and we can just fail the test now
      return false;
    }

    ////// (2) a full Admissions object that contains at least one seen patient

    // since we have a reference to the patient at index 2, we'll just mark them seen here
    seenPatient.seePatient();

    try {
      full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
      // if we get here, no exception was thrown and the test fails
      return false;
    } catch (IllegalStateException e) {
      // this is the correct type of exception again, but we expect a different error
      // message this time:
      String message = e.getMessage();
      String expected = "cleanPatientsList()";
      if (!message.equals(expected))
        return false;
    } catch (Exception e) {
      // this is the incorrect exception type, and the test fails here
      return false;
    }

    // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
    return true;
  }

  /**
   * This method tests the behavior of the getAdmissionIndex method using Admission object. This
   * method tests three scenarios. 1. get the index of a PatientRecord that should go at the end 2.
   * get the index of PatientRecord that should go at the beginning 3. get the index of a
   * patientRecord that should go in the middle
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndexValid() {
    PatientRecord.resetCounter();

    // create an Admissions object and add a few Records to it, leaving some space
    ExceptionalCareAdmissions c = new ExceptionalCareAdmissions(6);
    c.addPatient(new PatientRecord('M', 18, PatientRecord.YELLOW), 0);
    c.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);

    // (1) get the index of a PatientRecord that should go at the END
    int endIdx = c.getAdmissionIndex(new PatientRecord('F', 50, PatientRecord.GREEN));
    if (endIdx != 2) {
      return false;
    }

    // (2) get the index of a PatientRecord that should go at the BEGINNING
    int beginIdx = c.getAdmissionIndex(new PatientRecord('M', 21, PatientRecord.RED));
    if (beginIdx != 0) {
      return false;
    }

    // (3) get the index of a PatientRecord that should go in the MIDDLE
    int midIdx = c.getAdmissionIndex(new PatientRecord('F', 13, PatientRecord.GREEN));
    if (midIdx != 2) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the behavior of the addPatient method using admissions object. This method
   * tests two scenarios. 1. verify the exception when there are no patients who have been seen in
   * the list 2. verify the exception when there is at least one patient who has been seen
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndexError() {
    PatientRecord.resetCounter();
    // create an Admissions object and add Records to it until it is full, as in testAddPatientError
    ExceptionalCareAdmissions test = new ExceptionalCareAdmissions(2);
    // (1) verify the exception when there are no patients who have been seen in the list
    try {

      test.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
      test.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
      test.addPatient(new PatientRecord('F', 32, PatientRecord.GREEN), 2);
      test.getAdmissionIndex(new PatientRecord('M', 20, PatientRecord.RED));
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Cannot admit new patients")) {
        return false;
      }
    }



    // (2) verify the exception when there is at least one patient who has been seen
    try {
      test.seePatient(21801); // mark idx 0 patient has been seen
      test.getAdmissionIndex(new PatientRecord('M', 18, PatientRecord.RED));
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("cleanPatientsList()")) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method tests the behavior of the addPatient, getNumSeenPatients, and isFull methods using
   * a non-full and full admissions object. This method tests three scenarios. 1. verify isFull() on
   * a non-full and full admissions object. 2. verify size() before and after adding a PatientRecord
   * 3. verify getNumberSeenPatients() before and after seeing a patient
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testAdmissionsBasicAccessors() {
    // (1) verify isFull() on a non-full and a full Admissions object
    ExceptionalCareAdmissions admissions1 = new ExceptionalCareAdmissions(2);
    if (admissions1.isFull()) {
      return false;
    }
    admissions1.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    admissions1.addPatient(new PatientRecord('F', 22, PatientRecord.GREEN), 1);
    if (!admissions1.isFull()) {
      return false;
    }
    // (2) verify size() before and after adding a PatientRecord
    ExceptionalCareAdmissions admissions2 = new ExceptionalCareAdmissions(2);
    if (admissions2.size() != 0) {
      return false;
    }
    admissions2.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
    if (admissions2.size() != 1) {
      return false;
    }

    // (3) verify getNumberSeenPatients() before and after seeing a patient
    // (see testAddPatientError for a model of how to do this while bypassing seePatient())
    ExceptionalCareAdmissions admissions4 = new ExceptionalCareAdmissions(3);
    PatientRecord patient3 = new PatientRecord('X', 17, PatientRecord.YELLOW);
    admissions4.addPatient(patient3, 0);
    if (admissions4.getNumberSeenPatients() != 0) {
      return false;
    }
    patient3.seePatient();
    if (admissions4.getNumberSeenPatients() != 1) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the seePatient and hasBeenSeen methods using admission object. This method
   * tests 2 scenarios. 1. call seePatient() and verify that its hasBeenSeen() accessor return value
   * changes 2. verify getNumberSeenPatients() before and after seeing a different patient
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testSeePatientValid() {
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it, saving a shallow copy of
    // at least one of the PatientRecord references
    ExceptionalCareAdmissions c = new ExceptionalCareAdmissions(5);
    PatientRecord p1 = new PatientRecord('M', 18, PatientRecord.RED);
    PatientRecord p2 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 45, PatientRecord.GREEN);
    c.addPatient(p1, 0);
    c.addPatient(p2, 1);
    c.addPatient(p3, 2);
    PatientRecord p1Copy = p1;
    // (1) call seePatient() on the caseID of your saved reference and verify that its
    // hasBeenSeen() accessor return value changes
    boolean initialHasBeenSeen = p1.hasBeenSeen();
    c.seePatient(p1Copy.CASE_NUMBER);
    boolean afterHasBeenSeen = p1.hasBeenSeen();
    if (initialHasBeenSeen == afterHasBeenSeen) {
      return false;
    }

    // (2) verify getNumberSeenPatients() before and after seeing a different patient
    int initialNumSeenPatients = c.getNumberSeenPatients();
    c.seePatient(20502);
    int afterNumSeenPatients = c.getNumberSeenPatients();
    if (initialNumSeenPatients + 1 != afterNumSeenPatients) {
      return false;
    }

    return true;
  }

  /**
   * This method tests the addPatient and seePatient methods using admission object. This method has
   * one scenario, which is verify that seeing a caseID for a patient not in the list causes and
   * IllegalArgumentException
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testSeePatientError() {
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions c = new ExceptionalCareAdmissions(5);
    PatientRecord p1 = new PatientRecord('M', 18, PatientRecord.RED);
    PatientRecord p2 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 45, PatientRecord.GREEN);
    c.addPatient(p1, 0);
    c.addPatient(p2, 1);
    c.addPatient(p3, 2);
    // (1) verify that seeing a caseID for a patient not in the list causes an
    // IllegalArgumentException
    try {
      c.seePatient(9999); // caseID not in list
      return false; // exception not thrown
    } catch (IllegalArgumentException e) {
      // expected exception
    }

    return true;
  }

  /**
   * This method tests the getSummary method using admission object. this method test one scenario,
   * which is choose one of getSummary test from P01,(almost same as P01)
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetSummary() {
    PatientRecord.resetCounter();
    ExceptionalCareAdmissions c = new ExceptionalCareAdmissions(5);
    PatientRecord p1 = new PatientRecord('M', 18, PatientRecord.RED);
    PatientRecord p2 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 45, PatientRecord.GREEN);
    c.addPatient(p1, 0);
    c.addPatient(p2, 1);
    c.addPatient(p3, 2);
    // (1) choose one getSummary() test from P01; this method has not changed much
    String expected =
        "Total number of patients: 3\nTotal number seen: 0\nRED: 1\nYELLOW: 1\nGREEN: 1";
    String actual = c.getSummary();
    if (!actual.equals(expected)) {
      return false;
    }
    return true;
  }

  /**
   * This method tests the cleanPatientsList method using admission object. This method test two
   * scenarios. 1. using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO
   * seen patients does not change after calling cleanPatientsList() 2. call seePatient() for at
   * least two of the records in your patientsList, and use toString() to verify that they have been
   * removed after calling cleanPatientsList()
   * 
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testCleanList() {
    PatientRecord.resetCounter();
    // create an Admissions object and add a few Records to it
    ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(5);
    PatientRecord p1 = new PatientRecord('M', 18, PatientRecord.RED);
    PatientRecord p2 = new PatientRecord('F', 45, PatientRecord.GREEN);
    PatientRecord p3 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    admissions.addPatient(p1, 0);
    admissions.addPatient(p2, 1);
    admissions.addPatient(p3, 2);
    // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
    // patients does not change after calling cleanPatientsList()
    String originalPatientsList = admissions.toString();
    File noSeenPatientsFile = new File("no_seen_patients.txt");
    admissions.cleanPatientsList(noSeenPatientsFile);
    String cleanedPatientsList = admissions.toString();
    if (!originalPatientsList.equals(cleanedPatientsList)) {
      return false;
    }
    // (2) call seePatient() for at least two of the records in your patientsList, and use
    // toString()
    // to verify that they have been removed after calling cleanPatientsList()
    p1.seePatient();
    p2.seePatient();
    File seenPatientsFile = new File("text.file");
    admissions.cleanPatientsList(seenPatientsFile);
    String cleanedPatientsList2 = admissions.toString();
    if (cleanedPatientsList2.contains(p1.toString())
        || cleanedPatientsList2.contains(p2.toString())) {
      return false;
    }
    if (!cleanedPatientsList2.contains(p3.toString())) {
      return false;
    }
    // NOTE: you do NOT need to verify file contents in this test method; please do so manually
    return true;
  }


  /**
   * Runs each of the tester methods and displays the result. Methods with two testers have their
   * output grouped for convenience; a failed test is displayed as "X" and a passed test is
   * displayed as "pass"
   * 
   * @param args unused
   * @author hobbes
   */
  public static void main(String[] args) {
    System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
    System.out
        .println("Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X")
            + ", " + (testAdmissionsConstructorError() ? "pass" : "X"));
    System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", "
        + (testAddPatientError() ? "pass" : "X"));
    System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", "
        + (testGetIndexError() ? "pass" : "X"));
    System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
    System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", "
        + (testSeePatientError() ? "pass" : "X"));
    System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
    System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
  }

}
