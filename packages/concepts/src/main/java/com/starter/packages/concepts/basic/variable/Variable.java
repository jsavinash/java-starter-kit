package com.starter.packages.concepts.basic.variable;

public class Variable {
  /*
  Declared inside a class but outside any method, constructor, or block.
  Each object (instance) of the class has its own copy of these variables.
  */
  String varInstance = "instance";
  /*
  Declared with the static keyword inside a class, one copy of a static variable,
  which is shared by all the instance
   */
  static String varStatic = "static";

  void func() {
    /*
     Declared inside a method, constructor, or block.
     must be initialized before use,
     as Java does not assign them a default value.
    */
    String varLocal = "local";
  }
}
