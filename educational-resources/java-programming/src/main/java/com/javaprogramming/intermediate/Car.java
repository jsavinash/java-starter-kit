package com.javaprogramming.intermediate;

import java.util.Objects;

/**
 * ==========================================================
 * SCENARIO: Car Dealership Management System
 * ==========================================================
 *
 * This file demonstrates ALL intermediate Java OOP concepts
 * through a realistic Car Dealership scenario.
 *
 * Concepts covered:
 *  1)  Class
 *  2)  Constructor
 *      i)   Default Constructor
 *      ii)  No-Arg Constructor
 *      iii) Parameterized Constructor
 *      iv)  Constructor Overloading
 *      v)   Constructor Chaining (this())
 *  3)  Member variables
 *      i)   Instance variable
 *      ii)  Static variable
 *      iii) final variable (constant)
 *  4)  Getters and setters
 *  5)  Methods
 *      i)   Instance methods
 *      ii)  Static methods
 *      iii) Method overloading
 *      iv)  Method overriding
 *      v)   final method
 *  6)  Initialization block
 *      i)   Normal block
 *      ii)  Static block
 *  7)  Interface inside class
 *  8)  Nested class (inner class)
 *  9)  Abstract class
 *  10) this keyword
 *  11) super keyword
 *  12) instanceof operator
 *  13) Encapsulation
 *  14) Polymorphism
 *  15) Composition (has-a relationship)
 *  16) Anonymous class
 *  17) Local class (class inside a method)
 *  18) Variable shadowing
 *  19) var keyword (local variable type inference)
 *  20) Record (Java 14+)
 *  21) Enum
 *  22) Object class overrides (toString, equals, hashCode)
 */
public class Car {

  // ═══════════════════════════════════════════════
  // 21) Enum inside class
  // ═══════════════════════════════════════════════
  public enum FuelType {
    PETROL, DIESEL, ELECTRIC, HYBRID, HYDROGEN
  }

  public enum ServiceStatus {
    PENDING_SERVICE("Needs service"),
    SERVICED("Fully serviced"),
    IN_PROGRESS("Service in progress");

    private final String description;

    ServiceStatus(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }

  // ═══════════════════════════════════════════════
  // 20) Record inside class (Java 14+)
  // ═══════════════════════════════════════════════
  // Immutable data carrier for service history entries.
  public record ServiceRecord(String date, String mechanicName, double cost, String description) {
    // Compact constructor with validation
    public ServiceRecord {
      if (cost < 0) {
        throw new IllegalArgumentException("Service cost cannot be negative");
      }
      if (date == null || date.isBlank()) {
        date = "Unknown date";
      }
    }

    // Additional instance method in a record
    public String formattedEntry() {
      return "[" + date + "] " + description + " by " + mechanicName + " — $" + String.format("%.2f", cost);
    }
  }

  // ═══════════════════════════════════════════════
  // 7) Interface inside class
  // ═══════════════════════════════════════════════
  public interface Driver {
    void drive(Car car);
  }

  // ═══════════════════════════════════════════════
  // 9) Abstract class inside class
  // ═══════════════════════════════════════════════
  public abstract class Person {
    // 3i) Instance variables
    // 18) Variable shadowing demo: parameter 'name' shadows field 'this.name'
    protected String name;
    protected int age;

    // 2iii) Parameterized Constructor
    // 11) super keyword (implicit — Object constructor)
    public Person(String name, int age) {
      // 10) this keyword — disambiguates shadowed variable
      this.name = name;
      this.age = age;
    }

    // 5iv) Abstract method (must be overridden)
    public abstract String getRoleDescription();

    public String getName() {
      return name;
    }

    // 5iv) Method overriding — overrides Object.toString()
    @Override
    public String toString() {
      return getRoleDescription() + " [name=" + name + ", age=" + age + "]";
    }
  }

  // ═══════════════════════════════════════════════
  // 8) Nested Class — implements Driver interface
  // ═══════════════════════════════════════════════
  public class CarDriver extends Person implements Driver {
    // 3iii) final instance variable (set once, cannot change)
    private final String licenseNumber;
    private int experienceYears;

    // 11) super keyword — calls Person(name, age) constructor
    public CarDriver(String name, int age, String licenseNumber, int experienceYears) {
      super(name, age);
      this.licenseNumber = licenseNumber;
      this.experienceYears = experienceYears;
    }

