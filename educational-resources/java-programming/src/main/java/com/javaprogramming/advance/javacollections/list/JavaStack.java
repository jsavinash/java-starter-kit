package com.javaprogramming.advance.javacollections.list;

import java.util.Stack;

public class JavaStack {
  public static void main(String[] args) {
    Stack<String> s = new Stack<>();
    s.add("apple");
    s.add("mango");
    System.out.println(s);
    s.pop();
    System.out.println(s);
    s.push("dates");
    System.out.println(s);
  }
}
