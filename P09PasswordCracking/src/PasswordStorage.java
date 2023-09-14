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

import java.util.NoSuchElementException;

/**
 * This class implements the binary search tree, (NOT a balanced BST) the structure of the tree will
 * depend on the order in which passwords are added. It can add and remove the password and
 * determine the password is valid or empty. This class can search password in their tree.
 * 
 * @author seyeongoh
 *
 */
public class PasswordStorage {

  protected PasswordNode root; // the root of this BST that contains passwords
  private int size; // how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; // what password information to use to determine
                                               // order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * 
   * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.COMPARISON_CRITERIA = comparisonCriteria;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * 
   * @return the Attribute that is being used to make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return this.COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * 
   * @return the size of this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines whether or not this tree is empty.
   * 
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Provides in-order String representation of this BST, with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * 
   * @return this BST as a string
   */
  @Override
  public String toString() {
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to create the string representation of this
   * tree.
   * 
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
    if (currentNode == null) {
      return "";
    }
    String leftString = toStringHelper(currentNode.getLeft());
    String nodeString = currentNode.getPassword().toString() + "\n";
    String rightString = toStringHelper(currentNode.getRight());
    return leftString + nodeString + rightString;
  }

  /**
   * Determines whether or not this tree is actually a valid BST.
   * 
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
  }

  /**
   * Recurisvely determines if the the tree rooted at the current node is a valid BST. That is,
   * every value to the left of currentNode is "less than" the value in currentNode and every value
   * to the right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound,  the smallest possible password
   * @param upperBound,  the largest possible password
   * @return true if the tree rooted at currentNode is a BST, false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound,
      Password upperBound) {
    // Base case 1: the tree rooted at currentNode is empty, which does not violate any BST rules
    if (currentNode == null) {
      return true;
    }

    // Base case 2: the current Password is outside of the upper or lower bound for this subtree,
    // which is against the rules for a valid BST
    if (currentNode.getPassword().compareTo(lowerBound, COMPARISON_CRITERIA) < 0
        || currentNode.getPassword().compareTo(upperBound, COMPARISON_CRITERIA) > 0) {
      return false;
    }

    // Recursive case 1: check that the left subtree contains only Passwords greater than lowerBound
    // and less than currentNode's Password; return false if this property is NOT satisfied
    if (!isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword())) {
      return false;
    }

    // Recursive case 2: check that the right subtree contains only Passwords greater than
    // currentNode's Password and less than upperBound; return false if this property is NOT
    // satisfied
    if (!isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(), upperBound)) {
      return false;
    }

    // Combine recursive case answers: this is a valid BST if and only if both case 1 and 2 are
    // valid
    return true;

  }

  /**
   * Returns the password that matches the criteria of the provided key. Ex. if the COMPARISON
   * CRITERIA is OCCURRENCE and the key has an occurrence of 10 it will return the password stored
   * in the tree with occurrence of 10
   * 
   * @param key, the password that contains the information for the password we are searching for
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  }

  /**
   * Recursive helper method to find the matching password in this BST
   * 
   * @param key,         password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
    if (currentNode == null) {
      return null;
    }

    int comparedResult = key.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

    if (comparedResult < 0) {
      return lookupHelper(key, currentNode.getLeft());
    } else if (comparedResult > 0) {
      return lookupHelper(key, currentNode.getRight());
    } else {
      return currentNode.getPassword();
    }
    // else if (currentNode.getPassword().compareTo(key, COMPARISON_CRITERIA) < 0) {
    // return lookupHelper(key, currentNode.getLeft());
    // } else if (currentNode.getPassword().compareTo(key, COMPARISON_CRITERIA) > 0) {
    // return lookupHelper(key, currentNode.getRight());
    // } else {
    // return currentNode.getPassword();
    // }
  }

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() {
    if (root == null) {
      throw new NoSuchElementException("Tree is empty");
    }
    PasswordNode current = root;
    while (current.getRight() != null) {
      current = current.getRight();
    }
    return current.getPassword();
  }

  /**
   * Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() {
    if (root == null) {
      throw new NoSuchElementException("Tree is empty");
    }
    PasswordNode current = root;
    while (current.getLeft() != null) {
      current = current.getLeft();
    }
    return current.getPassword();

  }

  /**
   * Adds the Password to this BST.
   * 
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  public void addPassword(Password toAdd) throws IllegalArgumentException {
    if (lookupHelper(toAdd, root) != null) {
      throw new IllegalArgumentException("Password already exists in tree");
    }
    if (root == null) {
      root = new PasswordNode(toAdd);
      size++;
    } else {
      addPasswordHelper(toAdd, root);
      size++;
    }
  }

  /**
   * Recursive helper that traverses the tree and adds the password where it belongs
   * 
   * @param toAdd,       the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {

    int comparisonResult = toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

    if (comparisonResult == 0) {
      return false;
    }

    else if (comparisonResult < 0) {
      if (currentNode.getLeft() == null) {
        currentNode.setLeft(new PasswordNode(toAdd));
        return true;
      } else {
        return addPasswordHelper(toAdd, currentNode.getLeft());
      }
    }

    else {
      if (currentNode.getRight() == null) {
        currentNode.setRight(new PasswordNode(toAdd));
        return true;
      } else {
        return addPasswordHelper(toAdd, currentNode.getRight());
      }
    }
  }


  /**
   * Removes the matching password from the tree
   * 
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
    if (this.root == null) {
      throw new NoSuchElementException();
    }
    this.root = removePasswordHelper(toRemove, root);
    if (this.root != null) {
      this.size--;
    }
  }

  /**
   * Recursive helper method to that removes the password from this BST.
   * 
   * @param toRemove,    the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of this subtree now that toRemove has been
   *         removed. This may still be currentNode, or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
    if (currentNode == null) {
      throw new NoSuchElementException();
    }

    int compareResult = toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);

    if (compareResult < 0) {
      currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
    } else if (compareResult > 0) {
      currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
    } else {
      // CASE 1: Node to be deleted has no children
      if (currentNode.getLeft() == null && currentNode.getRight() == null) {
        return null;
      }
      // CASE 2a: Node to be deleted has only left child
      else if (currentNode.getRight() == null) {
        return currentNode.getLeft();
      }
      // CASE 2b: Node to be deleted has only right child
      else if (currentNode.getLeft() == null) {
        return currentNode.getRight();
      }
      // CASE 3: Node to be deleted has two children
      else {
        PasswordNode predecessorNode = findPredecessor(currentNode);
        PasswordNode newPredecessorNode = new PasswordNode(predecessorNode.getPassword());
        newPredecessorNode.setLeft(currentNode.getLeft());
        newPredecessorNode.setRight(currentNode.getRight());
        currentNode = newPredecessorNode;
        currentNode
            .setLeft(removePasswordHelper(predecessorNode.getPassword(), currentNode.getLeft()));
      }
    }

    return currentNode;
  }

  /**
   * Helper method that finds the predecessor node of the given node.
   * 
   * @param node, the node whose predecessor we want to find
   * @return the predecessor node of the given node
   */
  private PasswordNode findPredecessor(PasswordNode node) {
    PasswordNode current = node.getLeft();
    while (current.getRight() != null) {
      current = current.getRight();
    }
    return current;
  }
}
