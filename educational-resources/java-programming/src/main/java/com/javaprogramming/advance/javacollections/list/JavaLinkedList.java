package com.javaprogramming.advance.javacollections.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Spliterator;

public class JavaLinkedList {
  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    list.add("apple");
    list.add("mango");
    list.add("banana");
    list.add("grapes");
    list.offer("dates");
    list.offerFirst("fruit 1");
    list.offerLast("fruit 2");

    // for loop
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    // for each
    for (String item : list) {
      System.out.println(item);
    }
    // iterator
    Iterator<String> itr = list.iterator();
    itr.forEachRemaining(x -> System.out.println(x));
    Iterator<String> itr2 = list.iterator();
    while (itr2.hasNext()) {
      System.out.println(itr2.next());
    }
    ListIterator<String> itr3 = list.listIterator();
    while (itr3.hasPrevious()) {
      System.out.println(itr3.previous());
    }
    Spliterator<String> firstHalf = list.spliterator();
    Spliterator<String> secondHalf = firstHalf.trySplit(); // Splits the list
    System.out.println("First half");
    firstHalf.forEachRemaining(System.out::println); // Traverses first chunk
    System.out.println("Second half");
    if (secondHalf != null) {
      secondHalf.forEachRemaining(System.out::println); // Traverses second chunk
    }
    System.out.println("Descending");
    Iterator<String> itr4 = list.descendingIterator();
    itr4.forEachRemaining(x -> System.out.println(x));
    LinkedList<String> list2 = new LinkedList<>();
    list2.add("fruit 3");

    LinkedList<String> list3 = new LinkedList<>();
    list3.add("fruit 1");
    list3.add("fruit 2");
    list3.add("fruit 3");

    LinkedList<String> list4 = new LinkedList<>();
    list4.add("fruit 3");

    list.addAll(list2); // union: keep all unique
    System.out.println(list);
    list.retainAll(list3); // intersection: keep matching
    System.out.println(list);
    list.removeAll(list4); // difference: remove matching
    System.out.println(list);
  }
}
