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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VerifiedTwiterator implements Iterator<Tweet> {

  private TweetNode next;

  /**
   * Constructs a new twiterator at the given starting node
   * 
   * @param startNode : the node to begin the iteration at
   */
  public VerifiedTwiterator(TweetNode startNode) {
 // Find the first tweet by a verified user
    this.next = startNode;
    while (this.next != null && !this.next.getTweet().isUserVerified()) {
        this.next = this.next.getNext();
    }
  }

  /**
   * Checks whether there is a next tweet to return
   * 
   * @return true if there is a next tweet, false if the value of next is null
   */
  @Override
  public boolean hasNext() {
    return this.next != null;
  }

  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet by a
   * verified user
   * 
   * @return Returns the next tweet in the iteration if one exists
   * @throws NoSuchElementException : if there is not a next tweet to return
   */
  @Override
  public Tweet next() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    TweetNode current = this.next;
    // Find the next tweet by a verified user
    this.next = this.next.getNext();
    while (this.next != null && !this.next.getTweet().isUserVerified()) {
      this.next = this.next.getNext();
    }
    return current.getTweet();
  }


}
