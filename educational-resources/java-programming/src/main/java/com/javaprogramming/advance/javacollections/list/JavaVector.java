package com.javaprogramming.advance.javacollections.list;

import java.util.Vector;

public class JavaVector {
  public static void main(String[] args) {
    Vector<String> v = new Vector<>();
    v.add("apple");
    v.add("mango");
    for (String item : v) {
      System.out.println(item);
    }
  }
}
