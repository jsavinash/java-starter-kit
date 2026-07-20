package com.starter.packages.concepts.intermediate.oops.java_abstraction.java_abstract;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DerivedTest {
  Derived d = new Derived();

  @Test
  void testGetLastName() {
    assertEquals("Avinash", d.getFirstName());
  }

  @Test
  void getFullName() {
    assertEquals("Welcome", Derived.getFullName());
  }

  @Test
  void display() {
    assertEquals("Welcome 2 !", d.display());
  }

  @Test
  void getLastName() {
    assertEquals("Nishad", d.getLastName());
  }
}
