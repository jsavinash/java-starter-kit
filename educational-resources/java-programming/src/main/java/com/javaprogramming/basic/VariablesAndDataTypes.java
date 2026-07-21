package com.javaprogramming.basic;

public class VariablesAndDataTypes {
  public static void main(String[] args) {
    // Primitives
    int age = 25;
    double price = 19.99;
    boolean isActive = true;
    char grade = 'A';

    // Object types
    String name = "Alice";
    final double PI = 3.14159;

    // Type conversion
    String numStr = "42";
    int parsed = Integer.parseInt(numStr);
    String backToString = String.valueOf(parsed);

    // Type checking
    System.out.println(name instanceof String); // true
    System.out.println(name.getClass().getName()); // java.lang.String

    // Output
    System.out.printf("Name: %s, Age: %d, Grade: %c%n", name, age, grade);
    System.out.println("Parsed: " + parsed + ", Back: " + backToString);
    System.out.println("PI: " + PI);
  }
}
