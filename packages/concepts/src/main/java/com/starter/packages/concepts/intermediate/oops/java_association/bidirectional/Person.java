package com.starter.packages.concepts.intermediate.oops.java_association.bidirectional;

/*
Dog and person both know each other.
 */
public class Person {
  Dog dog;

  void setDog(Dog dog) {
    this.dog = dog;
    dog.owner = this;
  }
}
