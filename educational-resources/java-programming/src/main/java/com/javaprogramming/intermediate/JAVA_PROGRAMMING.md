# OOPS Concepts — with Examples

> Each concept from `OOPS.md` preserved with its original definition + independent code example

---

## 1) Class
**Definition:** User-defined blueprint or template used to create objects that share common properties and behaviors

```java
// Class is a blueprint
class Student {
    String name;
    int age;

    void study() {
        System.out.println(name + " is studying");
    }
}

// Creating objects from the class
Student s1 = new Student();
s1.name = "Alice";
s1.study();  // Output: Alice is studying
```

---

## 2) Constructor
**Definition:** Block of code similar to a method that is automatically invoked when an instance (object) of a class is created using the new keyword

### i) Default Constructor
**Definition:** If no constructor defined, Java compiler automatically creates a no-argument default constructor at compile time.

```java
class Employee {
    String name;
    int id;
    // No constructor defined — Java provides default constructor
}

Employee e = new Employee();  // Default constructor invoked
e.name = "John";              // Fields initialized to defaults (null, 0)
```

### ii) No-Arg Constructor
**Definition:** A constructor that accepts zero parameters.

```java
class Employee {
    String name;

    // No-Arg Constructor
    Employee() {
        name = "Unknown";
    }
}

Employee e = new Employee();  // name = "Unknown"
```

### iii) Parameterized Constructor
**Definition:** A constructor that accepts a specific number of parameters.

```java
class Employee {
    String name;
    int id;

    // Parameterized Constructor
    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

Employee e = new Employee("Alice", 101);  // name = "Alice", id = 101
```

### iv) Constructor Overloading
**Definition:** A technique that allows a class to have more than one constructor, provided they all have different parameter lists

```java
class Rectangle {
    int length, breadth;

    Rectangle() {                    // No-arg
        length = breadth = 0;
    }

    Rectangle(int side) {            // One param — square
        length = breadth = side;
    }

    Rectangle(int l, int b) {        // Two params
        length = l;
        breadth = b;
    }
}

Rectangle r1 = new Rectangle();       // 0x0
Rectangle r2 = new Rectangle(5);      // 5x5
Rectangle r3 = new Rectangle(4, 6);   // 4x6
```

### v) Constructor Chaining (this())
**Definition:** A process of one constructor calling another constructor within the same class.

```java
class Book {
    String title;
    String author;
    double price;

    Book() {
        this("Untitled", "Unknown", 0.0);  // Chaining to 3-param constructor
    }

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}

Book b = new Book();  // title = "Untitled", author = "Unknown", price = 0.0
```

---

## 3) Member variables

### i) Instance variable
**Definition:** Each object created from the class gets its own independent copy of this variable.

```java
class Counter {
    int count = 0;  // Instance variable — each object has its own copy

    void increment() {
        count++;
    }
}

Counter c1 = new Counter();
Counter c2 = new Counter();
c1.increment();  // c1.count = 1
c2.increment();  // c2.count = 1 (independent of c1)
```

### ii) Class Variable (Static)
**Definition:** Only one copy exists, which is shared across all instances of the class.

```java
class Counter {
    static int totalCount = 0;  // Class variable — shared across all objects
    int instanceCount = 0;

    void increment() {
        instanceCount++;
        totalCount++;
    }
}

Counter c1 = new Counter();
Counter c2 = new Counter();
c1.increment();  // c1.instanceCount=1, totalCount=1
c2.increment();  // c2.instanceCount=1, totalCount=2 (shared)
```

### iii) final variable (constant)
**Definition:** Class-level variable whose value cannot be changed or reassigned once it has been initialized. It acts as a constant within the class or object.

```java
class Circle {
    final double PI = 3.14159;  // final — cannot be changed
    final int id;                // blank final — assigned in constructor
    static int counter = 0;

    Circle() {
        id = ++counter;  // final assigned once in constructor
    }
}

Circle c = new Circle();
// c.PI = 3.14;       // COMPILE ERROR: cannot reassign final
// c.id = 5;          // COMPILE ERROR: cannot reassign final
```

---

## 4) Getters and setters
**Definition:** Public methods used to access and modify the hidden, private variables of a class.

```java
class BankAccount {
    private double balance;  // private — hidden

    // Getter — read access
    public double getBalance() {
        return balance;
    }

    // Setter — write access with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

BankAccount acc = new BankAccount();
acc.deposit(1000);                 // Controlled write
System.out.println(acc.getBalance());  // Controlled read: 1000.0
// acc.balance = 5000;             // COMPILE ERROR: private
```

---

## 5) Methods
**Definition:** Block of code containing a series of statements that only runs when it is called

### i) Instance methods
**Definition:** A method that belongs to an object (instance) of a class, rather than to the class itself. To use an instance method, you must first create an object using the new keyword

```java
class Calculator {
    // Instance method — needs object
    int add(int a, int b) {
        return a + b;
    }
}

Calculator calc = new Calculator();  // Create object first
int result = calc.add(5, 3);         // Call instance method: 8
```

### ii) Static methods
**Definition:** Belongs to the class itself rather than to any specific object (instance) of that class. You can call a static method directly without ever creating an object using the new keyword.

```java
class MathUtils {
    // Static method — no object needed
    static int square(int x) {
        return x * x;
    }
}

int result = MathUtils.square(5);  // Called via class, not object: 25
```

### iii) Method overloading
**Definition:** A feature in Java that allows a class to have more than one method with the same name, as long as their parameter lists are different

```java
class Printer {
    void print(String s) {
        System.out.println("String: " + s);
    }

    void print(int i) {
        System.out.println("Integer: " + i);
    }

    void print(String s, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(s);
        }
    }
}

Printer p = new Printer();
p.print("Hello");       // String: Hello
p.print(42);            // Integer: 42
p.print("Hi", 3);       // Hi (3 times)
```

### iv) Method overriding
**Definition:** A feature in Java that allows a subclass to provide a specific implementation of a method that is already provided by its superclass (parent class).

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {  // Overriding parent's method
        System.out.println("Dog barks");
    }
}

Animal a = new Dog();
a.sound();  // Output: Dog barks (runtime polymorphism)
```

### v) final method
**Definition:** A method in Java that cannot be overridden by any subclass. Once a method is declared as final in a parent class, its implementation is permanently locked.

```java
class Parent {
    final void show() {
        System.out.println("This cannot be changed");
    }
}

class Child extends Parent {
    // void show() { }  // COMPILE ERROR: cannot override final method
}
```

---

## 6) Initialization block
**Definition:** A nameless block of code enclosed in curly braces {} inside a class. It executes automatically to set up or initialize variables when a class is loaded or when an object is created.

### i) Normal block
**Definition:** Allow you to initialize non static member variable.

```java
class Demo {
    int x;

    {  // Instance initialization block — runs before constructor
        x = 10;
        System.out.println("Init block: x = " + x);
    }

    Demo() {
        System.out.println("Constructor called");
    }
}

Demo d = new Demo();
// Output:
// Init block: x = 10
// Constructor called
```

### ii) Static block
**Definition:** Allow you to initialize static member variable.

```java
class Database {
    static String connection;

    static {  // Static initialization block — runs once when class loads
        connection = "Connected to DB";
        System.out.println("Static block executed");
    }
}

// Output when class is first loaded:
// Static block executed
```

---

## 7) Nested interface (or member interface)
**Definition:** It is used to logically group an interface within the class that uses it, making your code cleaner and more organized.

```java
class Computer {
    // Nested interface
    interface USB {
        void connect();
    }
}

class Mouse implements Computer.USB {
    public void connect() {
        System.out.println("Mouse connected via USB");
    }
}

Computer.USB device = new Mouse();
device.connect();  // Output: Mouse connected via USB
```

---

## 8) Nested class (inner class)

### i) Inner Class (Non-Static)
**Definition:** Is bound to a specific instance of the outer class. It can directly access all variables and methods of the outer class, even private ones.

```java
class Outer {
    private String message = "Hello from Outer";

    class Inner {  // Non-static inner class
        void display() {
            System.out.println(message);  // Accessing private outer field
        }
    }
}

Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();  // Requires outer instance
inner.display();  // Output: Hello from Outer
```

### ii) Static Nested Class
**Definition:** Acts like a normal top-level class that is simply packaged inside another class for convenience. It cannot access non-static members of the outer class directly.

```java
class Outer {
    static String staticMsg = "Static";
    String instanceMsg = "Instance";

    static class StaticNested {  // Static nested class
        void display() {
            System.out.println(staticMsg);  // Can access static members
            // System.out.println(instanceMsg);  // COMPILE ERROR
        }
    }
}

Outer.StaticNested nested = new Outer.StaticNested();  // No outer instance needed
nested.display();  // Output: Static
```

---

## 9) Abstract class
**Definition:** A restricted class that cannot be used to create objects directly.

```java
abstract class Shape {
    abstract double area();  // Abstract method — no body

    void display() {         // Concrete method — has body
        System.out.println("Area: " + area());
    }
}

class Circle extends Shape {
    double radius;

    Circle(double r) { radius = r; }

    @Override
    double area() {  // Must implement abstract method
        return 3.14 * radius * radius;
    }
}

