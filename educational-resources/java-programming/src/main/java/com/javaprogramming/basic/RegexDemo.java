package com.javaprogramming.basic;

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
      System.out.println("  Found: " + matcher.group() + " at index " + matcher.start());
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
    System.out.println("Case insensitive: " + caseInsensitive.matcher("HELLO").find());

    // Email validation
    String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    System.out.println("Is email: " + Pattern.matches(emailPattern, "user@example.com"));
  }
}
