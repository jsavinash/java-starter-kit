package com.javaprogramming.intermediate;

import java.util.Objects;

/**
 * ========================================================== SCENARIO: Car Dealership Management
 * System ==========================================================
 *
 * <p>This file demonstrates intermediate Java OOP concepts through a realistic Car Dealership
 * scenario where:
 *
 * <p>- A Car can be serviced, driven, cleaned, and owned. - The Owner (abstract class) provides
 * base owner details. - The Driver (interface) defines driving contracts. - Multiple nested/inner
 * classes implement concrete behaviors. - Static members track global dealership stats. -
 * Initialization blocks ensure consistent state.
 *
 * Concepts covered: 
 * 1) Class 
 * 2) Constructor 
 *  i) Default Constructor 
 *  ii) No-Arg Constructor 
 *  iii) Parameterized Constructor 
 * 3) Member variables 
 *  i) Instance variable 
 *  ii) Static variable 
 * 4) Getters and setters 
 * 5) Methods 
 *  i) Instance methods 
 *  ii) Static methods 
 * 6) Initialization block 
 *  i) Normal block 
 *  ii) Static block 
 * 7) Interface inside class 
 * 8) Nested class 
 * 9) Abstract class
 */
public class Car {

  // ──────────────────────────────────────────────
  // 7) Interface inside class
  // ──────────────────────────────────────────────
  // Defines a contract: any Driver must be able to drive.
  public interface Driver {
    void drive(Car car);
  }