// Shape s = new Shape();  // COMPILE ERROR: cannot instantiate abstract class
Shape s = new Circle(5);
s.display();  // Output: Area: 78.5
```

---

## 10) this keyword
**Definition:** A reference variable that refers directly to the current object executing the method or constructor.

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;  // this.name = field, name = parameter
    }

    void introduce() {
        System.out.println("Hi, I'm " + this.name);
    }

    Person getCurrent() {
        return this;  // Returns current object reference
    }
}

Person p = new Person("Alice");
p.introduce();       // Output: Hi, I'm Alice
System.out.println(p == p.getCurrent());  // Output: true
```

---

## 11) super keyword
**Definition:** A reference variable used to refer directly to an object's immediate parent class

```java
class Parent {
    String message = "Parent message";

    Parent() {
        System.out.println("Parent constructor");
    }

    void show() {
        System.out.println("Parent show()");
    }
}

class Child extends Parent {
    String message = "Child message";

    Child() {
        super();  // Calls Parent constructor — added implicitly if not written
        System.out.println("Child constructor");
    }

    void display() {
        System.out.println(super.message);  // Access parent's field
        super.show();                       // Call parent's method
    }
}

Child c = new Child();
c.display();
// Output:
// Parent constructor
// Child constructor
// Parent message
// Parent show()
```

---

## 12) instanceof operator
**Definition:** A binary operator in Java used to test whether an object is an instance of a specific class, subclass, or interface.

```java
class Animal {}
class Dog extends Animal {}

Animal a = new Dog();
System.out.println(a instanceof Dog);     // true
System.out.println(a instanceof Animal);  // true
System.out.println(a instanceof String);  // false

// Pattern matching (Java 16+)
if (a instanceof Dog d) {
    System.out.println("It's a Dog!");  // d is already cast
}
```

---

## 13) Anonymous class
**Definition:** Inner class that does not have a name

```java
interface Greeting {
    void sayHello();
}

// Anonymous class implementing interface
Greeting g = new Greeting() {
    @Override
    public void sayHello() {
        System.out.println("Hello from anonymous class!");
    }
};

g.sayHello();  // Output: Hello from anonymous class!
```

---

## 14) Local class (class inside a method)
**Definition:** A nested class declared entirely inside a block of code, which is typically the body of a method. It can also be placed inside a constructor, a loop, or an if statement.

```java
class Outer {
    void display() {
        // Local class inside a method
        class Local {
            String name;

            Local(String name) {
                this.name = name;
            }

            void greet() {
                System.out.println("Hello from " + name);
            }
        }

        Local local = new Local("LocalClass");
        local.greet();  // Output: Hello from LocalClass
    }
}

new Outer().display();
```

---

## 15) Variable shadowing
**Definition:** Happens when a variable declared within a specific scope (like a method or a block) has the exact same name as a variable declared in an outer scope (like a class).

```java
class ShadowDemo {
    int x = 10;  // Class-level variable

    void method(int x) {  // Parameter shadows class variable
        System.out.println(x);       // Prints parameter: 20
        System.out.println(this.x);  // Prints class variable: 10

        int x = 30;  // Local variable shadows parameter
        // (This would cause compile error in Java — duplicate local)
    }
}

// Correct example:
class ShadowDemo2 {
    int value = 100;

    void setValue(int value) {  // Parameter shadows field
        this.value = value;     // this.value resolves to field
    }
}
```

---

## 16) var keyword (local variable type inference)
**Definition:** Allows for local variable type inference. This means you can omit the explicit data type (like String, int, or ArrayList) when declaring a variable, and Java will automatically figure out the correct data type based on the value you assign to it.

```java
// Before var — explicit types
String name = "Alice";
int age = 30;
java.util.List<String> list = new java.util.ArrayList<>();

// After var — type inference (Java 10+)
var name = "Alice";           // Inferred as String
var age = 30;                 // Inferred as int
var list = new java.util.ArrayList<String>();  // Inferred as ArrayList<String>

// var cannot be used for fields, method params, or return types
// var x;  // COMPILE ERROR: must initialize
```

---

## 17) Record (Java 16)
**Definition:** A record in Java (introduced as a permanent feature in Java 16) is a special, restricted type of class designed purely to act as a transparent data carrier. It provides a compact syntax to declare shallowly immutable data classes, completely eliminating the standard boilerplate code like getters, toString(), equals(), and hashCode()

```java
// Record — compact immutable data carrier
record Point(int x, int y) {
    // Compact constructor with validation
    Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be positive");
        }
    }
}

Point p1 = new Point(3, 4);
Point p2 = new Point(3, 4);

System.out.println(p1.x());          // Getter: 3
System.out.println(p1);              // toString: Point[x=3, y=4]
System.out.println(p1.equals(p2));   // true (value equality)
System.out.println(p1.hashCode() == p2.hashCode());  // true
// p1.x = 5;  // COMPILE ERROR: fields are final
```

---

## 18) Enum
**Definition:** An enum (short for enumeration) in Java is a special data type used to define a collection of fixed, named constants.

```java
// Simple enum
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Enum with fields, constructor, and methods
enum Status {
    PENDING("Waiting"),
    APPROVED("Confirmed"),
    REJECTED("Declined");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

Day today = Day.MONDAY;
System.out.println(today);                    // MONDAY

Status s = Status.APPROVED;
System.out.println(s.getDisplayName());       // Confirmed

// Iterate all values
for (Day d : Day.values()) {
    System.out.println(d + " (ordinal: " + d.ordinal() + ")");
}
```

---

## 19) Object class overrides (toString, equals, hashCode)
**Definition:** In Java, the Object class is the ultimate ancestor of all classes. Every class implicitly extends Object. It provides several core methods designed to be overridden to customize how your objects behave when compared, printed, or managed in memory. The three most frequently overridden methods are toString(), equals(), and hashCode()

```java
import java.util.Objects;

class Student {
    String name;
    int rollNo;

    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNo=" + rollNo + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return rollNo == s.rollNo && name.equals(s.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rollNo);
    }
}

Student s1 = new Student("Alice", 101);
Student s2 = new Student("Alice", 101);

System.out.println(s1.toString());           // Student{name='Alice', rollNo=101}
System.out.println(s1.equals(s2));           // true (value equality)
System.out.println(s1.hashCode() == s2.hashCode());  // true (contract maintained)
```

---

## 20) Object
**Definition:** Self-contained unit of data and behavior that represents a real-world entity. It is an instance of a class.

```java
// Class definition
class Laptop {
    String brand;
    int ram;

    void display() {
        System.out.println(brand + " with " + ram + "GB RAM");
    }
}

// Objects — instances of the class
Laptop laptop1 = new Laptop();  // Object 1
laptop1.brand = "Dell";
laptop1.ram = 16;

Laptop laptop2 = new Laptop();  // Object 2 (separate, independent)
laptop2.brand = "Apple";
laptop2.ram = 8;

laptop1.display();  // Dell with 16GB RAM
laptop2.display();  // Apple with 8GB RAM
```

---

## 21) Inheritance
**Definition:** A mechanism in Java that allows one class to acquire the properties (fields) and behaviors (methods) of another class.

```java
class Vehicle {          // Parent class
    String brand;

    void start() {
        System.out.println(brand + " vehicle starting");
    }
}

class Car extends Vehicle {  // Child class inherits from Vehicle
    int doors;

    void honk() {
        System.out.println(brand + " car honking");
    }
}

Car car = new Car();
car.brand = "Toyota";    // Inherited field
car.doors = 4;           // Own field
car.start();             // Inherited method: Toyota vehicle starting
car.honk();              // Own method: Toyota car honking
```

---

## 22) Polymorphism
**Definition:** Polymorphism is an object-oriented programming concept that allows an entity (such as a method or an object) to take on multiple forms.

### i) Compile-Time Polymorphism (Static Binding)
**Definition:** This occurs when the compiler determines exactly which method to execute during compilation. It is achieved through Method Overloading.

```java
class MathOperations {
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {  // Overloaded — different params
        return a + b + c;
    }
}

MathOperations m = new MathOperations();
System.out.println(m.add(2, 3));      // Compiler picks 2-param version: 5
System.out.println(m.add(2, 3, 4));   // Compiler picks 3-param version: 9
```

### ii) Runtime Polymorphism (Dynamic Binding)
**Definition:** This occurs when the JVM determines which method to execute at runtime based on the actual object type, not the reference type. It is achieved through Method Overriding and Upcasting.

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

Animal a = new Cat();  // Upcasting — reference type = Animal, object type = Cat
a.sound();             // JVM calls Cat's sound() at runtime: Cat meows
```

---

## 23) Abstraction
**Definition:** Hiding internal, complex execution details from the user and only exposing the essential features.

```java
// Abstract class — hides implementation details
abstract class Database {
    abstract void connect();   // What — not how
    abstract void execute(String query);

    // Common method with implementation
    void runQuery(String query) {
        connect();
        execute(query);
        System.out.println("Query completed");
    }
}

class MySQL extends Database {
    void connect() {
        System.out.println("Connecting to MySQL...");
    }

    void execute(String query) {
        System.out.println("Executing: " + query);
    }
}

