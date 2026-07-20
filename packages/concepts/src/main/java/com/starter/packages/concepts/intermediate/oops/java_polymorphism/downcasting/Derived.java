package com.starter.packages.concepts.intermediate.oops.java_polymorphism.downcasting;

public class Derived extends Base {
  String getClassName() {
    return "Derived";
  }

  static String performDowncasting(Base b) {
    if (b instanceof Derived) {
      Derived d = (Derived) b; // downcasting
      return d.getClassName();
    }
    return "";
  }
}