    // 5iv) Method overriding — implements Driver.drive()
    @Override
    public void drive(Car car) {
      if (!car.isRunning()) {
        car.start();
      }
      System.out.println(
          name + " (license: " + licenseNumber + ") is driving " + car.getBrand() + " " + car.getModel()
      );
      car.addMileage(15.0 + Math.random() * 35);
    }

    // 5iv) Method overriding — implements Person.getRoleDescription()
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

  // ═══════════════════════════════════════════════
  // 8) Nested Class — extends abstract Person
  // ═══════════════════════════════════════════════
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

  // ═══════════════════════════════════════════════
  // 8) Nested Class — CarCleaner
  // ═══════════════════════════════════════════════
  public class CarCleaner extends Person {
    private String shift;

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
          name + " (" + shift + " shift) cleaned " + car.getBrand() + " " + car.getModel()
          + " — service cost: $" + String.format("%.2f", cost)
      );
    }

    public String getShift() {
      return shift;
    }
  }

  // ═══════════════════════════════════════════════
  // 8) Nested Class — Mechanic
  // ═══════════════════════════════════════════════
  public class Mechanic extends Person {
    private String specialization;

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
          name + " (" + specialization + " specialist) serviced "
          + car.getBrand() + " " + car.getModel()
          + " — service cost: $" + String.format("%.2f", serviceCost)
      );
    }

    public String getSpecialization() {
      return specialization;
    }
  }

  // ═══════════════════════════════════════════════
  // 6i) Instance Initialization block
  // ═══════════════════════════════════════════════
  {
    this.brand = "Unknown";
    this.model = "Unknown";
    this.year = 2000;
    this.mileage = 0.0;
    this.totalServiceCost = 0.0;
    this.isRunning = false;
    this.isClean = false;
    this.needsService = ServiceStatus.PENDING_SERVICE;
    System.out.println("  [Instance Init Block] Defaults set for a new Car object");
  }

  // ═══════════════════════════════════════════════
  // 6ii) Static Initialization block
  // ═══════════════════════════════════════════════
  static {
    totalCarsProduced = 0;
    totalServiceRevenue = 0.0;
    System.out.println("  [Static Init Block] Car dealership system initialized");
  }

  // ═══════════════════════════════════════════════
  // 3i) Instance variables
  // ═══════════════════════════════════════════════
  private String brand;
  private String model;
  private int year;
  private double mileage;
  private double totalServiceCost;
  private boolean isRunning;
  private boolean isClean;
  private ServiceStatus needsService;

  // 3iii) final instance variable — VIN cannot change after construction
  private final String vin;

  // 21) Enum instance variable
  private FuelType fuelType;

  // Composition (15) — Car 'has' these roles
  private Driver carDriver;
  private CarOwner carOwner;
  private CarCleaner carCleaner;
  private Mechanic carMechanic;

  // 20) Record — service history
  private java.util.ArrayList<ServiceRecord> serviceHistory;

  // ═══════════════════════════════════════════════
  // 3ii) Static variables (shared across all Car instances)
  // ═══════════════════════════════════════════════
  private static int totalCarsProduced = 0;
  private static double totalServiceRevenue = 0.0;

  // 3iii) final static constant
  public static final double MAX_MILEAGE_BEFORE_OVERHAUL = 200_000.0;
  public static final String DEALERSHIP_NAME = "Premium Auto Dealers";

  // ═══════════════════════════════════════════════
  // 2i) Default Constructor
  // 2v) Constructor Chaining — calls parameterized constructor via this()
  // ═══════════════════════════════════════════════
  public Car() {
    this("DefaultBrand", "DefaultModel", 2024, "VIN-DEFAULT-00000", FuelType.PETROL);
    System.out.println("  (Default Constructor → chained via this())");
  }

  // ═══════════════════════════════════════════════
  // 2iii) Parameterized Constructor (primary)
  // ═══════════════════════════════════════════════
  public Car(String brand, String model, int year, String vin, FuelType fuelType) {
    // 10) this keyword — disambiguates parameter from field
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.vin = vin;               // final — assigned once in constructor
    this.fuelType = fuelType;
    this.needsService = ServiceStatus.PENDING_SERVICE;
    this.mileage = 5.0;
    this.serviceHistory = new java.util.ArrayList<>();
    totalCarsProduced++;
    System.out.println(
        "  [Constructor] Created Car #" + totalCarsProduced + ": "
        + year + " " + brand + " " + model + " [VIN: " + vin + ", Fuel: " + fuelType + "]"
    );
  }

  // ═══════════════════════════════════════════════
  // 2iv) Constructor Overloading — fewer parameters
  // ═══════════════════════════════════════════════
  public Car(String brand, String model, int year) {
    // 2v) Constructor Chaining
    this(brand, model, year, "VIN-" + brand.substring(0, 2).toUpperCase() + "-" + year, FuelType.PETROL);
    System.out.println("  (Overloaded constructor → chained to primary)");
  }

  // ═══════════════════════════════════════════════
  // 2ii) No-Arg Constructor (static factory method style)
  // ═══════════════════════════════════════════════
  public static Car createEmptyCar() {
    return new Car("TBD", "TBD", 0, "VIN-TBD-00000", FuelType.PETROL);
  }

  // ═══════════════════════════════════════════════
  // 4) Getters and Setters (Encapsulation — 13)
  // ═══════════════════════════════════════════════
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

  public ServiceStatus getNeedsService() {
    return needsService;
  }

  public void setNeedsService(boolean needsService) {
    this.needsService = needsService ? ServiceStatus.PENDING_SERVICE : ServiceStatus.SERVICED;
  }

  public boolean isRunning() {
    return isRunning;
  }

  // 3iii) Getter only (no setter) — VIN is final, read-only after construction
  public String getVin() {
    return vin;
  }

  public FuelType getFuelType() {
    return fuelType;
  }

  public void setFuelType(FuelType fuelType) {
    this.fuelType = fuelType;
  }

  // 20) Getter for service history (defensive copy)
  public java.util.List<ServiceRecord> getServiceHistory() {
    return new java.util.ArrayList<>(serviceHistory);
  }

  // ═══════════════════════════════════════════════
  // 5i) Instance methods
  // ═══════════════════════════════════════════════

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

  // ═══════════════════════════════════════════════
  // 5iii) Method Overloading — addMileage with different signatures
  // ═══════════════════════════════════════════════

  /** Add mileage (double version). */
  public void addMileage(double miles) {
    if (miles < 0) {
      System.out.println("Mileage cannot be negative.");
      return;
    }
    this.mileage += miles;
    System.out.println("  Mileage updated: " + String.format("%.1f", this.mileage) + " miles");
  }

  /** Add mileage (integer version — overloaded). */
  public void addMileage(int miles) {
    addMileage((double) miles);
  }

  /** Add mileage with trip description (overloaded with extra param). */
  public void addMileage(double miles, String tripDescription) {
    addMileage(miles);
    System.out.println("  Trip: " + tripDescription);
  }

  /** Accumulate service cost and add to global revenue. */
  public void addServiceCost(double cost) {
    this.totalServiceCost += cost;
    totalServiceRevenue += cost;
  }

  // ═══════════════════════════════════════════════
  // 5iii) Method Overloading — addServiceRecord
  // ═══════════════════════════════════════════════

  /** Add a service record with full details. */
  public void addServiceRecord(String date, String mechanicName, double cost, String description) {
    ServiceRecord record = new ServiceRecord(date, mechanicName, cost, description);
    serviceHistory.add(record);
    System.out.println("  Service record added: " + record.formattedEntry());
  }

  /** Add a service record with just cost and description (overloaded). */
  public void addServiceRecord(double cost, String description) {
    addServiceRecord("Today", "Unknown", cost, description);
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

  /** Perform a full service routine. */
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
    // 20) Record usage — add to service history
    addServiceRecord(
        java.time.LocalDate.now().toString(),
        carMechanic != null ? carMechanic.getName() : "Unknown",
        totalServiceCost,
        "Full service + cleaning"
    );
    System.out.println("--- Service Complete (Total Cost: $" + String.format("%.2f", totalServiceCost) + ") ---\n");
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
    System.out.println("  VIN: " + vin);
    System.out.println("  Fuel Type: " + fuelType);
    System.out.println("  Mileage: " + String.format("%.1f", mileage) + " miles");
    System.out.println("  Total Service Cost: $" + String.format("%.2f", totalServiceCost));
    System.out.println("  Engine Running: " + isRunning);
    System.out.println("  Clean: " + isClean);
    System.out.println("  Service Status: " + needsService.getDescription());
    System.out.println("  Service Records: " + serviceHistory.size());
    System.out.println("======================\n");
  }

  // ═══════════════════════════════════════════════
  // 12) instanceof operator — type checking demo
  // ═══════════════════════════════════════════════

  /** Check if a person can drive this car based on their role. */
  public boolean canPersonDrive(Person person) {
    // 12) instanceof with pattern matching (Java 16+)
    if (person instanceof CarDriver driver) {
      System.out.println(person.getName() + " is a licensed driver (license: " + driver.getLicenseNumber() + ")");
      return true;
    } else if (person instanceof Mechanic) {
      System.out.println(person.getName() + " is a mechanic — can drive for test purposes.");
      return true;
    } else {
      System.out.println(person.getName() + " (" + person.getRoleDescription() + ") is not authorized to drive.");
      return false;
    }
  }

  // ═══════════════════════════════════════════════
  // 5v) final method — cannot be overridden by subclasses
  // ═══════════════════════════════════════════════
  public final boolean isEligibleForOverhaul() {
    return mileage >= MAX_MILEAGE_BEFORE_OVERHAUL;
  }

  // ═══════════════════════════════════════════════
  // 5ii) Static methods
  // ═══════════════════════════════════════════════

  /** Display global dealership statistics. */
  public static void showDealershipStats() {
    System.out.println("\n===== " + DEALERSHIP_NAME + " — DEALERSHIP STATS =====");
    System.out.println("  Total cars produced: " + totalCarsProduced);
    System.out.println("  Total service revenue: $" + String.format("%.2f", totalServiceRevenue));
    System.out.println("  Max mileage before overhaul: " + String.format("%.0f", MAX_MILEAGE_BEFORE_OVERHAUL) + " miles");
    System.out.println("==========================================\n");
  }

  /** Reset global stats. */
  public static void resetDealershipStats() {
    totalCarsProduced = 0;
    totalServiceRevenue = 0.0;
    System.out.println("  [Static] Dealership stats reset.");
  }

  // ═══════════════════════════════════════════════
  // 22) Object class overrides
  // ═══════════════════════════════════════════════

  @Override
  public String toString() {
    return year + " " + brand + " " + model + " [VIN: " + vin + ", " + String.format("%.1f", mileage) + " miles]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    // 12) instanceof — also used in equals()
    if (!(o instanceof Car car)) {
      return false;
    }
    return year == car.year
        && brand.equals(car.brand)
        && model.equals(car.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, model, year);
  }

  // ═══════════════════════════════════════════════
  // main — Scenario Demonstration
  // ═══════════════════════════════════════════════
  public static void main(String[] args) {
    System.out.println("\n=============================================");
    System.out.println(" CAR DEALERSHIP MANAGEMENT — SCENARIO DEMO");
    System.out.println("=============================================\n");

    // ─── 19) var keyword (local variable type inference) ───
    var car1 = new Car("Toyota", "Camry", 2024);
    var car2 = new Car("Tesla", "Model 3", 2025, "VIN-TSLA-2025-001", FuelType.ELECTRIC);
    var car3 = new Car("Ford", "Mustang", 2023, "VIN-FORD-2023-001", FuelType.PETROL);

    System.out.println("\n--- Cars created. Displaying status ---");
    car1.showStatus();
    car2.showStatus();
    car3.showStatus();

    // ─── Create people (nested class instances) ──
    var alice = car1.new CarDriver("Alice Johnson", 32, "LIC-A123", 10);
    var bob   = car1.new CarOwner("Bob Johnson", 35, "555-0101", "123 Main St, Springfield");
    var charlie = car1.new CarCleaner("Charlie", 28, "Morning");
    var diana   = car1.new Mechanic("Diana", 45, "Engine");

    var eve   = car2.new CarDriver("Eve Smith", 29, "LIC-B456", 5);
    var frank = car2.new CarOwner("Frank Smith", 33, "555-0202", "456 Oak Ave, Metropolis");
    var grace = car2.new CarCleaner("Grace", 31, "Evening");
    var henry = car2.new Mechanic("Henry", 50, "Electrical");

    var isaac = car3.new CarDriver("Isaac Newton", 40, "LIC-C789", 15);
    var judy  = car3.new CarOwner("Judy Newton", 38, "555-0303", "789 Pine Rd, Gotham");
    var kevin = car3.new CarCleaner("Kevin", 25, "Morning");
    var linda = car3.new Mechanic("Linda", 42, "Brakes");

    // ─── Assign roles ────────────────────────────
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

    // ─── 12) instanceof demo ─────────────────────
    System.out.println("=== instanceof OPERATOR DEMO ===");
    car1.canPersonDrive(alice);    // Driver → true
    car1.canPersonDrive(bob);      // Owner → false
    car1.canPersonDrive(diana);    // Mechanic → true
    car1.canPersonDrive(charlie);  // Cleaner → false
    System.out.println();

    // ─── Simulate driving ────────────────────────
    System.out.println("=== DRIVING SESSION ===");
    ((Driver) alice).drive(car1);
    eve.drive(car2);
    isaac.drive(car3);

    // ─── 5iii) Method overloading demo ───────────
    System.out.println("\n=== METHOD OVERLOADING DEMO ===");
    car1.addMileage(100);                    // int version
    car1.addMileage(50.5);                   // double version
    car1.addMileage(25.0, "Airport run");    // double + String version

    car1.showStatus();

    // ─── Service cars ────────────────────────────
    car1.performFullService();
    car2.performFullService();
    car3.performFullService();

    // ─── 20) Record demo — service history ───────
    System.out.println("=== RECORD (Service History) DEMO ===");
    System.out.println("car1 service records:");
    for (var record : car1.getServiceHistory()) {
      System.out.println("  • " + record.formattedEntry());
    }
    System.out.println();

    // ─── 16) Anonymous class demo ────────────────
    System.out.println("=== ANONYMOUS CLASS DEMO ===");
    // Creating an anonymous Driver that doesn't need a license
    Driver valetDriver = new Driver() {
      @Override
      public void drive(Car car) {
        System.out.println("Valet driver is parking " + car.getBrand() + " " + car.getModel());
        car.addMileage(0.5, "Valet parking");
      }
    };
    valetDriver.drive(car1);
    System.out.println();

    // ─── 17) Local class demo ────────────────────
    System.out.println("=== LOCAL CLASS DEMO ===");
    // Class defined inside a method
    class TestDriver {
      private final String name;
      TestDriver(String name) { this.name = name; }
      void testDrive(Car car) {
        System.out.println(name + " is test-driving " + car.getBrand() + " " + car.getModel());
        car.start();
        car.addMileage(2.0, "Test drive");
        car.stop();
      }
    }
    var testDriver = new TestDriver("Customer");
    testDriver.testDrive(car2);
    System.out.println();

    // ─── Display final state ─────────────────────
    car1.showStatus();
    car2.showStatus();
    car3.showStatus();

    // ─── Static: dealership stats ────────────────
    Car.showDealershipStats();

    // ─── 22) Object class methods demo ───────────
    System.out.println("=== toString() Demo ===");
    System.out.println("car1: " + car1);
    System.out.println("car2: " + car2);

    System.out.println("\n=== equals() Demo ===");
    var car4 = new Car("Toyota", "Camry", 2024);
    System.out.println("car1.equals(car4): " + car1.equals(car4));  // true
    System.out.println("car1.equals(car2): " + car1.equals(car2));  // false

    System.out.println("\n=== hashCode() Demo ===");
    System.out.println("car1 hashCode: " + car1.hashCode());
    System.out.println("car4 hashCode: " + car4.hashCode());
    System.out.println("Same hash? " + (car1.hashCode() == car4.hashCode()));

    // ─── 3iii) final constant demo ───────────────
    System.out.println("\n=== final CONSTANT DEMO ===");
    System.out.println("Dealership: " + Car.DEALERSHIP_NAME);
    System.out.println("Max mileage before overhaul: " + Car.MAX_MILEAGE_BEFORE_OVERHAUL + " miles");
    System.out.println("Car #1 eligible for overhaul? " + car1.isEligibleForOverhaul());

    // ─── 21) Enum demo ───────────────────────────
    System.out.println("\n=== ENUM DEMO ===");
    System.out.println("Car #1 fuel type: " + car1.getFuelType());
    System.out.println("Car #2 fuel type: " + car2.getFuelType());
    System.out.println("Car #3 fuel type: " + car3.getFuelType());
    System.out.println("All fuel types: ");
    for (var ft : FuelType.values()) {
      System.out.println("  - " + ft + " (ordinal: " + ft.ordinal() + ")");
    }

    System.out.println("\n=== Service Status Enum ===");
    System.out.println("Car #1 service status: " + car1.getNeedsService() + " — " + car1.getNeedsService().getDescription());

    // ─── 20) Record immutability demo ────────────
    System.out.println("\n=== RECORD IMMUTABILITY DEMO ===");
    var record = new ServiceRecord("2026-07-22", "Diana", 150.0, "Oil change");
    System.out.println("Record: " + record);
    System.out.println("Formatted: " + record.formattedEntry());
    // record.cost = 200.0;  // COMPILE ERROR — records are immutable!

    System.out.println("\n=============================================");
    System.out.println(" END OF CAR DEALERSHIP SCENARIO DEMO");
    System.out.println("=============================================");
  }
}