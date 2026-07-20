package com.starter.packages.concepts.intermediate.oops.java_polymorphism.covariant_return_type;

/*
 * Covariant return types are part of polymorphism in Java,
 * because they enable method overriding (a key aspect of runtime polymorphism)
 * while adhering to the Liskov Substitution Principle (LSP),
 * which states that objects of a superclass should be replaceable with objects of a subclass
 * without affecting the program's correctness.
 */
public class Base {
  Base getInstance() {
    return this;
  }

  String getClassname() {
    return "Base";
  }
}
