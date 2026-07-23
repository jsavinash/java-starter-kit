package com.javaprogramming.advance.javacollections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class JavaArrayList {
  public static void main(String[] args) {
    ArrayList<String> arrayList = new ArrayList<>();

    // Add element in array list.
    arrayList.add("apple");
    arrayList.add("mango");
    arrayList.add("peach");
    arrayList.add("banana");
    System.out.println(arrayList);
    // Remove element in array list.
    arrayList.remove(0);
    arrayList.remove("mango");
    System.out.println(arrayList);
    // Update element in array list.
    arrayList.set(0, "iceapple");
    System.out.println(arrayList);
    // Iterate element via for loop
    for (int i = 0; i < arrayList.size(); i++) {
      System.out.println(String.format("Item at index %d : %s", i, arrayList.get(i)));
    }
    // Iterate element via for each.
    for (String item : arrayList) {
      System.out.println(String.format("Item : %s", item));
    }
    // Iterate element via iterator.
    Iterator<String> itr = arrayList.iterator(); // forward direction iterator.
    System.out.println("forEachRemaining ::");
    itr.forEachRemaining(x -> System.out.println(x));
    while (itr.hasNext()) {
      System.out.println(String.format("Item : %s", itr.next()));
    }

    // Iterate element via listIterator.
    ListIterator<String> listitr = arrayList.listIterator(); // bi directional iterator.
    while (listitr.hasPrevious()) {
      System.out.println(String.format("Item : %s", listitr.previous()));
    }
    // Union :- all unique element will be there no duplication.
    ArrayList<String> list2 = new ArrayList<>();
    list2.add("grapes");
    arrayList.addAll(list2);
    System.out.println("Union :" + arrayList);
    // Intersection :- matching element will remain rest will be removed.
    // Keep only elements in list1 that are also in list2.
    ArrayList<String> list3 = new ArrayList<>();
    list3.add("banana");
    arrayList.retainAll(list3);
    System.out.println("Intersection :" + arrayList);
    // Difference : remove element which is there in second set.
    arrayList.removeAll(list3);
    System.out.println("Difference :" + arrayList);
    arrayList.add("fruit 1");
    arrayList.add("fruit 2");

    arrayList.forEach(x -> System.out.println(x));
  }
}
