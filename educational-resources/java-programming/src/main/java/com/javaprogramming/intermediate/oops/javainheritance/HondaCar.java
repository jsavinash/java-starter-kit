package com.javaprogramming.intermediate.oops.javainheritance;

import com.javaprogramming.intermediate.oops.javaclass.Car;

/*
Inheritance : A mechanism in Java that allows one class to acquire the properties (fields) and behaviors (methods) of another class.
Multilevel : When there is a chain of inheritance, it is known as multilevel inheritance.
Hierarchical : When two or more classes inherits a single class, it is known as hierarchical inheritance.
Multiple : Multiple Inheritance A class's capacity to inherit traits from several classes is referred to as multiple inheritances.
* Multiple inheritances can result in issues like the diamond problem.
* Java uses interfaces to implement multiple inheritances in order to prevent these conflicts.
Hybrid : The hybrid inheritance is the composition of two or more types of inheritance. The main purpose of using hybrid inheritance is to modularize the code into well-defined classes. It also provides the code reusability.
 */
public class HondaCar extends Car {
  private String accessories;
  private boolean isCleaningDone = false;

  HondaCar(String make, String model, int year, String accessories) {
    super(make, model, year);
    this.accessories = accessories;
  }

  void getAccessoriesDetails() {
    System.out.println(String.format("Accessories : %s", this.accessories));
  }

  void getCleaningStatus() {
    System.out.println(String.format("Cleaning done? : %s", this.isCleaningDone));
  }

  public static void main(String[] args) {
    HondaCar hondaCar = new HondaCar("Honda", "Baleno", 2021, "car cover");
    hondaCar.getCarDetails();
    hondaCar.getAccessoriesDetails();
    hondaCar.getCleaningStatus();
  }
}
