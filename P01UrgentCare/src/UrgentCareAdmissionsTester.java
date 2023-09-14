//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Urgent Care Admissions Tester
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
// Partner Lecturer's Name:  NONE
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

import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

/**
 * This class is testing methods of UrgentCareAdmissions class
 * (getAdmissionIndex(), addPatient(), removeNextPatient(), getPatientIndex(),
 * getLongestWaitingPatientIndex(), getSummary()) are working well according to
 * instructions. Also, this class test Empty and Full patientList for testing
 * the empty and full list exceptions working well.
 *
 * @author Seyeong Oh
 */
public class UrgentCareAdmissionsTester {

	/**
	 * This test method is provided for you in its entirety, to give you a model for
	 * the rest of this class. This method tests the getAdmissionIndex() method on a
	 * non-empty, non-full array of patient records which we create and maintain
	 * here.
	 * 
	 * This method tests three scenarios:
	 * 
	 * 1. Adding a patient with a HIGHER triage priority than any currently present
	 * in the array. To do this, we create an array with no RED priority patients
	 * and get the index to add a RED priority patient. We expect this to be 0, so
	 * if we get any other value, the test fails.
	 * 
	 * 2. Adding a patient with a LOWER triage priority than any currently present
	 * in the array. To do this, we create an array with no GREEN priority patients
	 * and get the index to add a GREEN priority patient. We expect this to be the
	 * current size of the oversize array, so if we get any other value, the test
	 * fails.
	 * 
	 * 3. Adding a patient with the SAME triage priority as existing patients. New
	 * patients at the same priority should be added AFTER any existing patients. We
	 * test this for all three triage levels on an array containing patients at all
	 * three levels.
	 * 
	 * @author hobbes
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testGetIndex() {

		// The non-empty, non-full oversize arrays to use in this test.
		// Note that we're using the UrgentCareAdmissions named constants to create
		// these test records,
		// rather than their corresponding literal int values.
		// This way if the numbers were to change in UrgentCareAdmissions, our test will
		// still be valid.
		int[][] patientsListAllLevels = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
				{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.YELLOW },
				{ 11901, 5, UrgentCareAdmissions.YELLOW }, { 31501, 1, UrgentCareAdmissions.GREEN }, null, null, null };
		int allLevelsSize = 5;

		int[][] patientsListOnlyYellow = new int[][] { { 21801, 2, UrgentCareAdmissions.YELLOW },
				{ 22002, 4, UrgentCareAdmissions.YELLOW }, { 11901, 5, UrgentCareAdmissions.YELLOW }, null, null, null,
				null, null };
		int onlyYellowSize = 3;

		// scenario 1: add a patient with a higher priority than any existing patient
		{
			int expected = 0;
			// System.out.println("Hi");
			int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListOnlyYellow,
					onlyYellowSize);
			if (expected != actual)
				return false;
		}

		// scenario 2: add a patient with a lower priority than any existing patient
		{
			int expected = onlyYellowSize;
			int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListOnlyYellow,
					onlyYellowSize);
			if (expected != actual)
				return false;
		}

		// scenario 3: verify that a patient with the same priority as existing patients
		// gets
		// added after all of those patients
		{
			int expected = 1;
			int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListAllLevels,
					allLevelsSize);
			if (expected != actual)
				return false;

			expected = 4;
			actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListAllLevels,
					allLevelsSize);
			if (expected != actual)
				return false;

			expected = allLevelsSize;
			actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListAllLevels,
					allLevelsSize);
			if (expected != actual)
				return false;
		}

		// and finally, verify that the arrays were not changed at all
		{
			int[][] allLevelsCopy = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
					{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.YELLOW },
					{ 11901, 5, UrgentCareAdmissions.YELLOW }, { 31501, 1, UrgentCareAdmissions.GREEN }, null, null,
					null };
			if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy))
				return false;

			int[][] onlyYellowCopy = new int[][] { { 21801, 2, UrgentCareAdmissions.YELLOW },
					{ 22002, 4, UrgentCareAdmissions.YELLOW }, { 11901, 5, UrgentCareAdmissions.YELLOW }, null, null,
					null, null, null };
			if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy))
				return false;
		}

		return true;
	}

	/**
	 * This method tests the behavior of the addPatient method using a non-empty, 
	 * non-full array. Each test should verify that the returned size is as expected and 
	 * that the array has been updated (or not) appropriately
	 * This method tests three scenarios:
	 * 1. add a patient to the END of the patientsList
	 * 2. add a patient to the MIDDLE of the patientsList
	 * 3. add a patient using an invalid (out-of-bounds) index
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testAddPatient() {
		int[][] patientsListOne = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
			{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.YELLOW },
			{ 31501, 1, UrgentCareAdmissions.GREEN }, null, null, null, null };
		int ListOneSize = 4;

		int[][] patientsListTwo = new int[][] { { 34553, 3, UrgentCareAdmissions.RED },
			{ 21483, 2, UrgentCareAdmissions.YELLOW }, { 24542, 1, UrgentCareAdmissions.GREEN },
			null, null, null,null, null };
		int ListTwoSize = 3;

		int[] RecordOne = { 10101, 5, UrgentCareAdmissions.GREEN };
		int[] RecordTwo = { 20202, 4, UrgentCareAdmissions.YELLOW };
		int[] RecordThree = { 30303, 6, UrgentCareAdmissions.YELLOW };

		// (1) add a patient to the END of the patientsList
		{
			int expected = 5;
			int actual = UrgentCareAdmissions.addPatient(RecordOne, 3, patientsListOne, ListOneSize);
			if (expected != actual)
				return false;
		}
		// (2) add a patient to the MIDDLE of the patientsList
		{
			int expected = 4;
			int actual = UrgentCareAdmissions.addPatient(RecordTwo, 2, patientsListTwo, ListTwoSize);
			if (expected != actual)
				return false;
		}

		// (3) add a patient using an invalid (out-of-bounds) index
		{
			int expected = ListOneSize;// 5
			int actual = UrgentCareAdmissions.addPatient(RecordThree, 6, patientsListOne, ListOneSize);
			if (expected != actual)
				return false;
		}
		return true;
	}


	/**
	 * This method tests the behavior of the removeNextPatient method using a non-empty,
	 * non-full array. Each test should verify that the returned size is as expected and 
	 * that the array has been updated (or not) appropriately
	 * This method tests two scenarios:
	 * 1. remove a patient from a patientsList containing more than one record (size > 1)
	 * 2. remove a patient from a patientsList containing only one record (size == 1)
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testRemovePatient() {

		int[][] patientsList = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
				{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.YELLOW },
				{ 11901, 5, UrgentCareAdmissions.YELLOW }, { 31501, 1, UrgentCareAdmissions.GREEN }, null, null, null };

		int[][] patientsListOneSize = new int[][] { { 32702, 3, UrgentCareAdmissions.GREEN } };

		// (1) remove a patient from a patientsList containing more than one record
		// (size > 1)
		{
			int expected = 4;
			int actual = UrgentCareAdmissions.removeNextPatient(patientsList, 5);
			if (expected != actual)
				return false;
		}
		// (2) remove a patient from a patientsList containing only one record (size == 1)

		{
			int expected = 0;
			int actual = UrgentCareAdmissions.removeNextPatient(patientsListOneSize, 1);
			if (expected != actual)
				return false;
		}
		return true;
	}

	/**
	 * This method tests the behavior of the getPatientIndex method using a non-empty, 
	 * non-full array.
	 * This method tests three scenarios:
	 * 1. look for a patient at the end of the list
	 * 2. look for a patient in the middle of the list
	 * 3. look for a patient not present in the list
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testGetPatientIndex() {
		int[][] patientsAllTriage = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
				{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.YELLOW },
				{ 31501, 1, UrgentCareAdmissions.GREEN }, null, null, null, null };
		int allTriageSize = 4;
		// (1) look for a patient at the end of the list
		{
			int expected = 3;
			int actual = UrgentCareAdmissions.getPatientIndex(31501, patientsAllTriage, allTriageSize);
			if (expected != actual)
				return false;
		}

		// (2) look for a patient in the middle of the list
		{
			int expected = 2;
			int actual = UrgentCareAdmissions.getPatientIndex(22002, patientsAllTriage, allTriageSize);
			if (expected != actual)
				return false;
		}

		// (3) look for a patient not present in the list
		{
			int expected = -1;
			int actual = UrgentCareAdmissions.getPatientIndex(11111, patientsAllTriage, allTriageSize);
			if (expected != actual)
				return false;
		}
		return true;
	}

	/**
	 * This method tests the getLongestWaitingPatientIndex method using a non-empty, non-full
	 * array. When designing these tests, recall that arrivalOrder values will all be unique.
	 * This method tests two scenarios:
	 * 1. call the method on a patientsList with only one patient
	 * 2. call the method on a patientsList with at least three patients
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testLongestWaitingPatient() {
		int[][] onlyOnePatientList = new int[][] { { 32702, 3, UrgentCareAdmissions.RED }, null, null, null, null, null,
				null, null };
		int onlyOneSize = 1;

		int[][] ThreePatientList = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
				{ 21801, 1, UrgentCareAdmissions.YELLOW }, { 22002, 2, UrgentCareAdmissions.YELLOW }, null, null, null,
				null, null };
		int threePatientSize = 3;

		// (1) call the method on a patientsList with only one patient
		{
			int expected = 0;
			int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(onlyOnePatientList, onlyOneSize);
			if (expected != actual)
				return false;
		}

		// (2) call the method on a patientsList with at least three patients
		{
			int expected = 1;
			int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(ThreePatientList, threePatientSize);
			if (expected != actual)
				return false;
		}
		return true;
	}

	/**
	 * This method tests the edge case behavior of the UrgentCareAdmissions methods 
	 * using empty and full arrays
	 * This method tests six scenarios:
	 * 1. test getAdmissionIndex using an empty patientsList array and any triage level
	 * 2. test getAdmissionIndex using a full patientsList array and any triage level
	 * 3. test addPatient using a full patientsList array
	 * 4. test removeNextPatient using an empty patientsList array
	 * 5. test getPatientIndex using an empty patientsList array
	 * 6. test getLongestWaitingPatientIndex using an empty patientsList array
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testEmptyAndFullArrays() {
		int[][] emptyPatientList = new int[][] { null, null, null, null, null, null, null, null };
		int emptyPatientSize = 0;

		int[][] fullPatientList = new int[][] { { 32702, 8, UrgentCareAdmissions.RED },
				{ 21801, 1, UrgentCareAdmissions.YELLOW }, { 22002, 7, UrgentCareAdmissions.YELLOW },
				{ 12302, 6, UrgentCareAdmissions.RED }, { 21801, 2, UrgentCareAdmissions.YELLOW },
				{ 23457, 5, UrgentCareAdmissions.GREEN }, { 36644, 3, UrgentCareAdmissions.RED },
				{ 93620, 4, UrgentCareAdmissions.GREEN } };
		int fullPatientsize = 8;

		int[] RecordOne = { 10101, 9, UrgentCareAdmissions.GREEN };

		// (1) test getAdmissionIndex using an empty patientsList array and any triage
		// level
		{
			int expected = 0;
			int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, emptyPatientList,
					emptyPatientSize);
			if (expected != actual)
				return false;

		}
		// (2) test getAdmissionIndex using a full patientsList array and any triage
		// level
		{
			int expected = -1;
			int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, fullPatientList,
					fullPatientsize);
			if (expected != actual)
				return false;
		}

		// (3) test addPatient using a full patientsList array
		{
			int expected = 8;
			int actual = UrgentCareAdmissions.addPatient(RecordOne, 7, fullPatientList, fullPatientsize);
			if (expected != actual)
				return false;

		}

		// (4) test removeNextPatient using an empty patientsList array
		{
			int expected = 0;
			int actual = UrgentCareAdmissions.removeNextPatient(emptyPatientList, emptyPatientSize);
			if (expected != actual)
				return false;
		}

		// (5) test getPatientIndex using an empty patientsList array
		{
			int expected = -1;
			int actual = UrgentCareAdmissions.getPatientIndex(10101, emptyPatientList, emptyPatientSize);
			if (expected != actual)
				return false;
		}

		// (6) test getLongestWaitingPatientIndex using an empty patientsList array
		{
			int expected = -1;
			int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(emptyPatientList, emptyPatientSize);
			if (expected != actual)
				return false;
		}
		return true;
	}

	// Tests the getSummary method using arrays in various states
	/**
	 * This method tests the getSummary method using arrays in various states
	 * This method tests three scenarios:
	 * 1. test getSummary using an empty patientsList
	 * 2. test getSummary using a patientsList with multiple patients at a single triage level
	 * 3. test getSummary using a patientsList with patients at all triage levels
	 * 
	 * @return true if and only if all test scenarios pass, false otherwise
	 */
	public static boolean testGetSummary() {
		int[][] emptyPatientList = new int[][] { null, null, null, null, null, null, null, null };
		int emptyPatientSize = 0;

		int[][] singleLevelPatientsList = new int[][] { { 23456, 3, UrgentCareAdmissions.RED },
				{ 48045, 4, UrgentCareAdmissions.RED }, { 64334, 5, UrgentCareAdmissions.RED },
				{ 10834, 6, UrgentCareAdmissions.RED }, null, null, null, null };
		int singleLevelListSize = 4;

		int[][] allLevelsPatientsList = new int[][] { { 32702, 3, UrgentCareAdmissions.RED },
				{ 21801, 2, UrgentCareAdmissions.YELLOW }, { 22002, 4, UrgentCareAdmissions.GREEN },
				{ 15243, 5, UrgentCareAdmissions.GREEN }, null, null, null, null };
		int allLevelsListSize = 4;

		// (1) test getSummary using an empty patientsList
		{
			String expected = "Total number of patients: 0\nRED: 0\nYELLOW: 0\nGREEN: 0\n";
			String actual = UrgentCareAdmissions.getSummary(emptyPatientList, emptyPatientSize);
			if (!expected.equals(actual))
				return false;

		}

		// (2) test getSummary using a patientsList with multiple patients at a single
		// triage level
		{
			String expected = "Total number of patients: 4\nRED: 4\nYELLOW: 0\nGREEN: 0\n";
			String actual = UrgentCareAdmissions.getSummary(singleLevelPatientsList, singleLevelListSize);
			if (!expected.equals(actual))
				return false;
		}

		// (3) test getSummary using a patientsList with patients at all triage levels
		{
			String expected = "Total number of patients: 4\nRED: 1\nYELLOW: 1\nGREEN: 2\n";
			String actual = UrgentCareAdmissions.getSummary(allLevelsPatientsList, allLevelsListSize);
			if (!expected.equals(actual))
				return false;
		}
		return true;
	}

	/**
	 * Runs each of the tester methods and displays their result
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("get index: " + testGetIndex());
		System.out.println("add patient: " + testAddPatient());
		System.out.println("remove patient: " + testRemovePatient());
		System.out.println("get patient: " + testGetPatientIndex());
		System.out.println("longest wait: " + testLongestWaitingPatient());
		System.out.println("empty/full: " + testEmptyAndFullArrays());
		System.out.println("get summary: " + testGetSummary());
	}
}
