package com.starter.packages.concepts.intermediate.oops.java_polymorphism.block;

/*
It is used to initialize instance variables.
You can have multiple instance initializer blocks in a class; they execute in the order they appear.
It runs before the constructor when an object is created.
 */
public class Base {
  String firstname;
  String lastname;

  { // First block
    firstname = "Base 1";
    System.out.println("Base instance initializer block 1");
  }

  { // Second block
    lastname = "Base 2";
    System.out.println("Base instance initializer block 2");
  }

  Base() {
    System.out.println("Base constructor");
  }

  String getFullname() {
    return String.format("firstname :: %s, lastname :: %s", this.firstname, this.lastname);
  }
}