Database db = new MySQL();
db.runQuery("SELECT * FROM users");
// User only calls runQuery() — connect/execute details are hidden
```

---

## 24) Encapsulation
**Definition:** Wrapping data (variables) and the code that acts on that data (methods) together as a single unit.

```java
class Temperature {
    private double celsius;  // Data hidden

    public void setFahrenheit(double f) {
        celsius = (f - 32) * 5 / 9;  // Conversion logic wrapped
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return celsius * 9 / 5 + 32;
    }
}

Temperature t = new Temperature();
t.setFahrenheit(98.6);                    // Data + behavior bundled
System.out.println(t.getCelsius());       // 37.0
// System.out.println(t.celsius);         // COMPILE ERROR: private
```

---

## 25) Coupling
**Definition:** Coupling refers to the degree of direct knowledge, dependency, or connectivity between two or more classes in a software system

### i) Tight Coupling
**Definition:** One class dependent on other concrete class.

```java
class EmailService {
    void sendEmail(String msg) {
        System.out.println("Sending: " + msg);
    }
}

class Notification {
    EmailService email = new EmailService();  // Tightly coupled to EmailService

    void send(String msg) {
        email.sendEmail(msg);  // Direct dependency on concrete class
    }
}
// Problem: Cannot easily switch to SMS or Push notification
```

### ii) Loose Coupling
**Definition:** When classes interact through abstractions (interfaces) rather than concrete classes.

```java
interface MessageService {
    void send(String msg);
}

class EmailService implements MessageService {
    public void send(String msg) {
        System.out.println("Email: " + msg);
    }
}

class SMSService implements MessageService {
    public void send(String msg) {
        System.out.println("SMS: " + msg);
    }
}

class Notification {
    private MessageService service;  // Loosely coupled — depends on interface

    Notification(MessageService service) {
        this.service = service;
    }

    void send(String msg) {
        service.send(msg);  // Works with any MessageService implementation
    }
}

Notification n1 = new Notification(new EmailService());
Notification n2 = new Notification(new SMSService());
n1.send("Hello");  // Email: Hello
n2.send("Hello");  // SMS: Hello
```

---

## 26) Cohesion
**Definition:** Cohesion refers to how focused and single-minded a single class, method, or module is in what it does. It measures how closely related and unified the responsibilities inside a single piece of code are

### i) Low Cohesion
**Definition:** Low cohesion occurs when a single class is cluttered with completely unrelated tasks. This is often called a "God Class" or "Blob Class."

```java
class Utility {  // Low cohesion — unrelated tasks in one class
    void printDocument(String doc) { /* ... */ }
    void calculateTax(int salary) { /* ... */ }
    void sendEmail(String to) { /* ... */ }
    void connectDatabase() { /* ... */ }
    void playMusic(String song) { /* ... */ }
}
// Problem: Hard to maintain, test, and understand
```

### ii) High Cohesion
**Definition:** High cohesion splits those distinct responsibilities into their own dedicated, tightly focused classes.

```java
class Printer {           // High cohesion — focused on printing
    void print(String doc) { /* ... */ }
}

class TaxCalculator {     // High cohesion — focused on tax
    double calculate(int salary) { return salary * 0.1; }
}

class EmailSender {       // High cohesion — focused on email
    void send(String to, String msg) { /* ... */ }
}
// Benefit: Easy to maintain, test, and reuse
```

---

## 27) Association
**Definition:** Association is a structural relationship in Java that defines how two completely separate classes interact or connect with each other. It represents an "HAS-A" relationship through their objects (e.g., a Doctor has-a Patient).

### i) Aggregation (Weak Association)
**Definition:** Both objects can exist independently.

```java
class Student {
    String name;
    Student(String name) { this.name = name; }
}

class School {
    Student[] students;  // School has Students

    School(Student[] students) {
        this.students = students;  // Students exist independently
    }
}

Student s1 = new Student("Alice");
Student s2 = new Student("Bob");
School school = new School(new Student[]{s1, s2});
// Students continue to exist even if School is destroyed
```

### ii) Composition (Strong Association)
**Definition:** The child object cannot exist without the parent object.

```java
class Room {
    String type;
    Room(String type) { this.type = type; }
}

class House {
    private Room livingRoom;   // House owns Room
    private Room bedroom;

    House() {
        livingRoom = new Room("Living");  // Rooms created with House
        bedroom = new Room("Bedroom");
    }
    // Rooms are destroyed when House is destroyed
}

House house = new House();
// Rooms cannot exist independently outside the House
```

---

---

## 28) Java Array
**Definition:** An array in Java is a container object that holds a fixed number of values of a single type. The length of an array is established when the array is created.

```java
// Declaration and initialization
int[] numbers = new int[5];          // Array of 5 integers
numbers[0] = 10;
numbers[1] = 20;

int[] scores = {90, 85, 88, 92};     // Array literal

// Accessing and iterating
System.out.println(scores[0]);        // First element: 90
System.out.println("Length: " + scores.length);  // 4

for (int i = 0; i < scores.length; i++) {
    System.out.print(scores[i] + " ");  // 90 85 88 92
}

// Enhanced for-loop
for (int score : scores) {
    System.out.print(score + " ");
}

// Multi-dimensional array
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
System.out.println(matrix[1][2]);  // 6
```

---

## 29) Exception Handling
**Definition:** Exception handling in Java is a mechanism to handle runtime errors so that the normal flow of the application can be maintained. The core framework uses try, catch, finally, throw, and throws keywords.

```java
// try-catch-finally block
try {
    int result = 10 / 0;  // ArithmeticException
    System.out.println(result);
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    System.out.println("This always executes");
}

// Multiple catch blocks
try {
    int[] arr = new int[3];
    arr[5] = 100;  // ArrayIndexOutOfBoundsException
} catch (ArithmeticException e) {
    System.out.println("Arithmetic error");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index out of bounds");
} catch (Exception e) {
    System.out.println("General exception: " + e);
}

// throw keyword — manually throwing exception
void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be 18 or above");
    }
}

// throws keyword — declaring exception
void readFile(String path) throws java.io.FileNotFoundException {
    java.io.FileReader fr = new java.io.FileReader(path);
}

// Custom exception
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String msg) { super(msg); }
}
```

---

## 30) Java Inner Class
**Definition:** A Java inner class is a class that is defined inside another class. Inner classes can access the private members of the outer class and help in logically grouping classes.

```java
class Outer {
    private int data = 100;

    // Member Inner Class
    class MemberInner {
        void display() {
            System.out.println("Data: " + data);  // Accessing private outer field
        }
    }

    // Method-Local Inner Class
    void show() {
        class LocalInner {
            void print() {
                System.out.println("Inside local inner class");
            }
        }
        LocalInner local = new LocalInner();
        local.print();
    }

    // Static Nested Class
    static class StaticNested {
        void display() {
            System.out.println("Inside static nested class");
        }
    }
}

// Usage
Outer outer = new Outer();
Outer.MemberInner inner = outer.new MemberInner();
inner.display();        // Data: 100

Outer.StaticNested nested = new Outer.StaticNested();
nested.display();       // Inside static nested class

outer.show();           // Inside local inner class
```

---

## 31) Java Multithreading
**Definition:** Multithreading in Java is a process of executing multiple threads simultaneously to maximize CPU utilization. A thread is a lightweight sub-process.

```java
// Method 1: Extending Thread class
class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(500);  // 500ms delay
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Method 2: Implementing Runnable interface
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Running: " + Thread.currentThread().getName());
    }
}

// Usage
MyThread t1 = new MyThread();
MyThread t2 = new MyThread();
t1.setName("Thread-1");
t2.setName("Thread-2");
t1.start();  // Starts thread — calls run()
t2.start();

// Using Runnable
Thread t3 = new Thread(new MyRunnable(), "Runnable-Thread");
t3.start();

// Thread lifecycle states: NEW → RUNNABLE → RUNNING → BLOCKED/WAITING → TERMINATED
System.out.println("State: " + t1.getState());  // RUNNABLE, TIMED_WAITING, TERMINATED, etc.
```

---

## 32) Java Synchronization
**Definition:** Synchronization in Java is a mechanism that ensures that only one thread can access a shared resource at a time, preventing thread interference and memory consistency errors.

```java
// Synchronized method
class Counter {
    private int count = 0;

    public synchronized void increment() {  // Only one thread at a time
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// Synchronized block (more granular)
class BankAccount {
    private int balance = 1000;

    void withdraw(int amount) {
        synchronized (this) {  // Synchronized block on current object
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
                balance -= amount;
                System.out.println("Remaining balance: " + balance);
            } else {
                System.out.println("Insufficient balance");
            }
        }
    }
}

// Static synchronization — class-level lock
class SharedResource {
    private static int counter = 0;

    public static synchronized void staticIncrement() {
        counter++;
    }
}

// Usage with threads
Counter counter = new Counter();
Thread t1 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });
Thread t2 = new Thread(() -> { for (int i = 0; i < 1000; i++) counter.increment(); });
t1.start();
t2.start();
t1.join();  // Wait for thread to finish
t2.join();
System.out.println("Final count: " + counter.getCount());  // Always 2000
```

---

## 33) Java I/O
**Definition:** Java I/O (Input/Output) is used to process input and produce output. Java uses streams to perform I/O operations, with byte streams for binary data and character streams for text data.

```java
import java.io.*;

