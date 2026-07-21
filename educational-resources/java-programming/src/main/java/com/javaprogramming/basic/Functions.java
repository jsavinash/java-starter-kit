package com.javaprogramming.basic;

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
    List<Integer> doubled = nums.stream().map(n -> n * 2).collect(Collectors.toList());
    System.out.println("Doubled: " + doubled);

    // Method reference
    List<Integer> filtered = nums.stream().filter(n -> n > 2).collect(Collectors.toList());
    System.out.println("Filtered (>2): " + filtered);
  }
}
