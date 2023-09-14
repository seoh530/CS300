///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Seyeong Oh
// Campus ID: 9084744136
// WiscEmail: soh87@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects
// is optional.
////////////////////////////////////////////////////////////////////////////////

/**
 * This class models the Grade data type. It contains information about points earned and points
 * possible on an assignment, as well as a name for the assignment.
 * 
 * NOTES: You may NOT add any additional data fields to this class unless specified in the TODO
 * tags. You may NOT add ANY additional methods to this class, regardless of access modifier. There
 * is no tester or main method for this class.
 */
public class Grade {
  // TODO
  // 1. Declare a private instance field of type double named pointsEarned
  private double pointsEarned;

  // 2. Declare a private instance field of type String named assignmentName
  private String assignmentName;

  // 3. Declare a protected instance field of type int named pointsPossible
  protected int pointsPossible;

  // 4. Declare a private static data field of type int named totalNumGrades, initialized to zero
  // totalNumGrades represents the total number of Grade objects created by the constructor
  private static int totalNumGrades;

  /**
   * Creates a new Grade with the given number of points earned/possible.
   * 
   * @param pointsEarned   the number of points earned on the assignment
   * @param pointsPossible the number of points possible for the assignment
   * @param assignmentName the name of the assignment
   * @throws IllegalStateException if pointsPossible is non-positive (<=0), or if pointsEarned >
   *                               pointsPossible, or if the assignmentName is null or blank
   */
  public Grade(double pointsEarned, int pointsPossible, String assignmentName)
      throws IllegalStateException {
    // TODO
    // 5. Check the validity of the input parameters and handle appropriately
    if (pointsPossible <= 0 || pointsEarned > pointsPossible) {
      throw new IllegalStateException("Error, you need to put valid pointsPossible number");
    }
    if (assignmentName == null || assignmentName.isBlank()) {
      throw new IllegalStateException("Error, the assignment is missing");
    }

    // 6. Set the instance data fields to the provided input parameters
    this.pointsEarned = pointsEarned;
    this.pointsPossible = pointsPossible;
    this.assignmentName = assignmentName;

    // 7. Increment the total number of created Grade objects
    totalNumGrades++;

  }

  // Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)

  /**
   * Changes this Grade's pointsEarned field to the provided value.
   * 
   * @param newEarnedPoints the value to change the pointsEarned field to
   */
  public void setEarnedPoints(double newEarnedPoints) {
    // TODO
    // 8. Complete the implementation of this mutator method
    this.pointsEarned = newEarnedPoints;
  }

  /**
   * Gets the number of grades recorded
   * 
   * @return the total number of Grade objects created
   */
  public static int getNumGrades() {
    // TODO
    // 9. Complete the implementation of this accessor method
    return totalNumGrades;
  }

  /**
   * Gets the number of points earned
   *
   * @ return the number of points earned on the assignment
   */
  public double getEarnedPoints() {
    // TODO
    // 10. Complete the implementation of this accessor method
    return pointsEarned;
  }

  /**
   * Returns a string representation of this Grade. The returned string must have the following
   * format: assignmentName + ": " + pointsEarned + "/" + pointsPossible For example, "p04: 37.5/50"
   * 
   * @return a string formatted as assignmentName + ": " + pointsEarned + "/" + pointsPossible
   */
  @Override
  public String toString() {
    // TODO
    // 11. Complete the implementation of this method
    return this.assignmentName + ": " + this.pointsEarned + "/" + this.pointsPossible;
  }

}

// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S)


/**
 * This class models the ProgramGrade data type, representing a grade for a programming assignment.
 * As you know, programming assignment points earned have two components: the pre-quiz score and the
 * gradescope score.
 * 
 * NOTES: You may NOT add any additional data fields to this class. You may NOT add ANY additional
 * methods to this class, regardless of access modifier. There is no tester or main method for this
 * class.
 */
class ProgramGrade extends Grade { // TODO: 12. Set this class to be a subclass of the Grade class

  private double gradescopeScore; // the component of earned points from gradescope
  private double preQuizScore; // the component of earned points from the pre-assignment quiz

  /**
   * Creates a new ProgramGrade with the provided values
   * 
   * @param gradescopeScore the component of earned points from gradescope
   * @param preQuizScore    the component of earned points from the pre-assignment quiz
   * @param pointsPossible  the total number of points possible
   * @param assignmentName  the name of the assignment
   * @throws IllegalStateException    if pointsPossible is non-positive (<=0), or if pointsEarned >
   *                                  pointsPossible
   * @throws IllegalArgumentException if either gradescope or pre-quiz score is negative
   */
  public ProgramGrade(double gradescopeScore, double preQuizScore, int pointsPossible,
      String assignmentName) throws IllegalStateException, IllegalArgumentException {
    // TODO
    // 13. Call the constructor of the super class with required arguments. Note that the total
    // number of earned points is the score from gradescope + the pre-quiz score.
    // HINT: Do not catch the IllegalStateException that it may throw. Let the exception propogate.
    // public Grade(double pointsEarned, int pointsPossible, String assignmentName)
    // pointsEarned = gradescopeScore + preQuizScore
    super(gradescopeScore + preQuizScore, pointsPossible, assignmentName);

    // 14. Check the validity of the gradescope and pre-quiz parameters and handle invalid input
    // accordingly
    if (gradescopeScore + preQuizScore > pointsPossible || pointsPossible <= 0) {
      throw new IllegalStateException();
    }
    if (gradescopeScore < 0 || preQuizScore < 0) {
      throw new IllegalArgumentException();
    }

    // 15. Set the instance fields to the values passed as input
    this.gradescopeScore = gradescopeScore;
    this.preQuizScore = preQuizScore;
  }

  /**
   * Changes the gradescopeScore and pointsEarned fields according to the provided value, unless the
   * provided value is negative (<0)
   * 
   * @param newGradescope the new value for gradescopeScore
   */
  public void regrade(double newGradescope) {
    // TODO
    // 16. Complete the implementation of this mutator method, including calling setEarnedPoints()
    // from the super class with the correct pointsEarned (the new gradescope score plus the old
    // pre-
    // quiz score).
    if (newGradescope >= 0) {
      super.setEarnedPoints(gradescopeScore + preQuizScore);
      this.gradescopeScore = newGradescope;
    }

    // Note: if the provided newGradescope score is negative, do nothing
  }

  /**
   * Gets the ProgramGrade's total score as a percent of possible points expressed as a double, that
   * is: 0.0-1.0
   * 
   * @return the ProgramGrade as a percent of possible points
   */
  public double getPct() {
    // TODO
    // 17. Complete the implementation of this accessor method
    double pctScore = (gradescopeScore + preQuizScore) / pointsPossible;
    return pctScore;
  }

  /**
   * Returns a string representation of this ProgramGrade. The returned string must have the
   * following format: assignmentName + ": " + pointsEarned + "/" + pointsPossible + " (GS:" +
   * gradescopeScore + ")" For example, "p04: 37.5/50 (GS:33.25)"
   * 
   * @return a string formatted as name + ": " + earned + "/" + possible + " (GS:" + gsScore + ")"
   */
  @Override
  public String toString() {
    // TODO
    // 18. Complete the implementation of this method.
    // Note: the name of the assignment is a private data field with no accessor defined in the
    // super class.
    // Use the toString() method from the Grade class to start your implementation.

    return super.toString()+ " (GS:" + gradescopeScore + ")";
  }

  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope

}
