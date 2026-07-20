package com.starter.packages.concepts.intermediate.oops.java_polymorphism.block;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BaseTest {
  Base b = new Base();

  @Test
  void getFullname() {
    assertEquals("firstname :: Base 1, lastname :: Base 2", b.getFullname());
  }
}