// Byte Stream — reading/writing binary data
void byteStreamExample() throws IOException {
    // Writing bytes to file
    FileOutputStream fos = new FileOutputStream("data.bin");
    fos.write(65);  // Writes 'A'
    fos.write(66);  // Writes 'B'
    fos.close();

    // Reading bytes from file
    FileInputStream fis = new FileInputStream("data.bin");
    int data;
    while ((data = fis.read()) != -1) {
        System.out.print((char) data);  // AB
    }
    fis.close();
}

// Character Stream — reading/writing text
void charStreamExample() throws IOException {
    // Writing text
    FileWriter fw = new FileWriter("notes.txt");
    fw.write("Hello, Java I/O!");
    fw.close();

    // Reading text
    FileReader fr = new FileReader("notes.txt");
    int data;
    while ((data = fr.read()) != -1) {
        System.out.print((char) data);  // Hello, Java I/O!
    }
    fr.close();
}

// Buffered Stream — efficient reading/writing
void bufferedStreamExample() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("notes.txt"));
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
    br.close();

    BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
    bw.write("Buffered writing is efficient");
    bw.newLine();
    bw.close();
}
```

---

## 34) File Handling in Java
**Definition:** File handling in Java refers to reading from and writing data to files. The java.io.File class represents file and directory pathnames, while FileReader/FileWriter and FileInputStream/FileOutputStream handle file content.

```java
import java.io.File;
import java.io.IOException;

// File class operations
void fileOperations() {
    File file = new File("example.txt");

    // Creating a file
    try {
        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists");
        }
    } catch (IOException e) {
        System.out.println("Error: " + e);
    }

    // File information
    System.out.println("Name: " + file.getName());
    System.out.println("Path: " + file.getAbsolutePath());
    System.out.println("Writable: " + file.canWrite());
    System.out.println("Readable: " + file.canRead());
    System.out.println("Size: " + file.length() + " bytes");

    // Directory operations
    File dir = new File("myDirectory");
    if (dir.mkdir()) {
        System.out.println("Directory created");
    }

    // List files in directory
    File currentDir = new File(".");
    String[] files = currentDir.list();
    for (String f : files) {
        System.out.println(f);
    }

    // Deleting a file
    if (file.delete()) {
        System.out.println("Deleted: " + file.getName());
    }

    // Try-with-resources — auto-closes resources
    try (java.io.FileWriter fw = new java.io.FileWriter("autoClose.txt")) {
        fw.write("Resource auto-closed");
    } catch (IOException e) {
        System.out.println(e);
    }
}
```

---

## 35) Java Serialization
**Definition:** Serialization in Java is a mechanism of converting the state of an object into a byte stream. Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory.

```java
import java.io.*;

// Step 1: Make the class Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;  // Version control

    String name;
    int id;
    transient double salary;  // transient — not serialized

    Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    void display() {
        System.out.println(name + " " + id + " " + salary);
    }
}

// Serialization — object to byte stream
void serializeExample() {
    try {
        Employee emp = new Employee("Alice", 101, 50000);
        FileOutputStream fos = new FileOutputStream("employee.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(emp);  // Serializing
        oos.close();
        fos.close();
        System.out.println("Object serialized");
    } catch (IOException e) {
        System.out.println(e);
    }
}

// Deserialization — byte stream back to object
void deserializeExample() {
    try {
        FileInputStream fis = new FileInputStream("employee.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Employee emp = (Employee) ois.readObject();  // Deserializing
        emp.display();  // Alice 101 0.0 (salary is 0 because transient)
        ois.close();
        fis.close();
    } catch (IOException | ClassNotFoundException e) {
        System.out.println(e);
    }
}

// Key points:
// - transient fields: not serialized (default values)
// - static fields: not serialized (belong to class)
// - serialVersionUID: ensures same class version during deserialization
```

---

## 36) Java Networking
**Definition:** Java networking provides classes to develop network applications. The java.net package contains classes for networking, including Socket, ServerSocket, URL, URLConnection, and InetAddress.

```java
import java.net.*;

// InetAddress — represents IP address
void inetAddressExample() throws UnknownHostException {
    InetAddress address = InetAddress.getByName("www.google.com");
    System.out.println("Host: " + address.getHostName());
    System.out.println("IP: " + address.getHostAddress());

    InetAddress local = InetAddress.getLocalHost();
    System.out.println("Local: " + local.getHostAddress());
}

// URL class — represents a Uniform Resource Locator
void urlExample() throws MalformedURLException {
    URL url = new URL("https://api.example.com:8080/data?name=test#section");
    System.out.println("Protocol: " + url.getProtocol());    // https
    System.out.println("Host: " + url.getHost());            // api.example.com
    System.out.println("Port: " + url.getPort());            // 8080
    System.out.println("Path: " + url.getPath());            // /data
    System.out.println("Query: " + url.getQuery());          // name=test
}

// TCP Socket — Client-Server communication
// Server side
void serverExample() throws IOException {
    ServerSocket serverSocket = new ServerSocket(8080);
    System.out.println("Server waiting on port 8080...");
    Socket socket = serverSocket.accept();  // Blocks until client connects
    java.io.DataInputStream dis = new java.io.DataInputStream(socket.getInputStream());
    String msg = dis.readUTF();
    System.out.println("Received: " + msg);
    socket.close();
    serverSocket.close();
}

// Client side
void clientExample() throws IOException {
    Socket socket = new Socket("localhost", 8080);
    java.io.DataOutputStream dos = new java.io.DataOutputStream(socket.getOutputStream());
    dos.writeUTF("Hello Server!");
    dos.close();
    socket.close();
}

// URLConnection — for HTTP connections
void urlConnectionExample() throws IOException {
    URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    int responseCode = conn.getResponseCode();
    System.out.println("Response: " + responseCode);

    java.io.BufferedReader br = new java.io.BufferedReader(
        new java.io.InputStreamReader(conn.getInputStream())
    );
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
    br.close();
    conn.disconnect();
}
```

---

## 37) Java Reflection
**Definition:** Reflection in Java is an API that allows a program to inspect and modify its own structure and behavior at runtime. It can examine classes, interfaces, fields, methods, and constructors at runtime.

```java
import java.lang.reflect.*;

class Sample {
    private String message = "Secret";

    public Sample() {}

    public Sample(String msg) { this.message = msg; }

    public void show() {
        System.out.println("Message: " + message);
    }

    private void hiddenMethod() {
        System.out.println("This is private");
    }
}

void reflectionExample() throws Exception {
    // Getting Class object
    Class<?> cls = Class.forName("Sample");  // Using fully qualified name
    // or: Class<?> cls = Sample.class;
    // or: Class<?> cls = new Sample().getClass();

    System.out.println("Class: " + cls.getName());

    // Inspecting constructors
    Constructor<?>[] constructors = cls.getConstructors();
    for (Constructor<?> c : constructors) {
        System.out.println("Constructor: " + c);
    }

    // Creating instance via reflection
    Object obj = cls.getDeclaredConstructor().newInstance();

    // Inspecting methods
    Method[] methods = cls.getMethods();
    for (Method m : methods) {
        System.out.println("Method: " + m.getName());
    }

    // Invoking public method
    Method showMethod = cls.getMethod("show");
    showMethod.invoke(obj);  // Message: Secret

    // Invoking private method (need to set accessible)
    Method hidden = cls.getDeclaredMethod("hiddenMethod");
    hidden.setAccessible(true);  // Bypass private access
    hidden.invoke(obj);          // This is private

    // Accessing private field
    Field field = cls.getDeclaredField("message");
    field.setAccessible(true);
    System.out.println("Field value: " + field.get(obj));  // Secret
    field.set(obj, "Modified");
    showMethod.invoke(obj);  // Message: Modified
}
```

---

## 38) Java Collections
**Definition:** The Java Collections Framework provides a unified architecture for storing and manipulating groups of objects. It includes interfaces like List, Set, Queue, Map, and their implementations.

```java
import java.util.*;

// List — ordered, allows duplicates
void listExample() {
    List<String> list = new ArrayList<>();
    list.add("Apple");
    list.add("Banana");
    list.add("Apple");  // Duplicates allowed
    list.add(1, "Orange");  // Insert at index

    System.out.println("List: " + list);           // [Apple, Orange, Banana, Apple]
    System.out.println("Get index 1: " + list.get(1));  // Orange
    System.out.println("Size: " + list.size());    // 4

    for (String s : list) { System.out.print(s + " "); }

    // LinkedList — faster insertions/deletions
    List<Integer> linkedList = new LinkedList<>();
    linkedList.add(10);
    linkedList.add(20);
}

// Set — no duplicates, unordered
void setExample() {
    Set<String> set = new HashSet<>();
    set.add("Apple");
    set.add("Banana");
    set.add("Apple");  // Duplicate — ignored
    System.out.println("Set: " + set);              // [Apple, Banana]
    System.out.println("Contains Apple? " + set.contains("Apple"));  // true

    // TreeSet — sorted order
    Set<Integer> sorted = new TreeSet<>();
    sorted.add(30);
    sorted.add(10);
    sorted.add(20);
    System.out.println("Sorted: " + sorted);       // [10, 20, 30]

    // LinkedHashSet — insertion order preserved
    Set<String> linkedSet = new LinkedHashSet<>();
    linkedSet.add("C");
    linkedSet.add("A");
    linkedSet.add("B");
    System.out.println("LinkedSet: " + linkedSet);  // [C, A, B] (insertion order)
}

// Queue — FIFO (First-In-First-Out)
void queueExample() {
    Queue<String> queue = new LinkedList<>();
    queue.offer("First");     // Add element
    queue.offer("Second");
    queue.offer("Third");

    System.out.println("Queue: " + queue);           // [First, Second, Third]
    System.out.println("Peek: " + queue.peek());     // First (without removing)
    System.out.println("Poll: " + queue.poll());     // First (removes and returns)
    System.out.println("After poll: " + queue);      // [Second, Third]

    // PriorityQueue — elements ordered by priority
    Queue<Integer> pq = new PriorityQueue<>();
    pq.offer(30);
    pq.offer(10);
    pq.offer(20);
    System.out.println("Priority poll: " + pq.poll());  // 10 (smallest first)
}

// Map — key-value pairs
void mapExample() {
    Map<String, Integer> map = new HashMap<>();
    map.put("Alice", 25);
    map.put("Bob", 30);
    map.put("Charlie", 35);

    System.out.println("Map: " + map);               // {Alice=25, Bob=30, Charlie=35}
    System.out.println("Get Alice: " + map.get("Alice"));  // 25
    System.out.println("Contains key Bob? " + map.containsKey("Bob"));  // true

    // Iterating map
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
        System.out.println(entry.getKey() + " = " + entry.getValue());
    }

    // TreeMap — sorted by keys
    Map<String, Integer> sortedMap = new TreeMap<>();
    sortedMap.put("Zebra", 1);
    sortedMap.put("Apple", 2);
    System.out.println("Sorted Map: " + sortedMap);  // {Apple=2, Zebra=1}

    // LinkedHashMap — insertion order preserved
    Map<String, Integer> linkedMap = new LinkedHashMap<>();
    linkedMap.put("C", 3);
    linkedMap.put("A", 1);
    linkedMap.put("B", 2);
    System.out.println("Linked Map: " + linkedMap);  // {C=3, A=1, B=2}
}

// Collections utility methods
void collectionsUtility() {
    List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));

    Collections.sort(list);          // [1, 2, 5, 8, 9]
    Collections.reverse(list);       // [9, 8, 5, 2, 1]
    Collections.shuffle(list);       // Random order
    Collections.max(list);           // 9
    Collections.min(list);           // 1
    Collections.frequency(list, 5);  // Count occurrences of 5

    // Unmodifiable collections
    List<Integer> immutable = Collections.unmodifiableList(list);
    // immutable.add(10);  // UnsupportedOperationException
}
```

---

## 39) Java Strings (String, StringBuilder, StringBuffer)
**Definition:** String is an immutable sequence of characters in Java. StringBuilder and StringBuffer are mutable sequences — StringBuilder is non-synchronized (faster), StringBuffer is synchronized (thread-safe).

```java
// String — immutable
String s1 = "Hello";                     // String literal — stored in String Pool
String s2 = new String("Hello");         // Creates new object in heap
String s3 = "Hello";                     // Reuses from String Pool

