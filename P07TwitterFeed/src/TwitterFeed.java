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

/**
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list
 * 
 * @author seyeongoh
 *
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {

  private TweetNode head;
  private TweetNode tail;
  private int size;
  private TimelineMode mode;
  private static double ratio = .5;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default
   * values, and the timeline mode to CHRONOLOGICAL
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
  }

  /**
   * Accessor for the size of the feed
   * 
   * @return the number of TweetNodes in this TwitterFeed
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Determines whether this feed is empty. Checking MORE than just the size field to get this
   * information, though if all methods are implemented correctly the size field's value will be
   * sufficient
   * 
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0 && head == null && tail == null)
      return true;
    return false;
  }

  /**
   * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's equals() method
   * 
   * @param findObject : the Tweet to search for
   * @return true if the Tweet is present, false otherwise
   */
  @Override
  public boolean contains(Tweet findObject) {
    TweetNode current = head;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   * 
   * @param findObject the Tweet to search for
   * @return the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  @Override
  public int indexOf(Tweet findObject) {
    int index = -1;
    for (int i = 0; i < size(); i++) {
      if (get(i).equals(findObject)) {
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Accessor method for the Tweet at a given index
   * 
   * @param index : the index of the Tweet in question
   * @return the Tweet object at that index (NOT its TweetNode!)
   * @throws IndexOutOfBoundsException : if the index is negative or greater than the largest index
   *                                   of the TwitterFeed
   */
  @Override
  public Tweet get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size() - 1) {
      throw new IndexOutOfBoundsException("Invalid index");
    }
    TweetNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getTweet();
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   * 
   * @return the Tweet object at the head of the linked list
   * @throws NoSuchElementException : if the TwitterFeed is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   * 
   * @return the Tweet object at the tail of the linked list
   * @throws NoSuchElementException : if the TwitterFeed is empty
   */
  public Tweet getTail() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return tail.getTweet();
  }

  /**
   * Adds the given Tweet to the tail of the linked list
   * 
   * @param newObject : the Tweet to add
   */
  public void addLast(Tweet newObject) {
    if (newObject == null) {
      throw new NullPointerException();
    }
    TweetNode newNode = new TweetNode(newObject);
    if (size == 0) {
      head = tail = newNode;
    } else {
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  /**
   * Adds the given Tweet to the head of the linked list
   * 
   * @param newObject : the Tweet to add
   */
  public void addFirst(Tweet newObject) {
    if (newObject == null) {
      throw new NullPointerException();
    }
    TweetNode newNode = new TweetNode(newObject);
    if (size == 0) {
      head = tail = newNode;
    } else {
      newNode.setNext(head);
      head = newNode;
    }
    size++;
  }

  /**
   * Adds the given Tweet to a specified position in the linked list
   * 
   * @param index     : the position at which to add the new Tweet
   * @param newObject : the Tweet to add
   * @throws IndexOutOfBoundsException : if the index is negative or greater than the size of the
   *                                   linked list
   */
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (newObject == null) {
      throw new NullPointerException();
    }
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    // Add on index 0 : addFirst method
    if (index == 0) {
      addFirst(newObject);
      return;
    }

    // Add on last index : addLast method
    if (index == size()) {
      addLast(newObject);
      return;
    }

    // Add on middle index
    TweetNode current = head;
    for (int i = 0; i < index - 1; i++) {
      current = current.getNext();
    }
    TweetNode newNode = new TweetNode(newObject, current.getNext());
    current.setNext(newNode);
    if (newNode.getNext() == null) {
      tail = newNode;
    }
    size++;
  }

  /**
   * Removes and returns the Tweet at the given index
   * 
   * @param index : the position of the Tweet to remove
   * @return the Tweet object that was removed from the list
   * @throws IndexOutOfBoundsException : if the index is negative or greater than the largest index
   *                                   currently present in the linked list
   */
  public Tweet delete(int index) throws IndexOutOfBoundsException {

    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Invalid index");
    }

    TweetNode current = head;
    TweetNode previous = null;

    for (int i = 0; i < index; i++) {
      previous = current;
      current = current.getNext();
    }

    Tweet tweet = current.getTweet();

    if (size() == 1) { // deleting the last tweet
      head = tail = null;
    } else if (current == tail) {
      tail = previous;
      tail.setNext(null);
    } else if (previous == null) {
      head = current.getNext();
    } else {
      previous.setNext(current.getNext());
    }
    size--;

    return tweet;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   * 
   * @param m : the iteration mode; any value from the TimelineMode enum
   */
  public void setMode(TimelineMode m) {
    this.mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   * 
   * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the
   *         head of this TwitterFeed list
   */
  @Override
  public Iterator<Tweet> iterator() {

    Iterator<Tweet> iterator;

    switch (mode) {
      case CHRONOLOGICAL:
        iterator = new ChronoTwiterator(head);
        break;
      case VERIFIED_ONLY:
        iterator = new VerifiedTwiterator(head);
        break;
      case LIKE_RATIO:
        iterator = new RatioTwiterator(head,ratio);
        break;
      default:
        throw new IllegalStateException("Invalid mode");
    }
    return iterator;
  }

}
