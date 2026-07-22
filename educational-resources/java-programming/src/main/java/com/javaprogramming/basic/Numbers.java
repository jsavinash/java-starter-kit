package com.javaprogramming.basic;

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
    System.out.println("Random int 1-10: " + ThreadLocalRandom.current().nextInt(1, 11));

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
