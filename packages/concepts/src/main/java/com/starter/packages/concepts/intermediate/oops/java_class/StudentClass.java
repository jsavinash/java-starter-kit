package com.starter.packages.concepts.intermediate.oops.java_class;

/*
 * Class is a logical entity.
 * A class can also be defined as a blueprint from which we can create an individual object.
 * Class does not consume any space.
 */
public class StudentClass {
  // Field
  String varInstance = "instance";

  // Inner interface
  public interface InnerInterface {
    void display();
  }

  // Inner class
  class InnerClass implements InnerInterface {

    public void display() {
      System.out.println("Display method from inner class");
    }
  }

  // Constructor
  StudentClass() {
    System.out.println("Constructor called");
  }

  // Blocks
  {
    System.out.println("Initialization block called");
  }
}