System.out.println(s1 == s3);            // true (same reference from pool)
System.out.println(s1 == s2);            // false (different references)

// String methods
System.out.println(s1.length());         // 5
System.out.println(s1.charAt(1));        // 'e'
System.out.println(s1.substring(1, 4));  // "ell"
System.out.println(s1.toUpperCase());    // "HELLO"
System.out.println(s1.indexOf('l'));     // 2
System.out.println(s1.replace('l', 'p'));// "Heppo"

// StringBuilder — mutable, not thread-safe, faster
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");                     // "Hello World"
sb.insert(5, " Java");                   // "Hello Java World"
sb.reverse();                            // "dlroW avaJ olleH"
sb.delete(0, 6);                         // "avaJ olleH"
System.out.println(sb.toString());

// StringBuffer — mutable, thread-safe (synchronized), slower
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World");
System.out.println(sbf.toString());      // "Hello World"

// Key differences:
// String: immutable, stored in String pool, use when no changes
// StringBuilder: mutable, not synchronized, use in single-thread
// StringBuffer: mutable, synchronized, use in multi-thread

// String Pool — intern() method
String a = new String("Java").intern();  // Forces pool reference
String b = "Java";
System.out.println(a == b);              // true (both from pool)
```

---

## 40) Generics
**Definition:** Generics enable types (classes, interfaces, methods) to be parameterized, providing stronger type checks at compile time and eliminating the need for explicit casting.

```java
// Generic class
class Box<T> {
    private T value;

    void set(T value) { this.value = value; }
    T get() { return value; }
}

Box<String> stringBox = new Box<>();
stringBox.set("Hello");
String val = stringBox.get();  // No casting needed

Box<Integer> intBox = new Box<>();
intBox.set(100);
int num = intBox.get();

// Generic method
<T> T echo(T item) {
    return item;
}

String s = echo("Hello");     // Type inferred as String
Integer i = echo(123);        // Type inferred as Integer

// Bounded type parameters
class NumberBox<T extends Number> {  // T must be Number or subclass
    private T value;
}

NumberBox<Integer> n1 = new NumberBox<>();  // OK
NumberBox<Double> n2 = new NumberBox<>();   // OK

// Wildcard — ?
void printList(List<?> list) {               // Unbounded wildcard
    for (Object obj : list) System.out.print(obj);
}

void processNumbers(List<? extends Number> list) {  // Upper bounded
    for (Number n : list) System.out.print(n);
}

void addIntegers(List<? super Integer> list) {      // Lower bounded
    list.add(10);  // Can add Integer
}

// Type erasure: Generics are compile-time only
// List<String> and List<Integer> both become List at runtime
```

---

## 41) Annotations
**Definition:** Annotations are metadata attached to Java code (classes, methods, fields) that provide information to the compiler, runtime, or frameworks.

```java
import java.lang.annotation.*;

// Built-in annotations
@Override  // Indicates method overrides superclass method
@Deprecated  // Marks element as no longer recommended
@SuppressWarnings("unchecked")  // Suppresses compiler warnings

// Custom annotation definition
@Retention(RetentionPolicy.RUNTIME)   // Available at runtime
@Target({ElementType.METHOD, ElementType.FIELD})  // Applied to methods and fields
@interface Author {
    String name();
    String date() default "unknown";
    int version() default 1;
}

// Using custom annotation
class MyClass {
    @Author(name = "Alice", date = "2024-01-15", version = 2)
    public void myMethod() {
        System.out.println("Annotated method");
    }

    @Author(name = "Bob")
    private String data;
}

// Reading annotations via reflection
void readAnnotation() throws Exception {
    Method method = MyClass.class.getMethod("myMethod");

    if (method.isAnnotationPresent(Author.class)) {
        Author author = method.getAnnotation(Author.class);
        System.out.println("Author: " + author.name());      // Alice
        System.out.println("Date: " + author.date());        // 2024-01-15
        System.out.println("Version: " + author.version());  // 2
    }
}

// Retention policies:
// SOURCE — discarded by compiler (e.g., @Override)
// CLASS — stored in .class file, not available at runtime (default)
// RUNTIME — available at runtime via reflection (e.g., custom annotations)
```

---

## 42) Lambda Expressions & Functional Interfaces
**Definition:** Lambda expressions provide a concise way to implement functional interfaces (interfaces with a single abstract method). Introduced in Java 8.

```java
import java.util.function.*;

// Functional interface — exactly one abstract method
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}

// Before lambda — anonymous class
Calculator add = new Calculator() {
    @Override
    public int operate(int a, int b) {
        return a + b;
    }
};

// After lambda
Calculator addLambda = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(addLambda.operate(5, 3));     // 8
System.out.println(multiply.operate(5, 3));      // 15

// Built-in functional interfaces (java.util.function package)

// 1. Predicate<T> — returns boolean
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4));              // true

// 2. Function<T, R> — transforms input to output
Function<String, Integer> lengthFunc = s -> s.length();
System.out.println(lengthFunc.apply("Hello"));   // 5

// 3. Consumer<T> — consumes input, no return
Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello");                         // Hello

// 4. Supplier<T> — supplies value, no input
Supplier<Double> random = () -> Math.random();
System.out.println(random.get());

// 5. BinaryOperator<T> — two inputs, same type output
BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
System.out.println(max.apply(10, 20));           // 20

// Method reference — shorthand for lambda
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(System.out::println);              // Method reference
names.sort(String::compareToIgnoreCase);
```

---

## 43) Streams API
**Definition:** The Stream API (Java 8) provides a functional approach to process sequences of elements using declarative pipeline operations.

```java
import java.util.*;
import java.util.stream.*;

List<String> list = Arrays.asList("Apple", "Banana", "Cherry", "Date");

// Creating streams
Stream<String> stream = list.stream();
Stream<Integer> fromValues = Stream.of(1, 2, 3);

// Intermediate operations (return Stream — lazy)
// Terminal operations (return result — trigger execution)

