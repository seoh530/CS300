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
 * A container for a Tweet in a singly-linked list
 * 
 * @author seyeongoh
 *
 */
public class TweetNode {

  private Tweet tweet;
  private TweetNode nextTweet;

  /**
   * Constructs a singly-linked node containing a tweet
   * 
   * @param tweet : the tweet to put in this node
   * @param next  : the next tweet in the linked list
   */
  public TweetNode(Tweet tweet, TweetNode next) {
    this.tweet = tweet;
    this.nextTweet = next;
  }

  /**
   * Constructs a singly-linked node containing a tweet, with no successor tweet
   * 
   * @param tweet : the tweet to put in this node
   */
  public TweetNode(Tweet tweet) {
    this(tweet, null);
  }

  /**
   * Accesses the tweet in this node
   * 
   * @return the tweet in this node
   * 
   */
  public Tweet getTweet() {
    return this.tweet;
  }

  /**
   * Accesses the next TweetNode in the list
   * 
   * @return the successor TweetNode
   */
  public TweetNode getNext() {
    return this.nextTweet;
  }

  /**
   * Links this node to another node
   * 
   * @param next the successor TweetNode (can be null)
   */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }
}
