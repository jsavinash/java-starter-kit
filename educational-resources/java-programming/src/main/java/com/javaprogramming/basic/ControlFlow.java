package com.javaprogramming.basic;

public class ControlFlow {
  public static void main(String[] args) {
    int score = 85;
    if (score >= 90) {
      System.out.println("Grade: A");
    } else if (score >= 80) {
      System.out.println("Grade: B");
    } else if (score >= 70) {
      System.out.println("Grade: C");
    } else {
      System.out.println("Grade: F");
    }

    // Switch (traditional)
    int day = 3;
    switch (day) {
      case 1:
        System.out.println("Monday");

        break;
      case 2:
        System.out.println("Tuesday");

        break;
      case 3:
        System.out.println("Wednesday");

        break;
      case 4:
        System.out.println("Thursday");

        break;
      case 5:
        System.out.println("Friday");

        break;
      case 6:
        System.out.println("Saturday");

        break;
      case 7:
        System.out.println("Sunday");

        break;
      default:
        System.out.println("Invalid day");
    }

    // Switch (enhanced - Java 14+)
    String dayType =
        switch (day) {
          case 1, 2, 3, 4, 5 -> "Weekday";
          case 6, 7 -> "Weekend";
          default -> "Invalid";
        };
    System.out.println("Day type: " + dayType);

    // Ternary
    String status = (score >= 60) ? "Pass" : "Fail";
    System.out.println("Status: " + status);
  }
}