// Filter — select elements by condition
List<String> filtered = list.stream()
    .filter(s -> s.startsWith("A") || s.startsWith("C"))
    .collect(Collectors.toList());
System.out.println(filtered);  // [Apple, Cherry]

// Map — transform elements
List<Integer> lengths = list.stream()
    .map(String::length)
    .collect(Collectors.toList());
System.out.println(lengths);  // [5, 6, 6, 4]

// FlatMap — flatten nested collections
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6)
);
List<Integer> flat = nested.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());
System.out.println(flat);  // [1, 2, 3, 4, 5, 6]

// Sorted
List<String> sorted = list.stream().sorted().collect(Collectors.toList());

// Distinct — remove duplicates
List<Integer> distinct = Arrays.asList(1, 2, 2, 3, 3, 3).stream()
    .distinct().collect(Collectors.toList());
System.out.println(distinct);  // [1, 2, 3]

// Limit & Skip — pagination
List<Integer> paginated = Stream.of(1,2,3,4,5,6,7,8,9,10)
    .skip(3).limit(4).collect(Collectors.toList());
System.out.println(paginated);  // [4, 5, 6, 7]

// Terminal operations
list.stream().forEach(System.out::println);

String joined = list.stream().collect(Collectors.joining(", "));
// "Apple, Banana, Cherry, Date"

// Reduce — combine elements into single result
int sum = Stream.of(1, 2, 3, 4, 5)
    .reduce(0, Integer::sum);          // 15

long count = list.stream().count();                    // 4
Optional<String> first = list.stream().findFirst();    // Apple
boolean anyMatch = list.stream().anyMatch(s -> s.length() > 5);  // true

// Grouping By
Map<Integer, List<String>> grouped = list.stream()
    .collect(Collectors.groupingBy(String::length));
// {4=[Date], 5=[Apple], 6=[Banana, Cherry]}

// Parallel stream — uses multiple threads
List<Integer> parallelResult = Stream.of(1,2,3,4,5,6,7,8,9,10)
    .parallel()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

---

## 44) Optional Class
**Definition:** Optional is a container object (Java 8) that may or may not contain a non-null value, helping avoid NullPointerException.

```java
import java.util.Optional;

// Creating Optional
Optional<String> empty = Optional.empty();                    // Empty
Optional<String> nonEmpty = Optional.of("Hello");             // Must be non-null
Optional<String> nullable = Optional.ofNullable(getValue());  // Can be null

// Checking presence
if (nonEmpty.isPresent()) {
    System.out.println(nonEmpty.get());  // "Hello"
}

nonEmpty.ifPresent(val -> System.out.println(val));  // "Hello"

// Default values
String result1 = nullable.orElse("Default");               // Returns default
String result2 = nullable.orElseGet(() -> compute());       // Lazy evaluation
String result3 = nullable.orElseThrow(RuntimeException::new); // Throw

// Mapping
Optional<String> upper = nonEmpty.map(String::toUpperCase);
System.out.println(upper.get());  // "HELLO"

Optional<String> filtered = nonEmpty.filter(s -> s.length() > 3);

// FlatMap — avoid nested Optional
Optional<Optional<String>> nested = Optional.of(Optional.of("Hi"));
Optional<String> flat = nested.flatMap(opt -> opt);
System.out.println(flat.get());  // "Hi"

// Chaining example
class User {
    String email;
    User(String email) { this.email = email; }
    String getEmail() { return email; }
}

User user = new User("alice@example.com");
String email = Optional.ofNullable(user)
    .map(User::getEmail)
    .filter(e -> e.contains("@"))
    .orElse("unknown@example.com");
System.out.println(email);  // alice@example.com
```

---

## 45) Date & Time API (Java 8+)
**Definition:** The java.time package provides comprehensive, immutable, thread-safe classes for date and time operations.

```java
import java.time.*;
import java.time.format.*;

// LocalDate — date only
LocalDate today = LocalDate.now();
LocalDate specificDate = LocalDate.of(2024, Month.JANUARY, 15);
LocalDate parsedDate = LocalDate.parse("2024-01-15");

System.out.println(specificDate.getDayOfWeek());        // MONDAY
System.out.println(specificDate.plusDays(10));          // 2024-01-25
System.out.println(specificDate.minusMonths(1));        // 2023-12-15
System.out.println(specificDate.isLeapYear());          // true

// LocalTime — time only
LocalTime now = LocalTime.now();
LocalTime specificTime = LocalTime.of(14, 30, 0);       // 2:30 PM
System.out.println(specificTime.plusHours(2));          // 16:30

// LocalDateTime — date and time
LocalDateTime dateTime = LocalDateTime.now();
LocalDateTime specificDT = LocalDateTime.of(2024, 1, 15, 14, 30);

// ZonedDateTime — with timezone
ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

// Duration — time-based
Duration duration = Duration.ofHours(2);
LocalTime later = specificTime.plus(duration);

// Period — date-based
Period period = Period.ofDays(10);
LocalDate futureDate = specificDate.plus(period);

// Formatting
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String formatted = specificDate.format(formatter);
System.out.println(formatted);                          // 15/01/2024
LocalDate parsed = LocalDate.parse("15/01/2024", formatter);

// TemporalAdjusters
LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth());
LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

// Instant — machine-readable timestamp
Instant timestamp = Instant.now();
```

---

## 46) Design Patterns (Singleton, Factory, Builder)
**Definition:** Reusable solutions to common software design problems.

```java
// ──────── Singleton Pattern ────────
// Bill Pugh Singleton (best approach)
class BillPughSingleton {
    private BillPughSingleton() {}
    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}

BillPughSingleton s1 = BillPughSingleton.getInstance();
BillPughSingleton s2 = BillPughSingleton.getInstance();
System.out.println(s1 == s2);  // true

// ──────── Factory Pattern ────────
interface Vehicle {
    void drive();
}

class CarVehicle implements Vehicle {
    public void drive() { System.out.println("Driving car"); }
}

class Bike implements Vehicle {
    public void drive() { System.out.println("Riding bike"); }
}

class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car": return new CarVehicle();
            case "bike": return new Bike();
            default: throw new IllegalArgumentException("Unknown: " + type);
        }
    }
}

Vehicle car = VehicleFactory.createVehicle("car");
Vehicle bike = VehicleFactory.createVehicle("bike");
car.drive();   // Driving car
bike.drive();  // Riding bike

// ──────── Builder Pattern ────────
class Pizza {
    private String size;
    private boolean cheese;
    private boolean pepperoni;

    private Pizza(PizzaBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
    }

    static class PizzaBuilder {
        private String size;
        private boolean cheese;
        private boolean pepperoni;

        PizzaBuilder(String size) { this.size = size; }
        PizzaBuilder cheese(boolean v) { this.cheese = v; return this; }
        PizzaBuilder pepperoni(boolean v) { this.pepperoni = v; return this; }
        Pizza build() { return new Pizza(this); }
    }
}

Pizza pizza = new Pizza.PizzaBuilder("Large")
    .cheese(true)
    .pepperoni(true)
    .build();
```

---

## 47) JVM Architecture & Garbage Collection
**Definition:** JVM is the runtime engine that runs Java bytecode. GC is automatic memory management that reclaims memory from unused objects.

```java
// ──────── JVM Memory Areas ────────
// Method Area: class metadata, static variables, constants (shared)
// Heap: all objects and arrays (shared) — where GC operates
// Stack: method calls, local variables, references (per thread)
// PC Registers: current executing instruction (per thread)
// Native Method Stack: native method calls (per thread)

// ──────── Heap Generations ────────
// Young Generation: new objects (Eden, S0, S1) — Minor GC
// Old Generation: long-living objects — Major GC
// Metaspace (Java 8+): class metadata (replaced PermGen)

// ──────── GC Algorithms ────────
// Serial GC: single thread, small apps (-XX:+UseSerialGC)
// Parallel GC: multiple threads, throughput oriented (default)
// G1 GC: default since Java 9, balanced throughput and pause
// ZGC: Java 11+, ultra-low pause, scalable

// ──────── GC Roots ────────
// 1. Active threads
// 2. Static variables
// 3. Local variables in stack frames
// 4. JNI references

// ──────── Object Eligibility for GC ────────
class GarbageDemo {
    String name;
    GarbageDemo(String name) { this.name = name; }
}

void gcDemo() {
    GarbageDemo obj1 = new GarbageDemo("Obj1");
    GarbageDemo obj2 = new GarbageDemo("Obj2");

    obj1 = null;      // Eligible for GC
    obj2 = null;      // Eligible for GC

    System.gc();      // Suggests JVM to run GC (not guaranteed)
    Runtime.getRuntime().gc();  // Same as System.gc()
}

// ──────── Reference Types ────────
import java.lang.ref.*;

void referenceDemo() {
    String strong = new String("Strong");                    // Default — not GC'd
    SoftReference<String> soft = new SoftReference<>(new String("Soft"));  // GC'd before OOM
    WeakReference<String> weak = new WeakReference<>(new String("Weak"));  // GC'd on next GC
    ReferenceQueue<String> queue = new ReferenceQueue<>();
    PhantomReference<String> phantom = new PhantomReference<>(new String("Phantom"), queue);

    System.out.println(strong);     // Strong
    System.out.println(soft.get()); // May be null
    System.out.println(weak.get()); // May be null
    System.out.println(phantom.get()); // Always null
}
```

