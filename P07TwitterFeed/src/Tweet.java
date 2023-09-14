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

import java.util.Calendar;
import java.util.Date;

/**
 * This data type models a tweet - a short text post made on the social media service Twitter
 * 
 * @author seyeongoh
 *
 */
public class Tweet {
  private static Calendar dateGenerator;
  private User user;
  private String text;
  private int numLikes;
  private int numRetweets;
  private Date timestamp;
  private static final int MAX_LENGTH = 280;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its
   * timestamp should be set to the current time
   * 
   * @param user : the User posting this tweet
   * @param text : the text of the tweet
   * @throws IllegalArgumentException : if the tweet's text exceeds MAX_LENGTH characters
   * @throws NullPointerException     : if the provided text or user are null
   * @throws IllegalStateException    : if the dateGenerator field has not yet been initialized
   */
  public Tweet(User user, String text)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (text == null || user == null) {
      throw new NullPointerException("Text and user cannot be null");
    }
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Text cannot exceed " + MAX_LENGTH);
    }
    if (dateGenerator == null) {
      throw new IllegalStateException("Date generator not initialized");
    }
    this.user = user;
    this.text = text;
    this.timestamp = dateGenerator.getTime();
    this.numLikes = 0;
    this.numRetweets = 0;
  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object. For tests which do
   * not require a consistent date, you can use Calendar.getInstance() to get a Calendar set to the
   * current time
   * 
   * @param c : the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }

  /**
   * Accesses the text of this tweet
   * 
   * @return the text of this tweet
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * 
   * @return the total engagement of this tweet
   * 
   */
  public int getTotalEngagement() {
    return numLikes + numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * 
   * @return true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    return user.isVerified();
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do
   * positive, uplifting ratios around here
   * 
   * @return the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the
   *         total engagement is 0.
   */
  public double getLikesRatio() {
    int totalEngagement = getTotalEngagement();
    if (totalEngagement == 0) {
      return -1;
    }
    return (double) numLikes / totalEngagement;
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    numLikes++;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    numRetweets++;
  }

  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
   * that contains the same text posted at the same time by the same User (use the toString() method
   * from User to compare these!) then the two Tweets are considered equal regardless of their
   * respective likes/retweets
   * 
   * @param o : the object to compare this Tweet to
   * @return true if this Tweet is equivalent to the provided object, false otherwise
   * 
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Tweet) {
      Tweet tweet = (Tweet) o;
      return this.user.toString().equals(tweet.user.toString()) && this.text.equals(tweet.text)
          && this.timestamp.equals(tweet.timestamp);
    } else {
      return false;
    }
  }

  /**
   * A string representation of this tweet
   * 
   * @return a formatted string representation of this tweet
   */
  @Override
  public String toString() {
    String userString = user.toString();
    String timestampString = timestamp.toString();
    String infoString =
        String.format("-- %s\n-- likes: %d\n-- retweets: %d", this.text, numLikes, numRetweets);
    return String.format("tweet from %s at %s:\n%s", userString, timestampString, infoString);
  }
}
