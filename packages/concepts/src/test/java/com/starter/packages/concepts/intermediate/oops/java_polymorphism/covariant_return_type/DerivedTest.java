package com.starter.packages.concepts.intermediate.oops.java_polymorphism.covariant_return_type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

class DerivedTest {
  Derived d = new Derived();
  Base b = new Base();

  @Test
  @Name("Test instance return type")
  void getInstance() {
    assertTrue(b.getInstance() instanceof Base);
    assertTrue(d.getInstance() instanceof Derived);
  }

  @Test
  void getClassname() {
    assertEquals("Base", d.getInstance().getClassname());
  }
}
