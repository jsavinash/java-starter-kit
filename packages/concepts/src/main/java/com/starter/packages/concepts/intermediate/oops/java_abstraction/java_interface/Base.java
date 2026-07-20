package com.starter.packages.concepts.intermediate.oops.java_abstraction.java_interface;

public class Base implements IBase {
  @Override
  public String print() {
    System.out.println(IBase.NAME);
    return "Base class called";
  }
}
