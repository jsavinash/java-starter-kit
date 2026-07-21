# Programming Language Mastery: Java, JavaScript & Python

Learn all three languages with side-by-side comparisons and full runnable code examples.

---

## Table of Contents

1. [Hello World](#1-hello-world)
2. [Variables & Data Types](#2-variables--data-types)
3. [Operators](#3-operators)
4. [Control Flow](#4-control-flow)
5. [Loops](#5-loops)
6. [Functions](#6-functions)
7. [Strings](#7-strings)
8. [Numbers & Math](#8-numbers--math)
9. [Arrays & Lists](#9-arrays--lists)
10. [Maps & Dictionaries](#10-maps--dictionaries)
11. [Sets](#11-sets)
12. [Date & Time](#12-date--time)
13. [Regular Expressions](#13-regular-expressions)
14. [Classes & Objects](#14-classes--objects)
15. [Inheritance](#15-inheritance)
16. [Polymorphism](#16-polymorphism)
17. [Abstraction](#17-abstraction)
18. [Encapsulation](#18-encapsulation)
19. [Interfaces & Protocols](#19-interfaces--protocols)
20. [Error Handling](#20-error-handling)
21. [File I/O](#21-file-io)
22. [JSON & Serialization](#22-json--serialization)
23. [HTTP & Networking](#23-http--networking)
24. [Threads & Concurrency](#24-threads--concurrency)
25. [Async/Await](#25-asyncawait)
26. [Functional Programming](#26-functional-programming)
27. [Generics & Type Hints](#27-generics--type-hints)
28. [Enums](#28-enums)
29. [Iterators & Generators](#29-iterators--generators)
30. [Testing](#30-testing)

---

## 1. Hello World

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Print | `System.out.println("Hello");` | `console.log("Hello");` | `print("Hello")` |
| Formal Entry | `public static void main(String[] args)` | Top-level code | `if __name__ == "__main__":` |
| Comments (single) | `// comment` | `// comment` | `# comment` |
| Comments (multi) | `/* ... */` | `/* ... */` | `""" ... """` |

### Full Runnable Examples

**Java** (`Hello.java`):
```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("Welcome to Java!");
    }
}
// Compile: javac Hello.java
// Run:     java Hello
```

**JavaScript** (`hello.js`):
```javascript
console.log("Hello, World!");
console.log("Welcome to JavaScript!");

// Run: node hello.js
```

**Python** (`hello.py`):
```python
def main():
    print("Hello, World!")
    print("Welcome to Python!")

if __name__ == "__main__":
    main()

# Run: python hello.py
```

---

## 2. Variables & Data Types

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Declare | `int x = 5;` | `let x = 5;` | `x = 5` |
| Constant | `final int MAX = 10;` | `const MAX = 10;` | `MAX = 10` (convention) |
| Type Check | `x instanceof String` | `typeof x === 'string'` | `isinstance(x, str)` |
| String to Int | `Integer.parseInt("123")` | `parseInt("123")` | `int("123")` |
| Int to String | `String.valueOf(123)` | `String(123)` | `str(123)` |

### Full Runnable Examples

**Java** (`Variables.java`):
```java
public class Variables {
    public static void main(String[] args) {
        // Primitives
        int age = 25;
        double price = 19.99;
        boolean isActive = true;
        char grade = 'A';
        
        // Object types
        String name = "Alice";
        final double PI = 3.14159;
        
        // Type conversion
        String numStr = "42";
        int parsed = Integer.parseInt(numStr);
        String backToString = String.valueOf(parsed);
        
        // Type checking
        System.out.println(name instanceof String);  // true
        System.out.println(name.getClass().getName()); // java.lang.String
        
        // Output
        System.out.printf("Name: %s, Age: %d, Grade: %c%n", name, age, grade);
        System.out.println("Parsed: " + parsed + ", Back: " + backToString);
        System.out.println("PI: " + PI);
    }
}
```

**JavaScript** (`variables.js`):
```javascript
// Declaration
let age = 25;
const price = 19.99;
var isActive = true;  // function-scoped (avoid)
let name = "Alice";
const PI = 3.14159;

// Type conversion
let numStr = "42";
let parsed = parseInt(numStr);
let backToString = String(parsed);

// Type checking
console.log(typeof name === 'string');   // true
console.log(name.constructor.name);      // String

// Dynamic typing
let something = 42;
console.log(typeof something);  // number
something = "now I'm a string";
console.log(typeof something);  // string

// Output
console.log(`Name: ${name}, Age: ${age}`);
console.log(`Parsed: ${parsed}, Back: ${backToString}`);
console.log(`PI: ${PI}`);

// Null vs Undefined
let empty = null;
let notDefined;
console.log(empty, notDefined);  // null undefined
```

**Python** (`variables.py`):
```python
# Variables (no declaration keyword)
age = 25
price = 19.99
is_active = True
name = "Alice"
PI = 3.14159  # Convention for constants

# Type conversion
num_str = "42"
parsed = int(num_str)
back_to_string = str(parsed)

# Type checking
print(isinstance(name, str))   # True
print(type(name).__name__)     # str

# Dynamic typing
something = 42
print(type(something).__name__)  # int
something = "now I'm a string"
print(type(something).__name__)  # str

# None (null equivalent)
empty = None
print(empty)  # None

# Output with f-strings
print(f"Name: {name}, Age: {age}")
print(f"Parsed: {parsed}, Back: {back_to_string}")
print(f"PI: {PI}")

# Multiple assignment
x, y, z = 1, 2, 3
a = b = c = 0
```

---

## 3. Operators

### Side-by-Side Comparison

| Operation | Java | JavaScript | Python |
|-----------|------|-----------|--------|
| Arithmetic | `+ - * / % ++ --` | `+ - * / % ** ++ --` | `+ - * / % ** //` |
| Comparison | `== != > < >= <=` | `== === != !== > < >= <=` | `== != > < >= <=` |
| Logical | `&& \|\| !` | `&& \|\| !` | `and or not` |
| Ternary | `x > 0 ? "pos" : "neg"` | `x > 0 ? "pos" : "neg"` | `"pos" if x > 0 else "neg"` |
| Floor Division | N/A (cast to int) | N/A | `//` |

### Full Runnable Examples

**Java** (`Operators.java`):
```java
public class Operators {
    public static void main(String[] args) {
        // Arithmetic
        int a = 10, b = 3;
        System.out.println("a + b = " + (a + b));  // 13
        System.out.println("a - b = " + (a - b));  // 7
        System.out.println("a * b = " + (a * b));  // 30
        System.out.println("a / b = " + (a / b));  // 3 (integer)
        System.out.println("a % b = " + (a % b));  // 1
        System.out.println("Math.pow: " + Math.pow(2, 3)); // 8.0
        
        // Increment/Decrement
        int x = 5;
        System.out.println("x++ = " + (x++));  // 5 (post)
        System.out.println("++x = " + (++x));  // 7 (pre)
        
        // Comparison
        System.out.println("a == b? " + (a == b));  // false
        System.out.println("a != b? " + (a != b));  // true
        System.out.println("a > b? " + (a > b));    // true
        
        // Logical
        boolean t = true, f = false;
        System.out.println("t && f = " + (t && f));  // false
        System.out.println("t || f = " + (t || f));  // true
        System.out.println("!t = " + (!t));          // false
        
        // Ternary
        int result = (a > b) ? a : b;
        System.out.println("Max: " + result);  // 10
        
        // Bitwise
        System.out.println("5 & 3 = " + (5 & 3));   // 1
        System.out.println("5 | 3 = " + (5 | 3));   // 7
        System.out.println("5 ^ 3 = " + (5 ^ 3));   // 6
        System.out.println("5 << 1 = " + (5 << 1)); // 10
    }
}
```

**JavaScript** (`operators.js`):
```javascript
// Arithmetic
let a = 10, b = 3;
console.log("a + b =", a + b);  // 13
console.log("a - b =", a - b);  // 7
console.log("a * b =", a * b);  // 30
console.log("a / b =", a / b);  // 3.333... (always float)
console.log("a % b =", a % b);  // 1
console.log("2 ** 3 =", 2 ** 3); // 8

// Increment/Decrement
let x = 5;
console.log("x++ =", x++);  // 5
console.log("++x =", ++x);  // 7

// Comparison (loose vs strict)
console.log('5 == "5"?', 5 == "5");   // true (loose)
console.log('5 === "5"?', 5 === "5"); // false (strict)
console.log('5 != "5"?', 5 != "5");   // false
console.log('5 !== "5"?', 5 !== "5"); // true

// Logical
console.log("true && false:", true && false);  // false
console.log("true || false:", true || false);  // true
console.log("!true:", !true);                  // false

// Short-circuit (truthy/falsy)
console.log("0 || 'default':", 0 || "default");     // "default"
console.log("'hello' && 'world':", "hello" && "world"); // "world"

// Ternary
let result = a > b ? a : b;
console.log("Max:", result);  // 10

// Nullish coalescing
let value = null;
console.log("value ?? 'default':", value ?? "default");  // "default"
```

**Python** (`operators.py`):
```python
a, b = 10, 3
print("a + b =", a + b)   # 13
print("a - b =", a - b)   # 7
print("a * b =", a * b)   # 30
print("a / b =", a / b)   # 3.333... (float)
print("a // b =", a // b) # 3 (floor division)
print("a % b =", a % b)   # 1
print("2 ** 3 =", 2 ** 3) # 8

# No increment/decrement operators
x = 5
x += 1  # equivalent to x++

# Comparison
print('5 == "5"?', 5 == "5")    # False
print('5 != "5"?', 5 != "5")    # True

# Logical (use words)
print("True and False:", True and False)  # False
print("True or False:", True or False)    # True
print("not True:", not True)              # False

# Ternary
result = a if a > b else b
print("Max:", result)  # 10

# Identity vs equality
x = [1, 2]
y = [1, 2]
z = x
print("x == y:", x == y)   # True (value)
print("x is y:", x is y)   # False (reference)
print("x is z:", x is z)   # True

# Membership
print("3 in [1,2,3]:", 3 in [1, 2, 3])     # True
print("'x' not in 'hello':", 'x' not in 'hello')  # True
```

---

## 4. Control Flow

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| If-Else | `if (x > 0) { } else if (x < 0) { } else { }` | Same as Java | `if x > 0: ... elif x < 0: ... else: ...` |
| Switch | `switch(x) { case 1: break; default: }` | `switch(x) { case 1: break; default: }` | `match x: case 1: ... case _: ...` |

### Full Runnable Examples

**Java** (`ControlFlow.java`):
```java
public class ControlFlow {
    public static void main(String[] args) {
        int score = 85;
        
        // If-Else
        if (score >= 90) {
            System.out.println("Grade: A");
        } else if (score >= 80) {
            System.out.println("Grade: B");
        } else if (score >= 70) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: F");
        }
        
        // Switch (traditional)
        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Other day");
        }
        
        // Switch (enhanced - Java 14+)
        String dayType = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Invalid";
        };
        System.out.println("Day type: " + dayType);
        
        // Ternary
        String status = (score >= 60) ? "Pass" : "Fail";
        System.out.println("Status: " + status);
    }
}
```

**JavaScript** (`control-flow.js`):
```javascript
let score = 85;

// If-Else
if (score >= 90) {
    console.log("Grade: A");
} else if (score >= 80) {
    console.log("Grade: B");
} else if (score >= 70) {
    console.log("Grade: C");
} else {
    console.log("Grade: F");
}

// Switch
let day = 3;
switch (day) {
    case 1:
        console.log("Monday");
        break;
    case 2:
        console.log("Tuesday");
        break;
    case 3:
        console.log("Wednesday");
        break;
    default:
        console.log("Other day");
}

// Ternary
let status = score >= 60 ? "Pass" : "Fail";
console.log("Status:", status);

// Truthy/Falsy check
let name = "";
if (!name) {
    console.log("Name is empty");
}

// Optional chaining
let user = { address: null };
console.log(user?.address?.city ?? "No city");
```

**Python** (`control_flow.py`):
```python
score = 85

# If-Elif-Else
if score >= 90:
    print("Grade: A")
elif score >= 80:
    print("Grade: B")
elif score >= 70:
    print("Grade: C")
else:
    print("Grade: F")

# Match (Python 3.10+)
day = 3
match day:
    case 1:
        print("Monday")
    case 2:
        print("Tuesday")
    case 3:
        print("Wednesday")
    case _:
        print("Other day")

# Match with patterns
point = (2, 0)
match point:
    case (0, 0): print("Origin")
    case (x, 0): print(f"On X axis at x={x}")
    case (0, y): print(f"On Y axis at y={y}")
    case (x, y): print(f"At ({x}, {y})")

# Ternary
status = "Pass" if score >= 60 else "Fail"
print(f"Status: {status}")

# Truthiness check
name = ""
if not name:
    print("Name is empty")

# Walrus operator (assignment expression)
if (n := len([1, 2, 3, 4, 5])) > 3:
    print(f"List length {n} is greater than 3")
```

---

## 5. Loops

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| For (index) | `for(int i=0; i<5; i++)` | `for(let i=0; i<5; i++)` | `for i in range(5):` |
| For-Each | `for(String s : list)` | `for(const s of arr)` | `for s in list:` |
| While | `while(c) { }` | `while(c) { }` | `while c: ...` |
| Do-While | `do { } while(c);` | `do { } while(c);` | Not supported |

### Full Runnable Examples

**Java** (`Loops.java`):
```java
import java.util.Arrays;
import java.util.List;

public class Loops {
    public static void main(String[] args) {
        // For loop
        System.out.println("For loop:");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        // For-Each
        System.out.println("For-Each:");
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }
        
        // While loop
        System.out.println("While loop:");
        int count = 0;
        while (count < 3) {
            System.out.println("Count: " + count);
            count++;
        }
        
        // Do-While
        System.out.println("Do-While:");
        int x = 0;
        do {
            System.out.println("x = " + x);
            x++;
        } while (x < 3);
        
        // Nested loop (multiplication table)
        System.out.println("Multiplication table:");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.printf("%2d ", i * j);
            }
            System.out.println();
        }
        
        // Break and Continue
        System.out.println("Break example:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) break;
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("Continue example:");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();
        
        // Labeled break
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) break outer;
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}
```

**JavaScript** (`loops.js`):
```javascript
// For loop
console.log("For loop:");
for (let i = 0; i < 5; i++) {
    process.stdout.write(i + " ");
}
console.log();

// For-Of (values)
console.log("For-Of:");
const fruits = ["Apple", "Banana", "Cherry"];
for (const fruit of fruits) {
    console.log("- " + fruit);
}

// For-In (keys/indices)
console.log("For-In (object):");
const user = { name: "Alice", age: 30 };
for (const key in user) {
    console.log(`${key}: ${user[key]}`);
}

// While loop
console.log("While loop:");
let count = 0;
while (count < 3) {
    console.log("Count:", count);
    count++;
}

// Do-While
console.log("Do-While:");
let x = 0;
do {
    console.log("x =", x);
    x++;
} while (x < 3);

// ForEach method
console.log("ForEach method:");
fruits.forEach((fruit, index) => {
    console.log(`${index}: ${fruit}`);
});

// Break and Continue
console.log("Break:");
for (let i = 0; i < 10; i++) {
    if (i === 5) break;
    process.stdout.write(i + " ");
}
console.log();

console.log("Continue:");
for (let i = 0; i < 10; i++) {
    if (i % 2 === 0) continue;
    process.stdout.write(i + " ");
}
console.log();
```

**Python** (`loops.py`):
```python
# For loop with range
print("For loop with range:")
for i in range(5):
    print(i, end=" ")
print()

# For loop with list
print("For loop with list:")
fruits = ["Apple", "Banana", "Cherry"]
for fruit in fruits:
    print(f"- {fruit}")

# For loop with enumerate
print("For loop with enumerate:")
for index, fruit in enumerate(fruits):
    print(f"{index}: {fruit}")

# For loop with dictionary
print("For loop with dict:")
user = {"name": "Alice", "age": 30}
for key, value in user.items():
    print(f"{key}: {value}")

# While loop
print("While loop:")
count = 0
while count < 3:
    print(f"Count: {count}")
    count += 1

# No do-while in Python (simulate with break)
print("Simulated do-while:")
x = 0
while True:
    print(f"x = {x}")
    x += 1
    if x >= 3:
        break

# Nested loop
print("Multiplication table:")
for i in range(1, 4):
    for j in range(1, 4):
        print(f"{i * j:2}", end=" ")
    print()

# Break
print("Break:")
for i in range(10):
    if i == 5:
        break
    print(i, end=" ")
print()

# Continue
print("Continue:")
for i in range(10):
    if i % 2 == 0:
        continue
    print(i, end=" ")
print()

# List comprehension (functional loop)
print("List comprehension:")
squares = [x ** 2 for x in range(10)]
print(squares)

# For-Else (executes if no break)
print("For-Else:")
for i in range(5):
    print(i, end=" ")
else:
    print("\nLoop completed without break")
```

---

## 6. Functions

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Declare | `int add(int a, int b) { return a+b; }` | `function add(a, b) { return a+b; }` | `def add(a, b): return a+b` |
| Lambda | `(a, b) -> a + b` | `(a, b) => a + b` | `lambda a, b: a + b` |
| Default Args | Manual null check | `function f(x = 5) { }` | `def f(x=5):` |
| Variable Args | `void f(int... nums) { }` | `function f(...nums) { }` | `def f(*nums):` |
| Named Args | Not natively supported | Destructuring pattern | `def f(name, age):` called as `f(age=25, name="A")` |

### Full Runnable Examples

**Java** (`Functions.java`):
```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Functions {
    // Regular method
    static int add(int a, int b) {
        return a + b;
    }
    
    // Overloaded methods
    static int add(int a, int b, int c) {
        return a + b + c;
    }
    
    static double add(double a, double b) {
        return a + b;
    }
    
    // Varargs
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }
    
    // Recursion
    static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("add(2,3): " + add(2, 3));
        System.out.println("add(2,3,4): " + add(2, 3, 4));
        System.out.println("add(2.5, 3.5): " + add(2.5, 3.5));
        System.out.println("sum(1,2,3,4,5): " + sum(1, 2, 3, 4, 5));
        System.out.println("factorial(5): " + factorial(5));
        
        // Lambda
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> doubled = nums.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Doubled: " + doubled);
        
        // Method reference
        List<Integer> filtered = nums.stream()
            .filter(n -> n > 2)
            .collect(Collectors.toList());
        System.out.println("Filtered (>2): " + filtered);
    }
}
```

**JavaScript** (`functions.js`):
```javascript
// Function declaration
function add(a, b) {
    return a + b;
}

// Arrow function
const multiply = (a, b) => a * b;

// Default parameters
function greet(name, greeting = "Hello") {
    return `${greeting}, ${name}!`;
}

// Rest parameters
function sum(...numbers) {
    return numbers.reduce((total, n) => total + n, 0);
}

// Recursion
function factorial(n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}

// Higher-order function
function createCounter() {
    let count = 0;
    return function() {
        count++;
        return count;
    };
}

// Callback
function processUser(name, callback) {
    console.log("Processing:", name);
    callback("Success");
}

// Usage
console.log("add(2,3):", add(2, 3));
console.log("multiply(4,5):", multiply(4, 5));
console.log("greet('Alice'):", greet("Alice"));
console.log("greet('Bob', 'Hi'):", greet("Bob", "Hi"));
console.log("sum(1,2,3,4,5):", sum(1, 2, 3, 4, 5));
console.log("factorial(5):", factorial(5));

// Closure
const counter = createCounter();
console.log("counter():", counter());  // 1
console.log("counter():", counter());  // 2
console.log("counter():", counter());  // 3

// Callback
processUser("Alice", (status) => {
    console.log("Callback status:", status);
});

// Array functional methods
const nums = [1, 2, 3, 4, 5];
console.log("map (doubled):", nums.map(n => n * 2));
console.log("filter (>2):", nums.filter(n => n > 2));
console.log("reduce (sum):", nums.reduce((a, b) => a + b, 0));
```

**Python** (`functions.py`):
```python
def add(a, b):
    return a + b

# Default parameters
def greet(name, greeting="Hello"):
    return f"{greeting}, {name}!"

# Variable arguments
def sum_all(*numbers):
    return sum(numbers)

# Keyword arguments
def create_profile(name, age, **kwargs):
    profile = {"name": name, "age": age}
    profile.update(kwargs)
    return profile

# Recursion
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)

# Closure
def create_counter():
    count = [0]  # Use list to make it mutable
    def increment():
        count[0] += 1
        return count[0]
    return increment

# Decorator
def logger(func):
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__}")
        result = func(*args, **kwargs)
        print(f"{func.__name__} returned {result}")
        return result
    return wrapper

@logger
def add_with_log(a, b):
    return a + b

# Lambda
double = lambda x: x * 2

# Usage
print(f"add(2,3): {add(2, 3)}")
print(f"greet('Alice'): {greet('Alice')}")
print(f"greet('Bob', 'Hi'): {greet('Bob', 'Hi')}")
print(f"sum_all(1,2,3,4,5): {sum_all(1, 2, 3, 4, 5)}")
print(f"create_profile('Alice', 30, city='NYC', job='Engineer'):")
print(create_profile("Alice", 30, city="NYC", job="Engineer"))
print(f"factorial(5): {factorial(5)}")

# Closure
counter = create_counter()
print(f"counter(): {counter()}")  # 1
print(f"counter(): {counter()}")  # 2

# Decorator
add_with_log(3, 4)

# Lambda usage
nums = [1, 2, 3, 4, 5]
print(f"map (doubled): {list(map(lambda x: x * 2, nums))}")
print(f"filter (>2): {list(filter(lambda x: x > 2, nums))}")

# List comprehensions (Pythonic functional style)
print(f"Doubled (comprehension): {[x * 2 for x in nums]}")
print(f"Filtered (comprehension): {[x for x in nums if x > 2]}")
```

---

## 7. Strings

### Side-by-Side Comparison

| Operation | Java | JavaScript | Python |
|-----------|------|-----------|--------|
| Length | `str.length()` | `str.length` | `len(str)` |
| Substring | `str.substring(0,5)` | `str.slice(0,5)` | `str[0:5]` |
| Contains | `str.contains("abc")` | `str.includes("abc")` | `"abc" in str` |
| Upper/Lower | `str.toUpperCase()` | `str.toUpperCase()` | `str.upper()` |
| Split | `str.split(",")` | `str.split(",")` | `str.split(",")` |
| Join | `String.join(",", arr)` | `arr.join(",")` | `",".join(list)` |
| Format | `String.format("Hi %s", n)` | `` `Hi ${n}` `` | `f"Hi {n}"` |

### Full Runnable Examples

**Java** (`Strings.java`):
```java
public class Strings {
    public static void main(String[] args) {
        String s = "Hello, World!";
        
        // Basic operations
        System.out.println("Length: " + s.length());
        System.out.println("Char at 0: " + s.charAt(0));
        System.out.println("Substring (0,5): " + s.substring(0, 5));
        System.out.println("Contains 'World': " + s.contains("World"));
        System.out.println("Starts with 'Hello': " + s.startsWith("Hello"));
        System.out.println("Ends with '!': " + s.endsWith("!"));
        System.out.println("Index of 'o': " + s.indexOf('o'));
        System.out.println("Last index of 'o': " + s.lastIndexOf('o'));
        
        // Case
        System.out.println("Upper: " + s.toUpperCase());
        System.out.println("Lower: " + s.toLowerCase());
        
        // Replace
        System.out.println("Replace: " + s.replace("World", "Java"));
        
        // Split and Join
        String csv = "apple,banana,cherry";
        String[] parts = csv.split(",");
        System.out.println("Split: " + String.join(" | ", parts));
        
        // Trim
        String spaced = "  Hello  ";
        System.out.println("Trimmed: '" + spaced.trim() + "'");
        
        // Format
        String name = "Alice";
        int age = 30;
        System.out.println(String.format("Name: %s, Age: %d", name, age));
        
        // StringBuilder (mutable)
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" ");
        sb.append("Java");
        sb.insert(6, "Beautiful ");
        System.out.println("StringBuilder: " + sb);
        
        // Reverse
        System.out.println("Reverse: " + new StringBuilder(s).reverse());
        
        // Equality
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println("a == b: " + (a == b));     // true (interned)
        System.out.println("a == c: " + (a == c));     // false
        System.out.println("a.equals(c): " + a.equals(c)); // true
    }
}
```

**JavaScript** (`strings.js`):
```javascript
let s = "Hello, World!";

// Basic operations
console.log("Length:", s.length);
console.log("Char at 0:", s[0]);
console.log("Substring (0,5):", s.slice(0, 5));
console.log("Substring (alternative):", s.substring(0, 5));
console.log("Includes 'World':", s.includes("World"));
console.log("Starts with 'Hello':", s.startsWith("Hello"));
console.log("Ends with '!':", s.endsWith("!"));
console.log("Index of 'o':", s.indexOf("o"));
console.log("Last index of 'o':", s.lastIndexOf("o"));

// Case
console.log("Upper:", s.toUpperCase());
console.log("Lower:", s.toLowerCase());

// Replace
console.log("Replace:", s.replace("World", "JavaScript"));
console.log("Replace All:", "a-b-c".replace(/-/g, "|"));

// Split and Join
let csv = "apple,banana,cherry";
console.log("Split:", csv.split(","));
console.log("Join:", ["apple", "banana", "cherry"].join(" | "));

// Trim
let spaced = "  Hello  ";
console.log("Trimmed:", "'" + spaced.trim() + "'");

// Template literals
let name = "Alice";
let age = 30;
console.log(`Name: ${name}, Age: ${age}`);

// Multi-line
let multi = `Line 1
Line 2
Line 3`;
console.log("Multi-line:", multi);

// Repeat
console.log("Repeat:", "Ha".repeat(3));

// Reverse
console.log("Reverse:", s.split("").reverse().join(""));

// Pad
console.log("PadStart:", "5".padStart(3, "0"));
console.log("PadEnd:", "5".padEnd(3, "0"));

// Search
let regex = /\d+/g;
let text = "abc123def456";
console.log("Match:", text.match(regex));
console.log("Search:", text.search(/\d+/));
```

**Python** (`strings.py`):
```python
s = "Hello, World!"

# Basic operations
print(f"Length: {len(s)}")
print(f"Char at 0: {s[0]}")
print(f"Substring (0,5): {s[0:5]}")
print(f"Slice (7:): {s[7:]}")
print(f"Step slice: {s[::2]}")
print(f"Contains 'World': {'World' in s}")
print(f"Starts with 'Hello': {s.startswith('Hello')}")
print(f"Ends with '!': {s.endswith('!')}")
print(f"Index of 'o': {s.index('o')}")
print(f"Find (no error): {s.find('z')}")  # -1 instead of error

# Case
print(f"Upper: {s.upper()}")
print(f"Lower: {s.lower()}")
print(f"Title: {s.title()}")

# Replace
print(f"Replace: {s.replace('World', 'Python')}")

# Split and Join
csv = "apple,banana,cherry"
print(f"Split: {csv.split(',')}")
print(f"Join: {' | '.join(['apple', 'banana', 'cherry'])}")

# Strip
spaced = "  Hello  "
print(f"Strip: '{spaced.strip()}'")
print(f"LStrip: '{spaced.lstrip()}'")
print(f"RStrip: '{spaced.rstrip()}'")

# F-strings (format)
name = "Alice"
age = 30
print(f"Name: {name}, Age: {age}")

# Multi-line
multi = """Line 1
Line 2
Line 3"""
print(f"Multi-line:\n{multi}")

# Repeat
print(f"Repeat: {'Ha' * 3}")

# Reverse
print(f"Reverse: {s[::-1]}")

# Count
print(f"Count 'l': {s.count('l')}")

# Check types
print(f"Is alpha: {'abc'.isalpha()}")
print(f"Is digit: {'123'.isdigit()}")
print(f"Is alnum: {'abc123'.isalnum()}")

# Padding
print(f"Zfill: {'42'.zfill(5)}")
print(f"Center: {'hello'.center(11, '*')}")

# Partition
print(f"Partition: {s.partition(', ')}")
```

---

## 8. Numbers & Math

### Side-by-Side Comparison

| Operation | Java | JavaScript | Python |
|-----------|------|-----------|--------|
| Abs | `Math.abs(-5)` | `Math.abs(-5)` | `abs(-5)` |
| Max/Min | `Math.max(a,b)` | `Math.max(a,b)` | `max(a,b)` / `min(a,b)` |
| Power | `Math.pow(2,3)` | `Math.pow(2,3)` or `2**3` | `pow(2,3)` or `2**3` |
| Random | `Math.random()` | `Math.random()` | `random.random()` |
| Random Range | `ThreadLocalRandom.current().nextInt(1,10)` | `Math.floor(Math.random()*9)+1` | `random.randint(1,10)` |

### Full Runnable Examples

**Java** (`Numbers.java`):
```java
import java.util.concurrent.ThreadLocalRandom;

public class Numbers {
    public static void main(String[] args) {
        // Constants
        System.out.println("Max int: " + Integer.MAX_VALUE);
        System.out.println("Min int: " + Integer.MIN_VALUE);
        System.out.println("PI: " + Math.PI);
        System.out.println("E: " + Math.E);
        
        // Basic math
        System.out.println("Abs(-5): " + Math.abs(-5));
        System.out.println("Ceil(4.2): " + Math.ceil(4.2));
        System.out.println("Floor(4.7): " + Math.floor(4.7));
        System.out.println("Round(4.5): " + Math.round(4.5));
        System.out.println("Max(10,20): " + Math.max(10, 20));
        System.out.println("Min(10,20): " + Math.min(10, 20));
        System.out.println("Pow(2,10): " + Math.pow(2, 10));
        System.out.println("Sqrt(16): " + Math.sqrt(16));
        
        // Random
        System.out.println("Random: " + Math.random());
        System.out.println("Random int 1-10: " + 
            ThreadLocalRandom.current().nextInt(1, 11));
        
        // Rounding
        double d = 3.14159;
        System.out.printf("Formatted: %.2f%n", d);
        
        // Infinity and NaN
        System.out.println("Infinity: " + Double.POSITIVE_INFINITY);
        System.out.println("Is NaN: " + Double.isNaN(Math.sqrt(-1)));
        System.out.println("Is Finite: " + Double.isFinite(123.0));
        
        // Big integers
        java.math.BigInteger big = new java.math.BigInteger("9999999999999999");
        System.out.println("BigInt: " + big.multiply(java.math.BigInteger.TEN));
    }
}
```

**JavaScript** (`numbers.js`):
```javascript
// Constants
console.log("Max safe int:", Number.MAX_SAFE_INTEGER);
console.log("Min safe int:", Number.MIN_SAFE_INTEGER);
console.log("PI:", Math.PI);
console.log("E:", Math.E);

// Basic math
console.log("Abs(-5):", Math.abs(-5));
console.log("Ceil(4.2):", Math.ceil(4.2));
console.log("Floor(4.7):", Math.floor(4.7));
console.log("Round(4.5):", Math.round(4.5));
console.log("Trunc(4.7):", Math.trunc(4.7));
console.log("Max(10,20):", Math.max(10, 20));
console.log("Min(10,20):", Math.min(10, 20));
console.log("Pow(2,10):", Math.pow(2, 10));
console.log("2**10:", 2 ** 10);
console.log("Sqrt(16):", Math.sqrt(16));

// Random
console.log("Random:", Math.random());
console.log("Random 1-10:", Math.floor(Math.random() * 10) + 1);

// Number formatting
let d = 3.14159;
console.log("To fixed:", d.toFixed(2));
console.log("To precision:", d.toPrecision(3));

// Infinity and NaN
console.log("Infinity:", Infinity);
console.log("IsNaN:", isNaN("hello"));
console.log("Number.isNaN:", Number.isNaN(NaN));
console.log("IsFinite:", isFinite(123));

// BigInt
const big = 9999999999999999n;
console.log("BigInt:", big * 10n);

// parseInt / parseFloat
console.log("parseInt:", parseInt("42"));
console.log("parseFloat:", parseFloat("3.14"));
```

**Python** (`numbers.py`):
```python
import math
import random

# Constants
print(f"PI: {math.pi}")
print(f"E: {math.e}")
print(f"Infinity: {float('inf')}")
print(f"NaN: {float('nan')}")

# Basic math
print(f"Abs(-5): {abs(-5)}")
print(f"Ceil(4.2): {math.ceil(4.2)}")
print(f"Floor(4.7): {math.floor(4.7)}")
print(f"Round(4.5): {round(4.5)}")
print(f"Trunc(4.7): {math.trunc(4.7)}")
print(f"Max(10,20): {max(10, 20)}")
print(f"Min(10,20): {min(10, 20)}")
print(f"Pow(2,10): {pow(2, 10)}")
print(f"2**10: {2 ** 10}")
print(f"Sqrt(16): {math.sqrt(16)}")

# Random
print(f"Random: {random.random()}")
print(f"Randint(1,10): {random.randint(1, 10)}")
print(f"Uniform(1,10): {random.uniform(1, 10)}")
print(f"Choice: {random.choice(['a', 'b', 'c'])}")
print(f"Sample: {random.sample(range(10), 3)}")

# Number formatting
d = 3.14159
print(f"Format: {d:.2f}")
print(f"Format: {d:.3f}")

# Check types
print(f"Is nan: {math.isnan(float('nan'))}")
print(f"Is finite: {math.isfinite(123.0)}")
print(f"Is integer: {isinstance(42, int)}")
print(f"Is float: {isinstance(3.14, float)}")

# Complex numbers
c = 1 + 2j
print(f"Complex: {c}")
print(f"Real: {c.real}, Imag: {c.imag}")

# Arbitrary precision integers
big = 9999999999999999
print(f"Big int: {big * 10}")
```

---

## 9. Arrays & Lists

### Side-by-Side Comparison

| Operation | Java | JavaScript | Python |
|-----------|------|-----------|--------|
| Create | `int[] a = {1,2,3};` | `let a = [1,2,3];` | `a = [1, 2, 3]` |
| Access | `a[0]` | `a[0]` | `a[0]` |
| Length | `a.length` | `a.length` | `len(a)` |
| Add End | N/A (use ArrayList) | `a.push(4)` | `a.append(4)` |
| Remove | N/A | `a.pop()` | `a.pop()` |
| Slice | `Arrays.copyOfRange` | `a.slice(1,3)` | `a[1:3]` |
| Map | Stream API | `a.map(x => x*2)` | `[x*2 for x in a]` |

### Full Runnable Examples

**Java** (`ArraysDemo.java`):
```java
import java.util.*;
import java.util.stream.*;

public class ArraysDemo {
    public static void main(String[] args) {
        // Fixed array
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Length: " + arr.length);
        System.out.println("First: " + arr[0]);
        System.out.println("Last: " + arr[arr.length - 1]);
        
        // ArrayList (dynamic)
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 20, 30));
        list.add(40);
        list.add(50);
        list.remove(Integer.valueOf(20));
        System.out.println("ArrayList: " + list);
        System.out.println("Size: " + list.size());
        System.out.println("Contains 30: " + list.contains(30));
        
        // Iterate
        System.out.print("For loop: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        
        System.out.print("For-Each: ");
        for (int n : list) {
            System.out.print(n + " ");
        }
        System.out.println();
        
        // Stream operations
        List<Integer> doubled = list.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Doubled: " + doubled);
        
        List<Integer> filtered = list.stream()
            .filter(n -> n > 25)
            .collect(Collectors.toList());
        System.out.println("Filtered (>25): " + filtered);
        
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);
        
        // Sort
        List<Integer> unsorted = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
        Collections.sort(unsorted);
        System.out.println("Sorted: " + unsorted);
        
        // 2D array
        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println("Matrix[0][1]: " + matrix[0][1]);
    }
}
```

**JavaScript** (`arrays.js`):
```javascript
let arr = [10, 20, 30, 40, 50];
console.log("Array:", arr);
console.log("Length:", arr.length);
console.log("First:", arr[0]);
console.log("Last:", arr[arr.length - 1]);

// Add/Remove
arr.push(60);          // Add end
arr.unshift(0);        // Add front
console.log("After push/unshift:", arr);

let last = arr.pop();  // Remove end
let first = arr.shift(); // Remove front
console.log("After pop/shift:", arr, "removed:", last, first);

// Splice (add/remove at index)
arr.splice(2, 1);      // Remove at index 2
console.log("After splice remove:", arr);

arr.splice(2, 0, 25);  // Insert at index 2
console.log("After splice insert:", arr);

// Search
console.log("Includes 30:", arr.includes(30));
console.log("Index of 30:", arr.indexOf(30));

// Functional methods
let doubled = arr.map(n => n * 2);
console.log("Doubled:", doubled);

let filtered = arr.filter(n => n > 25);
console.log("Filtered (>25):", filtered);

let sum = arr.reduce((a, b) => a + b, 0);
console.log("Sum:", sum);

// Sort
let unsorted = [3, 1, 4, 1, 5];
unsorted.sort((a, b) => a - b);
console.log("Sorted:", unsorted);

// Spread operator
let copy = [...arr];
let combined = [...arr, 60, 70];
console.log("Copy:", copy);
console.log("Combined:", combined);

// Destructuring
let [x, y, ...rest] = arr;
console.log("Destructured:", x, y, rest);

// Flat
let nested = [[1, 2], [3, 4]];
console.log("Flat:", nested.flat());

// 2D array
let matrix = [[1, 2], [3, 4]];
console.log("Matrix[0][1]:", matrix[0][1]);
```

**Python** (`lists.py`):
```python
arr = [10, 20, 30, 40, 50]
print(f"List: {arr}")
print(f"Length: {len(arr)}")
print(f"First: {arr[0]}")
print(f"Last: {arr[-1]}")
print(f"Last (alt): {arr[len(arr) - 1]}")

# Add/Remove
arr.append(60)           # Add end
arr.insert(0, 0)         # Add at index
print(f"After append/insert: {arr}")

last = arr.pop()         # Remove end
first = arr.pop(0)       # Remove at index
print(f"After pop: {arr}, removed: {last}, {first}")

arr.remove(30)           # Remove by value
print(f"After remove(30): {arr}")

# Slice
print(f"Slice [1:3]: {arr[1:3]}")
print(f"Slice [:3]: {arr[:3]}")
print(f"Slice [::2]: {arr[::2]}")
print(f"Reverse: {arr[::-1]}")

# Search
print(f"Contains 30: {30 in arr}")
print(f"Index of 40: {arr.index(40)}")
print(f"Count of 20: {arr.count(20)}")

# List comprehensions
doubled = [n * 2 for n in arr]
print(f"Doubled: {doubled}")

filtered = [n for n in arr if n > 25]
print(f"Filtered (>25): {filtered}")

squared = [n ** 2 for n in arr]
print(f"Squared: {squared}")

# Sort
unsorted = [3, 1, 4, 1, 5]
unsorted.sort()
print(f"Sorted: {unsorted}")

# Reverse
unsorted.reverse()
print(f"Reversed: {unsorted}")

# Built-in functions
print(f"Sum: {sum(arr)}")
print(f"Max: {max(arr)}")
print(f"Min: {min(arr)}")

# Extend and concatenate
arr1 = [1, 2, 3]
arr2 = [4, 5, 6]
combined = arr1 + arr2
print(f"Combined: {combined}")

arr1.extend(arr2)
print(f"Extended: {arr1}")

# 2D matrix
matrix = [[1, 2], [3, 4]]
print(f"Matrix[0][1]: {matrix[0][1]}")

# List to string
print(f"Join: {', '.join(map(str, arr))}")

# Enumerate
for index, value in enumerate(arr):
    print(f"  Index {index}: {value}")

# Zip
names = ["Alice", "Bob", "Charlie"]
ages = [25, 30, 35]
for name, age in zip(names, ages):
    print(f"  {name} is {age} years old")
```

---

## 10. Maps & Dictionaries

### Side-by-Side Comparison

| Operation | Java (HashMap) | JavaScript (Map) | Python (dict) |
|-----------|---------------|-----------------|---------------|
| Create | `new HashMap<>()` | `new Map()` | `{}` or `dict()` |
| Put/Set | `map.put("k",1)` | `map.set("k",1)` | `d["k"] = 1` |
| Get | `map.get("k")` | `map.get("k")` | `d.get("k")` |
| Has Key | `map.containsKey("k")` | `map.has("k")` | `"k" in d` |
| Delete | `map.remove("k")` | `map.delete("k")` | `del d["k"]` |
| Size | `map.size()` | `map.size` | `len(d)` |
| Keys | `map.keySet()` | `map.keys()` | `d.keys()` |

### Full Runnable Examples

**Java** (`MapsDemo.java`):
```java
import java.util.*;

public class MapsDemo {
    public static void main(String[] args) {
        // HashMap
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        
        System.out.println("Scores: " + scores);
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Contains 'Bob': " + scores.containsKey("Bob"));
        System.out.println("Contains value 100: " + scores.containsValue(100));
        System.out.println("Size: " + scores.size());
        
        // Iterate
        System.out.println("\nAll entries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Get with default
        System.out.println("David (default): " + 
            scores.getOrDefault("David", 0));
        
        // Update
        scores.put("Alice", 97);  // Update
        scores.replace("Bob", 88);
        System.out.println("After update: " + scores);
        
        // Remove
        scores.remove("Charlie");
        System.out.println("After remove: " + scores);
        
        // TreeMap (sorted)
        Map<String, Integer> sorted = new TreeMap<>(scores);
        System.out.println("Sorted: " + sorted);
        
        // ForEach with lambda
        scores.forEach((name, score) -> 
            System.out.println(name + " -> " + score));
        
        // Compute
        scores.compute("Alice", (k, v) -> v + 5);
        System.out.println("After compute: " + scores);
    }
}
```

**JavaScript** (`maps.js`):
```javascript
// Map (any keys)
let scores = new Map();
scores.set("Alice", 95);
scores.set("Bob", 87);
scores.set("Charlie", 92);

console.log("Map:", scores);
console.log("Alice's score:", scores.get("Alice"));
console.log("Has Bob:", scores.has("Bob"));
console.log("Size:", scores.size);

// Iterating Map
console.log("\nAll entries:");
for (let [name, score] of scores) {
    console.log(`${name}: ${score}`);
}

// ForEach on Map
scores.forEach((value, key) => {
    console.log(`${key} -> ${value}`);
});

// Delete
scores.delete("Charlie");
console.log("After delete:", scores);

// Clear
let temp = new Map(scores);

// Map from object
let obj = { a: 1, b: 2, c: 3 };
let fromObj = new Map(Object.entries(obj));
console.log("From object:", fromObj);

// Object (string keys only)
let objScores = {
    Alice: 95,
    Bob: 87,
    Charlie: 92,
};

console.log("\nObject scores:", objScores);
console.log("Keys:", Object.keys(objScores));
console.log("Values:", Object.values(objScores));
console.log("Entries:", Object.entries(objScores));

// Merge maps
let map1 = new Map([["a", 1], ["b", 2]]);
let map2 = new Map([["b", 3], ["c", 4]]);
let merged = new Map([...map1, ...map2]);
console.log("Merged:", merged);

// Default value pattern
const defaults = new Map([["x", 0], ["y", 0]]);
function getValue(map, key) {
    return map.has(key) ? map.get(key) : 0;
}
```

**Python** (`dicts.py`):
```python
# Dictionary
scores = {
    "Alice": 95,
    "Bob": 87,
    "Charlie": 92,
}

print(f"Scores: {scores}")
print(f"Alice's score: {scores['Alice']}")
print(f"Alice (get): {scores.get('Alice')}")
print(f"David (get default): {scores.get('David', 0)}")
print(f"Has 'Bob': {'Bob' in scores}")
print(f"Size: {len(scores)}")

# Iterate
print("\nAll entries:")
for name, score in scores.items():
    print(f"{name}: {score}")

print("Keys:", list(scores.keys()))
print("Values:", list(scores.values()))
print("Items:", list(scores.items()))

# Update
scores["Alice"] = 97  # Update
scores["David"] = 88  # Add
print(f"After updates: {scores}")

# Delete
del scores["Charlie"]
removed = scores.pop("David")
print(f"After deletes: {scores}, removed: {removed}")

# Merge
d1 = {"a": 1, "b": 2}
d2 = {"b": 3, "c": 4}
merged = {**d1, **d2}
print(f"Merged: {merged}")

d1.update(d2)
print(f"Updated d1: {d1}")

# Default dict
from collections import defaultdict
word_counts = defaultdict(int)
words = ["apple", "banana", "apple", "cherry", "banana", "apple"]
for word in words:
    word_counts[word] += 1
print(f"Word counts: {dict(word_counts)}")

# Dictionary comprehension
squares = {x: x ** 2 for x in range(5)}
print(f"Squares: {squares}")

# Setdefault
data = {}
data.setdefault("users", []).append("Alice")
data.setdefault("users", []).append("Bob")
print(f"Setdefault: {data}")

# Ordered dict (maintains insertion order - Python 3.7+)
ordered = dict([("z", 1), ("a", 2), ("c", 3)])
print(f"Ordered: {ordered}")
```

---

## 11. Sets

### Side-by-Side Comparison

| Operation | Java (HashSet) | JavaScript (Set) | Python (set) |
|-----------|---------------|-----------------|--------------|
| Create | `new HashSet<>()` | `new Set()` | `set()` or `{1,2,3}` |
| Add | `set.add(1)` | `set.add(1)` | `s.add(1)` |
| Remove | `set.remove(1)` | `set.delete(1)` | `s.remove(1)` or `s.discard(1)` |
| Has | `set.contains(1)` | `set.has(1)` | `1 in s` |
| Size | `set.size()` | `set.size` | `len(s)` |
| Union | `s1.addAll(s2)` | `new Set([...s1,...s2])` | `s1 \| s2` |
| Intersect | `s1.retainAll(s2)` | Manual filter | `s1 & s2` |
| Difference | `s1.removeAll(s2)` | Manual filter | `s1 - s2` |

### Full Runnable Examples

**Java** (`SetsDemo.java`):
```java
import java.util.*;

public class SetsDemo {
    public static void main(String[] args) {
        // HashSet
        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(10);  // Duplicate - ignored
        
        System.out.println("Set: " + set);
        System.out.println("Size: " + set.size());
        System.out.println("Contains 20: " + set.contains(20));
        System.out.println("Is Empty: " + set.isEmpty());
        
        // Remove
        set.remove(20);
        System.out.println("After remove: " + set);
        
        // Iterate
        System.out.print("Elements: ");
        for (int n : set) {
            System.out.print(n + " ");
        }
        System.out.println();
        
        // Set operations
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        
        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);
        
        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
        
        // Difference
        Set<Integer> diff = new HashSet<>(set1);
        diff.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + diff);
        
        // Subset
        Set<Integer> subset = new HashSet<>(Arrays.asList(1, 2));
        System.out.println("Is subset: " + set1.containsAll(subset));
        
        // TreeSet (sorted)
        Set<Integer> sorted = new TreeSet<>(Arrays.asList(5, 1, 4, 2, 3));
        System.out.println("Sorted: " + sorted);
        
        // LinkedHashSet (insertion order)
        Set<String> ordered = new LinkedHashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println("Insertion order: " + ordered);
    }
}
```

**JavaScript** (`sets.js`):
```javascript
let set = new Set();
set.add(10);
set.add(20);
set.add(30);
set.add(10);  // Duplicate - ignored

console.log("Set:", set);
console.log("Size:", set.size);
console.log("Has 20:", set.has(20));

// Delete
set.delete(20);
console.log("After delete:", set);

// Create from array
let fromArray = new Set([1, 2, 2, 3, 3, 4]);
console.log("From array:", fromArray);

// Iterate
console.log("Elements:");
for (let n of set) {
    console.log(" ", n);
}

// Convert to array
let arr = [...set];
console.log("As array:", arr);

// Set operations
let set1 = new Set([1, 2, 3, 4]);
let set2 = new Set([3, 4, 5, 6]);

// Union
let union = new Set([...set1, ...set2]);
console.log("Union:", union);

// Intersection
let intersection = new Set([...set1].filter(x => set2.has(x)));
console.log("Intersection:", intersection);

// Difference
let diff = new Set([...set1].filter(x => !set2.has(x)));
console.log("Difference:", diff);

// ES2024 methods (if available)
// console.log("Union:", set1.union(set2));
// console.log("Intersection:", set1.intersection(set2));
// console.log("Difference:", set1.difference(set2));
```

**Python** (`sets.py`):
```python
# Set
s = set()
s.add(10)
s.add(20)
s.add(30)
s.add(10)  # Duplicate - ignored

print(f"Set: {s}")
print(f"Size: {len(s)}")
print(f"Has 20: {20 in s}")
print(f"Is empty: {len(s) == 0}")

# Remove
s.remove(20)  # Raises KeyError if missing
s.discard(25)  # Safe (no error)
print(f"After remove: {s}")

# Create from list
from_list = set([1, 2, 2, 3, 3, 4])
print(f"From list: {from_list}")

# Set literal
literal = {1, 2, 3, 4}
print(f"Literal: {literal}")

# Frozen set (immutable)
fs = frozenset([1, 2, 3])
print(f"Frozen: {fs}")

# Set operations
set1 = {1, 2, 3, 4}
set2 = {3, 4, 5, 6}

print(f"Union: {set1 | set2}")
print(f"Intersection: {set1 & set2}")
print(f"Difference (set1-set2): {set1 - set2}")
print(f"Symmetric diff: {set1 ^ set2}")

# Comparisons
print(f"Is subset: {set1.issubset(set1)}")
print(f"Is superset: {set1 >= {1, 2}}")
print(f"Is disjoint: {set1.isdisjoint({7, 8})}")

# Update operations
a = {1, 2, 3}
a.update([4, 5])  # Union in-place
print(f"Updated: {a}")

# Comprehension
squares = {x ** 2 for x in range(5)}
print(f"Set comprehension: {squares}")
```

---

## 12. Date & Time

### Side-by-Side Comparison

| Operation | Java (java.time) | JavaScript (Date) | Python (datetime) |
|-----------|-----------------|-------------------|-------------------|
| Now | `LocalDateTime.now()` | `new Date()` | `datetime.now()` |
| Specific | `LocalDate.of(2024,12,1)` | `new Date(2024,11,1)` | `date(2024,12,1)` |
| Format | `d.format(DateTimeFormatter...)` | `d.toLocaleDateString()` | `d.strftime("%Y-%m-%d")` |
| Parse | `LocalDate.parse("2024-12-01")` | `new Date("2024-12-01")` | `datetime.strptime(...)` |
| Add Days | `d.plusDays(7)` | `d.setDate(d.getDate()+7)` | `d + timedelta(days=7)` |
| Difference | `ChronoUnit.DAYS.between(d1,d2)` | `(d2-d1)/(1000*60*60*24)` | `(d2-d1).days` |

### Full Runnable Examples

**Java** (`DateTimes.java`):
```java
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimes {
    public static void main(String[] args) {
        // Current date/time
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();
        
        System.out.println("Now: " + now);
        System.out.println("Today: " + today);
        System.out.println("Time: " + time);
        
        // Specific date
        LocalDate christmas = LocalDate.of(2024, 12, 25);
        System.out.println("Christmas: " + christmas);
        
        // Parse
        LocalDate parsed = LocalDate.parse("2024-12-01");
        System.out.println("Parsed: " + parsed);
        
        // Format
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Formatted: " + now.format(fmt));
        
        // Add/Subtract
        LocalDate nextWeek = today.plusDays(7);
        LocalDate lastMonth = today.minusMonths(1);
        System.out.println("Next week: " + nextWeek);
        System.out.println("Last month: " + lastMonth);
        
        // Difference
        long daysBetween = ChronoUnit.DAYS.between(today, christmas);
        System.out.println("Days to Christmas: " + daysBetween);
        
        // Compare
        System.out.println("Is after: " + christmas.isAfter(today));
        System.out.println("Is before: " + christmas.isBefore(today));
        
        // Zoned
        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("UTC: " + utc);
        
        // Timestamp
        long timestamp = System.currentTimeMillis();
        System.out.println("Timestamp: " + timestamp);
        
        // Day of week
        System.out.println("Day of week: " + today.getDayOfWeek());
        System.out.println("Day of year: " + today.getDayOfYear());
        
        // Period
        Period period = Period.between(today, christmas);
        System.out.println("Period: " + period.getMonths() + " months, " 
            + period.getDays() + " days");
    }
}
```

**JavaScript** (`datetimes.js`):
```javascript
// Current date/time
let now = new Date();
console.log("Now:", now);
console.log("ISO:", now.toISOString());
console.log("Date only:", now.toDateString());
console.log("Time only:", now.toTimeString());
console.log("Locale:", now.toLocaleDateString());

// Specific date (months are 0-indexed!)
let christmas = new Date(2024, 11, 25);  // Dec 25, 2024
console.log("Christmas:", christmas.toDateString());

// Parse
let parsed = new Date("2024-12-01");
console.log("Parsed:", parsed.toDateString());

// Get components
console.log("Year:", now.getFullYear());
console.log("Month:", now.getMonth() + 1);  // 0-indexed, so +1
console.log("Day:", now.getDate());
console.log("Hours:", now.getHours());
console.log("Minutes:", now.getMinutes());
console.log("Day of week:", now.getDay());  // 0=Sun

// Add days
let nextWeek = new Date(now);
nextWeek.setDate(now.getDate() + 7);
console.log("Next week:", nextWeek.toDateString());

// Difference in days
let diffMs = christmas - now;
let diffDays = Math.floor(diffMs / (1000 * 60 * 60 * 24));
console.log("Days to Christmas:", diffDays);

// Format
const formatter = new Intl.DateTimeFormat("en-US", {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
});
console.log("Formatted:", formatter.format(now));

// Timestamp
console.log("Timestamp:", Date.now());

// Timezone
console.log("Timezone offset:", now.getTimezoneOffset());

// UTC methods
console.log("UTC year:", now.getUTCFullYear());
console.log("UTC month:", now.getUTCMonth() + 1);
```

**Python** (`datetimes.py`):
```python
from datetime import datetime, date, time, timedelta, timezone

# Current date/time
now = datetime.now()
today = date.today()
current_time = datetime.now().time()

print(f"Now: {now}")
print(f"Today: {today}")
print(f"Time: {current_time}")

# Specific date
christmas = date(2024, 12, 25)
print(f"Christmas: {christmas}")

# Parse
parsed = datetime.strptime("2024-12-01", "%Y-%m-%d")
print(f"Parsed: {parsed}")

# Format
print(f"Formatted: {now.strftime('%Y-%m-%d %H:%M:%S')}")
print(f"Custom: {now.strftime('%A, %B %d, %Y')}")

# Add/Subtract
next_week = today + timedelta(days=7)
last_month = today - timedelta(days=30)
print(f"Next week: {next_week}")
print(f"Last month: {last_month}")

# Difference
diff = christmas - today
print(f"Days to Christmas: {diff.days}")

# Compare
print(f"Is after: {christmas > today}")
print(f"Is before: {christmas < today}")

# Timezone
utc_now = datetime.now(timezone.utc)
print(f"UTC: {utc_now}")

# Timestamp
print(f"Timestamp: {datetime.now().timestamp()}")

# Components
print(f"Year: {now.year}")
print(f"Month: {now.month}")
print(f"Day: {now.day}")
print(f"Hour: {now.hour}")
print(f"Minute: {now.minute}")
print(f"Second: {now.second}")
print(f"Day of week: {now.weekday()}")  # 0=Monday

# Replace
midnight = now.replace(hour=0, minute=0, second=0, microsecond=0)
print(f"Midnight: {midnight}")

# ISO format
print(f"ISO: {now.isoformat()}")
```

---

## 13. Regular Expressions

### Side-by-Side Comparison

| Operation | Java | JavaScript | Python |
|-----------|------|-----------|--------|
| Compile | `Pattern.compile("\\d+")` | `/\d+/g` | `re.compile(r'\d+')` |
| Test | `pattern.matcher("123").matches()` | `regex.test("123")` | `bool(re.search(r'\d+', "123"))` |
| Find All | Loop with matcher.find() | `"123".match(regex)` | `re.findall(r'\d+', "123")` |
| Replace | `str.replaceAll("\\d+", "#")` | `str.replace(/\d+/g, "#")` | `re.sub(r'\d+', '#', str)` |
| Split | `str.split("\\s+")` | `str.split(/\s+/)` | `re.split(r'\s+', str)` |

### Full Runnable Examples

**Java** (`RegexDemo.java`):
```java
import java.util.regex.*;

public class RegexDemo {
    public static void main(String[] args) {
        String text = "Hello 123 World 456 Test";
        
        // Compile pattern
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        
        // Test if matches entire string
        System.out.println("Full match: " + Pattern.matches("\\d+", "123"));
        
        // Find all matches
        System.out.println("All numbers:");
        while (matcher.find()) {
            System.out.println("  Found: " + matcher.group() + 
                " at index " + matcher.start());
        }
        
        // Replace
        String replaced = text.replaceAll("\\d+", "#");
        System.out.println("Replace: " + replaced);
        
        // Replace first
        String replacedFirst = text.replaceFirst("\\d+", "#");
        System.out.println("Replace first: " + replacedFirst);
        
        // Split
        String[] parts = text.split("\\s+");
        System.out.println("Split: " + String.join(", ", parts));
        
        // Groups
        Pattern groupPattern = Pattern.compile("(\\w+)\\s(\\d+)");
        Matcher groupMatcher = groupPattern.matcher("Hello 123");
        if (groupMatcher.find()) {
            System.out.println("Group 1: " + groupMatcher.group(1));
            System.out.println("Group 2: " + groupMatcher.group(2));
        }
        
        // Flags
        Pattern caseInsensitive = Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
        System.out.println("Case insensitive: " + 
            caseInsensitive.matcher("HELLO").find());
        
        // Email validation
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        System.out.println("Is email: " + 
            Pattern.matches(emailPattern, "user@example.com"));
    }
}
```

**JavaScript** (`regex.js`):
```javascript
let text = "Hello 123 World 456 Test";

// Literal syntax
let regex = /\d+/g;

// Test
console.log("Test:", regex.test("123"));  // true

// Reset lastIndex (important!)
regex.lastIndex = 0;

// Find all matches
console.log("All numbers:", text.match(regex));

// Search (returns index)
console.log("Search index:", text.search(/\d+/));

// Replace
console.log("Replace:", text.replace(/\d+/g, "#"));
console.log("Replace first:", text.replace(/\d+/, "#"));

// Split
console.log("Split:", text.split(/\s+/));

// Groups
let match = "Hello 123".match(/(\w+)\s(\d+)/);
console.log("Full match:", match[0]);
console.log("Group 1:", match[1]);
console.log("Group 2:", match[2]);

// Named groups
let namedMatch = "Hello 123".match(/(?<word>\w+)\s(?<num>\d+)/);
console.log("Named groups:", namedMatch.groups);
console.log("Word:", namedMatch.groups.word);

// Flags
console.log("Case insensitive:", /hello/i.test("HELLO"));

// Global replace with function
let result = "apple1 banana2".replace(/(\w+)(\d+)/g, (match, word, num) => {
    return word.toUpperCase() + num;
});
console.log("Replace with function:", result);

// Email validation
const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;
console.log("Is email:", emailRegex.test("user@example.com"));
```

**Python** (`regex_demo.py`):
```python
import re

text = "Hello 123 World 456 Test"

# Compile pattern
pattern = re.compile(r'\d+')

# Find all
print(f"All numbers: {pattern.findall(text)}")

# Find with spans
print("All numbers with positions:")
for match in pattern.finditer(text):
    print(f"  Found: {match.group()} at {match.start()}-{match.end()}")

# Search (first match)
match = re.search(r'\d+', text)
print(f"First match: {match.group() if match else 'None'}")

# Full match
print(f"Full match: {bool(re.fullmatch(r'\d+', '123'))}")

# Replace
print(f"Replace: {re.sub(r'\d+', '#', text)}")
print(f"Replace count 1: {re.sub(r'\d+', '#', text, count=1)}")

# Split
print(f"Split: {re.split(r'\s+', text)}")

# Groups
match = re.search(r'(\w+)\s(\d+)', text)
if match:
    print(f"Full: {match.group(0)}")
    print(f"Group 1: {match.group(1)}")
    print(f"Group 2: {match.group(2)}")
    print(f"Groups: {match.groups()}")

# Named groups
match = re.search(r'(?P<word>\w+)\s(?P<num>\d+)', text)
if match:
    print(f"Named: {match.groupdict()}")

# Flags
print(f"Case insensitive: {bool(re.search(r'hello', 'HELLO', re.IGNORECASE))}")

# Email validation
email_pattern = r'^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$'
print(f"Is email: {bool(re.match(email_pattern, 'user@example.com'))}")

# Compile for performance
email_regex = re.compile(email_pattern)
print(f"Is email (compiled): {bool(email_regex.match('user@example.com'))}")
```

---

## 14. Classes & Objects

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Class | `public class Car { }` | `class Car { }` | `class Car:` |
| Constructor | `public Car(String b) { this.brand = b; }` | `constructor(b) { this.brand = b; }` | `def __init__(self, b): self.brand = b` |
| Instance | `Car c = new Car("Tesla");` | `const c = new Car("Tesla");` | `c = Car("Tesla")` |
| Static | `static int count = 0;` | `static count = 0;` | `count = 0` (class-level) |
| toString | `@Override toString()` | Not natively needed | `def __str__(self):` |

### Full Runnable Examples

**Java** (`ClassesDemo.java`):
```java
import java.util.Objects;

class Car {
    // Instance variables
    private String brand;
    private String model;
    private int year;
    
    // Static variable
    static int totalCars = 0;
    
    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        totalCars++;
    }
    
    // Getters and Setters
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    // Instance method
    public void start() {
        System.out.println(brand + " " + model + " is starting...");
    }
    
    // Static method
    static void showTotalCars() {
        System.out.println("Total cars: " + totalCars);
    }
    
    // toString override
    @Override
    public String toString() {
        return year + " " + brand + " " + model;
    }
    
    // equals override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && 
               brand.equals(car.brand) && 
               model.equals(car.model);
    }
    
    // hashCode override
    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year);
    }
}

public class ClassesDemo {
    public static void main(String[] args) {
        Car car1 = new Car("Tesla", "Model 3", 2024);
        Car car2 = new Car("Toyota", "Camry", 2023);
        
        System.out.println("Car 1: " + car1);
        System.out.println("Car 2: " + car2);
        
        car1.start();
        
        Car.showTotalCars();
        
        // Equality
        Car car3 = new Car("Tesla", "Model 3", 2024);
        System.out.println("car1.equals(car3): " + car1.equals(car3));
        
        // Anonymous class
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class running");
            }
        };
        r.run();
    }
}
```

**JavaScript** (`classes.js`):
```javascript
class Car {
    // Static property
    static totalCars = 0;
    
    // Private field (ES2022+)
    #vin;
    
    // Constructor
    constructor(brand, model, year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.#vin = Math.random().toString(16).slice(2, 10);
        Car.totalCars++;
    }
    
    // Instance method
    start() {
        console.log(`${this.brand} ${this.model} is starting...`);
    }
    
    // Getter
    get description() {
        return `${this.year} ${this.brand} ${this.model}`;
    }
    
    // Setter
    set modelName(value) {
        this.model = value;
    }
    
    // Private method
    #generateVin() {
        return Math.random().toString(16).slice(2, 10);
    }
    
    // Static method
    static showTotalCars() {
        console.log(`Total cars: ${Car.totalCars}`);
    }
}

// Usage
const car1 = new Car("Tesla", "Model 3", 2024);
const car2 = new Car("Toyota", "Camry", 2023);

console.log(car1.description);
car1.start();
Car.showTotalCars();

// instanceof check
console.log("is Car?", car1 instanceof Car);

// Object literal
const obj = {
    name: "Alice",
    greet() {
        console.log(`Hello, I'm ${this.name}`);
    }
};
obj.greet();

// Factory function
function createPerson(name, age) {
    return {
        name,
        age,
        sayHello() {
            console.log(`Hi, I'm ${this.name}`);
        }
    };
}

const person = createPerson("Bob", 30);
person.sayHello();
```

**Python** (`classes_demo.py`):
```python
class Car:
    # Class variable (shared across instances)
    total_cars = 0
    
    def __init__(self, brand, model, year):
        # Instance variables
        self.brand = brand
        self.model = model
        self.year = year
        self.__vin = None  # Private (name mangling)
        Car.total_cars += 1
    
    # Instance method
    def start(self):
        print(f"{self.brand} {self.model} is starting...")
    
    # String representation
    def __str__(self):
        return f"{self.year} {self.brand} {self.model}"
    
    # Official representation
    def __repr__(self):
        return f"Car('{self.brand}', '{self.model}', {self.year})"
    
    # Equality
    def __eq__(self, other):
        if not isinstance(other, Car):
            return False
        return (self.brand == other.brand and 
                self.model == other.model and 
                self.year == other.year)
    
    # Hash
    def __hash__(self):
        return hash((self.brand, self.model, self.year))
    
    # Property (getter)
    @property
    def description(self):
        return f"{self.year} {self.brand} {self.model}"
    
    # Property setter
    @description.setter
    def description(self, value):
        self.brand, self.model, self.year = value.split()
    
    # Static method
    @staticmethod
    def show_total_cars():
        print(f"Total cars: {Car.total_cars}")
    
    # Class method
    @classmethod
    def from_string(cls, car_string):
        brand, model, year = car_string.split()
        return cls(brand, model, int(year))

# Usage
car1 = Car("Tesla", "Model 3", 2024)
car2 = Car("Toyota", "Camry", 2023)

print(car1)  # Uses __str__
print(repr(car1))  # Uses __repr__
car1.start()
Car.show_total_cars()

# Equality
car3 = Car("Tesla", "Model 3", 2024)
print(f"car1 == car3: {car1 == car3}")

# isinstance check
print(f"Is Car? {isinstance(car1, Car)}")

# Property
print(f"Description: {car1.description}")

# Class method
car4 = Car.from_string("Honda Civic 2022")
print(f"From string: {car4}")

# Dataclass (Python 3.7+)
from dataclasses import dataclass

@dataclass
class Point:
    x: int
    y: int
    
    def distance(self):
        return (self.x ** 2 + self.y ** 2) ** 0.5

p = Point(3, 4)
print(f"Point: {p}, distance: {p.distance()}")
```

---

## 15. Inheritance

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Extends | `class Tesla extends Car` | `class Tesla extends Car` | `class Tesla(Car):` |
| Super | `super(brand)` | `super(brand)` | `super().__init__(brand)` |
| Override | `@Override void start()` | `start() { super.start(); }` | `def start(self): super().start()` |
| Multiple | Not supported | Not supported | `class C(A, B):` |

### Full Runnable Examples

**Java** (`InheritanceDemo.java`):
```java
// Parent class
class Vehicle {
    protected String brand;
    protected int year;
    
    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }
    
    public void start() {
        System.out.println(brand + " is starting...");
    }
    
    public void info() {
        System.out.println(year + " " + brand);
    }
}

// Child class (Single Inheritance)
class Car extends Vehicle {
    private int doors;
    
    public Car(String brand, int year, int doors) {
        super(brand, year);  // Call parent constructor
        this.doors = doors;
    }
    
    @Override
    public void start() {
        super.start();  // Call parent method
        System.out.println("Engine is running!");
    }
    
    public void honk() {
        System.out.println("Beep beep!");
    }
}

// Child class (Multilevel Inheritance)
class ElectricCar extends Car {
    private int batteryRange;
    
    public ElectricCar(String brand, int year, int doors, int range) {
        super(brand, year, doors);
        this.batteryRange = range;
    }
    
    @Override
    public void start() {
        System.out.println("Electric motor is whirring...");
    }
    
    public void charge() {
        System.out.println("Charging battery...");
    }
}

// Abstract class
abstract class Shape {
    abstract double area();
    
    void display() {
        System.out.println("Area: " + area());
    }
}

class Circle extends Shape {
    double radius;
    
    Circle(double r) { this.radius = r; }
    
    @Override
    double area() { return Math.PI * radius * radius; }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        // Single inheritance
        Car car = new Car("Toyota", 2023, 4);
        car.start();
        car.honk();
        car.info();
        
        // Multilevel inheritance
        ElectricCar tesla = new ElectricCar("Tesla", 2024, 4, 400);
        tesla.start();
        tesla.charge();
        
        // Polymorphism (Parent reference, child object)
        Vehicle v = new ElectricCar("BMW", 2024, 4, 300);
        v.start();  // Calls ElectricCar's start()
        
        // Abstract class
        Shape circle = new Circle(5);
        circle.display();
        
        // instanceof
        System.out.println("Is Car? " + (car instanceof Car));
        System.out.println("Is Vehicle? " + (car instanceof Vehicle));
        System.out.println("Is Object? " + (car instanceof Object));
    }
}
```

**JavaScript** (`inheritance.js`):
```javascript
// Parent class
class Vehicle {
    constructor(brand, year) {
        this.brand = brand;
        this.year = year;
    }
    
    start() {
        console.log(`${this.brand} is starting...`);
    }
    
    info() {
        console.log(`${this.year} ${this.brand}`);
    }
}

// Child class (Single Inheritance)
class Car extends Vehicle {
    constructor(brand, year, doors) {
        super(brand, year);  // Call parent constructor
        this.doors = doors;
    }
    
    start() {
        super.start();  // Call parent method
        console.log("Engine is running!");
    }
    
    honk() {
        console.log("Beep beep!");
    }
}

// Multilevel Inheritance
class ElectricCar extends Car {
    constructor(brand, year, doors, range) {
        super(brand, year, doors);
        this.batteryRange = range;
    }
    
    start() {
        console.log("Electric motor is whirring...");
    }
    
    charge() {
        console.log("Charging battery...");
    }
}

// Mixin (alternative to multiple inheritance)
const Flyable = {
    fly() {
        console.log(`${this.brand} is flying!`);
    }
};

// Apply mixin
Object.assign(Vehicle.prototype, Flyable);

// Usage
const car = new Car("Toyota", 2023, 4);
car.start();
car.honk();
car.info();

const tesla = new ElectricCar("Tesla", 2024, 4, 400);
tesla.start();
tesla.charge();

// Polymorphism
const vehicles = [
    new Car("Honda", 2022, 2),
    new ElectricCar("Tesla", 2024, 4, 350)
];

vehicles.forEach(v => v.start());  // Different behavior

// instanceof
console.log("Is Car?", car instanceof Car);
console.log("Is Vehicle?", car instanceof Vehicle);

// Prototype chain
console.log("Prototype:", Object.getPrototypeOf(car));
```

**Python** (`inheritance_demo.py`):
```python
class Vehicle:
    def __init__(self, brand, year):
        self.brand = brand
        self.year = year
    
    def start(self):
        print(f"{self.brand} is starting...")
    
    def info(self):
        print(f"{self.year} {self.brand}")


class Car(Vehicle):  # Single Inheritance
    def __init__(self, brand, year, doors):
        super().__init__(brand, year)
        self.doors = doors
    
    def start(self):
        super().start()  # Call parent
        print("Engine is running!")
    
    def honk(self):
        print("Beep beep!")


class ElectricCar(Car):  # Multilevel Inheritance
    def __init__(self, brand, year, doors, battery_range):
        super().__init__(brand, year, doors)
        self.battery_range = battery_range
    
    def start(self):
        print("Electric motor is whirring...")
    
    def charge(self):
        print("Charging battery...")


# Multiple Inheritance
class Flyable:
    def fly(self):
        print(f"{self.brand} is flying!")


class FlyingCar(Car, Flyable):  # Multiple Inheritance
    def __init__(self, brand, year, doors):
        Car.__init__(self, brand, year, doors)


# Abstract base class
from abc import ABC, abstractmethod

class Shape(ABC):
    @abstractmethod
    def area(self):
        pass
    
    def display(self):
        print(f"Area: {self.area()}")

class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius
    
    def area(self):
        return 3.14159 * self.radius ** 2


# Usage
car = Car("Toyota", 2023, 4)
car.start()
car.honk()
car.info()

tesla = ElectricCar("Tesla", 2024, 4, 400)
tesla.start()
tesla.charge()

# Multiple inheritance
flying_car = FlyingCar("AeroCar", 2025, 2)
flying_car.fly()

# Polymorphism
vehicles = [
    Car("Honda", 2022, 2),
    ElectricCar("Tesla", 2024, 4, 350)
]
for v in vehicles:
    v.start()  # Different behavior

# isinstance
print(f"Is Car? {isinstance(car, Car)}")
print(f"Is Vehicle? {isinstance(car, Vehicle)}")

# issubclass
print(f"Is Car subclass of Vehicle? {issubclass(Car, Vehicle)}")

# Abstract class
c = Circle(5)
c.display()

# Method Resolution Order (MRO)
print(f"MRO of FlyingCar: {FlyingCar.__mro__}")
```

---

## 16. Polymorphism

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Method Overriding | `@Override void speak()` | `speak() { }` | `def speak(self):` |
| Method Overloading | Supported (different signatures) | Not supported | Not supported |
| Duck Typing | Not supported | Supported | Supported |
| Covariant Return | Supported | N/A | N/A |

### Full Runnable Examples

**Java** (`PolymorphismDemo.java`):
```java
// Method Overriding (Runtime Polymorphism)
class Animal {
    void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Meow!");
    }
}

// Method Overloading (Compile-time Polymorphism)
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
    
    double add(double a, double b) {
        return a + b;
    }
}

// Interface Polymorphism
interface Drawable {
    void draw();
}

class Rectangle implements Drawable {
    public void draw() {
        System.out.println("Drawing rectangle");
    }
}

class Triangle implements Drawable {
    public void draw() {
        System.out.println("Drawing triangle");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        // Runtime Polymorphism (Method Overriding)
        Animal[] animals = {new Dog(), new Cat(), new Animal()};
        
        for (Animal a : animals) {
            a.speak();  // Calls the appropriate method
        }
        
        // Compile-time Polymorphism (Method Overloading)
        Calculator calc = new Calculator();
        System.out.println("add(2,3): " + calc.add(2, 3));
        System.out.println("add(2,3,4): " + calc.add(2, 3, 4));
        System.out.println("add(2.5,3.5): " + calc.add(2.5, 3.5));
        
        // Interface Polymorphism
        Drawable[] shapes = {new Rectangle(), new Triangle()};
        for (Drawable d : shapes) {
            d.draw();
        }
    }
}
```

**JavaScript** (`polymorphism.js`):
```javascript
// Method Overriding (Runtime Polymorphism)
class Animal {
    speak() {
        console.log("Animal speaks");
    }
}

class Dog extends Animal {
    speak() {
        console.log("Woof!");
    }
}

class Cat extends Animal {
    speak() {
        console.log("Meow!");
    }
}

// Runtime Polymorphism
const animals = [new Dog(), new Cat(), new Animal()];
animals.forEach(a => a.speak());

// Duck Typing
function makeSound(animal) {
    if (typeof animal.speak === 'function') {
        animal.speak();
    }
}

const duck = {
    speak() { console.log("Quack!"); }
};

makeSound(duck);  // Works! Duck typing

// Method Overloading simulation (use rest params)
class Calculator {
    add(...args) {
        return args.reduce((sum, n) => sum + n, 0);
    }
}

const calc = new Calculator();
console.log("add(2,3):", calc.add(2, 3));
console.log("add(2,3,4):", calc.add(2, 3, 4));
console.log("add(2.5,3.5):", calc.add(2.5, 3.5));

// Polymorphism through interface-like behavior
class Rectangle {
    draw() { console.log("Drawing rectangle"); }
}

class Triangle {
    draw() { console.log("Drawing triangle"); }
}

const shapes = [new Rectangle(), new Triangle()];
shapes.forEach(s => s.draw());
```

**Python** (`polymorphism.py`):
```python
# Method Overriding (Runtime Polymorphism)
class Animal:
    def speak(self):
        print("Animal speaks")

class Dog(Animal):
    def speak(self):
        print("Woof!")

class Cat(Animal):
    def speak(self):
        print("Meow!")

# Runtime Polymorphism
animals = [Dog(), Cat(), Animal()]
for a in animals:
    a.speak()  # Different behavior based on type

# Duck Typing
class Duck:
    def speak(self):
        print("Quack!")

def make_sound(thing):
    if hasattr(thing, 'speak'):
        thing.speak()

make_sound(Duck())  # Works! Duck typing

# Method Overloading simulation (use default args)
class Calculator:
    def add(self, a, b=0, c=0):
        return a + b + c

calc = Calculator()
print(f"add(2,3): {calc.add(2, 3)}")
print(f"add(2,3,4): {calc.add(2, 3, 4)}")
print(f"add(2.5,3.5): {calc.add(2.5, 3.5)}")

# Polymorphism through Protocol (structural typing)
from typing import Protocol

class Drawable(Protocol):
    def draw(self): ...

class Rectangle:
    def draw(self):
        print("Drawing rectangle")

class Triangle:
    def draw(self):
        print("Drawing triangle")

def render(shape: Drawable):
    shape.draw()

shapes = [Rectangle(), Triangle()]
for s in shapes:
    render(s)

# Operator overloading (special form of polymorphism)
class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def __add__(self, other):
        return Vector(self.x + other.x, self.y + other.y)
    
    def __str__(self):
        return f"Vector({self.x}, {self.y})"

v1 = Vector(1, 2)
v2 = Vector(3, 4)
print(f"v1 + v2 = {v1 + v2}")  # Operator overloading
```

---

## 17. Abstraction

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Abstract Class | `abstract class Shape` | Simulated via `new.target` | `class Shape(ABC):` |
| Abstract Method | `abstract void draw();` | `draw() { throw Error(); }` | `@abstractmethod def draw(self):` |
| Interface | `interface Drawable { }` | Not natively supported | `class Drawable(Protocol):` |

### Full Runnable Examples

**Java** (`AbstractionDemo.java`):
```java
// Abstract class
abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method
    abstract double area();
    
    // Concrete method
    void display() {
        System.out.println("A " + color + " shape with area: " + area());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;
    
    public Rectangle(String color, double w, double h) {
        super(color);
        this.width = w;
        this.height = h;
    }
    
    @Override
    double area() {
        return width * height;
    }
}

// Interface
interface Drawable {
    void draw();  // implicitly public abstract
    
    // Default method (Java 8+)
    default void printInfo() {
        System.out.println("This is a drawable object");
    }
}

interface Resizable {
    void resize(double factor);
}

class GraphicCircle implements Drawable, Resizable {
    private double radius;
    
    GraphicCircle(double r) { this.radius = r; }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
    
    @Override
    public void resize(double factor) {
        this.radius *= factor;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        // Abstract class usage
        Shape s1 = new Circle("red", 5);
        Shape s2 = new Rectangle("blue", 4, 6);
        
        s1.display();
        s2.display();
        
        // Interface usage
        GraphicCircle gc = new GraphicCircle(10);
        gc.draw();
        gc.resize(2);
        gc.printInfo();  // Default method
    }
}
```

**JavaScript** (`abstraction.js`):
```javascript
// Abstract class simulation
class Shape {
    constructor(color) {
        if (new.target === Shape) {
            throw new Error("Cannot instantiate abstract class");
        }
        this.color = color;
    }
    
    // Abstract method (must be overridden)
    area() {
        throw new Error("Abstract method must be implemented");
    }
    
    // Concrete method
    display() {
        console.log(`A ${this.color} shape with area: ${this.area()}`);
    }
}

class Circle extends Shape {
    constructor(color, radius) {
        super(color);
        this.radius = radius;
    }
    
    area() {
        return Math.PI * this.radius ** 2;
    }
}

class Rect extends Shape {
    constructor(color, width, height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    area() {
        return this.width * this.height;
    }
}

// Interface simulation through duck typing
const Drawable = {
    draw() {
        throw new Error("Must implement draw()");
    }
};

class GraphicCircle {
    constructor(radius) {
        this.radius = radius;
    }
    
    draw() {
        console.log("Drawing circle");
    }
    
    resize(factor) {
        this.radius *= factor;
    }
}

// Usage
const shapes = [new Circle("red", 5), new Rect("blue", 4, 6)];
shapes.forEach(s => s.display());

const gc = new GraphicCircle(10);
gc.draw();
gc.resize(2);
```

**Python** (`abstraction.py`):
```python
from abc import ABC, abstractmethod

# Abstract class
class Shape(ABC):
    def __init__(self, color):
        self.color = color
    
    @abstractmethod
    def area(self):
        pass
    
    # Concrete method
    def display(self):
        print(f"A {self.color} shape with area: {self.area()}")

class Circle(Shape):
    def __init__(self, color, radius):
        super().__init__(color)
        self.radius = radius
    
    def area(self):
        return 3.14159 * self.radius ** 2

class Rectangle(Shape):
    def __init__(self, color, width, height):
        super().__init__(color)
        self.width = width
        self.height = height
    
    def area(self):
        return self.width * self.height

# Protocol (structural typing, like interface)
from typing import Protocol

class Drawable(Protocol):
    def draw(self): ...
    
    def resize(self, factor: float): ...

class GraphicCircle:
    def __init__(self, radius):
        self.radius = radius
    
    def draw(self):
        print("Drawing circle")
    
    def resize(self, factor):
        self.radius *= factor

# Using the protocol (duck typing)
def render(d: Drawable):
    d.draw()

# Usage
shapes = [Circle("red", 5), Rectangle("blue", 4, 6)]
for s in shapes:
    s.display()

gc = GraphicCircle(10)
render(gc)  # Works because it has draw()
gc.resize(2)

# Abstract class cannot be instantiated
# s = Shape("green")  # TypeError!
```

---

## 18. Encapsulation

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Private Field | `private String name;` | `#name` (ES2022+) | `self.__name` (name mangling) |
| Getter | `public String getName()` | `get name() { return #name; }` | `@property def name(self):` |
| Setter | `public void setName(String n)` | `set name(v) { #name = v; }` | `@name.setter def name(self, v):` |
| Protected | `protected String name;` | `_name` (convention) | `self._name` (convention) |

### Full Runnable Examples

**Java** (`EncapsulationDemo.java`):
```java
class BankAccount {
    // Private fields
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    // Constructor
    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getOwnerName() {
        return ownerName;
    }
    
    // Setter with validation
    public void setOwnerName(String ownerName) {
        if (ownerName != null && !ownerName.trim().isEmpty()) {
            this.ownerName = ownerName;
        }
    }
    
    // Business methods (controlled access)
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
            return true;
        }
        System.out.println("Insufficient funds");
        return false;
    }
    
    // Internal method (can be private)
    private void logTransaction(String type, double amount) {
        System.out.println("[" + type + "] Account: " + 
            accountNumber + " Amount: $" + amount);
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345", "Alice");
        
        account.deposit(1000);
        account.withdraw(200);
        
        System.out.println("Balance: $" + account.getBalance());
        System.out.println("Owner: " + account.getOwnerName());
        
        // Cannot access private fields directly
        // account.balance = 1000000;  // Error!
        // account.logTransaction("test", 0);  // Error!
    }
}
```

**JavaScript** (`encapsulation.js`):
```javascript
class BankAccount {
    // Private fields (ES2022+)
    #accountNumber;
    #balance;
    #ownerName;
    
    constructor(accountNumber, ownerName) {
        this.#accountNumber = accountNumber;
        this.#ownerName = ownerName;
        this.#balance = 0.0;
    }
    
    // Getters
    get accountNumber() {
        return this.#accountNumber;
    }
    
    get balance() {
        return this.#balance;
    }
    
    get ownerName() {
        return this.#ownerName;
    }
    
    // Setter with validation
    set ownerName(name) {
        if (name && name.trim().length > 0) {
            this.#ownerName = name;
        }
    }
    
    // Public methods
    deposit(amount) {
        if (amount > 0) {
            this.#balance += amount;
            console.log(`Deposited: $${amount}`);
        } else {
            console.log("Invalid deposit amount");
        }
    }
    
    withdraw(amount) {
        if (amount > 0 && amount <= this.#balance) {
            this.#balance -= amount;
            console.log(`Withdrew: $${amount}`);
            return true;
        }
        console.log("Insufficient funds");
        return false;
    }
    
    // Private method
    #logTransaction(type, amount) {
        console.log(`[${type}] Account: ${this.#accountNumber} Amount: $${amount}`);
    }
}

// Usage
const account = new BankAccount("12345", "Alice");
account.deposit(1000);
account.withdraw(200);
console.log(`Balance: $${account.balance}`);
console.log(`Owner: ${account.ownerName}`);

// Cannot access private fields
// console.log(account.#balance);  // SyntaxError!
```

**Python** (`encapsulation.py`):
```python
class BankAccount:
    def __init__(self, account_number, owner_name):
        # Private attributes (name mangling)
        self.__account_number = account_number
        self.__balance = 0.0
        # Protected attribute (convention only)
        self._owner_name = owner_name
    
    # Property (getter)
    @property
    def balance(self):
        return self.__balance
    
    @property
    def account_number(self):
        return self.__account_number
    
    @property
    def owner_name(self):
        return self._owner_name
    
    # Property setter with validation
    @owner_name.setter
    def owner_name(self, name):
        if name and name.strip():
            self._owner_name = name
        else:
            raise ValueError("Name cannot be empty")
    
    # Public methods
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print(f"Deposited: ${amount}")
        else:
            print("Invalid deposit amount")
    
    def withdraw(self, amount):
        if amount > 0 and amount <= self.__balance:
            self.__balance -= amount
            print(f"Withdrew: ${amount}")
            return True
        print("Insufficient funds")
        return False
    
    # Private method (name mangled)
    def __log_transaction(self, type_, amount):
        print(f"[{type_}] Account: {self.__account_number} Amount: ${amount}")

# Usage
account = BankAccount("12345", "Alice")
account.deposit(1000)
account.withdraw(200)
print(f"Balance: ${account.balance}")
print(f"Owner: {account.owner_name}")

# Accessing "private" fields (name mangling - possible but not recommended)
print(f"Hacked balance: {account._BankAccount__balance}")

# Protected is just a convention
print(f"Owner (protected): {account._owner_name}")
```

---

## 19. Interfaces & Protocols

### Full Runnable Examples

**Java** (`InterfacesDemo.java`):
```java
// Interface
interface Drawable {
    void draw();
    
    // Default method (Java 8+)
    default void printInfo() {
        System.out.println("Drawable object");
    }
    
    // Static method (Java 8+)
    static boolean isDrawable(Object obj) {
        return obj instanceof Drawable;
    }
}

// Another interface
interface Scalable {
    void scale(double factor);
}

// Single interface implementation
class Circle implements Drawable {
    private double radius;
    
    Circle(double r) { this.radius = r; }
    
    @Override
    public void draw() {
        System.out.println("Drawing circle with radius " + radius);
    }
}

// Multiple interface implementation
class Rectangle implements Drawable, Scalable {
    private double width, height;
    
    Rectangle(double w, double h) { this.width = w; this.height = h; }
    
    @Override
    public void draw() {
        System.out.println("Drawing rectangle " + width + "x" + height);
    }
    
    @Override
    public void scale(double factor) {
        width *= factor;
        height *= factor;
    }
}

// Functional Interface (Single Abstract Method)
@FunctionalInterface
interface Processor {
    String process(String input);
}

public class InterfacesDemo {
    public static void main(String[] args) {
        Drawable circle = new Circle(5);
        circle.draw();
        circle.printInfo();
        System.out.println("Is drawable? " + Drawable.isDrawable(circle));
        
        Rectangle rect = new Rectangle(4, 6);
        rect.draw();
        rect.scale(2);
        rect.draw();
        
        // Lambda (functional interface)
        Processor upper = s -> s.toUpperCase();
        System.out.println("Processed: " + upper.process("hello"));
    }
}
```

**JavaScript** (`interfaces.js`):
```javascript
// JavaScript doesn't have interfaces - using duck typing
const Drawable = {
    draw() {
        throw new Error("Must implement draw()");
    }
};

const Scalable = {
    scale(factor) {
        throw new Error("Must implement scale()");
    }
};

class Circle {
    constructor(radius) {
        this.radius = radius;
    }
    
    draw() {
        console.log(`Drawing circle with radius ${this.radius}`);
    }
}

class Rectangle {
    constructor(width, height) {
        this.width = width;
        this.height = height;
    }
    
    draw() {
        console.log(`Drawing rectangle ${this.width}x${this.height}`);
    }
    
    scale(factor) {
        this.width *= factor;
        this.height *= factor;
    }
}

// Duck typing check
function drawShape(shape) {
    if (typeof shape.draw === 'function') {
        shape.draw();
    }
}

// Type checking with Symbol
const DrawableSymbol = Symbol('Drawable');
class Shape {
    constructor() {
        this[DrawableSymbol] = true;
    }
}

// Usage
const circle = new Circle(5);
const rect = new Rectangle(4, 6);

drawShape(circle);
drawShape(rect);

rect.scale(2);
drawShape(rect);
```

**Python** (`interfaces_demo.py`):
```python
from typing import Protocol, runtime_checkable

# Protocol (structural subtyping)
@runtime_checkable
class Drawable(Protocol):
    def draw(self): ...

@runtime_checkable
class Scalable(Protocol):
    def scale(self, factor: float): ...

# These classes don't inherit from Drawable/Scalable
# but satisfy the protocol through structural typing
class Circle:
    def __init__(self, radius):
        self.radius = radius
    
    def draw(self):
        print(f"Drawing circle with radius {self.radius}")

class Rectangle:
    def __init__(self, width, height):
        self.width = width
        self.height = height
    
    def draw(self):
        print(f"Drawing rectangle {self.width}x{self.height}")
    
    def scale(self, factor):
        self.width *= factor
        self.height *= factor

# ABC (formal interface)
from abc import ABC, abstractmethod

class Processor(ABC):
    @abstractmethod
    def process(self, input_str: str) -> str:
        pass

class UpperProcessor(Processor):
    def process(self, input_str: str) -> str:
        return input_str.upper()

# Usage
def draw_shape(shape: Drawable):
    if isinstance(shape, Drawable):  # runtime_checkable allows this
        shape.draw()
    else:
        print("Not drawable")

circle = Circle(5)
rect = Rectangle(4, 6)

draw_shape(circle)
draw_shape(rect)

# Check protocol membership
print(f"Is Drawable? {isinstance(circle, Drawable)}")  # True

# ABC usage
proc = UpperProcessor()
print(f"Processed: {proc.process('hello')}")
```

---

## 20. Error Handling

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Try-Catch | `try { } catch(E e) { }` | `try { } catch(e) { }` | `try: ... except: ...` |
| Finally | `finally { }` | `finally { }` | `finally: ...` |
| Throw | `throw new Exception()` | `throw new Error()` | `raise Exception()` |
| Custom | `class MyEx extends Exception { }` | `class MyError extends Error { }` | `class MyError(Exception):` |
| Try-with-Resources | `try (FileReader fr = ...) { }` | Not supported | `with open(...) as f: ...` |

### Full Runnable Examples

**Java** (`ErrorsDemo.java`):
```java
import java.io.*;

// Custom exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
    
    public InsufficientFundsException(String message, double amount) {
        super(message + " Needed: $" + amount);
    }
}

class BankAccount {
    private double balance;
    
    public BankAccount(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient funds", amount - balance);
        }
        balance -= amount;
    }
    
    public double getBalance() { return balance; }
}

public class ErrorsDemo {
    public static void main(String[] args) {
        // Try-Catch-Finally
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block always runs");
        }
        
        // Multiple catch blocks
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("Null pointer: " + e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
        }
        
        // Custom exception
        BankAccount account = new BankAccount(500);
        try {
            account.withdraw(1000);
        } catch (InsufficientFundsException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }
        
        // Try-with-resources (auto close)
        try (BufferedReader br = new BufferedReader(
                new FileReader("test.txt"))) {
            String line = br.readLine();
            System.out.println("Read: " + line);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
        
        // Finally with return
        System.out.println("Result: " + getValue());
    }
    
    static int getValue() {
        try {
            return 42;
        } finally {
            System.out.println("Cleanup in finally");
        }
    }
}
```

**JavaScript** (`errors.js`):
```javascript
// Custom error
class InsufficientFundsError extends Error {
    constructor(message, needed) {
        super(message);
        this.name = "InsufficientFundsError";
        this.needed = needed;
    }
}

class BankAccount {
    constructor(balance) {
        this.balance = balance;
    }
    
    withdraw(amount) {
        if (amount > this.balance) {
            throw new InsufficientFundsError(
                "Insufficient funds", amount - this.balance);
        }
        this.balance -= amount;
    }
}

// Try-Catch-Finally
try {
    const result = 10 / 0;
    console.log("Result:", result);  // Infinity (no error in JS)
} catch (e) {
    console.log("Caught:", e.message);
} finally {
    console.log("Finally block always runs");
}

// TypeError
try {
    let x = null;
    console.log(x.length);
} catch (e) {
    if (e instanceof TypeError) {
        console.log("TypeError:", e.message);
    }
}

// Custom error
const account = new BankAccount(500);
try {
    account.withdraw(1000);
} catch (e) {
    if (e instanceof InsufficientFundsError) {
        console.log(`${e.name}: ${e.message}, Needed: $${e.needed}`);
    }
}

// Finally with return
function getValue() {
    try {
        return 42;
    } finally {
        console.log("Cleanup in finally");
    }
}
console.log("Result:", getValue());

// Async error
async function fetchData() {
    try {
        const response = await fetch("https://api.example.com");
        const data = await response.json();
        return data;
    } catch (e) {
        console.error("Failed to fetch:", e.message);
    }
}

// Promise error handling
Promise.resolve(42)
    .then(v => { throw new Error("Boom!"); })
    .catch(e => console.log("Caught in promise chain:", e.message));
```

**Python** (`errors_demo.py`):
```python
# Custom exception
class InsufficientFundsError(Exception):
    def __init__(self, message, needed=0):
        self.needed = needed
        super().__init__(f"{message}, Needed: ${needed}")

class BankAccount:
    def __init__(self, balance):
        self.balance = balance
    
    def withdraw(self, amount):
        if amount > self.balance:
            raise InsufficientFundsError("Insufficient funds", amount - self.balance)
        self.balance -= amount

# Try-Except-Finally
try:
    result = 10 / 0
    print(f"Result: {result}")
except ZeroDivisionError as e:
    print(f"Caught: {e}")
finally:
    print("Finally block always runs")

# Multiple except blocks
try:
    x = None
    print(len(x))
except TypeError as e:
    print(f"Type error: {e}")
except Exception as e:
    print(f"General error: {e}")

# Custom exception
account = BankAccount(500)
try:
    account.withdraw(1000)
except InsufficientFundsError as e:
    print(f"Custom exception: {e}")
    print(f"Needed: ${e.needed}")

# Else clause (runs if no exception)
try:
    result = 10 / 2
except ZeroDivisionError:
    print("Can't divide by zero")
else:
    print(f"Division successful: {result}")

# Context manager (try-with-resources equivalent)
# No need for explicit try-finally:
with open("test.txt", "w") as f:
    f.write("Hello!")

# Custom context manager
class ManagedResource:
    def __enter__(self):
        print("Acquiring resource")
        return self
    
    def __exit__(self, exc_type, exc_val, exc_tb):
        print("Releasing resource")
        return False  # Don't suppress exceptions
    
    def do_something(self):
        print("Doing something")

with ManagedResource() as resource:
    resource.do_something()

# Raise from (exception chaining)
try:
    try:
        1 / 0
    except ZeroDivisionError as e:
        raise RuntimeError("Something went wrong") from e
except RuntimeError as e:
    print(f"Chained: {e}")
    print(f"Cause: {e.__cause__}")
```

---

## 21. File I/O

### Side-by-Side Comparison

| Operation | Java | JavaScript (Node.js) | Python |
|-----------|------|---------------------|--------|
| Read File | `Files.readString(Path.of("f.txt"))` | `fs.readFileSync("f.txt","utf8")` | `with open("f.txt") as f: data = f.read()` |
| Write File | `Files.writeString(Path.of("f.txt"), "data")` | `fs.writeFileSync("f.txt", "data")` | `with open("f.txt","w") as f: f.write("data")` |
| Append | `Files.write(path, data, APPEND)` | `fs.appendFileSync("f.txt", "data")` | `with open("f.txt","a") as f: f.write("data")` |
| Exists | `Files.exists(path)` | `fs.existsSync("f.txt")` | `os.path.exists("f.txt")` |
| Delete | `Files.delete(path)` | `fs.unlinkSync("f.txt")` | `os.remove("f.txt")` |

### Full Runnable Examples

**Java** (`FileIODemo.java`):
```java
import java.nio.file.*;
import java.io.*;
import java.util.List;

public class FileIODemo {
    public static void main(String[] args) throws IOException {
        String filename = "test.txt";
        String content = "Hello File I/O!\nThis is Java.\nLine 3.";
        
        // Write file
        Files.writeString(Path.of(filename), content);
        System.out.println("File written successfully");
        
        // Read entire file
        String read = Files.readString(Path.of(filename));
        System.out.println("Read content:\n" + read);
        
        // Read lines
        List<String> lines = Files.readAllLines(Path.of(filename));
        System.out.println("Lines: " + lines);
        
        // Append to file
        Files.writeString(Path.of(filename), "\nAppended line!", 
            StandardOpenOption.APPEND);
        
        // Check existence
        System.out.println("File exists: " + Files.exists(Path.of(filename)));
        
        // File info
        Path path = Path.of(filename);
        System.out.println("Size: " + Files.size(path) + " bytes");
        System.out.println("Last modified: " + Files.getLastModifiedTime(path));
        
        // Buffered (for larger files)
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("buffered.txt"))) {
            writer.write("Buffered write test");
        }
        
        try (BufferedReader reader = new BufferedReader(
                new FileReader("buffered.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Buffered read: " + line);
            }
        }
        
        // Delete file
        Files.delete(Path.of("buffered.txt"));
        Files.delete(Path.of(filename));
        System.out.println("Files deleted");
    }
}
```

**JavaScript** (`file-io.js`):
```javascript
const fs = require('fs');
const path = require('path');

const filename = "test.txt";
const content = "Hello File I/O!\nThis is JavaScript.\nLine 3.";

// Write file (synchronous)
fs.writeFileSync(filename, content);
console.log("File written successfully");

// Read file (synchronous)
const read = fs.readFileSync(filename, 'utf8');
console.log("Read content:\n" + read);

// Read lines
const lines = read.split('\n');
console.log("Lines:", lines);

// Append
fs.appendFileSync(filename, "\nAppended line!");
console.log("Content after append:");
console.log(fs.readFileSync(filename, 'utf8'));

// Check existence
console.log("File exists:", fs.existsSync(filename));

// File info
const stats = fs.statSync(filename);
console.log("Size:", stats.size, "bytes");
console.log("Last modified:", stats.mtime);
console.log("Is file:", stats.isFile());

// Async version
fs.writeFile('async.txt', 'Async content', (err) => {
    if (err) throw err;
    console.log("Async write complete");
    
    fs.readFile('async.txt', 'utf8', (err, data) => {
        if (err) throw err;
        console.log("Async read:", data);
        
        // Cleanup
        fs.unlink('async.txt', () => {});
    });
});

// Stream (for large files)
const readStream = fs.createReadStream(filename, 'utf8');
readStream.on('data', (chunk) => {
    console.log("Stream chunk:", chunk);
});

// Directory operations
fs.mkdirSync('test-dir', { recursive: true });
console.log("Directory created");
fs.rmdirSync('test-dir');

// Delete
fs.unlinkSync(filename);
console.log("File deleted");
```

**Python** (`file_io.py`):
```python
import os

filename = "test.txt"
content = "Hello File I/O!\nThis is Python.\nLine 3."

# Write file
with open(filename, "w") as f:
    f.write(content)
print("File written successfully")

# Read entire file
with open(filename, "r") as f:
    read = f.read()
print(f"Read content:\n{read}")

# Read lines
with open(filename, "r") as f:
    lines = f.readlines()
print(f"Lines: {lines}")

# Read line by line
print("Line by line:")
with open(filename, "r") as f:
    for line in f:
        print(f"  Line: {line.strip()}")

# Append
with open(filename, "a") as f:
    f.write("\nAppended line!")
print("Content after append:")
with open(filename) as f:
    print(f.read())

# Check existence
print(f"File exists: {os.path.exists(filename)}")

# File info
print(f"Size: {os.path.getsize(filename)} bytes")
print(f"Last modified: {os.path.getmtime(filename)}")
print(f"Is file: {os.path.isfile(filename)}")

# Path operations
print(f"Basename: {os.path.basename(filename)}")
print(f"Dirname: {os.path.dirname(filename)}")
print(f"Join: {os.path.join('dir', 'subdir', filename)}")

# Directory operations
os.makedirs("test_dir", exist_ok=True)
print("Directory created")
os.rmdir("test_dir")

# Delete
os.remove(filename)
print("File deleted")

# Context manager auto-closes
with open("auto_close.txt", "w") as f:
    f.write("Auto closed")
# File is already closed here

# Read binary
with open("auto_close.txt", "rb") as f:
    data = f.read()
    print(f"Binary read: {data}")

os.remove("auto_close.txt")
```

---

## 22. JSON & Serialization

### Full Runnable Examples

**Java** (`JsonDemo.java`):
```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.*;
import java.util.*;

// Note: Requires Jackson dependency
public class JsonDemo {
    public static void main(String[] args) throws Exception {
        // Manual JSON building
        String json = "{\"name\":\"Alice\",\"age\":30,\"active\":true}";
        
        // Using Jackson ObjectMapper (most common)
        ObjectMapper mapper = new ObjectMapper();
        
        // Parse to Map
        Map<String, Object> map = mapper.readValue(json, Map.class);
        System.out.println("Parsed map: " + map);
        System.out.println("Name: " + map.get("name"));
        
        // Parse to custom object
        Person person = mapper.readValue(json, Person.class);
        System.out.println("Person: " + person);
        System.out.println("Name: " + person.getName());
        
        // Object to JSON
        Person output = new Person("Bob", 25, true);
        String outputJson = mapper.writeValueAsString(output);
        System.out.println("To JSON: " + outputJson);
        
        // Pretty print
        String pretty = mapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(output);
        System.out.println("Pretty JSON:\n" + pretty);
        
        // List to JSON
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, true),
            new Person("Bob", 25, false)
        );
        String listJson = mapper.writeValueAsString(people);
        System.out.println("List to JSON: " + listJson);
        
        // JSON to List
        List<Person> parsedList = mapper.readValue(
            listJson, 
            mapper.getTypeFactory().constructCollectionType(List.class, Person.class)
        );
        System.out.println("Parsed list: " + parsedList);
    }
}

class Person {
    private String name;
    private int age;
    private boolean active;
    
    public Person() {}  // Default constructor needed
    
    public Person(String name, int age, boolean active) {
        this.name = name; this.age = age; this.active = active;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public String toString() {
        return "Person(name=" + name + ", age=" + age + ", active=" + active + ")";
    }
}
```

**JavaScript** (`json.js`):
```javascript
const fs = require('fs');

// Object to JSON string
const person = {
    name: "Alice",
    age: 30,
    active: true,
    hobbies: ["reading", "coding"],
    address: null
};

const jsonString = JSON.stringify(person);
console.log("JSON string:", jsonString);

// Pretty print
const prettyJson = JSON.stringify(person, null, 2);
console.log("Pretty JSON:\n", prettyJson);

// Custom serialization (replacer)
const filtered = JSON.stringify(person, (key, value) => {
    if (key === 'password') return undefined;
    if (typeof value === 'string') return value.toUpperCase();
    return value;
}, 2);
console.log("Filtered:\n", filtered);

// JSON string to object
const json = '{"name":"Bob","age":25,"active":false}';
const parsed = JSON.parse(json);
console.log("Parsed:", parsed);
console.log("Name:", parsed.name);

// Reviver (transform during parse)
const dateJson = '{"name":"Event","date":"2024-12-25T10:00:00Z"}';
const withDate = JSON.parse(dateJson, (key, value) => {
    if (key === 'date') return new Date(value);
    return value;
});
console.log("With date:", withDate.date instanceof Date);

// Array to JSON
const people = [
    { name: "Alice", age: 30 },
    { name: "Bob", age: 25 }
];
console.log("Array JSON:", JSON.stringify(people));

// Deep copy
const original = { a: 1, b: { c: 2 } };
const deepCopy = JSON.parse(JSON.stringify(original));
console.log("Deep copy:", deepCopy);

// Custom toJSON
class User {
    constructor(name, password) {
        this.name = name;
        this.password = password;
    }
    
    toJSON() {
        return {
            name: this.name,
            // Don't include password
        };
    }
}

const user = new User("Alice", "secret123");
console.log("User to JSON:", JSON.stringify(user));

// Write JSON to file
fs.writeFileSync('data.json', JSON.stringify(person, null, 2));
console.log("Written to data.json");

// Read JSON from file
const fileData = JSON.parse(fs.readFileSync('data.json', 'utf8'));
console.log("Read from file:", fileData);

// Cleanup
fs.unlinkSync('data.json');
```

**Python** (`json_demo.py`):
```python
import json
from dataclasses import dataclass, asdict

# Object to JSON string
person = {
    "name": "Alice",
    "age": 30,
    "active": True,
    "hobbies": ["reading", "coding"],
    "address": None
}

json_string = json.dumps(person)
print(f"JSON string: {json_string}")

# Pretty print
pretty_json = json.dumps(person, indent=2)
print(f"Pretty JSON:\n{pretty_json}")

# Custom serialization
class Person:
    def __init__(self, name, age, active):
        self.name = name
        self.age = age
        self.active = active

# Using dataclass for simple serialization
@dataclass
class PersonData:
    name: str
    age: int
    active: bool

# Convert dataclass to dict then JSON
p = PersonData("Alice", 30, True)
print(f"Dataclass to JSON: {json.dumps(asdict(p))}")

# Custom encoder
class PersonEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, Person):
            return {"name": obj.name, "age": obj.age, "active": obj.active}
        return super().default(obj)

p2 = Person("Bob", 25, False)
print(f"Custom encoder: {json.dumps(p2, cls=PersonEncoder)}")

# JSON string to object
json_str = '{"name": "Bob", "age": 25, "active": false}'
parsed = json.loads(json_str)
print(f"Parsed: {parsed}")
print(f"Name: {parsed['name']}")

# JSON to dataclass
p3 = PersonData(**parsed)
print(f"As dataclass: {p3}")

# Parse with object hook
def person_from_dict(d):
    if all(k in d for k in ("name", "age", "active")):
        return Person(d["name"], d["age"], d["active"])
    return d

json_str2 = '{"name": "Charlie", "age": 35, "active": true}'
p4 = json.loads(json_str2, object_hook=person_from_dict)
print(f"With object hook: {p4}")

# Array to JSON
people = [
    {"name": "Alice", "age": 30},
    {"name": "Bob", "age": 25}
]
print(f"Array JSON: {json.dumps(people)}")

# Deep copy
original = {"a": 1, "b": {"c": 2}}
deep_copy = json.loads(json.dumps(original))
print(f"Deep copy: {deep_copy}")

# File I/O with JSON
with open("data.json", "w") as f:
    json.dump(person, f, indent=2)
print("Written to data.json")

with open("data.json", "r") as f:
    file_data = json.load(f)
print(f"Read from file: {file_data}")

# Cleanup (not deleting in demo)
import os
os.remove("data.json")
```

---

## 23. HTTP & Networking

### Full Runnable Examples

**Java** (`HttpDemo.java`):
```java
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpDemo {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        // GET request
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .GET()
            .build();
        
        HttpResponse<String> getResponse = client.send(getRequest, 
            BodyHandlers.ofString());
        
        System.out.println("GET Status: " + getResponse.statusCode());
        System.out.println("GET Body: " + getResponse.body().substring(0, 100) + "...");
        
        // POST request
        String json = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        
        HttpResponse<String> postResponse = client.send(postRequest, 
            BodyHandlers.ofString());
        
        System.out.println("POST Status: " + postResponse.statusCode());
        System.out.println("POST Body: " + postResponse.body());
        
        // Async request
        client.sendAsync(getRequest, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(body -> System.out.println("Async: " + body.substring(0, 50)))
            .join();  // Wait for completion
    }
}
```

**JavaScript** (`http.js`):
```javascript
const https = require('https');
const http = require('http');

// Using fetch (Node.js 18+)
async function fetchExample() {
    // GET request
    const getResponse = await fetch(
        'https://jsonplaceholder.typicode.com/posts/1');
    const getData = await getResponse.json();
    console.log("GET:", getData.title);
    
    // POST request
    const postResponse = await fetch(
        'https://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            title: 'foo',
            body: 'bar',
            userId: 1
        })
    });
    const postData = await postResponse.json();
    console.log("POST:", postData);
    
    // PUT
    const putResponse = await fetch(
        'https://jsonplaceholder.typicode.com/posts/1', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: 1, title: 'updated' })
    });
    console.log("PUT status:", putResponse.status);
    
    // DELETE
    const deleteResponse = await fetch(
        'https://jsonplaceholder.typicode.com/posts/1', {
        method: 'DELETE'
    });
    console.log("DELETE status:", deleteResponse.status);
}

// Using http/https module (lower level)
function httpModuleExample() {
    const options = {
        hostname: 'jsonplaceholder.typicode.com',
        path: '/posts/1',
        method: 'GET'
    };
    
    const req = https.request(options, (res) => {
        let data = '';
        res.on('data', chunk => data += chunk);
        res.on('end', () => {
            console.log("HTTPS module:", JSON.parse(data).title);
        });
    });
    
    req.on('error', console.error);
    req.end();
}

// URL parsing
const url = new URL('https://api.example.com/search?q=hello&page=1');
console.log("Hostname:", url.hostname);
console.log("Path:", url.pathname);
console.log("Query:", url.searchParams.get('q'));
console.log("All params:", Object.fromEntries(url.searchParams));

fetchExample().catch(console.error);
httpModuleExample();
```

**Python** (`http_demo.py`):
```python
import requests
import json
from urllib.parse import urlparse, parse_qs

# GET request
get_response = requests.get(
    'https://jsonplaceholder.typicode.com/posts/1')
print(f"GET Status: {get_response.status_code}")
data = get_response.json()
print(f"GET Title: {data['title']}")
print(f"GET Headers: {dict(get_response.headers)}")

# POST request
post_response = requests.post(
    'https://jsonplaceholder.typicode.com/posts',
    json={"title": "foo", "body": "bar", "userId": 1}
)
print(f"POST Status: {post_response.status_code}")
print(f"POST Response: {post_response.json()}")

# PUT request
put_response = requests.put(
    'https://jsonplaceholder.typicode.com/posts/1',
    json={"id": 1, "title": "updated", "body": "new body", "userId": 1}
)
print(f"PUT Status: {put_response.status_code}")

# DELETE request
delete_response = requests.delete(
    'https://jsonplaceholder.typicode.com/posts/1')
print(f"DELETE Status: {delete_response.status_code}")

# Query parameters
params_response = requests.get(
    'https://jsonplaceholder.typicode.com/posts',
    params={"userId": 1, "_limit": 3}
)
print(f"Params count: {len(params_response.json())}")

# Headers
headers_response = requests.get(
    'https://jsonplaceholder.typicode.com/posts/1',
    headers={"Authorization": "Bearer token123"}
)
print(f"Headers sent OK: {headers_response.ok}")

# Session (reuse connection)
session = requests.Session()
session.headers.update({"User-Agent": "MyApp/1.0"})

resp1 = session.get('https://jsonplaceholder.typicode.com/posts/1')
resp2 = session.get('https://jsonplaceholder.typicode.com/posts/2')
print(f"Session requests: {resp1.status_code}, {resp2.status_code}")

# Error handling
try:
    bad_response = requests.get('https://nonexistent.example.com', timeout=5)
except requests.exceptions.RequestException as e:
    print(f"Request failed: {e}")

# URL parsing
url = "https://api.example.com/search?q=hello&page=1"
parsed = urlparse(url)
print(f"Hostname: {parsed.hostname}")
print(f"Path: {parsed.path}")
print(f"Query params: {parse_qs(parsed.query)}")

# Response encoding
r = requests.get('https://jsonplaceholder.typicode.com')
print(f"Encoding: {r.encoding}")
print(f"Content type: {r.headers.get('content-type')}")
```

---

## 24. Threads & Concurrency

### Side-by-Side Comparison

| Concept | Java | JavaScript (Node.js) | Python |
|---------|------|---------------------|--------|
| Thread Creation | `new Thread(() -> {}).start()` | `new Worker('./worker.js')` | `threading.Thread(target=fn)` |
| Synchronization | `synchronized` block | Not needed (single-threaded) | `threading.Lock()` |
| Thread Pool | `Executors.newFixedThreadPool(4)` | Not applicable | `ThreadPoolExecutor(max_workers=4)` |
| Atomic | `AtomicInteger` | `Atomics` (SharedArrayBuffer) | Not native (use locks) |

### Full Runnable Examples

**Java** (`ThreadsDemo.java`):
```java
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsDemo {
    public static void main(String[] args) throws Exception {
        // 1. Extending Thread
        class MyThread extends Thread {
            public void run() {
                System.out.println("Thread: " + getName());
            }
        }
        new MyThread().start();
        
        // 2. Implementing Runnable
        Thread t2 = new Thread(() -> {
            System.out.println("Runnable: " + Thread.currentThread().getName());
        });
        t2.start();
        
        // 3. Thread Pool
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + 
                    " on " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        
        // 4. Callable (return value)
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> future = pool.submit(() -> {
            Thread.sleep(500);
            return 42;
        });
        System.out.println("Future result: " + future.get());
        pool.shutdown();
        
        // 5. Synchronization
        Counter counter = new Counter();
        ExecutorService syncPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            syncPool.submit(counter::increment);
        }
        syncPool.shutdown();
        syncPool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Counter: " + counter.getCount());
        
        // 6. Atomic Variables
        AtomicInteger atomic = new AtomicInteger(0);
        ExecutorService atomicPool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            atomicPool.submit(atomic::incrementAndGet);
        }
        atomicPool.shutdown();
        atomicPool.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Atomic: " + atomic.get());
        
        // 7. CompletableFuture (async)
        CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenApply(s -> s + " World")
          .thenAccept(System.out::println)
          .join();
    }
}

class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

**JavaScript** (`threads.js`):
```javascript
// JavaScript is single-threaded (event loop)
// Worker threads for CPU-intensive tasks

// main.js
const { Worker } = require('worker_threads');

function runWorker(data) {
    return new Promise((resolve, reject) => {
        const worker = new Worker('./worker.js', {
            workerData: data
        });
        
        worker.on('message', resolve);
        worker.on('error', reject);
        worker.on('exit', (code) => {
            if (code !== 0) {
                reject(new Error(`Worker stopped with code ${code}`));
            }
        });
    });
}

async function main() {
    console.log("Main thread running");
    
    // Run worker
    const result = await runWorker({ iterations: 1000000 });
    console.log("Worker result:", result);
    
    // Multiple workers
    const workers = await Promise.all([
        runWorker({ iterations: 500000 }),
        runWorker({ iterations: 500000 }),
        runWorker({ iterations: 500000 })
    ]);
    console.log("All workers done:", workers);
}

main().catch(console.error);

// setTimeout (async but stays in main thread)
console.log("Before timeout");
setTimeout(() => {
    console.log("Timeout executed");
}, 100);
console.log("After timeout");

// setInterval
const interval = setInterval(() => {
    console.log("Interval tick");
}, 1000);

// Clear after 3 seconds
setTimeout(() => {
    clearInterval(interval);
    console.log("Interval cleared");
}, 3000);

// Event loop demonstration
console.log("Start");
Promise.resolve().then(() => console.log("Microtask 1"));
process.nextTick(() => console.log("Next tick"));
setTimeout(() => console.log("Timer"), 0);
console.log("End");
// Output: Start, End, Next tick, Microtask 1, Timer
```

**Worker file** (`worker.js`):
```javascript
const { parentPort, workerData } = require('worker_threads');

// CPU-intensive task
function heavyComputation(iterations) {
    let result = 0;
    for (let i = 0; i < iterations; i++) {
        result += Math.sqrt(i);
    }
    return result;
}

const result = heavyComputation(workerData.iterations);
parentPort.postMessage(result);
```

**Python** (`threads_demo.py`):
```python
import threading
import time
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor
import queue

# 1. Basic Thread
def worker(name):
    print(f"Thread {name} starting")
    time.sleep(1)
    print(f"Thread {name} finished")

t = threading.Thread(target=worker, args=("A",))
t.start()
t.join()

# 2. Thread with Lock
counter = 0
lock = threading.Lock()

def increment():
    global counter
    for _ in range(100000):
        with lock:  # Acquire/release automatically
            counter += 1

threads = [threading.Thread(target=increment) for _ in range(4)]
for t in threads: t.start()
for t in threads: t.join()
print(f"Counter with lock: {counter}")

# Without lock (race condition)
counter2 = 0
def increment_no_lock():
    global counter2
    for _ in range(100000):
        counter2 += 1  # Race condition!

threads2 = [threading.Thread(target=increment_no_lock) for _ in range(4)]
for t in threads2: t.start()
for t in threads2: t.join()
print(f"Counter without lock: {counter2} (may not be 400000)")

# 3. Thread Pool
def task(n):
    time.sleep(0.1)
    return n ** 2

with ThreadPoolExecutor(max_workers=3) as executor:
    futures = [executor.submit(task, i) for i in range(10)]
    for f in futures:
        print(f"Result: {f.result()}")

# 4. Thread-safe Queue
q = queue.Queue()

def producer():
    for i in range(5):
        q.put(i)
        time.sleep(0.1)
    q.put(None)  # Sentinel

def consumer():
    while True:
        item = q.get()
        if item is None:
            break
        print(f"Consumed: {item}")

prod = threading.Thread(target=producer)
cons = threading.Thread(target=consumer)
prod.start()
cons.start()
prod.join()
cons.join()

# 5. Event (signaling)
event = threading.Event()

def waiter():
    print("Waiting for event...")
    event.wait()
    print("Event received!")

def setter():
    time.sleep(1)
    print("Setting event")
    event.set()

w = threading.Thread(target=waiter)
s = threading.Thread(target=setter)
w.start()
s.start()
w.join()
s.join()

# 6. Thread Local Data
local_data = threading.local()

def set_and_show():
    local_data.value = threading.current_thread().name
    time.sleep(0.1)
    print(f"{threading.current_thread().name}: {local_data.value}")

threads3 = [threading.Thread(target=set_and_show) for _ in range(3)]
for t in threads3: t.start()
for t in threads3: t.join()

# 7. Process Pool (bypasses GIL)
def cpu_intensive(n):
    """Useful for CPU-bound tasks"""
    result = 0
    for i in range(n):
        result += i ** 0.5
    return result

with ProcessPoolExecutor(max_workers=4) as executor:
    future = executor.submit(cpu_intensive, 1000000)
    print(f"Process result: {future.result()}")
```

---

## 25. Async/Await

### Side-by-Side Comparison

| Concept | Java | JavaScript | Python |
|---------|------|-----------|--------|
| Async Function | `CompletableFuture.supplyAsync(() -> { })` | `async function f() { }` | `async def f():` |
| Await | `.get()` (blocks) or `thenAccept()` | `await promise` | `await coroutine` |
| Promise/Future | `CompletableFuture<T>` | `Promise` | `asyncio.Future` |
| Parallel | `CompletableFuture.allOf(f1, f2)` | `Promise.all([p1, p2])` | `asyncio.gather(c1, c2)` |

### Full Runnable Examples

**Java** (`AsyncDemo.java`):
```java
import java.util.concurrent.*;

public class AsyncDemo {
    public static void main(String[] args) throws Exception {
        // 1. Basic CompletableFuture
        CompletableFuture<String> future = CompletableFuture
            .supplyAsync(() -> {
                sleep(500);
                return "Hello";
            });
        
        // Blocking get (not recommended in production)
        System.out.println("Blocking get: " + future.get());
        
        // 2. Chaining (non-blocking)
        CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "Hello";
        }).thenApply(s -> {
            return s + " World";
        }).thenApply(String::toUpperCase)
          .thenAccept(System.out::println)
          .join();  // Wait for completion
        
        System.out.println("After chain (runs concurrently)");
        
        // 3. Error handling
        CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) throw new RuntimeException("Failed!");
            return "Success";
        }).exceptionally(e -> {
            System.out.println("Error: " + e.getMessage());
            return "Fallback";
        }).thenAccept(System.out::println)
          .join();
        
        // 4. Combine two futures
        CompletableFuture<String> f1 = CompletableFuture
            .supplyAsync(() -> { sleep(200); return "First"; });
        CompletableFuture<String> f2 = CompletableFuture
            .supplyAsync(() -> { sleep(300); return "Second"; });
        
        String combined = f1.thenCombine(f2, (a, b) -> a + " + " + b).join();
        System.out.println("Combined: " + combined);
        
        // 5. Multiple futures (all)
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> "A");
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> "B");
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> "C");
        
        CompletableFuture.allOf(a, b, c).join();
        System.out.println("All completed: " + a.join() + b.join() + c.join());
        
        // 6. Any of
        CompletableFuture<String> slow = CompletableFuture
            .supplyAsync(() -> { sleep(500); return "Slow"; });
        CompletableFuture<String> fast = CompletableFuture
            .supplyAsync(() -> { sleep(100); return "Fast"; });
        
        String first = CompletableFuture.anyOf(slow, fast).join().toString();
        System.out.println("First to complete: " + first);
    }
    
    static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { }
    }
}
```

**JavaScript** (`async.js`):
```javascript
// Simulate async operations
function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function fetchData(id) {
    await delay(500);
    return `Data ${id}`;
}

// 1. Basic async/await
async function basicExample() {
    console.log("Start");
    const result = await fetchData(1);
    console.log("Result:", result);
    console.log("End");
}

// 2. Sequential vs Parallel
async function sequentialVsParallel() {
    console.time("Sequential");
    const r1 = await fetchData(1);
    const r2 = await fetchData(2);
    console.timeEnd("Sequential");  // ~1000ms
    
    console.time("Parallel");
    const [p1, p2] = await Promise.all([
        fetchData(1),
        fetchData(2)
    ]);
    console.timeEnd("Parallel");  // ~500ms
    console.log("Parallel results:", p1, p2);
}

// 3. Error handling
async function errorHandling() {
    try {
        const result = await Promise.reject(new Error("Boom!"));
    } catch (error) {
        console.log("Caught:", error.message);
    }
    
    // With .catch()
    const result = await fetchData(1)
        .then(data => data.toUpperCase())
        .catch(err => "Fallback");
    console.log("Result with error handling:", result);
}

// 4. Promise chaining
function promiseChaining() {
    return delay(100)
        .then(() => "Step 1")
        .then(result => {
            console.log(result);
            return "Step 2";
        })
        .then(result => {
            console.log(result);
            return "Done";
        });
}

// 5. Race and allSettled
async function raceAndAllSettled() {
    const slow = delay(500).then(() => "Slow");
    const fast = delay(100).then(() => "Fast");
    
    const winner = await Promise.race([slow, fast]);
    console.log("Race winner:", winner);
    
    const results = await Promise.allSettled([
        fetchData(1),
        Promise.reject("Error!"),
        fetchData(2)
    ]);
    console.log("All settled:", results);
}

// 6. Async iterator
async function* asyncGenerator() {
    for (let i = 0; i < 3; i++) {
        await delay(200);
        yield i;
    }
}

async function useAsyncGenerator() {
    for await (const value of asyncGenerator()) {
        console.log("Async gen value:", value);
    }
}

// Run all
async function main() {
    await basicExample();
    await sequentialVsParallel();
    await errorHandling();
    console.log("Chain result:", await promiseChaining());
    await raceAndAllSettled();
    await useAsyncGenerator();
}

main().catch(console.error);
```

**Python** (`async_demo.py`):
```python
import asyncio
import time

# Simulate async operations
async def fetch_data(id_):
    await asyncio.sleep(0.5)
    return f"Data {id_}"

# 1. Basic async/await
async def basic_example():
    print("Start")
    result = await fetch_data(1)
    print(f"Result: {result}")
    print("End")

# 2. Sequential vs Parallel
async def sequential_vs_parallel():
    # Sequential
    start = time.time()
    r1 = await fetch_data(1)
    r2 = await fetch_data(2)
    seq_time = time.time() - start
    print(f"Sequential: {seq_time:.2f}s")  # ~1.0s
    
    # Parallel
    start = time.time()
    p1, p2 = await asyncio.gather(
        fetch_data(1),
        fetch_data(2)
    )
    par_time = time.time() - start
    print(f"Parallel: {par_time:.2f}s")  # ~0.5s
    print(f"Results: {p1}, {p2}")

# 3. Error handling
async def error_handling():
    try:
        raise ValueError("Boom!")
    except ValueError as e:
        print(f"Caught: {e}")
    
    # with return value
    result = await safe_fetch()
    print(f"Safe result: {result}")

async def safe_fetch():
    try:
        data = await fetch_data(1)
        return data.upper()
    except Exception:
        return "Fallback"

# 4. Tasks (create background work)
async def background_work():
    task1 = asyncio.create_task(fetch_data(1))
    task2 = asyncio.create_task(fetch_data(2))
    
    # Do other work while tasks run
    await asyncio.sleep(0.2)
    print("Doing other work...")
    
    results = await asyncio.gather(task1, task2)
    print(f"Task results: {results}")

# 5. Timeouts
async def with_timeout():
    try:
        result = await asyncio.wait_for(
            fetch_data(1),
            timeout=0.3  # Will timeout
        )
        print(f"Got: {result}")
    except asyncio.TimeoutError:
        print("Operation timed out!")

# 6. Async context manager
class AsyncResource:
    async def __aenter__(self):
        print("Acquiring async resource")
        await asyncio.sleep(0.1)
        return self
    
    async def __aexit__(self, exc_type, exc_val, exc_tb):
        print("Releasing async resource")
        await asyncio.sleep(0.1)
    
    async def use(self):
        print("Using resource")

async def async_context_manager():
    async with AsyncResource() as resource:
        await resource.use()

# 7. Async iterator
class AsyncRange:
    def __init__(self, n):
        self.n = n
        self.i = 0
    
    def __aiter__(self):
        return self
    
    async def __anext__(self):
        if self.i >= self.n:
            raise StopAsyncIteration
        await asyncio.sleep(0.2)
        val = self.i
        self.i += 1
        return val

async def use_async_iterator():
    async for value in AsyncRange(3):
        print(f"Async iter value: {value}")

# Run everything
async def main():
    await basic_example()
    await sequential_vs_parallel()
    await error_handling()
    await background_work()
    # await with_timeout()  # Uncomment to test timeout
    await async_context_manager()
    await use_async_iterator()

asyncio.run(main())
```

---

## 26. Functional Programming

### Full Runnable Examples

**Java** (`FunctionalDemo.java`):
```java
import java.util.*;
import java.util.stream.*;

public class FunctionalDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Map
        List<Integer> doubled = numbers.stream()
            .map(n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Doubled: " + doubled);
        
        // Filter
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Evens: " + evens);
        
        // Reduce
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum);
        
        // Chained
        int result = numbers.stream()
            .filter(n -> n > 5)
            .map(n -> n * n)
            .reduce(0, Integer::sum);
        System.out.println("Sum of squares >5: " + result);
        
        // FlatMap
        List<List<Integer>> nested = Arrays.asList(
            Arrays.asList(1, 2), Arrays.asList(3, 4));
        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Flat: " + flat);
        
        // Any/All/None
        System.out.println("Any > 8: " + numbers.stream().anyMatch(n -> n > 8));
        System.out.println("All > 0: " + numbers.stream().allMatch(n -> n > 0));
        System.out.println("None > 10: " + numbers.stream().noneMatch(n -> n > 10));
        
        // Find
        Optional<Integer> first = numbers.stream()
            .filter(n -> n > 5)
            .findFirst();
        first.ifPresent(n -> System.out.println("First > 5: " + n));
        
        // Group by
        Map<String, List<Integer>> grouped = numbers.stream()
            .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        System.out.println("Grouped: " + grouped);
        
        // Sorting
        List<Integer> sorted = numbers.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        System.out.println("Reversed: " + sorted);
        
        // Distinct
        List<Integer> withDups = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> distinct = withDups.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Distinct: " + distinct);
        
        // Limit and Skip
        List<Integer> limited = numbers.stream()
            .skip(3)
            .limit(4)
            .collect(Collectors.toList());
        System.out.println("Skip 3, limit 4: " + limited);
        
        // Parallel stream
        long count = numbers.parallelStream()
            .filter(n -> n > 3)
            .count();
        System.out.println("Count > 3 (parallel): " + count);
    }
}
```

**JavaScript** (`functional.js`):
```javascript
const numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// Map
const doubled = numbers.map(n => n * 2);
console.log("Doubled:", doubled);

// Filter
const evens = numbers.filter(n => n % 2 === 0);
console.log("Evens:", evens);

// Reduce
const sum = numbers.reduce((a, b) => a + b, 0);
console.log("Sum:", sum);

// Chained
const result = numbers
    .filter(n => n > 5)
    .map(n => n * n)
    .reduce((a, b) => a + b, 0);
console.log("Sum of squares >5:", result);

// FlatMap
const nested = [[1, 2], [3, 4], [5, 6]];
const flat = nested.flatMap(arr => arr);
console.log("FlatMap:", flat);

// Flat
console.log("Flat:", nested.flat());

// ForEach
console.log("ForEach:");
numbers.forEach((n, i) => {
    if (n % 2 === 0) console.log(`  Index ${i}: ${n}`);
});

// Some / Every
console.log("Some > 8:", numbers.some(n => n > 8));
console.log("Every > 0:", numbers.every(n => n > 0));

// Find / FindIndex
const found = numbers.find(n => n > 5);
const foundIndex = numbers.findIndex(n => n > 5);
console.log("First > 5:", found, "at index:", foundIndex);

// Sort
const sorted = [...numbers].sort((a, b) => b - a);
console.log("Descending:", sorted);

// Chaining example
const users = [
    { name: "Alice", age: 25, active: true },
    { name: "Bob", age: 30, active: false },
    { name: "Charlie", age: 35, active: true },
    { name: "David", age: 20, active: true }
];

const activeNames = users
    .filter(u => u.active)
    .map(u => u.name.toUpperCase())
    .sort();
console.log("Active users:", activeNames);

// Reduce to object
const grouped = users.reduce((acc, user) => {
    const key = user.active ? 'active' : 'inactive';
    if (!acc[key]) acc[key] = [];
    acc[key].push(user);
    return acc;
}, {});
console.log("Grouped:", grouped);

// From scratch functional utilities
const compose = (...fns) => x => fns.reduceRight((acc, fn) => fn(acc), x);
const pipe = (...fns) => x => fns.reduce((acc, fn) => fn(acc), x);

const add1 = x => x + 1;
const double = x => x * 2;
const toString = x => String(x);

const transform = pipe(add1, double, toString);
console.log("Pipe result:", transform(5));  // "12"
```

**Python** (`functional.py`):
```python
from functools import reduce
from itertools import groupby
import operator

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# List comprehensions (Pythonic functional)
doubled = [n * 2 for n in numbers]
print(f"Doubled: {doubled}")

evens = [n for n in numbers if n % 2 == 0]
print(f"Evens: {evens}")

# Map with lambda
doubled_map = list(map(lambda n: n * 2, numbers))
print(f"Map: {doubled_map}")

# Filter
evens_filter = list(filter(lambda n: n % 2 == 0, numbers))
print(f"Filter: {evens_filter}")

# Reduce
total = reduce(lambda a, b: a + b, numbers, 0)
print(f"Reduce sum: {total}")

# Chained comprehension
result = sum(n ** 2 for n in numbers if n > 5)
print(f"Sum of squares >5: {result}")

# Flat list from nested
nested = [[1, 2], [3, 4], [5, 6]]
flat = [item for sublist in nested for item in sublist]
print(f"Flat: {flat}")

# Any / All
print(f"Any > 8: {any(n > 8 for n in numbers)}")
print(f"All > 0: {all(n > 0 for n in numbers)}")

# Sorted
descending = sorted(numbers, reverse=True)
print(f"Descending: {descending}")

# Group by
from itertools import groupby
users = [
    {"name": "Alice", "age": 25, "active": True},
    {"name": "Bob", "age": 30, "active": False},
    {"name": "Charlie", "age": 35, "active": True},
]

# Sort before groupby
sorted_users = sorted(users, key=lambda u: u["active"], reverse=True)
grouped = {}
for key, group in groupby(sorted_users, key=lambda u: u["active"]):
    grouped["active" if key else "inactive"] = list(group)
print(f"Grouped: {grouped}")

# Enumerate and Zip
for i, n in enumerate(numbers[:5]):
    print(f"  Index {i}: {n}")

names = ["Alice", "Bob", "Charlie"]
ages = [25, 30, 35]
for name, age in zip(names, ages):
    print(f"  {name} is {age}")

# Partial application
from functools import partial

def power(base, exponent):
    return base ** exponent

square = partial(power, exponent=2)
cube = partial(power, exponent=3)

print(f"Square of 5: {square(5)}")
print(f"Cube of 3: {cube(3)}")

# Decorator (functional pattern)
def memoize(func):
    cache = {}
    def wrapper(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]
    return wrapper

@memoize
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)

print(f"Fibonacci(35): {fibonacci(35)}")  # Fast with memoization

# Dictionary comprehensions
squares = {x: x ** 2 for x in range(5)}
print(f"Dict comprehension: {squares}")

# Set comprehension
even_set = {x for x in numbers if x % 2 == 0}
print(f"Set comprehension: {even_set}")
```

---

## 27. Generics & Type Hints

### Full Runnable Examples

**Java** (`GenericsDemo.java`):
```java
import java.util.*;

// Generic class
class Box<T> {
    private T content;
    
    public void put(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public boolean isEmpty() {
        return content == null;
    }
}

// Generic interface
interface Repository<T, ID> {
    T findById(ID id);
    List<T> findAll();
    void save(T entity);
    void delete(ID id);
}

// Generic method
class Utils {
    public static <T> T identity(T value) {
        return value;
    }
    
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    
    // Wildcards
    public static double sumOfNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }
    
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
}

// Bounded type
class NumberBox<T extends Number> {
    private T value;
    
    NumberBox(T value) { this.value = value; }
    
    double doubleValue() {
        return value.doubleValue();
    }
}

public class GenericsDemo {
    public static void main(String[] args) {
        // Generic class
        Box<String> stringBox = new Box<>();
        stringBox.put("Hello");
        System.out.println("String box: " + stringBox.get());
        
        Box<Integer> intBox = new Box<>();
        intBox.put(42);
        System.out.println("Int box: " + intBox.get());
        
        // Generic method
        System.out.println("Identity: " + Utils.identity("test"));
        System.out.println("Max string: " + Utils.max("apple", "banana"));
        System.out.println("Max int: " + Utils.max(10, 20));
        
        // Wildcards
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println("Sum of ints: " + Utils.sumOfNumbers(ints));
        System.out.println("Sum of doubles: " + Utils.sumOfNumbers(doubles));
        
        // Bounded type
        NumberBox<Integer> nb = new NumberBox<>(42);
        System.out.println("Double value: " + nb.doubleValue());
        
        // Type inference
        List<String> list = new ArrayList<>();
        // Diamond operator <>
        Map<String, List<Integer>> complex = new HashMap<>();
    }
}
```

**JavaScript** (`generics.js`):
```javascript
// JavaScript doesn't have native generics
// TypeScript adds them, but here's the JS approximation

// Generic class simulation (no type checking at runtime)
class Box {
    constructor() {
        this.content = null;
    }
    
    put(content) {
        this.content = content;
    }
    
    get() {
        return this.content;
    }
    
    isEmpty() {
        return this.content === null || this.content === undefined;
    }
}

const stringBox = new Box();
stringBox.put("Hello");
console.log("String box:", stringBox.get());

const intBox = new Box();
intBox.put(42);
console.log("Int box:", intBox.get());

// Generic function simulation
function identity(value) {
    return value;
}

function max(a, b) {
    return a > b ? a : b;
}

console.log("Identity:", identity("test"));
console.log("Max:", max(10, 20));

// Using JSDoc for type hints (IDE support only)
/**
 * @template T
 * @param {T[]} array
 * @returns {T}
 */
function first(array) {
    return array[0];
}

const firstNum = first([1, 2, 3]);
const firstStr = first(["a", "b", "c"]);
console.log("First number:", firstNum);
console.log("First string:", firstStr);
```

**Python** (`generics_demo.py`):
```python
from typing import (
    TypeVar, Generic, List, Dict, Optional, 
    Union, Callable, Any, Sequence, Protocol
)

# Type variables
T = TypeVar('T')
K = TypeVar('K')
V = TypeVar('V')
Number = TypeVar('Number', int, float)

# Generic class
class Box(Generic[T]):
    def __init__(self):
        self._content: Optional[T] = None
    
    def put(self, content: T) -> None:
        self._content = content
    
    def get(self) -> Optional[T]:
        return self._content
    
    def is_empty(self) -> bool:
        return self._content is None

# Usage
string_box = Box[str]()
string_box.put("Hello")
print(f"String box: {string_box.get()}")

int_box = Box[int]()
int_box.put(42)
print(f"Int box: {int_box.get()}")

# Generic function
def identity(value: T) -> T:
    return value

def max_of_two(a: T, b: T) -> T:
    return a if a > b else b

print(f"Identity: {identity('test')}")
print(f"Max: {max_of_two(10, 20)}")

# Bounded type
def double_value(value: Number) -> float:
    return float(value * 2)

print(f"Double int: {double_value(5)}")
print(f"Double float: {double_value(3.14)}")

# Multiple type variables
class Pair(Generic[K, V]):
    def __init__(self, key: K, value: V):
        self.key = key
        self.value = value
    
    def __str__(self):
        return f"Pair({self.key}, {self.value})"

pair = Pair("name", "Alice")
print(pair)

# Type aliases
UserDict = Dict[str, Union[str, int, bool]]
user: UserDict = {
    "name": "Alice",
    "age": 30,
    "active": True
}

# Callable type
def apply_twice(func: Callable[[T], T], value: T) -> T:
    return func(func(value))

def add_one(x: int) -> int:
    return x + 1

print(f"Apply twice: {apply_twice(add_one, 5)}")  # 7

# Protocol with generics
class SupportsSum(Protocol):
    def __add__(self, other: Any) -> Any: ...

def add_all(items: List[SupportsSum]) -> Any:
    result = items[0]
    for item in items[1:]:
        result += item
    return result

print(f"Add all: {add_all([1, 2, 3, 4, 5])}")
print(f"Add all strings: {add_all(['a', 'b', 'c'])}")

# Dataclass with type hints
from dataclasses import dataclass

@dataclass
class Point:
    x: float
    y: float
    
    def distance(self) -> float:
        return (self.x ** 2 + self.y ** 2) ** 0.5

p = Point(3.0, 4.0)
print(f"Point: {p}, distance: {p.distance()}")
```

---

## 28. Enums

### Full Runnable Examples

**Java** (`EnumsDemo.java`):
```java
public class EnumsDemo {
    // Basic enum
    enum Status {
        PENDING, ACTIVE, INACTIVE, DELETED
    }
    
    // Enum with fields and methods
    enum Color {
        RED("#FF0000"),
        GREEN("#00FF00"),
        BLUE("#0000FF");
        
        private final String hexCode;
        
        Color(String hexCode) {
            this.hexCode = hexCode;
        }
        
        public String getHexCode() {
            return hexCode;
        }
        
        public boolean isWarm() {
            return this == RED;
        }
    }
    
    // Enum with abstract method
    enum Operation {
        ADD {
            public double apply(double x, double y) { return x + y; }
        },
        SUBTRACT {
            public double apply(double x, double y) { return x - y; }
        },
        MULTIPLY {
            public double apply(double x, double y) { return x * y; }
        };
        
        public abstract double apply(double x, double y);
    }
    
    public static void main(String[] args) {
        // Basic usage
        Status s = Status.ACTIVE;
        System.out.println("Status: " + s);
        System.out.println("Name: " + s.name());
        System.out.println("Ordinal: " + s.ordinal());
        
        // Iterate
        System.out.println("All statuses:");
        for (Status status : Status.values()) {
            System.out.println("  " + status);
        }
        
        // Switch on enum
        switch (s) {
            case PENDING:
                System.out.println("Waiting...");
                break;
            case ACTIVE:
                System.out.println("Running...");
                break;
            default:
                System.out.println("Other");
        }
        
        // Enum with fields
        Color c = Color.RED;
        System.out.println("Color: " + c);
        System.out.println("Hex: " + c.getHexCode());
        System.out.println("Is warm: " + c.isWarm());
        
        // Enum with abstract method
        double a = 10, b = 5;
        for (Operation op : Operation.values()) {
            System.out.println(op + ": " + op.apply(a, b));
        }
        
        // ValueOf
        Status parsed = Status.valueOf("PENDING");
        System.out.println("Parsed: " + parsed);
        
        // EnumSet
        java.util.EnumSet<Status> activeStatuses = 
            java.util.EnumSet.of(Status.ACTIVE, Status.PENDING);
        System.out.println("Active statuses: " + activeStatuses);
        
        // EnumMap
        java.util.EnumMap<Status, String> descriptions = 
            new java.util.EnumMap<>(Status.class);
        descriptions.put(Status.PENDING, "Waiting for processing");
        descriptions.put(Status.ACTIVE, "Currently active");
        System.out.println("Description: " + descriptions.get(s));
    }
}
```

**JavaScript** (`enums.js`):
```javascript
// JavaScript doesn't have native enums
// Approach 1: Object.freeze (most common)
const Status = Object.freeze({
    PENDING: "PENDING",
    ACTIVE: "ACTIVE",
    INACTIVE: "INACTIVE",
    DELETED: "DELETED"
});

console.log("Status:", Status.ACTIVE);
console.log("Is frozen:", Object.isFrozen(Status));

// Approach 2: Symbol values for uniqueness
const Color = Object.freeze({
    RED: Symbol("red"),
    GREEN: Symbol("green"),
    BLUE: Symbol("blue")
});

// Approach 3: Enum with attached metadata
const Operation = Object.freeze({
    ADD: {
        symbol: "+",
        apply(x, y) { return x + y; }
    },
    SUBTRACT: {
        symbol: "-",
        apply(x, y) { return x - y; }
    },
    MULTIPLY: {
        symbol: "*",
        apply(x, y) { return x * y; }
    }
});

// Using enums
let s = Status.ACTIVE;
console.log("Status:", s);

// Switch
switch (s) {
    case Status.PENDING:
        console.log("Waiting...");
        break;
    case Status.ACTIVE:
        console.log("Running...");
        break;
    default:
        console.log("Other");
}

// Iterate
console.log("All statuses:");
Object.values(Status).forEach(v => console.log(" ", v));

// Enum with methods
let a = 10, b = 5;
Object.entries(Operation).forEach(([name, op]) => {
    console.log(`${name}: ${op.apply(a, b)}`);
});

// TypeScript-style enum with reverse mapping
const Size = Object.freeze({
    SMALL: 0,
    MEDIUM: 1,
    LARGE: 2,
    
    // Reverse mapping
    0: "SMALL",
    1: "MEDIUM",
    2: "LARGE"
});

console.log("Size:", Size.MEDIUM, Size[1]);

// Approach 4: Class-based enum
class Direction {
    static NORTH = new Direction("N", 0);
    static EAST = new Direction("E", 90);
    static SOUTH = new Direction("S", 180);
    static WEST = new Direction("W", 270);
    
    constructor(abbrev, degrees) {
        this.abbrev = abbrev;
        this.degrees = degrees;
    }
    
    toString() {
        return `${this.abbrev} (${this.degrees}°)`;
    }
    
    static values() {
        return [Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST];
    }
}

console.log("Direction:", Direction.NORTH.toString());
Direction.values().forEach(d => console.log(" ", d.toString()));
```

**Python** (`enums_demo.py`):
```python
from enum import Enum, auto, IntEnum

# Basic enum
class Status(Enum):
    PENDING = 1
    ACTIVE = 2
    INACTIVE = 3
    DELETED = 4

print(f"Status: {Status.ACTIVE}")
print(f"Name: {Status.ACTIVE.name}")
print(f"Value: {Status.ACTIVE.value}")

# Enum with auto values
class Color(Enum):
    RED = auto()
    GREEN = auto()
    BLUE = auto()

print(f"Color values: {list(Color)}")
for color in Color:
    print(f"  {color.name} = {color.value}")

# Enum with custom values and methods
class ColorWithHex(Enum):
    RED = "#FF0000"
    GREEN = "#00FF00"
    BLUE = "#0000FF"
    
    @property
    def is_warm(self):
        return self == ColorWithHex.RED
    
    def __str__(self):
        return f"{self.name} ({self.value})"

c = ColorWithHex.RED
print(f"Color: {c}")
print(f"Is warm: {c.is_warm}")

# Enum with methods
class Operation(Enum):
    ADD = "+"
    SUBTRACT = "-"
    MULTIPLY = "*"
    
    def apply(self, x, y):
        match self:
            case Operation.ADD:
                return x + y
            case Operation.SUBTRACT:
                return x - y
            case Operation.MULTIPLY:
                return x * y

a, b = 10, 5
for op in Operation:
    print(f"{op.name}: {op.apply(a, b)}")

# IntEnum (can be used as integer)
class Priority(IntEnum):
    LOW = 1
    MEDIUM = 2
    HIGH = 3

print(f"Priority > 1: {Priority.HIGH > 1}")  # True
print(f"Priority name: {Priority(2).name}")  # MEDIUM

# Functional creation
Animal = Enum('Animal', ['DOG', 'CAT', 'BIRD'])
print(f"Animal: {Animal.DOG}")
print(f"List: {list(Animal)}")

# Flag enum (bitwise operations)
from enum import Flag, auto

class Permission(Flag):
    READ = auto()
    WRITE = auto()
    EXECUTE = auto()
    ALL = READ | WRITE | EXECUTE

perm = Permission.READ | Permission.WRITE
print(f"Has read: {Permission.READ in perm}")
print(f"Has execute: {Permission.EXECUTE in perm}")
print(f"Full permission: {Permission.ALL}")

# Unique decorator (prevents duplicate values)
from enum import unique

@unique
class UniqueStatus(Enum):
    PENDING = 1
    ACTIVE = 2
    # DUPLICATE = 1  # Would raise ValueError!

# Switch on enum (match statement)
def handle_status(status: Status):
    match status:
        case Status.PENDING:
            print("Waiting...")
        case Status.ACTIVE:
            print("Running...")
        case _:
            print("Other")

handle_status(Status.ACTIVE)
```

---

## 29. Iterators & Generators

### Full Runnable Examples

**Java** (`IteratorsDemo.java`):
```java
import java.util.*;
import java.util.stream.Stream;

public class IteratorsDemo {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
        
        // 1. Regular Iterator
        System.out.println("Iterator:");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }
        
        // 2. Enhanced For-Each
        System.out.println("For-Each:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }
        
        // 3. ListIterator (bidirectional)
        ListIterator<String> li = fruits.listIterator(fruits.size());
        System.out.println("ListIterator (reverse):");
        while (li.hasPrevious()) {
            System.out.println("  " + li.previous());
        }
        
        // 4. Stream (Java 8+)
        System.out.println("Stream:");
        fruits.stream()
            .map(String::toUpperCase)
            .forEach(f -> System.out.println("  " + f));
        
        // 5. Custom Iterable
        class Range implements Iterable<Integer> {
            private int start, end;
            
            Range(int start, int end) {
                this.start = start;
                this.end = end;
            }
            
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int current = start;
                    
                    @Override
                    public boolean hasNext() {
                        return current < end;
                    }
                    
                    @Override
                    public Integer next() {
                        return current++;
                    }
                };
            }
        }
        
        System.out.println("Custom Range:");
        for (int n : new Range(0, 5)) {
            System.out.println("  " + n);
        }
        
        // 6. Stream.iterate (generator-like)
        System.out.println("Stream.iterate:");
        Stream.iterate(0, n -> n + 2)
            .limit(5)
            .forEach(n -> System.out.println("  " + n));
        
        // 7. Spliterator
        Spliterator<String> spliterator = fruits.spliterator();
        spliterator.forEachRemaining(f -> System.out.println("Spliterator: " + f));
        
        // 8. Primitive iterators
        int[] numbers = {1, 2, 3, 4, 5};
        Arrays.stream(numbers)
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);
    }
}
```

**JavaScript** (`iterators.js`):
```javascript
const fruits = ["Apple", "Banana", "Cherry"];

// 1. Array iterator
console.log("Array iterator:");
const it = fruits[Symbol.iterator]();
let result = it.next();
while (!result.done) {
    console.log(" ", result.value);
    result = it.next();
}

// 2. For-Of (uses Symbol.iterator)
console.log("For-Of:");
for (const fruit of fruits) {
    console.log(" ", fruit);
}

// 3. For-In (keys/indices)
console.log("For-In (keys):");
for (const index in fruits) {
    console.log(`  ${index}: ${fruits[index]}`);
}

// 4. ForEach method
console.log("ForEach:");
fruits.forEach((fruit, i) => console.log(`  ${i}: ${fruit}`));

// 5. Generator function
function* range(start, end) {
    for (let i = start; i < end; i++) {
        yield i;
    }
}

console.log("Generator range:");
for (const n of range(0, 5)) {
    console.log(" ", n);
}

// 6. Custom iterable
const customRange = {
    [Symbol.iterator]: function() {
        let current = 0;
        return {
            next() {
                if (current < 5) {
                    return { value: current++, done: false };
                }
                return { value: undefined, done: true };
            }
        };
    }
};

console.log("Custom iterable:");
for (const n of customRange) {
    console.log(" ", n);
}

// 7. Generator with yield*
function* gen1() {
    yield 1;
    yield 2;
}

function* gen2() {
    yield* gen1();
    yield 3;
    yield 4;
}

console.log("Yield*:");
console.log([...gen2()]);  // [1, 2, 3, 4]

// 8. Infinite generator
function* fibonacci() {
    let a = 0, b = 1;
    while (true) {
        yield a;
        [a, b] = [b, a + b];
    }
}

console.log("Fibonacci (first 10):");
const fib = fibonacci();
for (let i = 0; i < 10; i++) {
    console.log(" ", fib.next().value);
}

// 9. Generator methods
function* numbers() {
    yield 1;
    yield 2;
    yield 3;
}

const gen = numbers();
console.log("Generator methods:");
console.log("  next():", gen.next());
console.log("  next():", gen.next());
console.log("  next():", gen.next());
console.log("  next():", gen.next());  // done: true

// 10. Using .entries(), .keys(), .values()
const map = new Map([["a", 1], ["b", 2]]);
console.log("Map entries:", [...map.entries()]);
console.log("Map keys:", [...map.keys()]);
console.log("Map values:", [...map.values()]);
```

**Python** (`iterators_demo.py`):
```python
fruits = ["Apple", "Banana", "Cherry"]

# 1. Regular iterator
print("Iterator:")
it = iter(fruits)
while True:
    try:
        fruit = next(it)
        print(f"  {fruit}")
    except StopIteration:
        break

# 2. For loop (uses __iter__ internally)
print("For loop:")
for fruit in fruits:
    print(f"  {fruit}")

# 3. Enumerate
print("Enumerate:")
for i, fruit in enumerate(fruits):
    print(f"  {i}: {fruit}")

# 4. Generator function
def range_gen(start, end):
    for i in range(start, end):
        yield i

print("Generator range:")
for n in range_gen(0, 5):
    print(f"  {n}")

# 5. Generator expression
squares = (x ** 2 for x in range(10))
print("Generator expression:")
print(f"  First 3: {[next(squares) for _ in range(3)]}")

# 6. Custom iterator class
class Range:
    def __init__(self, start, end):
        self.start = start
        self.end = end
    
    def __iter__(self):
        self.current = self.start
        return self
    
    def __next__(self):
        if self.current >= self.end:
            raise StopIteration
        value = self.current
        self.current += 1
        return value

print("Custom Range class:")
for n in Range(0, 5):
    print(f"  {n}")

# 7. Yield from (delegation)
def gen1():
    yield 1
    yield 2

def gen2():
    yield from gen1()
    yield 3
    yield 4

print("Yield from:")
print(f"  {list(gen2())}")  # [1, 2, 3, 4]

# 8. Infinite generator
def fibonacci():
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b

print("Fibonacci (first 10):")
fib = fibonacci()
for i in range(10):
    print(f"  {next(fib)}")

# 9. Generator with send()
def counter():
    i = 0
    while True:
        value = yield i
        if value is not None:
            i = value
        else:
            i += 1

print("Generator send:")
c = counter()
print(f"  {next(c)}")     # 0
print(f"  {next(c)}")     # 1
print(f"  {c.send(10)}")  # 10
print(f"  {next(c)}")     # 11

# 10. itertools
from itertools import count, cycle, repeat, chain, zip_longest

print("Count:", list(next(islice := count(10), 5)))  # [10, 11, 12, 13, 14]
# Need helper
def take(n, iterable):
    return [x for _, x in zip(range(n), iterable)]

print(f"Cycle: {take(5, cycle([1, 2, 3]))}")
print(f"Repeat: {take(3, repeat('A'))}")
print(f"Chain: {list(chain([1, 2], [3, 4]))}")

# 11. reversed
print(f"Reversed: {list(reversed(fruits))}")

# 12. sorted (returns iterator in Python 3.x)
sorted_fruits = sorted(fruits, key=len)
print(f"Sorted by length: {sorted_fruits}")
```

---

## 30. Testing

### Side-by-Side Comparison

| Concept | Java (JUnit 5) | JavaScript (Jest) | Python (pytest) |
|---------|----------------|-------------------|-----------------|
| Simple Test | `@Test void test() { }` | `test('name', () => { })` | `def test_name():` |
| Assert Equal | `assertEquals(4, add(2,2))` | `expect(add(2,2)).toBe(4)` | `assert add(2,2) == 4` |
| Setup | `@BeforeEach void setup() { }` | `beforeEach(() => { })` | `def setup_method(self):` |
| Mock | Mockito: `when(mock.method()).thenReturn(value)` | `jest.fn().mockReturnValue(value)` | `unittest.mock.patch(...)` |

### Full Runnable Examples

**Java** (`CalculatorTest.java` - JUnit 5):
```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}

public class CalculatorTest {
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
    
    @Test
    @DisplayName("Addition works correctly")
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-1, calculator.add(-2, 1));
        assertEquals(0, calculator.add(0, 0));
    }
    
    @Test
    void testSubtract() {
        assertEquals(3, calculator.subtract(5, 2));
    }
    
    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(0, calculator.multiply(5, 0));
    }
    
    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(10, 5));
    }
    
    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, 
            () -> calculator.divide(10, 0));
    }
    
    @ParameterizedTest
    @CsvSource({"1,1,2", "2,3,5", "10,20,30"})
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    
    @ParameterizedTest
    @MethodSource("provideNumbers")
    void testWithMethodSource(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }
    
    static Stream<Arguments> provideNumbers() {
        return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of(-1, 1, 0),
            Arguments.of(0, 0, 0)
        );
    }
    
    @Test
    void testMultipleAssertions() {
        assertAll("Calculator operations",
            () -> assertEquals(5, calculator.add(2, 3)),
            () -> assertEquals(1, calculator.subtract(3, 2)),
            () -> assertEquals(6, calculator.multiply(2, 3))
        );
    }
}
```

**JavaScript** (`calculator.test.js` - Jest):
```javascript
// calculator.js
class Calculator {
    add(a, b) { return a + b; }
    subtract(a, b) { return a - b; }
    multiply(a, b) { return a * b; }
    divide(a, b) {
        if (b === 0) throw new Error("Cannot divide by zero");
        return a / b;
    }
}

module.exports = Calculator;

// calculator.test.js
const Calculator = require('./calculator');

describe('Calculator', () => {
    let calc;
    
    beforeEach(() => {
        calc = new Calculator();
    });
    
    afterEach(() => {
        // Cleanup
    });
    
    test('adds two numbers', () => {
        expect(calc.add(2, 3)).toBe(5);
        expect(calc.add(-2, 1)).toBe(-1);
        expect(calc.add(0, 0)).toBe(0);
    });
    
    test('subtracts two numbers', () => {
        expect(calc.subtract(5, 2)).toBe(3);
    });
    
    test('multiplies two numbers', () => {
        expect(calc.multiply(2, 3)).toBe(6);
        expect(calc.multiply(5, 0)).toBe(0);
    });
    
    test('divides two numbers', () => {
        expect(calc.divide(10, 5)).toBe(2);
    });
    
    test('throws on division by zero', () => {
        expect(() => calc.divide(10, 0)).toThrow();
        expect(() => calc.divide(10, 0)).toThrow("Cannot divide by zero");
    });
    
    // Parameterized test
    test.each([
        [1, 1, 2],
        [2, 3, 5],
        [10, 20, 30]
    ])('adds %i + %i = %i', (a, b, expected) => {
        expect(calc.add(a, b)).toBe(expected);
    });
    
    // Testing async code
    test('async operation', async () => {
        const result = await Promise.resolve(42);
        expect(result).toBe(42);
    });
    
    // Mocking
    test('mock function', () => {
        const mockFn = jest.fn().mockReturnValue(42);
        expect(mockFn()).toBe(42);
        expect(mockFn).toHaveBeenCalled();
        expect(mockFn).toHaveBeenCalledTimes(1);
    });
    
    // Multiple assertions
    test('multiple operations', () => {
        expect(calc.add(2, 3)).toBe(5);
        expect(calc.subtract(3, 2)).toBe(1);
        expect(calc.multiply(2, 3)).toBe(6);
    });
    
    // Object matching
    test('object matching', () => {
        const user = { name: 'Alice', age: 30 };
        expect(user).toEqual({ name: 'Alice', age: 30 });
        expect(user).toMatchObject({ name: 'Alice' });
        expect(user).toHaveProperty('name');
    });
});

// Snapshot testing
// test('snapshot', () => {
//     const user = { name: 'Alice', age: 30 };
//     expect(user).toMatchSnapshot();
// });
```

**Python** (`test_calculator.py` - pytest):
```python
import pytest
from unittest.mock import Mock, patch

class Calculator:
    def add(self, a, b): return a + b
    def subtract(self, a, b): return a - b
    def multiply(self, a, b): return a * b
    
    def divide(self, a, b):
        if b == 0:
            raise ValueError("Cannot divide by zero")
        return a / b

# Fixture
@pytest.fixture
def calc():
    return Calculator()

# Tests
def test_add(calc):
    assert calc.add(2, 3) == 5
    assert calc.add(-2, 1) == -1
    assert calc.add(0, 0) == 0

def test_subtract(calc):
    assert calc.subtract(5, 2) == 3

def test_multiply(calc):
    assert calc.multiply(2, 3) == 6
    assert calc.multiply(5, 0) == 0

def test_divide(calc):
    assert calc.divide(10, 5) == 2

def test_divide_by_zero(calc):
    with pytest.raises(ValueError) as exc_info:
        calc.divide(10, 0)
    assert str(exc_info.value) == "Cannot divide by zero"

# Parameterized test
@pytest.mark.parametrize("a, b, expected", [
    (1, 1, 2),
    (2, 3, 5),
    (10, 20, 30),
    (-1, 1, 0),
])
def test_add_parametrized(calc, a, b, expected):
    assert calc.add(a, b) == expected

# Multiple test data from fixture
@pytest.fixture(params=[(1, 2, 3), (0, 0, 0), (-1, 1, 0)])
def add_data(request):
    return request.param

def test_add_with_fixture(calc, add_data):
    a, b, expected = add_data
    assert calc.add(a, b) == expected

# Setup/Teardown via yield fixture
@pytest.fixture
def resource():
    print("Setup")
    yield "resource"
    print("Teardown")

def test_with_resource(resource):
    assert resource == "resource"

# Mocking
def test_mocking():
    mock = Mock(return_value=42)
    assert mock() == 42
    mock.assert_called_once()

# Mock with patch
def test_patch():
    with patch('builtins.print') as mock_print:
        print("hello")
        mock_print.assert_called_with("hello")

# Test grouping
class TestCalculatorGroup:
    def test_add_group(self, calc):
        assert calc.add(1, 2) == 3
    
    def test_multiply_group(self, calc):
        assert calc.multiply(2, 3) == 6

# Approximate comparison
def test_float():
    assert 0.1 + 0.2 == pytest.approx(0.3, rel=1e-9)

# Exception info
def test_exception_details():
    with pytest.raises(ValueError) as exc_info:
        raise ValueError("Custom error", 42)
    assert exc_info.value.args[0] == "Custom error"
    assert exc_info.value.args[1] == 42

# Skip tests
@pytest.mark.skip(reason="Not implemented yet")
def test_not_implemented():
    pass

@pytest.mark.skipif(True, reason="Conditional skip")
def test_conditional_skip():
    pass
```

---

## Quick Reference: Common Patterns

| Task | Java | JavaScript | Python |
|------|------|-----------|--------|
| Check if empty | `str.isEmpty()` | `str.length === 0` | `not str` |
| Default value | `x != null ? x : default` | `x ?? default` | `x if x else default` |
| Ternary | `a > b ? a : b` | `a > b ? a : b` | `a if a > b else b` |
| Swap | `int tmp = a; a = b; b = tmp;` | `[a, b] = [b, a]` | `a, b = b, a` |
| Deep copy | Manual (clone) | `structuredClone(obj)` | `copy.deepcopy(obj)` |
| Sleep | `Thread.sleep(1000)` | `await new Promise(r => setTimeout(r, 1000))` | `time.sleep(1)` |
| Timestamp | `System.currentTimeMillis()` | `Date.now()` | `time.time()` |
| Type check | `x instanceof String` | `typeof x === 'string'` | `isinstance(x, str)` |
| ForEach | `list.forEach(x -> ...)` | `arr.forEach(x => ...)` | `for x in arr: ...` |
| Map | `stream().map(x -> ...)` | `arr.map(x => ...)` | `[fn(x) for x in arr]` |
| Filter | `stream().filter(x -> ...)` | `arr.filter(x => ...)` | `[x for x in arr if cond]` |
| Reduce | `stream().reduce(0, (a,b) -> a+b)` | `arr.reduce((a,b) => a+b, 0)` | `functools.reduce(fn, arr, 0)` |
| Spread | Not natively supported | `[...arr]` | `*arr` |
| Destructure | N/A | `const {a, b} = obj` | `a, b = dict.values()` |
| Optional chaining | N/A (use Optional) | `obj?.prop?.sub` | `getattr(obj, 'prop', None)` |
| Null coalescing | `Objects.requireNonNullElse(x, d)` | `x ?? default` | `x or default` |

---

## Summary: Language Characteristics

| Aspect | Java | JavaScript | Python |
|--------|------|-----------|--------|
| **Typing** | Static, Strong | Dynamic, Weak | Dynamic, Strong |
| **Paradigm** | OOP (strict) | Multi-paradigm | Multi-paradigm |
| **Platform** | JVM | Browser / Node.js | Interpreter |
| **Concurrency** | Multi-threaded | Event-loop (single-threaded) | GIL-limited threading |
| **Async** | CompletableFuture | Promise / async-await | asyncio |
| **Build** | Maven / Gradle | npm / yarn / Vite | pip / poetry |
| **Testing** | JUnit / TestNG | Jest / Mocha | pytest / unittest |
| **Popular for** | Enterprise, Android | Web, Full-stack | Data Science, Backend |
| **Learning curve** | Steep | Moderate | Gentle |
| **File** | `*.java` → `*.class` | `*.js` | `*.py` |
| **Version** | Java 21 (LTS) | ES2024 | Python 3.13 |

---

*Created: July 2026 | A comprehensive reference for mastering Java, JavaScript, and Python*
