//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Urgent Care Admissions
// Course:   CS 300 Spring 2023
//
// Author:   Seyeong Oh
// Email:    soh87@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NONE
// Partner Email:   NONE
// Partner Lecturer's Name: NONE
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is looking for our assistance automating its case management
 * protocols. As each patient arrives at the clinic, they are assigned five
 * digit case number, a count of which patient number they are that day, and the
 * triage priority level. And then, we get a index of each patient and add and
 * remove patient in our patientsList.
 *
 * @author Seyeong Oh
 */
public class UrgentCareAdmissions {
	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int GREEN = 2;

	/**
	 * This method adds the patient record, a three-element perfect size array of
	 * integers, to the patients list in the given position.
	 * 
	 * @param triage       the urgency level of the next patient's issue, RED YELLOW
	 *                     or GREEN
	 * @param patientsList the current, active list of patient records
	 * @param size         the number of patients currently in the list
	 * @return the index at which the patient should be inserted into the list, or
	 *         -1 if the list is full
	 */
	public static int getAdmissionIndex(int triage, int[][] patientsList, int size) {
		if (patientsList.length <= size) {
			return -1;
		}

		int index = 0;
		for (int i = 0; i < size; i++) {
			if (patientsList[i][2] <= triage) {
				index++;
			} else {
				break;
			}
		}
		return index;

	}

	/**
	 * This method adds the patient record, a three-element perfect size array of
	 * ints, to the patients list in the given position. This method must maintain
	 * the ordering of the rest of the array, so any patients in higher index
	 * positions must be shifted out of the way.
	 * 
	 * @param patientRecord a three-element perfect size array of ints, containing
	 *                      the patient's 5-digit case ID, their admission order
	 *                      number, and their triage level
	 * @param index         the index at which the patientRecord should be added to
	 *                      patientsList, assumed to correctly follow the
	 *                      requirements of getAdmissionIndex()
	 * @param patientsList  the current, active list of patient records
	 * @param size          the number of patients currently in the list
	 * @return the number of patients in patientsList after this method has finished
	 *         running
	 */
	public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
		if (patientsList.length == size || size < index || index < 0) {
			return size;
		}
		if (patientsList.length < size)
			return patientsList.length;

		for (int i = size - 1; i >= index; i--) {
			patientsList[i + 1] = patientsList[i];
		}

		patientsList[index] = patientRecord;
		return size + 1;
	}

	/**
	 * This method removes the patient record at index 0 of the patientsList, if
	 * there is one, and updates the rest of the list to maintain the oversize array
	 * in its current ordering.
	 * 
	 * @param patientsList the current, active list of patient records
	 * @param size         the number of patients currently in the list
	 * @return the number of patients in patientsList after this method has finished
	 *         running
	 */
	public static int removeNextPatient(int[][] patientsList, int size) {
		if (size == 0 || patientsList == null) {
			return 0;
		}
		for (int i = 0; i < size - 1; i++) {
			patientsList[i] = patientsList[i + 1];
		}
		patientsList[size - 1] = null;
		return size - 1;

	}

	/**
	 * This method finds the index of a patient given their caseID number. This
	 * method must not modify patientsList in any way.
	 * 
	 * @param caseID       the five-digit case number assigned to the patient record
	 *                     to find.
	 * @param patientsList the current, active list of patient records
	 * @param size         the number of patients currently in the list
	 * @return the index of the patient record matching the given caseID number, or
	 *         -1 if the list is empty or the caseID is not found
	 */
	public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
		for (int i = 0; i < size; i++) {
			if (patientsList[i][0] == caseID) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method finds the patient who arrived earliest still currently present in
	 * the patientsList, and returns the index of their patient record within the
	 * patientsList. The arrival value is strictly increasing for each new patient,
	 * so you will not need to handle the case where two values are equal.
	 * 
	 * @param patientsList the current, active list of patient records
	 * @param size         the number of patients currently in the list
	 * @return the index of the patient record with the smallest value in their
	 *         arrival integer, or -1 if the list is empty
	 */
	public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
		if (size == 0 || patientsList == null) {
			return -1;
		}
		int minimumArrival = patientsList[0][1];
		int minArrivalIndex = 0;
		for (int i = 1; i < size; i++) {
			if (patientsList[i][1] < minimumArrival) {
				minimumArrival = patientsList[i][1];
				minArrivalIndex = i;
			}
		}
		return minArrivalIndex;
	}

	/**
	 * This method creates a formatted String summary of the current state of the
	 * patientsList array
	 * 
	 * @param patientsList the current, active list of patient records
	 * @param size         the number of patients currently in the list
	 * @return a String summarizing the patientsList as shown in this comment
	 */
	public static String getSummary(int[][] patientsList, int size) {
		if (size == 0 || patientsList == null) {
			return "Total number of patients: 0\nRED: 0\nYELLOW: 0\nGREEN: 0\n";
		}
		int countRed = 0;
		int countYellow = 0;
		int countGreen = 0;
		for (int i = 0; i < size; i++) {
			if (patientsList[i][2] == 0) {
				countRed++;
			} else if (patientsList[i][2] == 1) {
				countYellow++;
			} else if (patientsList[i][2] == 2) {
				countGreen++;
			}
		}
		return "Total number of patients: " + size + "\nRED: " + countRed + "\nYELLOW: " + countYellow + "\nGREEN: "
				+ countGreen + "\n";

	}

}
