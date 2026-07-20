package com.starter.packages.concepts.intermediate.oops.java_abstraction.java_interface;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BaseTest {
  Base b = new Base();

  @Test
  void print() {
    assertEquals("Base class called", b.print());
  }
}
