package com.javaprogramming.intermediate.oops.javainheritance;

import com.javaprogramming.intermediate.oops.javaclass.Car;

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
