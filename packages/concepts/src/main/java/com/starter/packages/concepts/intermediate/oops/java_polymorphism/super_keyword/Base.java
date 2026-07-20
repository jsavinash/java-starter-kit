package com.starter.packages.concepts.intermediate.oops.java_polymorphism.super_keyword;

/*
 *Super keyword allows access to methods, variables, or constructors of parent class.
 *Features
 * Simplifies Constructor Chaining, if we won't do compiler will do that.
 * Access to Overridden Methods
 * Access to Hidden Fields
 * Code Reusability
 * Maintains Hierarchical Relationships
 * Note : static method and variable can't be accessed.
 * We don't need to use super if we don't use inheritance.
 */
public class Base {
  String classname = "Base class instance variable";

  Base(String name) {
    System.out.println("Base constructor");
    classname = name;
  }

  String getClassName() {
    return "Base class instance method";
  }
}
