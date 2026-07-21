package com.javaprogramming.basic;

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