---

## 48) Comparable vs Comparator
**Definition:** Comparable defines natural ordering (compareTo) within the class. Comparator defines external custom ordering (compare) without modifying the class.

```java
import java.util.*;

// ──────── Comparable (natural ordering) ────────
class Student implements Comparable<Student> {
    String name;
    int rollNo;
    double marks;

    Student(String name, int rollNo, double marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.rollNo, other.rollNo);  // By rollNo ascending
    }

    @Override
    public String toString() {
        return name + "(" + rollNo + ", " + marks + ")";
    }
}

void comparatorDemo() {
    List<Student> students = Arrays.asList(
        new Student("Alice", 103, 85.5),
        new Student("Bob", 101, 92.0),
        new Student("Charlie", 102, 78.5)
    );

    // Using Comparable (natural: by rollNo)
    Collections.sort(students);
    System.out.println(students);  // Bob(101), Charlie(102), Alice(103)

    // Using Comparator — by name (lambda)
    Collections.sort(students, (s1, s2) -> s1.name.compareTo(s2.name));
    System.out.println(students);  // Alice(103), Bob(101), Charlie(102)

    // Using Comparator.comparing (Java 8+)
    Comparator<Student> byMarks = Comparator.comparingDouble(s -> s.marks);
    Collections.sort(students, byMarks.reversed());  // Descending marks

    // Chaining comparators
    Comparator<Student> byNameThenMarks = Comparator
        .comparing((Student s) -> s.name)
        .thenComparingDouble(s -> s.marks);
    Collections.sort(students, byNameThenMarks);
}

// Key differences:
// Comparable: java.lang, compareTo(T o), this vs other, one ordering
// Comparator: java.util, compare(T o1, T o2), two objects, multiple orderings
```

---

## 49) Immutable Class
**Definition:** An immutable class cannot be modified after creation. All fields are final and set via constructor with defensive copies for mutable fields.

```java
import java.util.*;

// Rules:
// 1. Declare class as final (prevent subclassing)
// 2. Make all fields private and final
// 3. No setter methods
// 4. Initialize all fields via constructor (deep copy)
// 5. Return defensive copies for mutable fields

final class ImmutableEmployee {
    private final int id;
    private final String name;
    private final List<String> skills;
    private final Date joiningDate;

    // Deep copy in constructor for mutable fields
    ImmutableEmployee(int id, String name, List<String> skills, Date joiningDate) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>(skills);           // Defensive copy
        this.joiningDate = new Date(joiningDate.getTime());  // Defensive copy
    }

    public int getId() { return id; }
    public String getName() { return name; }

    // Return defensive copy for mutable fields
    public List<String> getSkills() {
        return new ArrayList<>(skills);  // Returns copy, not original
    }

    public Date getJoiningDate() {
        return new Date(joiningDate.getTime());  // Returns copy
    }

    @Override
    public String toString() {
        return name + "(" + id + ") skills=" + skills;
    }
}

ImmutableEmployee emp = new ImmutableEmployee(101, "Alice",
    Arrays.asList("Java", "Spring"), new Date());

// emp.id = 102;               // COMPILE ERROR: final field
// emp.getName() = "Bob";      // COMPILE ERROR: no setter

List<String> skills = emp.getSkills();
skills.add("Python");           // Modifies copy, not original
System.out.println(emp);        // Original skills unchanged

// Benefits: thread-safe, cacheable, no side effects, easy to reason about
// Examples: String, all wrapper classes (Integer, Double), LocalDate, BigDecimal
```

---

## 50) Wrapper Classes & Autoboxing
**Definition:** Wrapper classes convert primitive types to objects. Autoboxing is automatic conversion of primitives to wrapper objects; Unboxing is the reverse.

```java
// ──────── Wrapper Classes ────────
// byte → Byte, short → Short, int → Integer, long → Long
// float → Float, double → Double, boolean → Boolean, char → Character

// Use cases:
// 1. Collections (store primitives)
// 2. Generics (type parameters must be objects)
// 3. Utility methods (parseInt, toString, compareTo)

// Primitive to Wrapper (Boxing)
int primitive = 100;
Integer wrapper = Integer.valueOf(primitive);  // Explicit boxing

// Wrapper to Primitive (Unboxing)
Integer wrapperObj = 200;
int primitiveBack = wrapperObj.intValue();     // Explicit unboxing

// Autoboxing (Java 5+) — automatic
Integer autoBoxed = 100;          // Compiler inserts Integer.valueOf(100)
int autoUnboxed = autoBoxed;      // Compiler inserts autoBoxed.intValue()

// Collections with primitives
List<Integer> numbers = new ArrayList<>();
numbers.add(10);                   // Autoboxing: int → Integer
numbers.add(20);
int sum = numbers.get(0) + numbers.get(1);  // Unboxing: Integer → int

// Null pointer danger with unboxing
Integer nullable = null;
// int val = nullable;  // NullPointerException at runtime!

// Utility methods
int parsed = Integer.parseInt("123");       // String → int
String str = Integer.toString(456);         // int → String
int max = Integer.max(10, 20);              // 20
int binary = Integer.parseInt("1010", 2);   // 10 (binary to decimal)

// Caching: Integer cache for -128 to 127
Integer a = 127;
Integer b = 127;
System.out.println(a == b);     // true (cached)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);     // false (not cached, different objects)
// Always use .equals() for wrapper comparison!
```

---

## 51) Java 8+ Features Summary
**Definition:** Java 8 introduced major functional programming features. Subsequent versions added more improvements. Key features to remember for interviews.

```java
// ═══════════ Java 8 Features ═══════════

// 1. Lambda Expressions
// 2. Streams API
// 3. Optional Class
// 4. Functional Interfaces (@FunctionalInterface)
// 5. Default & Static Methods in Interfaces
// 6. Method References (::)
// 7. Date/Time API (java.time)
// 8. CompletableFuture (async programming)
// 9. Collectors class
// 10. StringJoiner

// Default method in interface
interface Vehicle {
    void drive();

    default void honk() {                    // Default implementation
        System.out.println("Beep beep!");
    }

    static boolean isMotorized() {           // Static method in interface
        return true;
    }
}

class CarDefault implements Vehicle {
    public void drive() { System.out.println("Driving car"); }
    // honk() inherited automatically
}

// ═══════════ Java 9 Features ═══════════
// 1. Module System (JPMS)
// 2. Private methods in interfaces
// 3. Factory methods for collections: List.of(), Set.of(), Map.of()
List<String> immutableList = List.of("A", "B", "C");
Set<Integer> immutableSet = Set.of(1, 2, 3);
Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2);

// 4. try-with-resources improvement (final/effectively final)
java.io.BufferedReader br = new java.io.BufferedReader(new java.io.StringReader("test"));
try (br) {  // br declared outside try (Java 9+)
    System.out.println(br.readLine());
}

// ═══════════ Java 10 Features ═══════════
// 1. var keyword (local variable type inference)
var list = new ArrayList<String>();
var map = new HashMap<String, Integer>();

// ═══════════ Java 11 Features ═══════════
// 1. HTTP Client (standard)
// 2. Local-Variable Syntax for Lambda Parameters
// 3. String methods: isBlank(), lines(), repeat(), strip()
System.out.println("  ".isBlank());               // true
System.out.println("Hello\nWorld".lines().count()); // 2
System.out.println("Hi ".repeat(3));              // "Hi Hi Hi "
System.out.println("  Hello  ".strip());          // "Hello" (trim)

// ═══════════ Java 14-16 Features ═══════════
// 1. Records (Java 16)  — see topic 17
// 2. Pattern Matching for instanceof (Java 16) — see topic 12
// 3. Text Blocks (Java 15)
String json = """
    {
        "name": "Alice",
        "age": 30
    }
    """;

// ═══════════ Java 17+ Features ═══════════
// 1. Sealed classes — restrict which classes can extend
// 2. Pattern Matching for switch (preview)
sealed class Shape permits CircleSealed, RectangleSealed {}
final class CircleSealed extends Shape {}
final class RectangleSealed extends Shape {}
```

---

## 52) Multi-threading — Advanced Topics
**Definition:** Advanced concurrency concepts including ExecutorService, Callable, Future, CompletableFuture, and thread coordination mechanisms.

