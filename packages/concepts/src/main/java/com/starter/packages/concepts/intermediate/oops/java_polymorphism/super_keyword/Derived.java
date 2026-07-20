package com.starter.packages.concepts.intermediate.oops.java_polymorphism.super_keyword;

public class Derived extends Base {
  Derived(String name) {
    super(name);
    System.out.println("Derived constructor");
  }

  void getClassname() {
    super.getClassName();
  }
}
