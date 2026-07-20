package com.starter.packages.concepts.intermediate.oops.java_polymorphism.method_overloading;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculatorTest {
  Calculator bc = new Calculator();

  @Test
  void testAddTwo() {
    assertEquals(3, bc.add(1, 2));
  }

  @Test
  void testAddThree() {
    assertEquals(6, bc.add(1, 2, 3));
  }

  @Test
  void testMultiplyInt() {
    assertEquals(2, bc.multiply(1, 2));
  }

  @Test
  void testMultiplyDouble() {
    assertEquals(2.64, bc.multiply(1.2, 2.2));
  }
}
