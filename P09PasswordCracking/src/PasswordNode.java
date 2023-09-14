//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Password Cracking
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
 * Class to represent a binary search tree (BST) node that contains only Password objects.
 * 
 * @author Michelle & Seyeong Oh
 */
public class PasswordNode {

  private Password password; // the password data this node stores
  private PasswordNode left; // a reference to node that is the left child
  private PasswordNode right; // a reference to the node that is the right child

  /**
   * 1-argument constructor that sets the only data of the node.
   * 
   * @param password the password for this node to store
   * 
   * @author Michelle
   */
  public PasswordNode(Password password) {
    this.password = password;
  }

  /**
   * 3-argument constructor that sets all three data field
   * 
   * @param password, password the password for this node to store
   * @param left,     the reference to the node that is the left child
   * @param right,    the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode(Password password, PasswordNode left, PasswordNode right) {
    this(password);
    this.left = left;
    this.right = right;
  }

  /**
   * Setter for left data field
   * 
   * @param left, the reference to the node to be the left child
   * 
   * @author Michelle
   */
  public void setLeft(PasswordNode left) {
    this.left = left;
  }

  /**
   * Setter for right data field
   * 
   * @param right, the reference to the node to be the right child
   * 
   * @author Michelle
   */
  public void setRight(PasswordNode right) {
    this.right = right;
  }

  /**
   * Getter for left data field
   * 
   * @return the reference to the node that is the left child
   * 
   * @author Michelle
   */
  public PasswordNode getLeft() {
    return this.left;
  }

  /**
   * Getter for right data field
   * 
   * @return the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode getRight() {
    return this.right;
  }

  /**
   * Getter for password data field
   * 
   * @return the password object that this node stores
   * 
   * @author Michelle
   */
  public Password getPassword() {
    return this.password;
  }

  /**
   * Determines if the current node is a leaf node
   * 
   * @return true if this node is a leaf, false otherwise
   * 
   * @author seyeong oh
   */
  public boolean isLeafNode() {
    return (this.left == null && this.right == null);
  }

  /**
   * Determines if the current node has a right child
   * 
   * @return true if this node has a right child, false otherwise
   * 
   * @author seyeong oh
   */
  public boolean hasRightChild() {
    return (this.right != null);
  }

  /**
   * Determines if the current node has a left child
   * 
   * @return true if this node has a left child, false otherwise
   * 
   * @author seyeong oh
   */
  public boolean hasLeftChild() {
    return (this.left != null);
  }

  /**
   * Determines how many children nodes this node has. RECALL: Nodes in a binary tree can have AT
   * MOST 2 children
   * 
   * @return The number of children this node has
   * 
   * @author seyeong oh
   */
  public int numberOfChildren() {
    if (this.left != null && this.right != null) {
      return 2;
    } else if (this.left != null || this.right != null) {
      return 1;
    } else {
      return 0;
    }
  }

}
