package com.starter.packages.concepts.intermediate.oops.java_polymorphism.method_override;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

class DerivedTest {
  Derived d = new Derived();

  @Test
  @Name("Test display class name method")
  void testDisplayClassName() {
    assertEquals("Derived", d.displayClassName());
  }
}
