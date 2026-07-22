package com.javaprogramming.basic;

import java.util.*;
import java.util.stream.Collectors;

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
    List<Integer> doubled = list.stream().map(n -> n * 2).collect(Collectors.toList());
    System.out.println("Doubled: " + doubled);

    List<Integer> filtered = list.stream().filter(n -> n > 25).collect(Collectors.toList());
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
