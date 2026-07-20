package com.starter.packages.concepts.intermediate.oops.java_abstraction.java_abstract;

/*
 * Abstraction (0 to 100%) is the process of hiding implementation details
 * and showing only the required functionality
 * It can have static, static, final member variable.
 * It can have static, abstract, non-abstract, final methods.
 * It can have instant initializer block
 * It can have static instant initializer block
 * Abstract must have class with abstract keyword.
 */
abstract class Base {
  String firstname;
  String lastname;
  static String message;
  final String message2 = "Welcome 2 !";

  {
    this.firstname = "Avinash";
    System.out.println("Instance initializer block 1");
  }

  {
    this.lastname = "Nishad";
    System.out.println("Instance initializer block 2");
  }

  static {
    message = "Welcome";
    System.out.println("Static instance initializer block");
  }

  Base() {
    System.out.println("Base constructor");
  }

  String getFirstName() {
    return this.firstname;
  }

  static String getFullName() {
    return message;
  }

  final String display() {
    return this.message2;
  }

  abstract String getLastName();
}
