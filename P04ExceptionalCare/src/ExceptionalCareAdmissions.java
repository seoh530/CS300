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
import java.io.IOException;
import java.io.PrintWriter;

/**
 * An object-oriented iteration of our UrgentCareAdmissions program, which performs its error
 * checking using exceptions instead of error codes.
 * 
 * Patient records in the patientsList are now single PatientRecord objects, containing the same
 * data as before but with a few additional components, namely: age, gender, and a boolean value
 * indicating whether they have been seen by a doctor.
 * 
 * @author seyeongoh
 *
 */
public class ExceptionalCareAdmissions {

  private PatientRecord[] patientsList; // An oversize array containing the PatientRecords currently
                                        // active in this object
  private int size; // The number of values in the oversize array

  /**
   * Creates a new, empty ExceptionalCareAdmissions object with the given capacity
   * 
   * @param capacity the maximum number of patient records this ExceptionalCareAdmissions object can
   *                 hold
   * @throws IllegalArgumentException if the provided capacity is less than or equal to zero
   */
  public ExceptionalCareAdmissions(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity should be more than zero");
    }
    patientsList = new PatientRecord[capacity];
    size = 0;
  }

  /**
   * An accessor method to determine if the patientsList is currently full
   * 
   * @return true if the patientsList is full, false otherwise
   */
  public boolean isFull() {
    if (size == patientsList.length)
      return true;
    return false;
  }

  /**
   * Accesses the current number of patient records in the patientsList
   * 
   * @return the current number of PatientRecord objects in this ExceptionalCareAdmissions object
   */
  public int size() {
    return size;

  }

  /**
   * Accesses the current number of patient records in this patientsList representing patients who
   * have already been seen (and could be removed from the list)
   * 
   * @return the current count of patientRecords for which the hasBeenSeen() method returns true
   */
  public int getNumberSeenPatients() {
    int numSeenPatients = 0;
    for (int i = 0; i < size; i++) {
      if (patientsList[i].hasBeenSeen()) {
        numSeenPatients += 1;
      }
    }
    return numSeenPatients;

  }

  /**
   * As before, this helper method will find the correct index to insert the new patient record into
   * patientsList, maintaining triage priority order. This method's logic is the same as that from
   * P01, but uses objects and exceptions rather than primitive values.This method must NOT modify
   * this.patientsList in any way.
   * 
   * 
   * @param rec the PatientRecord to be added to the list
   * 
   * @return the correct index of patientsList at which rec should be added
   * @throws IllegalStateException if the patientsList is full: - with the message
   *                               "cleanPatientsList()" if the patientsList contains any patients
   *                               who have been seen, or - with the message "Cannot admit new
   *                               patients" if there are NO seen patients in the patientsList
   */
  public int getAdmissionIndex(PatientRecord rec) throws IllegalStateException {
    // patientsList is full
    if (size == patientsList.length) {
      boolean hasSeen = false;
      for (PatientRecord patient : patientsList) {
        if (patient != null && patient.hasBeenSeen()) {
          hasSeen = true;
          break;
        }
      }
      if (hasSeen) {
        throw new IllegalStateException("cleanPatientsList()");
      } else {
        throw new IllegalStateException("Cannot admit new patients");
      }
    }
    // get index
    int index = 0;
    for (int i = 0; i < size; i++) {
      PatientRecord patient = patientsList[i];
      if (patient.getTriage() >= rec.getTriage()) {
        index = i;
        break;
      }
      // when the index is the end of the array
      if (i == size - 1) {
        index = i + 1;
        break;
      }
    }
    return index;
  }

  /**
   * Adds the provided PatientRecord at the provided position in the oversize patientsList array.
   * This method must maintain the ordering of the patientsList as before, and rather than returning
   * the new size, maintains the size field in this ExceptionalCareAdmissions object appropriately.
   * 
   * @param rec   the PatientRecord to be added
   * @param index the index at which the PatientRecord should be added to patientsList, which you
   *              may assume is the same as the output of getAdmissionIndex()
   * 
   * @throws IllegalStateException    if the patientsList is full: - with the message
   *                                  "cleanPatientsList()" if the patientsList contains any
   *                                  patients who have been seen, or - with the message "Cannot
   *                                  admit new patients" if there are NO seen patients in the
   *                                  patientsList
   * 
   * @throws IllegalArgumentException with a descriptive error message if the patientsList is NOT
   *                                  full and the index is not a valid index into the oversize
   *                                  array
   */
  public void addPatient(PatientRecord rec, int index)
      throws IllegalStateException, IllegalArgumentException {
    // patientsList is full
    if (size == patientsList.length) {
      boolean hasSeenPatients = false;
      for (PatientRecord patient : patientsList) {
        if (patient != null && patient.hasBeenSeen()) {
          hasSeenPatients = true;
          break;
        }
      }
      if (hasSeenPatients) {
        throw new IllegalStateException("cleanPatientsList()");
      } else {
        throw new IllegalStateException("Cannot admit new patients");
      }
    }
    // index is out of range
    else if (index < 0 || index > size) {
      throw new IllegalArgumentException("Invalid index");
    }
    // insert the new patient at the specified index
    else {
      for (int i = size - 1; i >= index; i--) {
        patientsList[i + 1] = patientsList[i];
      }
      patientsList[index] = rec;
      size++;
    }
  }

  /**
   * Marks the patient with the given caseID as having been seen. This method will require you to
   * find the patient with the given caseID within the patientsList before you can modify their
   * status. This method may only modify one PatientRecord, and may not modify the patientsList
   * array or its size.
   * 
   * @param caseID the CASE_NUMBER of the PatientRecord to be marked as having been seen
   * @throws IllegalStateException    if the patientsList is currently empty
   * @throws IllegalArgumentException if no PatientRecord with the given caseID is found
   */
  public void seePatient(int caseID) throws IllegalStateException, IllegalArgumentException {
    if (size == 0) {
      throw new IllegalStateException("patientsList is empty");
    }

    boolean patientFound = false;
    for (int i = 0; i < size; i++) {
      if (patientsList[i].CASE_NUMBER == caseID) {
        patientFound = true;
        patientsList[i].seePatient(); // mark the patient has been seen
        break;
      }
    }

    if (!patientFound) {
      throw new IllegalArgumentException("No patient with the given caseID was found");
    }

  }

  /**
   * Creates a formatted String summary of the current state of the patientsList, incorporating an
   * additional component from the PatientRecord class. See below for details.
   * 
   * @return a String summarizing the patientsList as shown in this comment
   */
  public String getSummary() {
    /**
     * Total number of patients: 5 Total number seen: 3 RED: 1 YELLOW: 3 GREEN: 1
     */

    int totalPatients = size;
    int totalSeen = 0;
    int redCount = 0;
    int yellowCount = 0;
    int greenCount = 0;

    for (int i = 0; i < size; i++) {
      if (patientsList[i].hasBeenSeen()) {
        totalSeen++;
      }
      switch (patientsList[i].getTriage()) {
        case PatientRecord.RED:
          redCount++;
          break;
        case PatientRecord.YELLOW:
          yellowCount++;
          break;
        case PatientRecord.GREEN:
          greenCount++;
          break;
      }
    }
    return "Total number of patients: " + totalPatients + "\nTotal number seen: " + totalSeen
        + "\nRED: " + redCount + "\nYELLOW: " + yellowCount + "\nGREEN: " + greenCount;
  }

  /**
   * This method runs occasionally to record the current state of the patientsList and save any
   * records for seen patients to a file, while removing them from the patientsList to make more
   * room.
   * 
   * @param file the file object to use for recording the data
   */
  public void cleanPatientsList(File file) {
    String patientRecord = "";
    PatientRecord[] notSeenPatient = new PatientRecord[patientsList.length];
    int notSeenIdx = 0;
    int notSeenSize = size;

    try {
      PrintWriter finalRecord = new PrintWriter(file);

      // seen
      for (int i = 0; i < size; i++) {
        if (patientsList[i].hasBeenSeen()) {
          patientRecord += patientsList[i].toString() + "\n";
          // the index of seen patients should be null after putting a record
          patientsList[i] = null;
        }
      }
      // not seen
      for (int i = 0; i < notSeenSize; i++) {
        if (patientsList[i] != null && !patientsList[i].hasBeenSeen()) {
          notSeenPatient[notSeenIdx] = patientsList[i];
          notSeenIdx++;
        }
      }
      // update the new patientList and its size
      patientsList = notSeenPatient;
      size = notSeenIdx;

      finalRecord.write(patientRecord);
      finalRecord.close();
    } catch (IOException e) {
      e.getMessage();
    }
  }


  /**
   * For testing purposes: this method creates and returns a string representation of the *
   * patientsList, as the in-order string representation of each patient in the list on a * separate
   * line. If patientsList is empty, returns an empty string.
   *
   * @return a string representation of the contents of patientsList
   */
  @Override
  public String toString() {
    String returnValue = "";
    for (PatientRecord r : patientsList) {
      returnValue += (r != null) ? r.toString() + "\n" : "";
    }
    return returnValue.trim();
  }
}
