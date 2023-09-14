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
 * This Changemaker class computes the optimal number of coins by receiving same length of two
 * arrays which are : denominations (value of each type of coin) and coinsRemaining(quantity of each
 * type of coin) and value.
 * 
 * @author seyeongoh
 *
 */
public class Changemaker {
  /**
   * Method to get the number of ways to make change for the given value using the denominations and
   * the coins remaining.
   * 
   * @param denominations  : array describes the value of each type of coin in your register
   * @param coinsRemaining : array describes the quantity of each type of coin in your register
   * @param value          : the total amount of change to be dispensed to the customer
   * @return count : number of ways to make change
   */
  public static int count(int[] denominations, int[] coinsRemaining, int value) {

    // base case 1: value is negative, no way to make change
    if (value < 0) {
      return 0;
    }
    // base case 2: value is zero, only one way to make change (return no coins)
    else if (value == 0) {
      return 1;
    }

    // recursive case
    else {
      int count = 0;
      for (int i = 0; i < denominations.length; i++) {
        if (coinsRemaining[i] > 0 && denominations[i] <= value) {
          int[] newCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
          newCoinsRemaining[i]--;
          count += count(denominations, newCoinsRemaining, value - denominations[i]);
        }
      }
      return count;
    }
  }


  /**
   * minCoins method returns the minimum number of coins required to make change for a given value
   * using the given denominations and remaining coins
   * 
   * @param denominations  : array describes the value of each type of coin in your register
   * @param coinsRemaining : array describes the quantity of each type of coin in your register
   * @param value          : the total amount of change to be dispensed to the customer
   * @return minCoins : the minimum number of coins required to make change
   */
  public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
    // base case 1: value is negative, no way to make change
    if (value < 0) {
      return -1;
    }
    // base case 2: value is zero, no coins needed
    else if (value == 0) {
      return 0;
    }

    // recursive case
    else {
      int minCoins = Integer.MAX_VALUE;
      for (int i = 0; i < denominations.length; i++) {
        if (coinsRemaining[i] > 0 && denominations[i] <= value) {
          int[] newCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
          newCoinsRemaining[i]--;
          int coins = minCoins(denominations, newCoinsRemaining, value - denominations[i]);
          if (coins >= 0 && coins < minCoins) {
            minCoins = coins + 1;
          }
        }
      }
      if (minCoins == Integer.MAX_VALUE) {
        return -1;
      } else {
        return minCoins;
      }
    }
  }

  /**
   * makeChange method returns an array of coin counts for each denomination, such that the sum of
   * the coin counts equals the given value and the total number of coins used is minimized, using
   * the given denominations and remaining coins in the register
   * 
   * @param denominations  : array describes the value of each type of coin in your register
   * @param coinsRemaining : array describes the quantity of each type of coin in your register
   * @param value          : the total amount of change to be dispensed to the customer
   * @return optimalCoins : value at index i represents how many coins of corresponding value
   *         denominations[i] selected. if value is negative, returns null. if value is zero,
   *         returns an array with 0.
   */
  public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
    // base case 1: value is negative, no way to make change
    if (value < 0) {
      return null;
    }
    // base case 2: value is zero, fill array with 0
    else if (value == 0) {
      return new int[denominations.length]; // default value of primitive type array : int 0
    }

    // recursive case
    else {

      int[] optimalCoins = null;
      int minCoins = Integer.MAX_VALUE;

      for (int i = 0; i < denominations.length; i++) {
        if (coinsRemaining[i] > 0 && denominations[i] <= value) {
          int[] newCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
          newCoinsRemaining[i]--;
          int[] coins = makeChange(denominations, newCoinsRemaining, value - denominations[i]);
          if (coins != null) {
            int sumCoins = sum(coins) + 1;
            if (sumCoins < minCoins) {
              minCoins = sumCoins;
              optimalCoins = Arrays.copyOf(coins, coins.length);
              optimalCoins[i]++;
            }
          }
        }
      }
      return optimalCoins;
    }
  }

  /**
   * sum method returns the sum of all values in an array.
   * 
   * @param coins an array of integers
   * @return the sum of all values in the array
   */
  private static int sum(int[] coins) {
    int totalCoin = 0;
    for (int coin : coins) {
      totalCoin += coin;
    }
    return totalCoin;
  }
}
