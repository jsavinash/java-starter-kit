package com.starter.packages.concepts.basic.control_statements;

/**
 * The {@code ControlStatements} class provides basic Control Statements operations such as
 * decision-making, fixed loop, while loop, switch case, break and continue.
 *
 * <p>This class is a example class and does not maintain a running state. All methods are static
 * and take two double parameters.
 *
 * <p>Example usage:
 *
 * <pre>
 * boolean boolOutput = ControlStatements.decisionMaking(18);
 * String strOutput = Calculator.fixedLoop();
 * String strOutput = Calculator.whileLoop();
 * String strOutput = Calculator.switchCase("A");
 * String strOutput = Calculator.breakContinue();
 * </pre>
 *
 * @author Avinash Nishad
 * @version 1.0
 */
public class ControlStatements implements IControlStatements {
  /**
   * Validate age.
   *
   * @param age The user age (int).
   * @return True or false.
   */
  public boolean decisionMaking(int age) {
    System.out.println("***************** Decision making *****************");
    return age >= 18;
  }

  /**
   * Fixed loop with number as string.
   *
   * @return Return string output.
   */
  public String fixedLoop() {
    System.out.println("***************** Fixed Loop *****************");
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      sb.append(i);
    }
    return sb.toString();
  }

  /**
   * While loop with number as string.
   *
   * @return Return string output.
   */
  public String whileLoop() {
    System.out.println("***************** While Loop *****************");
    StringBuilder sb = new StringBuilder();

    int i = 0;
    while (i < 5) {
      sb.append(i);
      i++;
    }
    return sb.toString();
  }

  /**
   * Switch case with number as string.
   *
   * @param str The selected case (String).
   * @return Return string output.
   */
  public String switchCase(String str) {
    System.out.println("***************** Switch case *****************");
    switch (str) {
      case "A":
        System.out.println("A");
        return "A";
      default:
        System.out.println("Default case executed");
        return "Default";
    }
  }

  /**
   * Brake and continue with number as string.
   *
   * @return Return string output.
   */
  public String breakContinue() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      if (i == 2) {
        continue;
      }
      if (i == 4) {
        break;
      }
      System.out.println(i);
      sb.append(i);
    }
    return sb.toString();
  }
}
