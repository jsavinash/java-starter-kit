package com.starter.packages.concepts.intermediate.oops.java_polymorphism.downcasting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DerivedTest {
  Base b = new Derived(); // Implicit upcasting

  @Test
  void performDowncasting() {
    assertEquals("Derived", Derived.performDowncasting(b));
  }
}
