package com.javaprogramming.intermediate.oops.javaclass;

import java.util.Objects;

/*
Class : A user defined blueprint used to create objects that share common properties and
behaviours.
*/
public class Car {
  /*
  Member variables
  i) Instance variable : Each object created from the class gets its own independent copy of instance variable.
  ii) Class Variable (Static) : Only one copy exists, which is shared across all instances of the class.
  iii) final variable (constant) : Class-level variable whose value cannot be changed or reassigned once it has been initialized. Each instance will get its own copy.
   */
  private String make;
  private String model;
  private int year;
  static boolean isCarNeedMaintenance;

  /*
  Instance initialization block:  A nameless block of code enclosed in curly braces {} inside a class. It executes automatically to set up or initialize variables when a class is loaded or when an object is created.
  i) Normal block: Allow you to initialize non static member variable.
  ii) Static block: Allow you to initialize static member variable.
   */

  // Instance initialization block
  { // runs before constructor
    System.out.println("************* Instance initialization block *************");
    make = "TBD";
    model = "TBD";
    year = 0;
  }

  // Static initialization block

  static { // runs before instance initialization block
    System.out.println("************* Static initialization block *************");
    isCarNeedMaintenance = false;
  }

  /*
  Constructor : A constructor in Java is a special method that is used to initialize objects.
  i) Default : If no constructor defined, Java compiler automatically creates a no-argument default constructor at compile time.
  ii) No-Arg : A constructor that accepts zero parameters.
  iii) Parameterized : A constructor that accepts a specific number of parameters.
  iv) Constructor Overloading :  A technique that allows a class to have more than one constructor, provided they all have different parameter lists.
  v) Constructor Chaining (this()) : A process of one constructor calling another constructor within the same class.
   */

  // No-Arg Constructor
  protected Car() {
    System.out.println("************* Constructor *************");
    this("Unknown", "Unknown", 0); // Chaining to 3-param constructor
  }

  // Parameterized Constructor
  Car(String make) {
    this.make = make;
    this.model = "TBD";
    this.year = 0;
  }

  // Constructor overloading by two parameter.
  Car(String make, String model) {
    this.make = make;
    this.model = model;
    this.year = 0;
  }

  // Constructor overloading by three parameter.
  public Car(String make, String model, int year) {
    this.make = make;
    this.model = model;
    this.year = year;
  }

  /*
  Getters and setters : Public methods used to access and modify the hidden, private variables of a class.
   */
  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  /*
    Methods : Block of code containing a series of statements that only runs when it is called.
    Instance methods : A method that belongs to an object (instance) of a class.
    Static methods : A method that belongs to class itself.
    Method overloading : A feature in Java that allows a class to have more than one method with the same name, as long as their parameter lists are different.
    Method overriding : A feature in Java that allows a subclass to provide a specific implementation of a method that is already provided by its superclass (parent class).
    Final method : A method in Java that cannot be overridden by any subclass. Once a method is declared as final in a parent class, its implementation is permanently locked.
  */

  // Instance methods
  public void getCarDetails() {
    System.out.println("************* Car Details *************");
    System.out.println(String.format("Make : %s", this.make));
    System.out.println(String.format("Model : %s", this.model));
    System.out.println(String.format("Year : %s", this.year));
  }

  // Static methods
  static boolean isCarNeedMaintenance() {
    return isCarNeedMaintenance;
  }

  // Instance methods overloading with one parameter.
  void setCarDetails(String make) {
    this.make = make;
  }

  // Instance methods overloading with two parameter.
  void setCarDetails(String make, String model) {
    this.make = make;
    this.model = model;
  }

  // Instance methods overloading with three parameter.
  void setCarDetails(String make, String model, int year) {
    this.make = make;
    this.model = model;
    this.year = year;
  }

  // Method overriding
  @Override
  public String toString() {
    return "Car{" + "make='" + make + '\'' + ", model='" + model + '\'' + ", year=" + year + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return year == car.year && Objects.equals(make, car.make) && Objects.equals(model, car.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(make, model, year);
  }

  public static void main(String[] args) {
    Car car = new Car();
    car.setCarDetails("Ford", "Z model", 1994);
    car.getCarDetails();
  }
}
