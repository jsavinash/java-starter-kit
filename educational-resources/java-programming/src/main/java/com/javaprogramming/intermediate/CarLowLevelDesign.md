# Car Dealership Management System — Low-Level Design (LLD)

> **Author:** Java Starter Kit  
> **Domain:** Car Dealership Management  
> **Technology:** Java 21+  
> **Design Pattern:** Composition-based OOP with layered roles

---

## Table of Contents

1. [Structural Diagrams](#1-structural-diagrams)
   - [1.1 Enhanced Class Diagram](#11-enhanced-class-diagram)
   - [1.2 Object Diagram (Runtime Snapshot)](#12-object-diagram-runtime-snapshot)
   - [1.3 Component Diagram](#13-component-diagram)
   - [1.4 Package Diagram](#14-package-diagram)
2. [Behavioral Diagrams](#2-behavioral-diagrams)
   - [2.1 Sequence Diagram — Full Service Flow](#21-sequence-diagram--full-service-flow)
   - [2.2 Sequence Diagram — Driving Session](#22-sequence-diagram--driving-session)
   - [2.3 Activity Diagram — Car Lifecycle](#23-activity-diagram--car-lifecycle)
   - [2.4 State Machine Diagram — Car States](#24-state-machine-diagram--car-states)
   - [2.5 Use Case Diagram — Dealership System](#25-use-case-diagram--dealership-system)
3. [Concept-to-Diagram Mapping](#3-concept-to-diagram-mapping)

---

## 1. Structural Diagrams

### 1.1 Enhanced Class Diagram

> **Purpose:** Shows the static structure of all classes, interfaces, enums, records, their attributes, methods, and inter-relationships.

```mermaid
classDiagram
    class FuelType {
        <<enumeration>>
        + PETROL
        + DIESEL
        + ELECTRIC
        + HYBRID
        + HYDROGEN
    }

    class ServiceStatus {
        <<enumeration>>
        + PENDING_SERVICE
        + SERVICED
        + IN_PROGRESS
        - String description
        + getDescription() String
    }

    class ServiceRecord {
        <<record>>
        + String date
        + String mechanicName
        + double cost
        + String description
        + formattedEntry() String
    }

    class Driver {
        <<interface>>
        + drive(Car car) void
    }

    class Person {
        <<abstract>>
        # String name
        # int age
        + Person(String name, int age)
        + getRoleDescription()* String
        + getName() String
        + toString() String
    }

    class CarDriver {
        - String licenseNumber
        - int experienceYears
        + drive(Car car) void
        + getRoleDescription() String
        + getLicenseNumber() String
        + getExperienceYears() int
    }

    class CarOwner {
        - String phoneNumber
        - String address
        + getRoleDescription() String
        + showOwnershipInfo() void
        + getPhoneNumber() String
        + setPhoneNumber(String) void
        + getAddress() String
        + setAddress(String) void
    }

    class CarCleaner {
        - String shift
        + getRoleDescription() String
        + clean(Car car) void
        + getShift() String
    }

    class Mechanic {
        - String specialization
        + getRoleDescription() String
        + service(Car car) void
        + getSpecialization() String
    }

    class Car {
        - String brand
        - String model
        - int year
        - double mileage
        - double totalServiceCost
        - boolean isRunning
        - boolean isClean
        - ServiceStatus needsService
        - String vin
        - FuelType fuelType
        - List~ServiceRecord~ serviceHistory
        - Driver carDriver
        - CarOwner carOwner
        - CarCleaner carCleaner
        - Mechanic carMechanic
        - static int totalCarsProduced
        - static double totalServiceRevenue
        + static final double MAX_MILEAGE_BEFORE_OVERHAUL
        + static final String DEALERSHIP_NAME
        + Car()
        + Car(String brand, String model, int year, String vin, FuelType fuelType)
        + Car(String brand, String model, int year)
        + createEmptyCar() Car
        + start() void
        + stop() void
        + addMileage(double) void
        + addMileage(int) void
        + addMileage(double, String) void
        + addServiceCost(double) void
        + addServiceRecord(String, String, double, String) void
        + addServiceRecord(double, String) void
        + assignDriver(CarDriver) void
        + assignOwner(CarOwner) void
        + assignCleaner(CarCleaner) void
        + assignMechanic(Mechanic) void
        + performFullService() void
        + showAllRoles() void
        + showStatus() void
        + canPersonDrive(Person) boolean
        + isEligibleForOverhaul() boolean
        + static showDealershipStats() void
        + static resetDealershipStats() void
        + toString() String
        + equals(Object) boolean
        + hashCode() int
        + static main(String[]) void
    }

    CarDriver --|> Person : extends
    CarDriver ..|> Driver : implements
    CarOwner --|> Person : extends
    CarCleaner --|> Person : extends
    Mechanic --|> Person : extends

    Car *-- Driver : composes (0..1)
    Car *-- CarOwner : composes (0..1)
    Car *-- CarCleaner : composes (0..1)
    Car *-- Mechanic : composes (0..1)
    Car *-- ServiceRecord : aggregates (0..*)
    Car *-- FuelType : references
    Car *-- ServiceStatus : references

    CarDriver ..> Car : uses
    CarCleaner ..> Car : uses
    Mechanic ..> Car : uses

    note for Car "22 OOP concepts - see Car.java"
    note for Person "Abstract class + shadowing"
    note for Driver "Interface inside class"
    note for ServiceRecord "Record (Java 14+)"
```

---

### 1.2 Object Diagram (Runtime Snapshot)

> **Purpose:** Captures a snapshot of instantiated objects and their specific values at a point in time during `main()`, showing relationships between Car instances and their assigned roles.

```mermaid
classDiagram
    class car1 {
        brand = "Toyota"
        model = "Camry"
        year = 2024
        vin = "VIN-TO-2024"
        fuelType = PETROL
        mileage = 202.5
        totalServiceCost = 79.12
        isRunning = true
        isClean = true
        needsService = SERVICED
    }

    class alice {
        name = "Alice Johnson"
        age = 32
        licenseNumber = "LIC-A123"
        experienceYears = 10
    }

    class bob {
        name = "Bob Johnson"
        age = 35
        phoneNumber = "555-0101"
        address = "123 Main St, Springfield"
    }

    class charlie {
        name = "Charlie"
        age = 28
        shift = "Morning"
    }

    class diana {
        name = "Diana"
        age = 45
        specialization = "Engine"
    }

    class sr1 {
        date = "2026-07-22"
        mechanicName = "Diana"
        cost = 79.12
        description = "Full service + cleaning"
    }

    class car2 {
        brand = "Tesla"
        model = "Model 3"
        year = 2025
        vin = "VIN-TSLA-2025-001"
        fuelType = ELECTRIC
        mileage = 41.3
        totalServiceCost = 199.83
        isRunning = false
        isClean = true
        needsService = SERVICED
    }

    class eve {
        name = "Eve Smith"
        age = 29
        licenseNumber = "LIC-B456"
        experienceYears = 5
    }

    class frank {
        name = "Frank Smith"
        age = 33
        phoneNumber = "555-0202"
        address = "456 Oak Ave, Metropolis"
    }

    class grace {
        name = "Grace"
        age = 31
        shift = "Evening"
    }

    class henry {
        name = "Henry"
        age = 50
        specialization = "Electrical"
    }

    class sr2 {
        date = "2026-07-22"
        mechanicName = "Henry"
        cost = 199.83
        description = "Full service + cleaning"
    }

    class car3 {
        brand = "Ford"
        model = "Mustang"
        year = 2023
        vin = "VIN-FORD-2023-001"
        fuelType = PETROL
        mileage = 49.9
        totalServiceCost = 266.91
        isRunning = true
        isClean = true
        needsService = SERVICED
    }

    class isaac {
        name = "Isaac Newton"
        age = 40
        licenseNumber = "LIC-C789"
        experienceYears = 15
    }

    class judy {
        name = "Judy Newton"
        age = 38
        phoneNumber = "555-0303"
        address = "789 Pine Rd, Gotham"
    }

    class kevin {
        name = "Kevin"
        age = 25
        shift = "Morning"
    }

    class linda {
        name = "Linda"
        age = 42
        specialization = "Brakes"
    }

    class sr3 {
        date = "2026-07-22"
        mechanicName = "Linda"
        cost = 266.91
        description = "Full service + cleaning"
    }

    class CarClass {
        totalCarsProduced = 3
        totalServiceRevenue = 545.86
        MAX_MILEAGE_BEFORE_OVERHAUL = 200000.0
        DEALERSHIP_NAME = "Premium Auto Dealers"
    }

    car1 --> alice : carDriver
    car1 --> bob : carOwner
    car1 --> charlie : carCleaner
    car1 --> diana : carMechanic
    car1 --> sr1 : serviceHistory[0]

    car2 --> eve : carDriver
    car2 --> frank : carOwner
    car2 --> grace : carCleaner
    car2 --> henry : carMechanic
    car2 --> sr2 : serviceHistory[0]

    car3 --> isaac : carDriver
    car3 --> judy : carOwner
    car3 --> kevin : carCleaner
    car3 --> linda : carMechanic
    car3 --> sr3 : serviceHistory[0]
```

---

### 1.3 Component Diagram

> **Purpose:** Shows high-level software components, their interfaces, and dependencies. The Car class acts as a central orchestrator composing role components.

```mermaid
graph TB
    User[User - runs main]
    JVM[JVM - loads and executes]
    CAR[Car - Central Orchestrator]
    D[Driver interface]
    O[CarOwner - Person]
    CL[CarCleaner - Person]
    M[Mechanic - Person]
    SR[ServiceRecord - record]
    FT[FuelType - enum]
    SS[ServiceStatus - enum]
    Anon[AnonymousValetDriver]
    Local[TestDriver - local class]

    User --> CAR
    JVM --> CAR

    CAR --> D
    CAR --> O
    CAR --> CL
    CAR --> M
    CAR --> SR
    CAR --> FT
    CAR --> SS

    D -.->|drive| CAR
    CL -.->|clean| CAR
    M -.->|service| CAR

    Anon -.->|implements| D
    Local -.->|testDrive| CAR
```

---

### 1.4 Package Diagram

> **Purpose:** Shows the logical organization of the codebase into packages and how the Car class and its nested types map to the Java package structure.

```mermaid
flowchart TD
    subgraph root["com.javaprogramming"]
        subgraph basic["basic"]
            Hello[Hello.java]
            Variables[Variables.java]
            Loops[Loops.java]
            Functions[Functions.java]
            Strings[Strings.java]
            Operators[Operators.java]
            Control[ControlFlow.java]
            Others[...]
        end

        subgraph intermediate["intermediate (FOCUS)"]
            subgraph carFile["Car.java"]
                subgraph carClass["public class Car"]
                    subgraph nested["Nested Types"]
                        I[<<interface>> Driver]
                        A[<<abstract>> Person]
                        CD[CarDriver extends Person implements Driver]
                        CO[CarOwner extends Person]
                        CC[CarCleaner extends Person]
                        MC[Mechanic extends Person]
                        E1[<<enum>> FuelType]
                        E2[<<enum>> ServiceStatus]
                        R[<<record>> ServiceRecord]
                    end

                    subgraph methodLocal["Method-local Types"]
                        Anon[AnonymousValetDriver]
                        Local[TestDriver]
                    end
                end
            end
        end
    end

    intermediate -->|depends on| basic
    carFile -->|contains| carClass
    carClass -->|contains| nested
    carClass -->|contains| methodLocal
```

---

## 2. Behavioral Diagrams

### 2.1 Sequence Diagram — Full Service Flow

> **Purpose:** Shows the timeline of method calls when `car1.performFullService()` is invoked.

```mermaid
sequenceDiagram
    participant Client as main()
    participant Car1 as car1 : Car
    participant Mech as diana : Mechanic
    participant Clean as charlie : CarCleaner
    participant History as serviceHistory : List
    participant Static as Car.class (static)

    Note over Client,Static: Full Service on Toyota Camry
    Client->>Car1: car1.performFullService()
    activate Car1

    Car1->>Car1: print("--- Full Service ---")
    Car1->>Mech: carMechanic.service(car1)
    activate Mech
    Mech->>Car1: car.addServiceCost(62.58)
    Car1->>Car1: this.totalServiceCost += cost
    Car1->>Static: totalServiceRevenue += cost
    Mech->>Car1: car.setNeedsService(false)
    Car1->>Car1: needsService = SERVICED
    Mech-->>Car1: return
    deactivate Mech

    Car1->>Clean: carCleaner.clean(car1)
    activate Clean
    Clean->>Car1: car.addServiceCost(16.54)
    Car1->>Car1: totalServiceCost += 16.54
    Car1->>Static: totalServiceRevenue += 16.54
    Clean->>Car1: car.setClean(true)
    Clean-->>Car1: return
    deactivate Clean

    Car1->>Car1: addServiceRecord("2026-07-22","Diana",79.12,"Full service + cleaning")
    activate Car1
    Car1->>Car1: new ServiceRecord(...)
    Car1->>History: serviceHistory.add(record)
    deactivate Car1

    Car1-->>Client: return
    deactivate Car1

    Note over Client,Static: Output: Service Complete (Total Cost: $79.12)
```

---

### 2.2 Sequence Diagram — Driving Session

> **Purpose:** Shows the timeline when a CarDriver drives a Car via the Driver interface (polymorphism).

```mermaid
sequenceDiagram
    participant Client as main()
    participant Driver as alice : CarDriver
    participant Car1 as car1 : Car

    Note over Client,Car1: Driving Session via Driver interface (polymorphism)
    Client->>Driver: ((Driver) alice).drive(car1)
    activate Driver

    Driver->>Car1: car.isRunning()
    Car1-->>Driver: false

    Driver->>Car1: car.start()
    activate Car1
    Car1->>Car1: isRunning = true
    Car1-->>Driver: return
    deactivate Car1

    Driver->>Driver: print driving info
    Driver->>Car1: car.addMileage(random 15-50)
    activate Car1
    Car1->>Car1: this.mileage += miles
    Car1-->>Driver: return
    deactivate Car1

    Driver-->>Client: return
    deactivate Driver
```

---

### 2.3 Activity Diagram — Car Lifecycle

> **Purpose:** Maps the complete workflow of a Car's lifecycle from creation to potential overhaul.

```mermaid
graph TD
    START(Start)
    StaticInit[Static Init Block once per JVM]
    InstanceInit[Instance Init Block before constructor]
    Constructor[Car Constructor sets brand model year VIN fuelType]
    ForkRoles{Roles assigned}
    AssignRoles[Assign Driver Owner Cleaner Mechanic]
    Ready[Car Ready for Use]
    StartEngine[Start Engine]
    DriveCar[Drive Car]
    StopEngine[Stop Engine]
    NeedService{Needs Service}
    MechService[Mechanic services car]
    CleanClean[Cleaner cleans car]
    AddRecord[Add ServiceRecord]
    UpdateCost[Update totalServiceCost and revenue]
    Overhaul{isEligibleForOverhaul}
    Overhauled(Car Overhauled)
    Final(End of Life)

    START --> StaticInit
    StaticInit --> InstanceInit
    InstanceInit --> Constructor

    Constructor --> ForkRoles
    ForkRoles -- Yes --> AssignRoles
    ForkRoles -- No --> Ready
    AssignRoles --> Ready

    Ready --> StartEngine
    StartEngine --> DriveCar
    DriveCar --> StopEngine

    StopEngine --> NeedService
    NeedService -- Yes --> MechService
    NeedService -- No --> Ready

    MechService --> CleanClean
    CleanClean --> AddRecord
    AddRecord --> UpdateCost
    UpdateCost --> Ready

    DriveCar --> Overhaul
    Overhaul -- mileage >= 200000 --> Overhauled
    Overhaul -- mileage < 200000 --> DriveCar
    Overhauled --> Final
```

---

### 2.4 State Machine Diagram — Car States

> **Purpose:** Represents the various states a Car object can be in and the transitions triggered by method calls.

```mermaid
stateDiagram-v2
    [*] --> NEW : new Car()

    state NEW {
        [*] --> InitBlock : instance init block
        InitBlock --> Constructed : constructor
    }

    NEW --> IDLE : after constructor

    IDLE --> RUNNING : start()
    RUNNING --> IDLE : stop()
    RUNNING --> RUNNING : drive() -> addMileage()

    IDLE --> SERVICE_IN_PROGRESS : performFullService()

    state SERVICE_IN_PROGRESS {
        [*] --> MECH_CHECK
        MECH_CHECK --> MECH_SERVICE : mechanic != null
        MECH_CHECK --> [*] : mechanic == null
        MECH_SERVICE --> CLEAN_CLEAN
        CLEAN_CLEAN --> RECORDING
        RECORDING --> [*]
    }

    SERVICE_IN_PROGRESS --> IDLE : service complete

    IDLE --> OVERHAUL_ELIGIBLE : isEligibleForOverhaul()
    OVERHAUL_ELIGIBLE --> [*] : car retired
```

---

### 2.5 Use Case Diagram — Dealership System

> **Purpose:** Defines functional requirements from the perspective of different actors interacting with the Car system.

```mermaid
flowchart LR
    subgraph Actors["Actors"]
        Dealer(Dealer / Owner)
        DriverUser(Driver)
        MechanicUser(Mechanic)
        CleanerUser(Cleaner)
        Customer(Customer)
        JavaRuntime(Java Runtime)
    end

    subgraph System["Car Dealership Management System"]
        UC1(UC1: Create Car)
        UC2(UC2: Assign Roles)
        UC3(UC3: Drive Car)
        UC4(UC4: Service Car)
        UC5(UC5: Clean Car)
        UC6(UC6: Check Status)
        UC7(UC7: View Dealership Stats)
        UC8(UC8: Test Drive)
        UC9(UC9: Valet Park)
    end

    Dealer --> UC1
    Dealer --> UC2
    Dealer --> UC7

    DriverUser --> UC3

    MechanicUser --> UC4

    CleanerUser --> UC5

    Customer --> UC6
    Customer --> UC8

    JavaRuntime --> UC1

    UC3 -.-> UC6
    UC4 -.-> UC5
    UC4 -.-> UC2
```

---

## 3. Concept-to-Diagram Mapping

This table maps each of the 22 OOP concepts to the specific diagrams where they are visually represented:

| # | Concept | Class | Object | Component | Package | Sequence | Activity | State Machine | Use Case |
|---|---------|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **Class** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| 2i | **Default Constructor** | ✅ |  |  |  |  | ✅ | ✅ |  |
| 2ii | **No-Arg Constructor** | ✅ |  |  |  |  |  |  |  |
| 2iii | **Parameterized Constructor** | ✅ | ✅ |  |  |  | ✅ | ✅ |  |
| 2iv | **Constructor Overloading** | ✅ |  |  |  |  |  |  |  |
| 2v | **Constructor Chaining** |  |  |  |  |  | ✅ | ✅ |  |
| 3i | **Instance variables** | ✅ | ✅ |  |  |  |  | ✅ |  |
| 3ii | **Static variables** | ✅ | ✅ |  |  | ✅ | ✅ |  |  |
| 3iii | **final variable** | ✅ | ✅ |  |  |  |  |  |  |
| 4 | **Getters & Setters** | ✅ |  |  |  |  |  |  |  |
| 5i | **Instance methods** | ✅ |  |  |  | ✅ | ✅ | ✅ | ✅ |
| 5ii | **Static methods** | ✅ | ✅ |  |  | ✅ |  |  | ✅ |
| 5iii | **Method Overloading** | ✅ |  |  |  |  |  |  |  |
| 5iv | **Method Overriding** | ✅ |  |  |  | ✅ |  |  |  |
| 5v | **final method** | ✅ |  |  |  |  | ✅ | ✅ |  |
| 6i | **Instance Init Block** |  |  |  |  |  | ✅ | ✅ |  |
| 6ii | **Static Init Block** | ✅ | ✅ |  |  | ✅ | ✅ |  |  |
| 7 | **Interface inside class** | ✅ |  | ✅ | ✅ | ✅ |  |  |  |
| 8 | **Nested class** | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |  |  |
| 9 | **Abstract class** | ✅ |  | ✅ | ✅ |  |  |  |  |
| 10 | **this keyword** |  |  |  |  |  | ✅ |  |  |
| 11 | **super keyword** | ✅ |  |  |  |  |  |  |  |
| 12 | **instanceof operator** |  |  |  |  |  | ✅ | ✅ |  |
| 13 | **Encapsulation** | ✅ | ✅ | ✅ |  |  |  |  |  |
| 14 | **Polymorphism** | ✅ |  | ✅ |  | ✅ |  |  |  |
| 15 | **Composition** | ✅ | ✅ | ✅ |  |  | ✅ |  |  |
| 16 | **Anonymous class** |  |  | ✅ | ✅ |  |  |  |  |
| 17 | **Local class** |  |  | ✅ | ✅ |  |  |  |  |
| 18 | **Variable shadowing** |  |  |  |  |  |  |  |  |
| 19 | **var keyword** |  |  |  |  |  |  |  |  |
| 20 | **Record** | ✅ | ✅ | ✅ | ✅ | ✅ |  |  |  |
| 21 | **Enum** | ✅ | ✅ | ✅ | ✅ |  |  | ✅ |  |
| 22 | **Object overrides** | ✅ |  |  |  |  |  |  |  |

---

## Files Summary

| File | Size | Contents |
|------|------|----------|
| `Car.java` | 858 lines | Full implementation with 22 OOP concepts |
| `CarLowLevelDesign.md` | ~700 lines | 9 UML diagrams (this file) |

> **To render diagrams:** Open this file with VS Code's built-in Markdown preview, or use a Mermaid extension. All diagrams use standard Mermaid syntax compatible with GitHub, GitLab, and most markdown renderers.