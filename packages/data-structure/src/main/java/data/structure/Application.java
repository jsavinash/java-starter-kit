package data.structure;

import data.structure.array.size.FixedArray;

public class Application {
  public static void main(String args[]) {
    try {
      FixedArray fa = new FixedArray(5);
      fa.insertAtEnd("One");
      fa.insertAtEnd("Two");
      fa.insertAtEnd("Three");
      fa.insertAtEnd("Four");
      fa.insertAtEnd("Five");
      fa.insertAtEnd("Six");
      System.out.println(fa.traverse());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
