package com.javaprogramming.advance.javacollections.set;

import java.util.HashSet;
import java.util.Iterator;

// No listiterator
public class JavaSet {
  static void main() {
    HashSet<String> set = new HashSet<String>();
    set.add("one");
    set.add("two");
    set.add("three");
    set.add("four");
    set.add("five");
    set.remove("five");
    System.out.println(set.contains("one"));
    System.out.println(set.size());
    System.out.println(set.isEmpty());
    set.forEach(x -> System.out.println(x));
    for (String item : set) {
      System.out.println(item);
    }
    Iterator<String> itr = set.iterator();
    itr.forEachRemaining(x -> System.out.println(x));
    Iterator<String> itr2 = set.iterator();
    while (itr2.hasNext()) {
      System.out.println(itr2.next());
    }
  }
}
