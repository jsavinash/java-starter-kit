# OOPS Concepts
```mermaid

/**
 * ==========================================================
 * SCENARIO: OOPS Concepts notes
 * ==========================================================
 *
 * Concepts covered:
 *  1)  Class :- User-defined blueprint or template used to create objects that share common properties and behaviors
 *  2)  Constructor :- Block of code similar to a method that is automatically invoked when an instance (object) of a class is created using the new keyword
 *      i)   Default Constructor :- if no constructor defined, Java compiler automatically creates a no-argument default constructor at compile time.
 *      ii)  No-Arg Constructor :- A constructor that accepts zero parameters.
 *      iii) Parameterized Constructor :- A constructor that accepts a specific number of parameters.
 *      iv)  Constructor Overloading :- A technique that allows a class to have more than one constructor, provided they all have different parameter lists
 *      v)   Constructor Chaining (this()) :- A process of one constructor calling another constructor within the same class.
 *  3)  Member variables
 *      i)   Instance variable :- Each object created from the class gets its own independent copy of this variable.
 *      ii)  Class Variable :- Only one copy exists, which is shared across all instances of the class.
 *      iii) final variable (constant) :- class-level variable whose value cannot be changed or reassigned once it has been initialized. It acts as a constant within the class or object.
 *  4)  Getters and setters :- public methods used to access and modify the hidden, private variables of a class.
 *  5)  Methods :- Block of code containing a series of statements that only runs when it is called
 *      i)   Instance methods :- a method that belongs to an object (instance) of a class, rather than to the class itself. To use an instance method, you must first create an object using the new keyword
 *      ii)  Static methods :- belongs to the class itself rather than to any specific object (instance) of that class. You can call a static method directly without ever creating an object using the new keyword.
 *      iii) Method overloading :-  A feature in Java that allows a class to have more than one method with the same name, as long as their parameter lists are different
 *      iv)  Method overriding :-  is a feature in Java that allows a subclass to provide a specific implementation of a method that is already provided by its superclass (parent class).
 *      v)   final method :- a method in Java that cannot be overridden by any subclass. Once a method is declared as final in a parent class, its implementation is permanently locked.
 *  6)  Initialization block :- a nameless block of code enclosed in curly braces {} inside a class. It executes automatically to set up or initialize variables when a class is loaded or when an object is created.
 *      i)   Normal block :- Allow you to initialize non static member variable.
 *      ii)  Static block :- Allow you to initialize static member variable.
 *  7)  Nested interface (or member interface) :- It is used to logically group an interface within the class that uses it, making your code cleaner and more organized.
 *  8)  Nested class (inner class) :-
 *        i) Inner Class (Non-Static): Is bound to a specific instance of the outer class. It can directly access all variables and methods of the outer class, even private ones.
 *        ii) Static Nested Class: Acts like a normal top-level class that is simply packaged inside another class for convenience. It cannot access non-static members of the outer class directly.
 *  9)  Abstract class :- a restricted class that cannot be used to create objects directly.
 *  10) this keyword :- A reference variable that refers directly to the current object executing the method or constructor.
 *  11) super keyword :- A reference variable used to refer directly to an object's immediate parent class
 *  12) instanceof operator :- A binary operator in Java used to test whether an object is an instance of a specific class, subclass, or interface.
 *  13) Anonymous class : inner class that does not have a name
 *  14) Local class (class inside a method) : A nested class declared entirely inside a block of code, which is typically the body of a method. It can also be placed inside a constructor, a loop, or an if statement.
 *  15) Variable shadowing :- happens when a variable declared within a specific scope (like a method or a block) has the exact same name as a variable declared in an outer scope (like a class).
 *  16) var keyword (local variable type inference) :- allows for local variable type inference. This means you can omit the explicit data type (like String, int, or ArrayList) when declaring a variable, and Java will automatically figure out the correct data type based on the value you assign to it.
 *  17) Record (Java 16) :- A record in Java (introduced as a permanent feature in Java 16) is a special, restricted type of class designed purely to act as a transparent data carrier. It provides a compact syntax to declare shallowly immutable data classes, completely eliminating the standard boilerplate code like getters, toString(), equals(), and hashCode()
 *  18) Enum :- An enum (short for enumeration) in Java is a special data type used to define a collection of fixed, named constants.
 *  19) Object class overrides (toString, equals, hashCode) :- In Java, the Object class is the ultimate ancestor of all classes. Every class implicitly extends Object. It provides several core methods designed to be overridden to customize how your objects behave when compared, printed, or managed in memory.The three most frequently overridden methods are toString(), equals(), and hashCode()
 *  20) Object :- :-  self-contained unit of data and behavior that represents a real-world entity. It is an instance of a class.
 *  21) Inheritance :- A mechanism in Java that allows one class to acquire the properties (fields) and behaviors (methods) of another class.
 *  22) Polymorphism :- Polymorphism is an object-oriented programming concept that allows an entity (such as a method or an object) to take on multiple forms.
 *    i) Compile-Time Polymorphism (Static Binding) :- This occurs when the compiler determines exactly which method to execute during compilation. It is achieved through Method Overloading.
 *    ii) Runtime Polymorphism (Dynamic Binding) :- This occurs when the JVM determines which method to execute at runtime based on the actual object type, not the reference type. It is achieved through Method Overriding and Upcasting.
 *  23) Abstraction :- hiding internal, complex execution details from the user and only exposing the essential features.
 *  24) Encapsulation :- wrapping data (variables) and the code that acts on that data (methods) together as a single unit.
 *  25) Coupling :- Coupling refers to the degree of direct knowledge, dependency, or connectivity between two or more classes in a software system
 *      i) Tight Coupling :- One class dependent on other concrete class.
 *      ii) Loose Coupling :- when classes interact through abstractions (interfaces) rather than concrete classes.
 *  26) Cohesion :- Cohesion refers to how focused and single-minded a single class, method, or module is in what it does. It measures how closely related and unified the responsibilities inside a single piece of code are
 *      i) Low Cohesion :- Low cohesion occurs when a single class is cluttered with completely unrelated tasks. This is often called a "God Class" or "Blob Class."
 *      ii) High Cohesion :- High cohesion splits those distinct responsibilities into their own dedicated, tightly focused classes.
 *  27) Association :- Association is a structural relationship in Java that defines how two completely separate classes interact or connect with each other. It represents an "HAS-A" relationship through their objects (e.g., a Doctor has-a Patient).
 *      i)  Aggregation (Weak Association): Both objects can exist independently.
 *   ii) Composition (Strong Association): The child object cannot exist without the parent object.
 */
