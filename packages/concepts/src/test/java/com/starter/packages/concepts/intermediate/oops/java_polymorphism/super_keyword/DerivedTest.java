package com.starter.packages.concepts.intermediate.oops.java_polymorphism.super_keyword;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

class DerivedTest {
  Derived d = new Derived("Custom class name");

  @Test
  @Name("Test get classname methods")
  void getClassname() {
    assertEquals("Base class instance method", d.getClassName());
  }
}
