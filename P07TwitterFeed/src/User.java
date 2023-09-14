//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Twitter Feed
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

/**
 * This (very basic) data type models a Twitter user
 * 
 * @author seyeongoh
 *
 */
public class User {

  private String username;
  private boolean isVerified;

  /**
   * Constructs a new User object with a given username. All Users are unverified by default
   * 
   * @param username : the username of this User
   */
  public User(String username) {
    if (username == null || username.isBlank() || username.contains("*")) {
      throw new IllegalArgumentException("Invalid username");
    }
    this.username = username;
    this.isVerified = false;
  }

  /**
   * Accesses the username of this User
   * 
   * @return the username this User tweets under
   */
  public String getUsername() {
    return username;
  }

  /**
   * Determines whether the User is a verified user or not
   * 
   * @return true if this User is verified
   */
  public boolean isVerified() {
    return isVerified;
  }

  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    this.isVerified = true;
  }

  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    this.isVerified = false;
  }

  /**
   * Creates a String representation of this User for display
   */
  @Override
  public String toString() {
    String result = "@" + username;
    if (isVerified) {
      result += "*";
    }
    return result;
  }

}


