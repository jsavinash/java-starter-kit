package com.starter.packages.concepts.intermediate.oops.java_coupling;

/*
 * Tight Coupling : If dependency is hard bounded to class.
 * Loose Coupling : If dependency is passed as injectable to class.
 */
public class MyDB {
  boolean connected() {
    return true;
  }
}
