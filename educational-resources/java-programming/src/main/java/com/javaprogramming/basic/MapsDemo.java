package com.javaprogramming.basic;

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
    System.out.println("David (default): " + scores.getOrDefault("David", 0));

    // Update
    scores.put("Alice", 97); // Update
    scores.replace("Bob", 88);
    System.out.println("After update: " + scores);

    // Remove
    scores.remove("Charlie");
    System.out.println("After remove: " + scores);

    // TreeMap (sorted)
    Map<String, Integer> sorted = new TreeMap<>(scores);
    System.out.println("Sorted: " + sorted);

    // ForEach with lambda
    scores.forEach((name, score) -> System.out.println(name + " -> " + score));

    // Compute
    scores.compute("Alice", (k, v) -> v + 5);
    System.out.println("After compute: " + scores);
  }
}
