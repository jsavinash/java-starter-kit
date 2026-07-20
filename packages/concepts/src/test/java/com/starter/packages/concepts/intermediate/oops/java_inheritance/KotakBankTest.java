package com.starter.packages.concepts.intermediate.oops.java_inheritance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

class KotakBankTest {
  KotakBank kb = new KotakBank();

  @Test()
  @Name("Test bank display name method")
  void testDisplay() {
    assertEquals("Bank", kb.display());
  }
}
