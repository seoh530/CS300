//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 ChangeMaker
// Course: CS 300 Spring 2023
//
// Author: Seyeong Oh
// Email: soh87@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class is testing class (including six tester methods) of Changemaker class are working well
 * according to instructions.
 * 
 * @author seyeongoh
 *
 */
public class ChangemakerTester {
  /**
   * This methods have three different scenarios of count() method in Changemaker: case 1. when
   * value is negative case 2. when value is positive but there is no way to make change. case 3.
   * when value = 0
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testCountBase() {

    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {1, 2, 3, 4};


    // case 1 : when the value is negative
    int value = -10;
    int expectedOutput = 0;
    int actualOutput = Changemaker.count(denominations, coinsRemaining, value);
    if (actualOutput != expectedOutput) {
      System.out.printf("testCountBase failed: expected %d but got %d\n", expectedOutput,
          actualOutput);
      return false;
    }

    // case 2 : when the value is zero
    int value2 = 0;
    int expectedOutput2 = 1;
    int actualOutput2 = Changemaker.count(denominations, coinsRemaining, value2);
    if (actualOutput2 != expectedOutput2) {
      System.out.printf("testCountBase2 failed: expected %d but got %d\n", expectedOutput2,
          actualOutput2);
      return false;
    }

    // testcase 3 : sum total of all the coins smaller than value
    int value3 = 100000;
    int expectedOutput3 = 0;
    int actualOutput3 = Changemaker.count(denominations, coinsRemaining, value3);
    if (actualOutput3 != expectedOutput3) {
      System.out.printf("testCountBase3 failed: expected %d but got %d\n", expectedOutput3,
          actualOutput3);
      return false;
    }
    return true;
  }

  /**
   * This methods have three different scenarios of count() method in Changemaker: case 1. at least
   * three different coins can be used to make change case 2. at least two different optimal ways to
   * make change using the same number of coins case 3. choosing the largest coin first
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testCountRecursive() {

    // case 1:
    int[] denominations = {1, 10, 16, 25};
    int[] coinsRemaining = {1, 1, 1, 1};
    int value = 27;
    int expectedOutput = 6;
    int actualOutput = Changemaker.count(denominations, coinsRemaining, value);
    if (actualOutput != expectedOutput) {
      System.out.printf("testCountRecursive failed: expected %d but got %d\n", expectedOutput,
          actualOutput);
      return false;
    }

    // case 2
    int[] denominations2 = {1, 5, 7, 11};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int expectedOutput2 = 4;
    int actualOutput2 = Changemaker.count(denominations2, coinsRemaining2, value2);
    if (actualOutput2 != expectedOutput2) {
      System.out.printf("testCountRecursive2 failed: expected %d but got %d\n", expectedOutput2,
          actualOutput2);
      return false;
    }

    // case 3
    int[] denominations3 = {1, 4, 7, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int expectedOutput3 = 5;
    int actualOutput3 = Changemaker.count(denominations3, coinsRemaining3, value3);
    if (actualOutput3 != expectedOutput3) {
      System.out.printf("testCountRecursive3 failed: expected %d but got %d\n", expectedOutput3,
          actualOutput3);
      return false;
    }
    return true;
  }

  /**
   * This methods have three different scenarios of minCoins() method in Changemaker: case 1. when
   * value is negative case 2. when value is positive but there is no way to make change. case 3.
   * when value = 0
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testMinCoinsBase() {

    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {1, 2, 3, 4};


    // case 1 : when the value is negative
    int value = -10;
    int expectedOutput = -1;
    int actualOutput = Changemaker.minCoins(denominations, coinsRemaining, value);
    if (actualOutput != expectedOutput) {
      System.out.printf("testMinCoinsBase failed: expected %d but got %d\n", expectedOutput,
          actualOutput);
      return false;
    }

    // case 2 : when the value is positive but there is no way to make change
    int value2 = 10000;
    int expectedOutput2 = -1;
    int actualOutput2 = Changemaker.minCoins(denominations, coinsRemaining, value2);
    if (actualOutput2 != expectedOutput2) {
      System.out.printf("testMinCoinsBase2 failed: expected %d but got %d\n", expectedOutput2,
          actualOutput2);
      return false;
    }

    // case 3 : when the value is zero
    int value3 = 0;
    int expectedOutput3 = 0;
    int actualOutput3 = Changemaker.minCoins(denominations, coinsRemaining, value3);
    if (actualOutput3 != expectedOutput3) {
      System.out.printf("testMinCoinsBase3 failed: expected %d but got %d\n", expectedOutput3,
          actualOutput3);
      return false;
    }
    return true;
  }

  /**
   * This methods have three different scenarios of minCoins() method in Changemaker: case 1. at
   * least three different coins can be used to make change case 2. at least two different optimal
   * ways to make change using the same number of coins case 3. choosing the largest coin first
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testMinCoinsRecursive() {

    // case 1: at least three different coins must be used to make change
    int[] denominations = {1, 10, 16, 25};
    int[] coinsRemaining = {1, 1, 1, 1};
    int value = 27;
    int expectedOutput = 3;
    int actualOutput = Changemaker.minCoins(denominations, coinsRemaining, value);
    if (actualOutput != expectedOutput) {
      System.out.printf("testMinCoinsRecursive failed: expected %d but got %d\n", expectedOutput,
          actualOutput);
      return false;
    }

    // case 2: at least two different ways to make change using the same optimal number of coins
    int[] denominations2 = {1, 5, 7, 11};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int expectedOutput2 = 2;
    int actualOutput2 = Changemaker.minCoins(denominations2, coinsRemaining2, value2);
    if (actualOutput2 != expectedOutput2) {
      System.out.printf("testMinCoinsRecursive2 failed: expected %d but got %d\n", expectedOutput2,
          actualOutput2);
      return false;
    }

    // case 3: choosing the largest coin first
    int[] denominations3 = {1, 4, 7, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int expectedOutput3 = 2;
    int actualOutput3 = Changemaker.minCoins(denominations3, coinsRemaining3, value3);
    if (actualOutput3 != expectedOutput3) {
      System.out.printf("testMinCoinsRecursive3 failed: expected %d but got %d\n", expectedOutput3,
          actualOutput3);
      return false;
    }
    return true;
  }

  /**
   * his methods have three different scenarios of makeChange() method in Changemaker: case 1. when
   * value is negative case 2. when value is positive but there is no way to make change. case 3.
   * when value = 0
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testMakeChangeBase() {

    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {1, 2, 3, 4};


    // case 1 : when the value is negative
    int value = -10;
    int[] expectedOutput = null;
    int[] actualOutput = Changemaker.makeChange(denominations, coinsRemaining, value);
    if (!Arrays.equals(actualOutput, expectedOutput)) {
      return false;
    }

    // case 2 : when the value is positive but there is no way to make change
    int value2 = 3;
    int[] expectedOutput2 = null;
    int[] actualOutput2 = Changemaker.makeChange(denominations, coinsRemaining, value2);
    if (!Arrays.equals(actualOutput2, expectedOutput2)) {
      return false;
    }

    // case 3 : when the value is zero
    int value3 = 0;
    int[] expectedOutput3 = {0, 0, 0, 0};
    int[] actualOutput3 = Changemaker.makeChange(denominations, coinsRemaining, value3);
    if (!Arrays.equals(actualOutput3, expectedOutput3)) {
      return false;
    }
    return true;
  }

  /**
   * This methods have three different scenarios of makeChange() method in Changemaker: case 1. at
   * least three different coins can be used to make change case 2. at least two different optimal
   * ways to make change using the same number of coins case 3. choosing the largest coin first
   * 
   * @return true if and only if all scenarios pass, false otherwise
   */
  public static boolean testMakeChangeRecursive() {

    // case 1: at least three different coins must be used to make change
    int[] denominations = {1, 10, 16, 25};
    int[] coinsRemaining = {1, 1, 1, 1};
    int value = 27;
    int[] expectedOutput = {1, 1, 1, 0};
    int[] actualOutput = Changemaker.makeChange(denominations, coinsRemaining, value);
    if (!Arrays.equals(actualOutput, expectedOutput)) {
      return false;
    }

    // case 2: at least two different ways to make change
    int[] denominations2 = {1, 5, 7, 11};
    int[] coinsRemaining2 = {1, 1, 1, 1};
    int value2 = 12;
    int[] expectedOutput2 = {1, 0, 0, 1};
    int[] expectedOutput22 = {0, 1, 1, 0};
    int[] actualOutput2 = Changemaker.makeChange(denominations2, coinsRemaining2, value2);
    if (!Arrays.equals(actualOutput2, expectedOutput2)
        && !Arrays.equals(actualOutput2, expectedOutput22)) {
      return false;
    }

    // case 3: always choosing the largest coin first doees not produce a result with the minimum
    // number of coins used
    int[] denominations3 = {1, 4, 7, 9};
    int[] coinsRemaining3 = {2, 1, 1, 1};
    int value3 = 11;
    int[] expectedOutput3 = {0, 1, 1, 0};
    int[] actualOutput3 = Changemaker.makeChange(denominations3, coinsRemaining3, value3);
    if (!Arrays.equals(actualOutput3, expectedOutput3)) {
      return false;
    }
    return true;
  }

  /**
   * Runs each of the tester methods and displays the result. A failed test is displayed as "X" and
   * a passed test is displayed as "pass"
   * 
   * @param args unused
   * 
   */
  public static void main(String[] args) {
    System.out.println("testCountBase: " + (testCountBase() ? "pass" : "X"));
    System.out.println("testCountRecursive: " + (testCountRecursive() ? "pass" : "X"));

    System.out.println("testMinCoinsBase: " + (testMinCoinsBase() ? "pass" : "X"));
    System.out.println("testMinCoinsRecursive: " + (testMinCoinsRecursive() ? "pass" : "X"));

    System.out.println("testMakeChangeBase: " + (testMakeChangeBase() ? "pass" : "X"));
    System.out.println("testMakeChangeRecursive: " + (testMakeChangeRecursive() ? "pass" : "X"));
  }
}
