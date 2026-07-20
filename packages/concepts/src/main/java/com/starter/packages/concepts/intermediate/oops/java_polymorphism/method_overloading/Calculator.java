package com.starter.packages.concepts.intermediate.oops.java_polymorphism.method_overloading;

/*Is an example of compile time polymorphism.
 *Static (Compile-time) Binding:
 * The method to be called is determined at compile time.
 * It happens with private, final, and static methods and method overloading.
 * Static binding is also known as Early binding.
 *By Changing the Number of Arguments
 *By Changing the Data Type
 *Note: In Java, method overloading is not possible by changing the return type of the method only.
 */
public class Calculator {
  public int add(int a, int b) {
    return a + b;
  }

  public double add(double a, double b, double c) {
    return a + b + c;
  }

  public int multiply(int a, int b) {
    return a * b;
  }

  // Method to add two doubles
  public double multiply(double a, double b) {
    return a * b;
  }
}
