package com.javaprogramming.intermediate.oops.javaobject;

import com.javaprogramming.intermediate.oops.javaclass.Car;

/*
Object:A self-contained instance of a class that encapsulates data (state) and behavior (methods).
 */
public class ObjectCreation {
  public static void main(String[] args) {
    Car car = new Car("Ford", "Pickup", 2022); // Object creation.
    car.getCarDetails();
  }
}