  // ──────────────────────────────────────────────
  // 9) Abstract class inside class
  // ──────────────────────────────────────────────
  // Provides common structure for all car-related human roles.
  public abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public abstract String getRoleDescription();

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return getRoleDescription() + " [name=" + name + ", age=" + age + "]";
    }
  }

  // ──────────────────────────────────────────────
  // 8i) Nested Class — implements Driver interface
  // ──────────────────────────────────────────────
  public class CarDriver extends Person implements Driver {
    private String licenseNumber;
    private int experienceYears;

    public CarDriver(String name, int age, String licenseNumber, int experienceYears) {
      super(name, age);
      this.licenseNumber = licenseNumber;
      this.experienceYears = experienceYears;
    }

    @Override
    public void drive(Car car) {
      if (!car.isRunning()) {
        car.start();
      }
      System.out.println(
          name
              + " (license: "
              + licenseNumber
              + ") is driving "
              + car.getBrand()
              + " "
              + car.getModel());
      car.addMileage(15.0 + Math.random() * 35); // simulate distance driven
    }

    @Override
    public String getRoleDescription() {
      return "Driver";
    }

    public String getLicenseNumber() {
      return licenseNumber;
    }

    public int getExperienceYears() {
      return experienceYears;
    }
  }

  // ──────────────────────────────────────────────
  // 8ii) Nested Class — extends abstract Person
  // ──────────────────────────────────────────────
  public class CarOwner extends Person {
    private String phoneNumber;
    private String address;

    public CarOwner(String name, int age, String phoneNumber, String address) {
      super(name, age);
      this.phoneNumber = phoneNumber;
      this.address = address;
    }

    @Override
    public String getRoleDescription() {
      return "Owner";
    }

    public void showOwnershipInfo() {
      System.out.println("Owner: " + name + " | Phone: " + phoneNumber + " | Address: " + address);
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }
  }

  // ──────────────────────────────────────────────
  // 8iii) Nested Class — CarCleaner (simple role)
  // ──────────────────────────────────────────────
  public class CarCleaner extends Person {
    private String shift; // "Morning", "Evening"

    public CarCleaner(String name, int age, String shift) {
      super(name, age);
      this.shift = shift;
    }

    @Override
    public String getRoleDescription() {
      return "Cleaner";
    }

    public void clean(Car car) {
      double cost = 15.0 + Math.random() * 20;
      car.addServiceCost(cost);
      car.setClean(true);
      System.out.println(
          name
              + " ("
              + shift
              + " shift) cleaned "
              + car.getBrand()
              + " "
              + car.getModel()
              + " — service cost: $"
              + String.format("%.2f", cost));
    }

    public String getShift() {
      return shift;
    }
  }

  // ──────────────────────────────────────────────
  // 8iv) Nested Class — Mechanic (adds service role)
  // ──────────────────────────────────────────────
  public class Mechanic extends Person {
    private String specialization; // "Engine", "Brakes", "Electrical", "General"

    public Mechanic(String name, int age, String specialization) {
      super(name, age);
      this.specialization = specialization;
    }

    @Override
    public String getRoleDescription() {
      return "Mechanic";
    }

    public void service(Car car) {
      double serviceCost = 50.0 + Math.random() * 200;
      car.addServiceCost(serviceCost);
      car.setNeedsService(false);
      System.out.println(
          name
              + " ("
              + specialization
              + " specialist) serviced "
              + car.getBrand()
              + " "
              + car.getModel()
              + " — service cost: $"
              + String.format("%.2f", serviceCost));
    }

    public String getSpecialization() {
      return specialization;
    }
  }

  // ──────────────────────────────────────────────
  // 6i) Instance Initialization block
  // ──────────────────────────────────────────────
  // Runs before constructor — ensures fields have safe defaults
  {
    this.brand = "Unknown";
    this.model = "Unknown";
    this.year = 2000;
    this.mileage = 0.0;
    this.totalServiceCost = 0.0;
    this.isRunning = false;
    this.isClean = false;
    this.needsService = false;
    System.out.println("  [Instance Init Block] Defaults set for a new Car object");
  }

  // ──────────────────────────────────────────────
  // 6ii) Static Initialization block
  // ──────────────────────────────────────────────
  // Runs once when the class is first loaded — sets up global stats
  static {
    totalCarsProduced = 0;
    totalServiceRevenue = 0.0;
    System.out.println("  [Static Init Block] Car dealership system initialized");
  }

  // ──────────────────────────────────────────────
  // 3i) Instance variables
  // ──────────────────────────────────────────────
  private String brand;
  private String model;
  private int year;
  private double mileage;
  private double totalServiceCost;
  private boolean isRunning;
  private boolean isClean;
  private boolean needsService;

  // Role assignments (composition — Car 'has' these roles)
  private Driver carDriver;
  private CarOwner carOwner;
  private CarCleaner carCleaner;
  private Mechanic carMechanic;

  // ──────────────────────────────────────────────
  // 3ii) Static variables (shared across all Car instances)
  // ──────────────────────────────────────────────
  private static int totalCarsProduced = 0;
  private static double totalServiceRevenue = 0.0;

  // ──────────────────────────────────────────────
  // 2i) Default Constructor
  //     (explicit — the compiler would generate one, but we define it
  //      to demonstrate it exists.)
  // ──────────────────────────────────────────────
  public Car() {
    this("DefaultBrand", "DefaultModel", 2024);
    System.out.println("  (Default Constructor called via this())");
  }

  // ──────────────────────────────────────────────
  // 2iii) Parameterized Constructor
  // ──────────────────────────────────────────────
  public Car(String brand, String model, int year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.needsService = true; // new cars need PDI (pre-delivery inspection)
    this.mileage = 5.0; // factory test drive mileage
    totalCarsProduced++;
    System.out.println(
        "  [Constructor] Created Car #"
            + totalCarsProduced
            + ": "
            + year
            + " "
            + brand
            + " "
            + model);
  }

  // ──────────────────────────────────────────────
  // 2ii) No-Arg Constructor (for completeness)
  //      Note: "no-arg" means no parameters but it's explicit.
  //      Already covered via default constructor above,
  //      but we provide a separate no-arg variant that
  //      does NOT chain to the parameterized constructor.
  // ──────────────────────────────────────────────
  public static Car createEmptyCar() {
    return new Car("TBD", "TBD", 0);
  }

  // ──────────────────────────────────────────────
  // 4) Getters and Setters
  // ──────────────────────────────────────────────
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
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

  public double getMileage() {
    return mileage;
  }

  public double getTotalServiceCost() {
    return totalServiceCost;
  }

  public boolean isClean() {
    return isClean;
  }

  public void setClean(boolean clean) {
    isClean = clean;
  }

  public boolean isNeedsService() {
    return needsService;
  }

  public void setNeedsService(boolean needsService) {
    this.needsService = needsService;
  }

  public boolean isRunning() {
    return isRunning;
  }

  // ──────────────────────────────────────────────
  // 5i) Instance methods
  // ──────────────────────────────────────────────

  /** Start the car engine. */
  public void start() {
    if (isRunning) {
      System.out.println(brand + " " + model + " is already running.");
      return;
    }
    isRunning = true;
    System.out.println(brand + " " + model + " engine started.");
  }

  /** Stop the car engine. */
  public void stop() {
    if (!isRunning) {
      System.out.println(brand + " " + model + " is already off.");
      return;
    }
    isRunning = false;
    System.out.println(brand + " " + model + " engine stopped.");
  }

  /** Add mileage (simulates driving distance). */
  public void addMileage(double miles) {
    if (miles < 0) {
      System.out.println("Mileage cannot be negative.");
      return;
    }
    this.mileage += miles;
    System.out.println("  Mileage updated: " + String.format("%.1f", this.mileage) + " miles");
  }

  /** Accumulate service cost and add to global revenue. */
  public void addServiceCost(double cost) {
    this.totalServiceCost += cost;
    totalServiceRevenue += cost;
  }

  /** Assign roles to this car (composition injection). */
  public void assignDriver(CarDriver driver) {
    this.carDriver = driver;
  }

  public void assignOwner(CarOwner owner) {
    this.carOwner = owner;
  }

  public void assignCleaner(CarCleaner cleaner) {
    this.carCleaner = cleaner;
  }

  public void assignMechanic(Mechanic mechanic) {
    this.carMechanic = mechanic;
  }

  /** Perform a full service routine — mechanic service + cleaner clean if needed. */
  public void performFullService() {
    System.out.println("\n--- Full Service for " + brand + " " + model + " ---");
    if (carMechanic != null) {
      carMechanic.service(this);
    } else {
      System.out.println("  No mechanic assigned — cannot service.");
    }
    if (carCleaner != null) {
      carCleaner.clean(this);
    } else {
      System.out.println("  No cleaner assigned — cannot clean.");
    }
    System.out.println(
        "--- Service Complete (Total Cost: $"
            + String.format("%.2f", totalServiceCost)
            + ") ---\n");
  }

  /** Display all role information. */
  public void showAllRoles() {
    System.out.println("\n===== CAR ROLES: " + brand + " " + model + " =====");
    if (carDriver != null) {
      System.out.println("  " + carDriver);
    } else {
      System.out.println("  Driver: Not assigned");
    }
    if (carOwner != null) {
      System.out.println("  " + carOwner);
    } else {
      System.out.println("  Owner: Not assigned");
    }
    if (carCleaner != null) {
      System.out.println("  " + carCleaner);
    } else {
      System.out.println("  Cleaner: Not assigned");
    }
    if (carMechanic != null) {
      System.out.println("  " + carMechanic);
    } else {
      System.out.println("  Mechanic: Not assigned");
    }
    System.out.println("===============================\n");
  }

  /** Display the full status of the car. */
  public void showStatus() {
    System.out.println("\n===== CAR STATUS =====");
    System.out.println("  " + year + " " + brand + " " + model);
    System.out.println("  Mileage: " + String.format("%.1f", mileage) + " miles");
    System.out.println("  Total Service Cost: $" + String.format("%.2f", totalServiceCost));
    System.out.println("  Engine Running: " + isRunning);
    System.out.println("  Clean: " + isClean);
    System.out.println("  Needs Service: " + needsService);
    System.out.println("======================\n");
  }

  // ──────────────────────────────────────────────
  // 5ii) Static methods
  // ──────────────────────────────────────────────

  /** Display global dealership statistics. */
  public static void showDealershipStats() {
    System.out.println("\n===== DEALERSHIP STATS =====");
    System.out.println("  Total cars produced: " + totalCarsProduced);
    System.out.println("  Total service revenue: $" + String.format("%.2f", totalServiceRevenue));
    System.out.println("=============================\n");
  }

  /** Reset global stats (useful for testing). */
  public static void resetDealershipStats() {
    totalCarsProduced = 0;
    totalServiceRevenue = 0.0;
    System.out.println("  [Static] Dealership stats reset.");
  }

  // ──────────────────────────────────────────────
  // Object class overrides
  // ──────────────────────────────────────────────

  @Override
  public String toString() {
    return year + " " + brand + " " + model + " [" + String.format("%.1f", mileage) + " miles]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Car car = (Car) o;
    return year == car.year
        && brand.equals(car.brand)
        && model.equals(car.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, model, year);
  }

  // ──────────────────────────────────────────────
  // main — Scenario Demonstration
  // ──────────────────────────────────────────────
  public static void main(String[] args) {
    System.out.println("\n=============================================");
    System.out.println(" CAR DEALERSHIP MANAGEMENT — SCENARIO DEMO");
    System.out.println("=============================================\n");

    // ─── Create some cars ────────────────────────
    Car car1 = new Car("Toyota", "Camry", 2024);
    Car car2 = new Car("Tesla", "Model 3", 2025);
    Car car3 = new Car("Ford", "Mustang", 2023);

    System.out.println("\n--- Cars created. Displaying status ---");
    car1.showStatus();
    car2.showStatus();
    car3.showStatus();

    // ─── Create people (nested class instances) ──
    // Each Car object creates its own nested instances
    Car.CarDriver alice = car1.new CarDriver("Alice Johnson", 32, "LIC-A123", 10);
    Car.CarOwner bob = car1.new CarOwner("Bob Johnson", 35, "555-0101", "123 Main St, Springfield");
    Car.CarCleaner charlie = car1.new CarCleaner("Charlie", 28, "Morning");
    Car.Mechanic diana = car1.new Mechanic("Diana", 45, "Engine");

    Car.CarDriver eve = car2.new CarDriver("Eve Smith", 29, "LIC-B456", 5);
    Car.CarOwner frank =
        car2.new CarOwner("Frank Smith", 33, "555-0202", "456 Oak Ave, Metropolis");
    Car.CarCleaner grace = car2.new CarCleaner("Grace", 31, "Evening");
    Car.Mechanic henry = car2.new Mechanic("Henry", 50, "Electrical");

    Car.CarDriver isaac = car3.new CarDriver("Isaac Newton", 40, "LIC-C789", 15);
    Car.CarOwner judy = car3.new CarOwner("Judy Newton", 38, "555-0303", "789 Pine Rd, Gotham");
    Car.CarCleaner kevin = car3.new CarCleaner("Kevin", 25, "Morning");
    Car.Mechanic linda = car3.new Mechanic("Linda", 42, "Brakes");

    // ─── Assign roles to cars ────────────────────
    car1.assignDriver(alice);
    car1.assignOwner(bob);
    car1.assignCleaner(charlie);
    car1.assignMechanic(diana);

    car2.assignDriver(eve);
    car2.assignOwner(frank);
    car2.assignCleaner(grace);
    car2.assignMechanic(henry);

    car3.assignDriver(isaac);
    car3.assignOwner(judy);
    car3.assignCleaner(kevin);
    car3.assignMechanic(linda);

    // ─── Show all roles ──────────────────────────
    car1.showAllRoles();
    car2.showAllRoles();
    car3.showAllRoles();

    // ─── Simulate driving ────────────────────────
    System.out.println("=== DRIVING SESSION ===");
    ((Driver) alice).drive(car1);
    eve.drive(car2);
    isaac.drive(car3);

    car1.showStatus();
    car2.showStatus();
    car3.showStatus();

    // ─── Service cars ────────────────────────────
    car1.performFullService();
    car2.performFullService();
    car3.performFullService();

    // ─── Display final state ─────────────────────
    car1.showStatus();
    car2.showStatus();
    car3.showStatus();

    // ─── Static: dealership stats ────────────────
    Car.showDealershipStats();

    // ─── Object class methods demo ───────────────
    System.out.println("=== toString() Demo ===");
    System.out.println("car1: " + car1.toString());
    System.out.println("car2: " + car2.toString());

    System.out.println("\n=== equals() Demo ===");
    Car toyotaCopy = new Car("Toyota", "Camry", 2024);
    toyotaCopy.assignDriver(car1.new CarDriver("Alice Johnson", 32, "LIC-A123", 10));
    toyotaCopy.assignOwner(
        car1.new CarOwner("Bob Johnson", 35, "555-0101", "123 Main St, Springfield"));
    // Equal because brand, model, year match (mileage will differ after driving, so we construct
    // directly)
    Car car4 = new Car("Toyota", "Camry", 2024);
    System.out.println("car1.equals(car4): " + car1.equals(car4)); // true
    System.out.println("car1.equals(car2): " + car1.equals(car2)); // false

    System.out.println("\n=== hashCode() Demo ===");
    System.out.println("car1 hashCode: " + car1.hashCode());
    System.out.println("car4 hashCode: " + car4.hashCode());
    System.out.println("Same hash? " + (car1.hashCode() == car4.hashCode()));

    System.out.println("\n=== Static method: Total Cars ===");
    Car.showDealershipStats();

    System.out.println("\n=============================================");
    System.out.println(" END OF CAR DEALERSHIP SCENARIO DEMO");
    System.out.println("=============================================");
  }
}
