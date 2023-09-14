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
 * This is a tester method for testing Password, PasswordNode, and PasswordStorage classes are
 * working properly
 * 
 * @author seyeongoh
 *
 */
public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the protected data
   * field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * 
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {
    // Create a new PasswordStorage object with the OCCURRENCE attribute
    PasswordStorage storage1 = new PasswordStorage(Attribute.OCCURRENCE);
    if (storage1.getComparisonCriteria() != Attribute.OCCURRENCE) {
      System.out.println("getcomparisoncriteria is NOT OCCURRENCE");
      return false;
    }
    if (storage1.size() != 0) {
      System.out.println("storage size is NOT 0");
      return false;
    }
    if (!storage1.isEmpty()) {
      System.out.println("storage is empty");
      return false;
    }

    // Add Password objects to the storage and test the size() and isEmpty() methods
    Password p1 = new Password("password1", 1);
    Password p2 = new Password("password2", 2);

    storage1.addPassword(p1);
    if (storage1.size() != 1) {
      System.out.println("storage size is NOT 1");
      return false;
    }
    if (storage1.isEmpty()) {
      System.out.println("storage is empty");
      return false;
    }
    storage1.addPassword(p2);
    if (storage1.size() != 2) {
      System.out.println("storage size is NOT 2");
      return false;
    }

    // Create another PasswordStorage object with the STRENGTH_RATING attribute
    PasswordStorage storage2 = new PasswordStorage(Attribute.STRENGTH_RATING);
    if (storage2.getComparisonCriteria() != Attribute.STRENGTH_RATING) {
      System.out.println("getComparisonCriteria is NOT STRENGTH_RATING");
      return false;
    }
    if (storage2.size() != 0) {
      System.out.println("storage is NOT 0");
      return false;
    }
    if (!storage2.isEmpty()) {
      System.out.println("storage is empty");
      return false;
    }

    return true;
  }

  /**
   * Validates the Password class compareTo() method. Create at least two DIFFERENT Password objects
   * and compare them on each of the Attribute values. See the writeup for details on how the
   * various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, false otherwise
   */
  public static boolean testPasswordCompareTo() {
    Password p1 = new Password("password1", 1);
    Password p2 = new Password("password2", 2);

    // compare on occurrences: occurrence of p1 = 1, p2 = 2
    if (p1.compareTo(p2, Attribute.OCCURRENCE) != -1
        || p2.compareTo(p1, Attribute.OCCURRENCE) != 1) {
      System.out.println("Fail to compare on occurrences");
      return false;
    }

    // compare on strengthRating: strengthRating of p1 and p2 = 9.75
    p1 = new Password("password", 1);
    p2 = new Password("password", 2);

    if (p1.compareTo(p2, Attribute.STRENGTH_RATING) != 0
        || p2.compareTo(p1, Attribute.STRENGTH_RATING) != 0) {
      System.out.println("Fail to compare on strengthRating");
      return false;
    }

    // compare on hashedPassword
    p1 = new Password("password", 1);
    p2 = new Password("passwore", 1);
    // System.out.println(p1.compareTo(p2, Attribute.HASHED_PASSWORD));
    // System.out.println(p2.compareTo(p1, Attribute.HASHED_PASSWORD));
    if (p1.compareTo(p2, Attribute.HASHED_PASSWORD) != -46
        || p2.compareTo(p1, Attribute.HASHED_PASSWORD) != 46) {
      System.out.println("Fail to compare on hashedPassword");
      return false;
    }
    return true;
  }

  /**
   * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all possible
   * configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
    Password p1 = new Password("password1", 5);
    Password p2 = new Password("password2", 5);
    Password p3 = new Password("password3", 5);

    // Test leaf node
    PasswordNode leafNode = new PasswordNode(p1);
    if (leafNode.isLeafNode() != true) {
      System.out.println("isLeafNode is false");
      return false;
    }
    if (leafNode.numberOfChildren() != 0) {
      System.out.println("number of children should be zero");
      return false;
    }
    if (leafNode.hasLeftChild() && leafNode.hasRightChild()) {
      System.out.println("There are child leaves");
      return false;
    }

    // Test node with left child only
    PasswordNode nodeWithLeftChild = new PasswordNode(p2, leafNode, null);

    if (nodeWithLeftChild.isLeafNode() != false) {
      System.out.println("isLeafNode is false");
      return false;
    }
    if (nodeWithLeftChild.numberOfChildren() != 1) {
      System.out.println("number of children should be one");
      return false;
    }
    if (!nodeWithLeftChild.hasLeftChild()) {
      System.out.println("No left child detected");
      return false;
    }
    if (nodeWithLeftChild.hasRightChild()) {
      System.out.println("Right child detected");
      return false;
    }


    // Test node with right child only
    PasswordNode nodeWithRightChild = new PasswordNode(p3, null, leafNode);

    if (nodeWithRightChild.isLeafNode() != false) {
      System.out.println("isLeafNode is false");
      return false;
    }
    if (nodeWithRightChild.numberOfChildren() != 1) {
      System.out.println("number of children should be one");
      return false;
    }
    if (nodeWithRightChild.hasLeftChild()) {
      System.out.println("left child detected");
      return false;
    }
    if (!nodeWithRightChild.hasRightChild()) {
      System.out.println("No right child detected");
      return false;
    }


    // Test node with both child
    PasswordNode nodeWithBothChild = new PasswordNode(p3, leafNode, leafNode);

    if (nodeWithBothChild.isLeafNode() != false) {
      System.out.println("isLeafNode is false");
      return false;
    }
    if (nodeWithBothChild.numberOfChildren() != 2) {
      System.out.println("number of children should be one");
      return false;
    }
    if (!nodeWithBothChild.hasLeftChild()) {
      System.out.println("No left child detected");
      return false;
    }
    if (!nodeWithBothChild.hasRightChild()) {
      System.out.println("No right child detected");
      return false;
    }


    return true;
  }

  // GIVE TO STUDENTS
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
          + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();

      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  // GIVE TO STUDENTS
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep
      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
            .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }

      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }


    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Test the lookup method in PasswordStorage class is working well in three different cases.
   * 
   * @return true if all cases passed, false otherwise
   */
  public static boolean testLookup() {
    try {

      PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

      Password p1 = new Password("password1", 1000);
      Password p2 = new Password("password2", 500);
      Password p3 = new Password("password3", 765);
      Password p4 = new Password("password4", 250);
      Password p5 = new Password("password5", 1002);
      Password p6 = new Password("password6", 2323);

      // p1
      // / \
      // p3 p5
      // / / \
      // p2 p4 p6

      PasswordNode n2 = new PasswordNode(p2);
      PasswordNode n4 = new PasswordNode(p4);
      PasswordNode n6 = new PasswordNode(p6);
      PasswordNode n3 = new PasswordNode(p3, n2, null);
      PasswordNode n5 = new PasswordNode(p5, n4, n6);
      PasswordNode root = new PasswordNode(p1, n3, n5);
      storage.root = root;

      if (!storage.lookup(p2).equals(p2)) {
        System.out.println("Cannot find p2 in lookup method");
        return false;
      }
      if (!storage.lookup(p5).equals(p5)) {
        System.out.println("Cannot find p5 in lookup method");
        return false;
      }
      if (!storage.lookup(p6).equals(p6)) {
        System.out.println("Cannot find p6 in lookup method");
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Test the addPassword method in PasswordStorage class is working well by adding different
   * passwords several times
   * 
   * @return true if all cases passed, false otherwise
   */
  public static boolean testAddPassword() {

    PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

    Password p1 = new Password("password1", 1000);
    Password p2 = new Password("password2", 500);
    Password p3 = new Password("password3", 765);
    Password p4 = new Password("password4", 250);
    Password p5 = new Password("password5", 1002);
    Password p6 = new Password("password6", 2323);

    // CASE 1: add random password 3 times
    storage.addPassword(p6);
    if (!storage.isValidBST() || storage.lookup(p6) == null || storage.size() != 1) {
      System.out.println("no p6 added in the tree");
    }

    storage.addPassword(p4);
    if (!storage.isValidBST() || storage.lookup(p4) == null || storage.size() != 2) {
      System.out.println("no p4 added in the tree");
    }

    storage.addPassword(p2);
    if (!storage.isValidBST() || storage.lookup(p2) == null || storage.size() != 3) {
      System.out.println("no p2 added in the tree");
    }

    storage.addPassword(p1);
    if (!storage.isValidBST() || storage.lookup(p1) == null || storage.size() != 4) {
      System.out.println("no p1 added in the tree");
    }

    storage.addPassword(p3);
    if (!storage.isValidBST() || storage.lookup(p3) == null || storage.size() != 5) {
      System.out.println("no p3 added in the tree");
    }

    storage.addPassword(p5);
    if (!storage.isValidBST() || storage.lookup(p5) == null || storage.size() != 6) {
      System.out.println("no p5 added in the tree");
    }

    // CASE 2: add already existed password
    try {
      storage.addPassword(p2);
      return false;
    } catch (IllegalArgumentException e) {
      // expected output
    }

    // CASE 3: create new password and overwrite it
    try {
      p4 = new Password("newpassword4", 333);
      storage.addPassword(p4);
      if (!storage.isValidBST() || storage.lookup(p4) == null || storage.size() != 7) {
        System.out.println("no p4 added in the tree");
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Test the removePassword method in PasswordStorage class is working well by different cases
   * 
   * @return true if all cases passed, false otherwise
   */
  public static boolean testRemovePassword() {

    PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

    Password p1 = new Password("password1", 10);
    Password p2 = new Password("password2", 50);
    Password p3 = new Password("password3", 5);
    Password p4 = new Password("password4", 2);
    Password p5 = new Password("password5", 12);
    Password notExist = new Password("password5", 12);

    storage.addPassword(p1);
    storage.addPassword(p2);
    storage.addPassword(p3);
    storage.addPassword(p4);
    storage.addPassword(p5);

    try {
      // CASE 1: remove password that already in the tree
      // remove password 1
      storage.removePassword(p1);
      if (!storage.isValidBST()) {
        System.out.println("Not valid BST");
        return false;
      }
      if (storage.size() != 4) {
        System.out.println("size is NOT 4");
        return false;
      }
      if (storage.lookup(p1) != null) {
        System.out.println("p1 is not successfully removed");
        return false;
      }

      // remove password 3
      storage.removePassword(p3);
      if (!storage.isValidBST()) {
        System.out.println("Not valid BST");
        return false;
      }
      if (storage.size() != 3) {
        System.out.println("size is NOT 3");
        return false;
      }
      if (storage.lookup(p3) != null) {
        System.out.println("p3 is not successfully removed");
        return false;
      }

      // remove password 5
      storage.removePassword(p5);
      if (!storage.isValidBST()) {
        System.out.println("Not valid BST");
        return false;
      }
      if (storage.size() != 2) {
        System.out.println("size is NOT 2");
        return false;
      }
      if (storage.lookup(p5) != null) {
        System.out.println("p5 is not successfully removed");
        return false;
      }

      // CASE 2: add and remove password
      Password p6 = new Password("additional", 45);
      storage.addPassword(p6);
      storage.removePassword(p2);

      if (!storage.isValidBST()) {
        System.out.println("Not valid BST");
        return false;
      }
      if (storage.size() != 2) {
        System.out.println("size is NOT 2");
        return false;
      }
      if (storage.lookup(p2) != null) {
        System.out.println("p2 is not successfully removed");
        return false;
      }

    } catch (Exception e) {
      return false;
    }

    // CASE 3: try to remove notExist password
    try {
      storage.removePassword(notExist);
      return false;
    } catch (NoSuchElementException e) {
      // expected output
    }

    return true;
  }

  public static void main(String[] args) {
    runAllTests();
  }

  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out
        .println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
