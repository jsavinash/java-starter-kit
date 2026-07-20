package com.starter.packages.concepts.basic.control_statements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ControlStatementsTest {
  ControlStatements cs = new ControlStatements();

  @Test
  void decisionMaking() {
    assertEquals(true, cs.decisionMaking(18));
    assertEquals(false, cs.decisionMaking(17));
  }

  @Test
  void fixedLoop() {
    assertEquals("01234", cs.fixedLoop());
  }

  @Test
  void whileLoop() {
    assertEquals("01234", cs.whileLoop());
  }

  @Test
  void switchCase() {
    assertEquals("A", cs.switchCase("A"));
  }

  @Test
  void breakContinue() {
    assertEquals("013", cs.breakContinue());
  }
}
