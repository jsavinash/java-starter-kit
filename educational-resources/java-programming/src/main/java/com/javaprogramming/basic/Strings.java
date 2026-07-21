package com.javaprogramming.basic;

public class Strings {
  public static void main(String[] args) {
    String s = "Hello, World!";

    // Basic operations
    System.out.println("Length: " + s.length());
    System.out.println("Char at 0: " + s.charAt(0));
    System.out.println("Substring (0,5): " + s.substring(0, 5)); // include 0 to 5 - 1.
    System.out.println("Contains 'World': " + s.contains("World!"));
    System.out.println("Starts with 'Hello': " + s.startsWith("Hello"));
    System.out.println("Ends with '!': " + s.endsWith("!"));
    System.out.println("Index of 'o': " + s.indexOf('o'));
    System.out.println("Last index of 'o': " + s.lastIndexOf('o'));
  }
}
