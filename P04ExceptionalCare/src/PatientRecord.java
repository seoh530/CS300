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

/**
 * An instantiable class which models a patient's record for the admissions program. This clas
 * automatically generates a caseID number from provided patiend data, which is not exactly
 * HIPAA-compliant.
 * 
 * @author seyeongoh
 *
 */
public class PatientRecord {

  public static void main(String[] args) {
    ExceptionalCareAdmissions c = new ExceptionalCareAdmissions(5);
    PatientRecord p1 = new PatientRecord('M', 18, PatientRecord.RED);
    PatientRecord p2 = new PatientRecord('M', 5, PatientRecord.YELLOW);
    PatientRecord p3 = new PatientRecord('F', 45, PatientRecord.GREEN);
    c.addPatient(p1, 0);
    c.addPatient(p2, 1);
    c.addPatient(p3, 2);
    System.out.println(c);
  }

  public static final int RED = 0;
  public static final int YELLOW = 1;
  public static final int GREEN = 2;
  public final int CASE_NUMBER; // The generated case number associated with this patient record
  private static int patientCounter = 1;// counts the number of patients created

  private int age; // This patient's age
  private char gender; // This patient's single-character gender maker;
  private boolean hasBeenSeen; // Whether this patient has been marked as "seen"
  private int orderOfArrival; // The order in which patient arrived
  private int triage; // This patient's triage level, one of [R,Y,G]

  /**
   * Creates a new patient record and assigns it a CASE_NUMBER using the provided information
   * 
   * @param gender a single-character representation of this patient's reported gender
   * @param age    the age of this patient in years
   * @param triage the triage level of this patient
   */
  PatientRecord(char gender, int age, int triage) {
    if (triage != RED && triage != YELLOW && triage != GREEN) {
      throw new IllegalArgumentException("Triage value must be RED, YELLOW, or GREEN");
    }
    this.gender = gender;
    this.age = age;
    this.triage = triage;
    this.orderOfArrival = patientCounter;
    this.CASE_NUMBER = generateCaseNumber(gender, age);
  }

  /**
   * Generates a five-digit case number for this patient using their reported gender and age
   * 
   * @param gender a single-character representation of this patient's reported gender
   * @param age    the age of this patient in years
   * @return a five-digit case number for the patient
   */
  public static int generateCaseNumber(char gender, int age) {
    // first digit(gender): F=1, M=2, X=3. Any other gender = 4
    // next two digits(age): 03 --> 3-year-old or 103-year-old
    // last two digits: increment according to the number of patients admitted during this run of
    // Exceptional Care; The first patient should be 01, counting up to 99, and then wrapping around
    // to 00.
    int genderDigit;
    int ageDigits;
    int numOfPatientDigits;

    if (gender == 'F') {
      genderDigit = 1;
    } else if (gender == 'M') {
      genderDigit = 2;
    } else if (gender == 'X') {
      genderDigit = 3;
    } else {
      genderDigit = 4;
    }

    ageDigits = age % 100;

    numOfPatientDigits = (patientCounter % 100);

    patientCounter++;


    return (genderDigit * 10000) + (ageDigits * 100) + numOfPatientDigits;
  }

  /**
   * For tester class purposes only: resents PatientRecord.patientCounter to 1. This method should
   * be called at the beginning of EACH tester method to ensure that the methods are not dependent
   * on being called in a particular order
   */
  public static void resetCounter() {
    patientCounter = 1;
  }

  /**
   * Accessor method for triage
   * 
   * @return this PatientRecord's triage value
   */
  public int getTriage() {
    return triage;
  }

  /**
   * Accessor method for gender
   * 
   * @return this PatientRecord's gender maker
   */
  public char getGender() {
    return gender;
  }

  /**
   * Accessor method for age
   * 
   * @return this PatientRecord's age value
   */
  public int getAge() {
    return age;
  }

  /**
   * Accessor method for order of arrival
   * 
   * @return this PatientRecord's orderOfArrival value
   */
  public int getArrivalOrder() {
    return orderOfArrival;
  }

  /**
   * Accessor method for hasBeenSeen
   * 
   * @return true if patient has been seen, false otherwise
   */
  public boolean hasBeenSeen() {
    return hasBeenSeen;
  }

  /**
   * Marks this patient as having been seen There is no way to undo this action
   */
  public void seePatient() {
    hasBeenSeen = true;
  }


  /**
   * Creates and returns a String representation of this PatientRecord using its data field values:
   * [CASE_NUMBER] : [AGE] [GENDER] ([TRIAGE])
   */
  @Override
  public String toString() {
    String color = null;
    if (triage == 0) {
      color = "RED";
    } else if (triage == 1) {
      color = "YELLOW";
    } else if (triage == 2) {
      color = "GREEN";
    }
    return CASE_NUMBER + ": " + age + gender + " (" + color + ")";

  }

}

