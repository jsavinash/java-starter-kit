package com.starter.packages.concepts.intermediate.oops.java_polymorphism.final_keyword;

/*
 * final is also called non-access modifier, will not allow child class to access.
 * Variable :- Value can't be changed.
 * Method :- Subclasses cannot override the final method.
 * Class :- final class cannot be inherited.
 * Parameter :- Value cannot be changed inside the method.
 */
public final class Base { // block inheritance.
  final String name = "Base"; // value can't be changed.

  final String getClassname() { // No override allowed in child class.
    return "Base";
  }

  void printName(final String name) { // value of name can't be changed inside method.
    System.out.println("name :: " + name);
  }
}
