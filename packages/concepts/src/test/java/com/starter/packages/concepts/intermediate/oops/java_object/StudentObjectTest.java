package com.starter.packages.concepts.intermediate.oops.java_object;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jdk.jfr.Name;
import org.junit.jupiter.api.Test;

class StudentObjectTest {
  StudentObject so = new StudentObject();

  @Test()
  @Name("Checking object creation")
  void testObject() {
    assertEquals("Avinash", so.name);
  }
}