```java
import java.util.concurrent.*;

// ──────── ExecutorService — Thread Pool ────────
void executorServiceExample() {
    // Fixed thread pool
    ExecutorService executor = Executors.newFixedThreadPool(4);

    // Submit Runnable (no return value)
    executor.submit(() -> {
        System.out.println("Task executed by: " + Thread.currentThread().getName());
    });

    // Submit Callable (returns value)
    Future<Integer> future = executor.submit(() -> {
        Thread.sleep(1000);
        return 42;
    });

    try {
        Integer result = future.get(2, TimeUnit.SECONDS);  // Blocks until done
        System.out.println("Result: " + result);           // 42
    } catch (Exception e) {
        System.out.println(e);
    }

    executor.shutdown();  // Graceful shutdown (no new tasks)
}

// ──────── CompletableFuture (Java 8+) ────────
void completableFutureExample() throws Exception {
    // Run async task
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
        return "Hello";
    });

    // Chain transformations
    CompletableFuture<String> greeting = future
        .thenApply(result -> result + " World")       // Map
        .thenApply(String::toUpperCase);               // Map again

    System.out.println(greeting.get());  // "HELLO WORLD"

    // Combine two futures
    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
    CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

    int combined = future1.thenCombine(future2, Integer::sum).get();
    System.out.println(combined);  // 30

    // Handle errors
    CompletableFuture<String> safe = CompletableFuture
        .supplyAsync(() -> { throw new RuntimeException("Error"); })
        .exceptionally(ex -> "Fallback: " + ex.getMessage());
    System.out.println(safe.get());  // "Fallback: Error"

    // Wait for all to complete
    CompletableFuture.allOf(future1, future2).get();
}

// ──────── Thread Coordination ────────
void threadCoordination() throws Exception {
    // CountDownLatch — wait for N operations
    CountDownLatch latch = new CountDownLatch(3);

    for (int i = 0; i < 3; i++) {
        new Thread(() -> {
            System.out.println("Task done by " + Thread.currentThread().getName());
            latch.countDown();  // Decrement count
        }).start();
    }

    latch.await();  // Main thread waits until count reaches 0
    System.out.println("All tasks completed");

    // CyclicBarrier — threads wait for each other
    CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("All threads reached barrier");
    });

    for (int i = 0; i < 3; i++) {
        new Thread(() -> {
            try {
                System.out.println("Waiting at barrier: " + Thread.currentThread().getName());
                barrier.await();  // Wait for all 3 threads
                System.out.println("Passed barrier: " + Thread.currentThread().getName());
            } catch (Exception e) {}
        }).start();
    }

    // Semaphore — control access to limited resources
    Semaphore semaphore = new Semaphore(2);  // Allow 2 concurrent accesses

    for (int i = 0; i < 5; i++) {
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Acquired: " + Thread.currentThread().getName());
                Thread.sleep(1000);
                semaphore.release();
            } catch (Exception e) {}
        }).start();
    }
}
```

---

## 53) JDBC (Java Database Connectivity)
**Definition:** JDBC is an API that enables Java programs to connect to relational databases, execute SQL queries, and process results.

```java
import java.sql.*;

// ──────── JDBC Steps ────────
// 1. Load driver (optional for modern JDBC)
// 2. Establish connection
// 3. Create statement
// 4. Execute query
// 5. Process results
// 6. Close resources

void jdbcExample() {
    String url = "jdbc:mysql://localhost:3306/mydb";
    String user = "root";
    String password = "password";

    // try-with-resources — auto-closes Connection, Statement, ResultSet
    String selectSQL = "SELECT id, name, salary FROM employees WHERE salary > ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

        // Set parameters (prevents SQL injection)
        pstmt.setDouble(1, 50000.0);

        // Execute query
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                System.out.println(id + " | " + name + " | " + salary);
            }
        }
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    }
}

// ──────── CRUD Operations ────────

// INSERT
void insertExample() throws SQLException {
    String sql = "INSERT INTO employees (name, salary) VALUES (?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "Alice");
        pstmt.setDouble(2, 60000.0);
        int rows = pstmt.executeUpdate();
        System.out.println(rows + " row(s) inserted");
    }
}

// UPDATE
void updateExample() throws SQLException {
    String sql = "UPDATE employees SET salary = ? WHERE id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setDouble(1, 65000.0);
        pstmt.setInt(2, 101);
        pstmt.executeUpdate();
    }
}

// DELETE
void deleteExample() throws SQLException {
    String sql = "DELETE FROM employees WHERE id = ?";
    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, 101);
        pstmt.executeUpdate();
    }
}

// Transaction management
void transactionExample() throws SQLException {
    Connection conn = getConnection();
    try {
        conn.setAutoCommit(false);  // Start transaction

        // Multiple operations
        try (PreparedStatement pstmt1 = conn.prepareStatement(
                "UPDATE accounts SET balance = balance - 100 WHERE id = 1");
             PreparedStatement pstmt2 = conn.prepareStatement(
                "UPDATE accounts SET balance = balance + 100 WHERE id = 2")) {

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

            conn.commit();  // Commit if all succeed
        }
    } catch (SQLException e) {
        conn.rollback();   // Rollback on error
        throw e;
    } finally {
        conn.setAutoCommit(true);
    }
}

Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
        "root", "password");
}
```

---

## 54) Memory Management & Best Practices
**Definition:** Key memory management concepts, common pitfalls, and best practices for writing efficient Java code.

```java
// ──────── Memory Leak Causes & Fixes ────────

// 1. Unclosed resources
// BAD:
FileInputStream fis = new FileInputStream("file.txt");
// use fis...
// fis.close();  // MISSING — resource leak

// GOOD — try-with-resources:
try (FileInputStream fis = new FileInputStream("file.txt")) {
    // use fis... auto-closed
}

// 2. Static collections growing indefinitely
// BAD:
class Cache {
    static Map<String, Object> cache = new HashMap<>();  // Never cleared
}

// GOOD — use WeakHashMap or bounded cache:
class BetterCache {
    static Map<String, Object> cache = new WeakHashMap<>();  // GC can clear
}

// 3. Inner class holding outer class reference
// BAD:
class OuterLeak {
    private String data;

    class InnerLeak {
        void doSomething() {
            System.out.println(data);  // Inner holds implicit reference to Outer
        }
    }
}
// OuterLeak instance cannot be GC'd if InnerLeak is still referenced

// 4. ThreadLocal misuse
// ThreadLocal values are not GC'd until thread dies — can leak in thread pools
// Always call ThreadLocal.remove() when done

// ──────── Performance Best Practices ────────

// 1. Use StringBuilder for string concatenation in loops
// BAD:
String s = "";
for (int i = 0; i < 100; i++) {
    s += i;  // Creates 100 StringBuilder objects
}

// GOOD:
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 100; i++) {
    sb.append(i);
}
String result = sb.toString();

// 2. Use primitives instead of wrapper classes when possible
// BAD: List<Integer> (autoboxing overhead)
// GOOD: int[] for performance-critical code

// 3. Initialize collections with expected size
// BAD: new ArrayList<>()  — resizing overhead
// GOOD: new ArrayList<>(100)  — avoids resizing

// 4. Use enhanced for-loop or streams instead of indexed loop
List<String> names = Arrays.asList("A", "B", "C");
for (String name : names) { /* ... */ }  // No index overhead

// 5. Avoid creating unnecessary objects
// BAD:
Boolean b1 = new Boolean(true);  // Creates new object
// GOOD:
Boolean b2 = Boolean.valueOf(true);  // Reuses cached instance
// or: Boolean b3 = true;  // Autoboxing uses valueOf

// 6. Use EnumMap/EnumSet for enum keys
enum Color { RED, GREEN, BLUE }
Map<Color, String> enumMap = new EnumMap<>(Color.class);  // Faster than HashMap

// 7. Use try-with-resources for all Closeable resources
// 8. Override equals() and hashCode() together
// 9. Use lazy initialization for expensive objects
// 10. Avoid finalizers — use try-with-resources or Cleaner

// ──────── equals() and hashCode() Contract ────────
// 1. If a.equals(b) == true, then a.hashCode() == b.hashCode() (MUST)
// 2. If a.hashCode() == b.hashCode(), a.equals(b) may be true or false
// 3. hashCode() should use same fields as equals()
// 4. Never use mutable fields in hashCode()
```

---

## 55) Java Module System (JPMS — Java 9+)
**Definition:** The Java Platform Module System (JPMS) introduced in Java 9 allows you to group related packages into modules, specify which packages are exported and which modules are required.

```java
// ──────── module-info.java ────────
// Each module has a module-info.java file at the root

// module-info.java for com.example.myapp
/*
module com.example.myapp {
    // Declare dependencies
    requires java.sql;
    requires java.logging;
    requires com.example.utils;
    requires transitive com.example.common;  // Transitive dependency

    // Export packages for other modules
    exports com.example.myapp.api;
    exports com.example.myapp.model;

    // Restrict which modules can access exported packages
    exports com.example.myapp.internal to com.example.admin;

    // Open packages for reflection (hibernate, spring)
    opens com.example.myapp.model to org.hibernate;

    // Service loading
    uses com.example.myapp.spi.MyService;
    provides com.example.myapp.spi.MyService with com.example.myapp.MyServiceImpl;
}
*/

// Benefits of modules:
// 1. Strong encapsulation (internal packages are hidden by default)
// 2. Explicit dependencies (no more classpath hell)
// 3. Reliable configuration
// 4. Smaller runtime images (jlink)
// 5. Improved security

// Common module names:
// java.base — always required (java.lang, java.util, java.io)
// java.sql — JDBC
// java.logging — Logging
// java.desktop — AWT, Swing
// java.xml — XML processing
// java.net.http — HTTP Client (Java 11+)

// Module types:
// Named modules — have module-info.java
// Unnamed modules — traditional JARs on classpath
// Automatic modules — JARs on module path without module-info
```

---

> **Note:** Original `OOPS.md` is preserved unchanged. This file covers 55 Java topics with definitions and independent examples.
