package com.javaprogramming.basic;

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
    System.out.println("a == b: " + (a == b)); // true (interned)
    System.out.println("a == c: " + (a == c)); // false
    System.out.println("a.equals(c): " + a.equals(c)); // true
  }
}
