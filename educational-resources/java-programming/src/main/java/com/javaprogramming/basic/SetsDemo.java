package com.javaprogramming.basic;

import java.util.*;

public class SetsDemo {
  public static void main(String[] args) {
    Set<Integer> set = new HashSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(10); // Duplicate - ignored
    set.remove(10);
    System.out.println(set.contains(10));
    System.out.println(set.size());
    System.out.println(set.isEmpty());
    for (Integer v : set) {
      System.out.println(v);
    }
    Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4));
    Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 4, 5, 6));
    Set<Integer> union = new HashSet<>(set1);
    union.addAll(set2);
    // TreeSet (sorted)
    Set<Integer> sorted = new TreeSet<>(Arrays.asList(5, 1, 4, 2, 3));
    System.out.println("Sorted: " + sorted);
    // LinkedHashSet (insertion order)
    Set<String> ordered = new LinkedHashSet<>(Arrays.asList("a", "b", "c"));
    System.out.println("Insertion order: " + ordered);
  }
}
