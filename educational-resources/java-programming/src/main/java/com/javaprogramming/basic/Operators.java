package com.javaprogramming.basic;

public class Operators {
  public static void main(String[] args) {
    // Arithmetic
    int a = 10, b = 3;
    System.out.println("a + b = " + (a + b)); // 13
    System.out.println("a - b = " + (a - b)); // 7
    System.out.println("a * b = " + (a * b)); // 30
    /*
    ExampleTake the problem 22 ÷ 5 :
    Dividend: 22
    Divisor: 5
    Quotient: 4 (since 5 × 4 = 20)
    Remainder: 2 (22 - 20 = 2)
    Checking the math: (5 × 4) + 2 = 22.
     */
    System.out.println("a / b = " + (a / b)); // 3 (integer)
    System.out.println("a % b = " + (a % b)); // 1
    System.out.println("Math.pow: " + Math.pow(2, 3)); // 8.0

    // Increment/Decrement
    /*
     Post-increment (x++): Java uses the current value of x (5) first, prints it, and then increments x to 6.
     Pre-increment (++x): Java increments x first (making it 7), and then uses that updated value in the expression.
    */
    int x = 5;
    System.out.println("x++ = " + (x++)); // 5 (post)
    System.out.println("++x = " + (++x)); // 7 (pre)

    // Comparison
    System.out.println("a == b? " + (a == b)); // false
    System.out.println("a != b? " + (a != b)); // true
    System.out.println("a > b? " + (a > b)); // true

    // Logical
    boolean t = true, f = false;
    System.out.println("t && f = " + (t && f)); // false
    System.out.println("t || f = " + (t || f)); // true
    System.out.println("!t = " + (!t)); // false

    // Ternary
    int result = (a > b) ? a : b;
    System.out.println("Max: " + result); // 10

    // Bitwise

    /*
      0101  (5)
    & 0011  (3)
    ------
      0001  (1)
    */
    System.out.println("5 & 3 = " + (5 & 3)); // 1
    /*
      0101  (5)
    | 0011  (3)
    ------
      0111  (7)
    */
    System.out.println("5 | 3 = " + (5 | 3)); // 7
    /*
      0101  (5)
    ^ 0011  (3)
    ------
      0110  (6)
    */
    System.out.println("5 ^ 3 = " + (5 ^ 3)); // 6
    /*
      0101  (5)
    ------
      1010  (10)
    */
    System.out.println("5 << 1 = " + (5 << 1)); // 10
  }
}
