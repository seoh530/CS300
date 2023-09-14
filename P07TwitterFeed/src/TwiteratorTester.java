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
import java.util.Iterator;

/**
 * This is the tester class to verify that the User, Tweet, TweetNode, and TwitterFeed classes are
 * working properly
 * 
 * @author seyeongoh
 *
 */
public class TwiteratorTester {

  /**
   * This is tester method to verify the User class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testUser() {

    // case 1: un-verified Usename
    User u1 = new User("Hyoshin");
    String expected = "@Hyoshin";
    String actual = u1.toString();
    if (!expected.equals(actual)) {
      System.out.println("Username doesn't match");
      return false;
    }

    // case 2: verified Username
    u1.verify();
    String expected2 = "@Hyoshin*";
    String actual2 = u1.toString();
    if (!expected2.equals(actual2)) {
      System.out.println("Doesn't have asterisk in verified username");
      return false;
    }

    // case 3: When username contains *
    try {
      User u3 = new User("Hyoshin*");
      return false;
    } catch (IllegalArgumentException e) {
      // Expected behavior
    }
    return true;
  }

  /**
   * This is tester method to verify the Tweet class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testTweet() {
    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    // case 1: check toString method is correct
    User user = new User("Hyoshin");
    String text = "Beautiful Tomorrow";
    Tweet tweet = new Tweet(user, text);
    tweet.like();
    tweet.retweet();
    String expected = "tweet from @Hyoshin at Tue May 22 14:46:03 CDT 2012:\n"
        + "-- Beautiful Tomorrow\n" + "-- likes: 1\n" + "-- retweets: 1";
    String actual = tweet.toString();
    if (!expected.equals(actual)) {
      return false;
    }

    return true;
  }

  /**
   * This is tester method to verify the TweetNode class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testNode() {
    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    User u1 = new User("Hyoshin");
    Tweet tweet1 = new Tweet(u1, "hello");

    User u2 = new User("Seyeong");
    Tweet tweet2 = new Tweet(u2, "I want to go home");

    User u3 = new User("Joe");
    Tweet tweet3 = new Tweet(u3, "sandwich");

    TweetNode nextNode = new TweetNode(tweet1);
    TweetNode node = new TweetNode(tweet2, nextNode);

    // CASE 1: Verify tweet2 is the current node.
    Tweet expected = tweet2;
    if (!expected.equals(node.getTweet())) {
      return false;
    }
    // CASE 2: Verify tweet1 is the next node.
    TweetNode expected2 = nextNode;
    if (!expected2.equals(node.getNext())) {
      return false;
    }
    // CASE 3: Verify that setNext() is working properly
    TweetNode newNext = new TweetNode(tweet3);
    expected2.setNext(newNext);

    if (!expected2.getNext().equals(newNext)) {
      return false;
    }

    return true;
  }

  /**
   * This is tester method to verify the addFirst, addLast, getHead, and getTail methods in
   * TwitterFeed class are working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testAddTweet() {

    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();

    // CASE 1: Verify that the feed is empty and has size 0
    int expected = 0;
    int actual = feed.size();
    if (expected != actual && feed.isEmpty()) {
      return false;
    }

    User u1 = new User("Hyoshin");
    Tweet firstTweet = new Tweet(u1, "wild flower");
    feed.addFirst(firstTweet);

    // CASE 2: Verify that it is no longer empty, has size 1
    int expected2 = 1;
    int actual2 = feed.size();
    if (expected2 != actual2 && !feed.isEmpty()) {
      return false;
    }

    // CASE 3: Verify contains() the Tweet you just added
    boolean expected3 = true;
    boolean actual3 = feed.contains(firstTweet);
    if (expected3 != actual3) {
      return false;
    }

    // CASE 4: Verify get(0) matches that Tweet
    String expected4 = "tweet from @Hyoshin at Tue May 22 14:46:03 CDT 2012:\n" + "-- wild flower\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual4 = feed.get(0);
    if ((!expected4.equals(actual4.toString()))) {
      return false;
    }

    User u2 = new User("BTS");
    Tweet secondTweet = new Tweet(u2, "Butter");
    feed.addLast(secondTweet);

    // CASE 5: Verify getHead() method
    String expected5 = "tweet from @Hyoshin at Tue May 22 14:46:03 CDT 2012:\n" + "-- wild flower\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual5 = feed.getHead();
    if ((!expected5.equals(actual5.toString()))) {
      return false;
    }

    // CASE 6: Verify getTail() method
    String expected6 = "tweet from @BTS at Tue May 22 14:46:03 CDT 2012:\n" + "-- Butter\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual6 = feed.getTail();
    if ((!expected6.equals(actual6.toString()))) {
      return false;
    }
    return true;
  }

  /**
   * This is tester method to verify the getHead, getTail, add, and get methods in TwitterFeed class
   * are working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testInsertTweet() {

    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();

    User u1 = new User("NCT");
    User u2 = new User("BlackPink");
    User u3 = new User("New Jeans");

    Tweet tweet1 = new Tweet(u1, "Graduation");
    Tweet tweet2 = new Tweet(u2, "Shut Down");
    Tweet tweet3 = new Tweet(u3, "Ditto");

    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);

    // CASE 1 : Verify the size
    int expected = 3;
    int actual = feed.size();
    if (expected != actual) {
      return false;
    }
    // CASE 2-1 : Verify get() with various indexes returns the Tweets you expect
    String expected2 = "tweet from @BlackPink at Tue May 22 14:46:03 CDT 2012:\n" + "-- Shut Down\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual2 = feed.get(1);
    if (!expected2.equals(actual2.toString())) {
      return false;
    }

    // CASE 2-2 : Verify get() with various indexes returns the Tweets you expect
    String expected3 = "tweet from @New Jeans at Tue May 22 14:46:03 CDT 2012:\n" + "-- Ditto\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual3 = feed.get(2);
    if (!expected3.equals(actual3.toString())) {
      return false;
    }

    // CASE 3: Check getHead() method
    String expected4 = "tweet from @NCT at Tue May 22 14:46:03 CDT 2012:\n" + "-- Graduation\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual4 = feed.getHead();
    if (!expected4.equals(actual4.toString())) {
      return false;
    }

    // CASE 4: Check getTail() method
    String expected5 = "tweet from @New Jeans at Tue May 22 14:46:03 CDT 2012:\n" + "-- Ditto\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual5 = feed.getTail();
    if (!expected5.equals(actual5.toString())) {
      return false;
    }

    return true;
  }

  /**
   * This is tester method to verify the getHead, getTail, delete, and get methods in TwitterFeed
   * class are working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testDeleteTweet() {

    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    // Create a new TwitterFeed and add 5 tweets
    TwitterFeed feed = new TwitterFeed();

    User u1 = new User("NCT");
    User u2 = new User("BlackPink");
    User u3 = new User("2PM");
    User u4 = new User("BTS");
    User u5 = new User("The Boyz");

    // The Boyz --> BTS --> NCT --> BlackPink --> 2PM
    feed.addLast(new Tweet(u1, "Jeno"));
    feed.addLast(new Tweet(u2, "Jennie"));
    feed.addLast(new Tweet(u3, "Junho"));
    feed.addFirst(new Tweet(u4, "Jin"));
    feed.addFirst(new Tweet(u5, "Juyeon"));

    // CASE 1: remove the last Tweet and verify that getTail() has been updated correctly
    feed.delete(feed.size() - 1);
    String expected = "tweet from @BlackPink at Tue May 22 14:46:03 CDT 2012:\n" + "-- Jennie\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual = feed.getTail();
    if (!expected.equals(actual.toString())) {
      return false;
    }

    // CASE 2: remove the first Tweet and verify that getHead() has been updated correctly
    feed.delete(0);
    String expected2 = "tweet from @BTS at Tue May 22 14:46:03 CDT 2012:\n" + "-- Jin\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual2 = feed.getHead();
    if (!expected2.equals(actual2.toString())) {
      return false;
    }

    // CASE 3: remove a Tweet from a middle index and verify that when you get() that index, it
    // returns the value you expect
    // BTS --> NCT --> BlackPink
    feed.delete(1);
    String expected3 = "tweet from @BlackPink at Tue May 22 14:46:03 CDT 2012:\n" + "-- Jennie\n"
        + "-- likes: 0\n" + "-- retweets: 0";
    Tweet actual3 = feed.get(1); // should be BlackPink
    if (!expected3.equals(actual3.toString())) {
      return false;
    }
    return true;
  }

  /**
   * This is tester method to verify the ChronoTwiterator class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testChronoTwiterator() {

    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    // Create a new TwitterFeed and add 3 tweets
    TwitterFeed feed = new TwitterFeed();

    User u1 = new User("user1");
    User u2 = new User("user2");
    User u3 = new User("user3");

    feed.addLast(new Tweet(u1, "Too long"));
    feed.addLast(new Tweet(u2, "Tester.."));
    feed.addLast(new Tweet(u3, "but still two more left.."));

    // Use enhanced-for loop to iterate through the feed using ChronoTwiterator
    feed.setMode(TimelineMode.CHRONOLOGICAL);
    Iterator<Tweet> chrono = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (!tweet.equals(chrono.next())) {
        System.out.println("Tweets not returned in correct order");
        return false;
      }
      i++;
    }
    // Check that the correct number of tweets were returned
    if (i != 3) {
      System.out.println("Incorrect number of tweets returned");
      return false;
    }

    return true;
  }

  /**
   * This is tester method to verify the VerifiedTwiterator class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testVerifiedTwiterator() {

    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    // Create a new TwitterFeed and add 3 tweets
    TwitterFeed feed = new TwitterFeed();

    User u1 = new User("user1");
    User u2 = new User("user2");
    User u3 = new User("user3");

    // verify two users
    u1.verify();
    u3.verify();

    feed.addLast(new Tweet(u1, "I'm hungry"));
    feed.addLast(new Tweet(u2, "I want to go home"));
    feed.addLast(new Tweet(u3, "please let me go home"));


    // Use enhanced-for loop to iterate through the feed using VerifiedTwiterator
    feed.setMode(TimelineMode.VERIFIED_ONLY);
    Iterator<Tweet> verified = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (!tweet.equals(verified.next())) {
        System.out.println("Tweets not returned in correct order");
        return false;
      }
      i++;
    }
    // Check that the correct number of tweets were returned
    if (i != 2) {
      System.out.println("Incorrect number of tweets returned");
      return false;
    }

    return true;
  }

  /**
   * This is tester method to verify the RatioTwiterator class is working properly
   * 
   * @return true if all test case passed, otherwise false
   */
  public static boolean testRatioTwiterator() {
    // Set up a calendar with the specified date
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(test);

    // Create a new TwitterFeed and add 3 tweets
    TwitterFeed feed = new TwitterFeed();

    User u1 = new User("user1");
    User u2 = new User("user2");
    User u3 = new User("user3");

    Tweet t1 = new Tweet(u1, "Nice weather");
    Tweet t2 = new Tweet(u2, "Today is so warm");
    Tweet t3 = new Tweet(u3, "I don't want to go library");

    t1.like();
    t1.like();
    t2.like();
    t1.retweet();
    t3.retweet();
    // t1 = 0.66, t2 = 1.0, t3 = 0.0

    feed.addLast(t1);
    feed.addLast(t2);
    feed.addLast(t3);


    // Use enhanced-for loop to iterate through the feed using VerifiedTwiterator
    // Use enhanced-for loop to iterate through the feed using VerifiedTwiterator
    feed.setMode(TimelineMode.LIKE_RATIO);
    Iterator<Tweet> ratio = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (!tweet.equals(ratio.next())) {
        System.out.println("Tweets not returned in correct order");
        return false;
      }
      i++;
    }
    // Check that the correct number of tweets were returned
    if (i != 2) { // u3(0.0) cannot returned
      System.out.println("Incorrect number of tweets returned");
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
    System.out.println("testUser: " + (testUser() ? "pass" : "X"));
    System.out.println("testTweet: " + (testTweet() ? "pass" : "X"));
    System.out.println("testNode: " + (testNode() ? "pass" : "X"));
    System.out.println("testAddTweet: " + (testAddTweet() ? "pass" : "X"));
    System.out.println("testInsertTweet: " + (testInsertTweet() ? "pass" : "X"));
    System.out.println("testDeleteTweet: " + (testDeleteTweet() ? "pass" : "X"));
    System.out.println("testChronoTwiterator: " + (testChronoTwiterator() ? "pass" : "X"));
    System.out.println("testVerifiedTwiterator: " + (testVerifiedTwiterator() ? "pass" : "X"));
    System.out.println("testRatioTwiterator: " + (testRatioTwiterator() ? "pass" : "X"));

  }
}
