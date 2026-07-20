package com.starter.packages.concepts.basic.variable;

public class DataType {
  void pritimive() { // Store directly into to memory
    // Boolean
    boolean varBool = true; // 1 byte (8 bits) || true or false

    // Character
    char varCharacter = 'a'; // 16-bits Unicode character. || DEFAULT: \u0000

    // Integer
    byte varByte = 1; // 8-bits signed two's complement integer (-128 to 127) ||  DEFAULT:0
    short varShort = 1; // 16-bits signed two-complement integer (-32,768 to 32,767) ||  DEFAULT:0
    int varInt =
        1; // 32-bits signed two's complement integer (-2,147,483,648 to 2,147,483,647) || DEFAULT:0
    long varLong = 1; // 64-bits signed two's complement integer ( 9,223,372,036,854,775,808 to
    // 9,223,372,036,854,775,807) || DEFAULT:0

    // Floating
    float varFloat =
        1.2f; // single-precision 32-bits IEEE 754 floating-point numbers || DEFAULT: 0.0f, 0.0F
    double varDouble =
        1.222; // double-precision 64-bits IEEE 754 floating-point numbers || DEFAULT:0.0d, 0.0D
    System.out.println("***************** Boolean *****************");
    System.out.println(varBool);

    System.out.println("***************** Character *****************");
    System.out.println(varCharacter);

    System.out.println("***************** Integer *****************");
    System.out.println(varInt);
    System.out.println(varByte);
    System.out.println(varLong);
    System.out.println(varShort);

    System.out.println("***************** Float *****************");
    System.out.println(varFloat);
    System.out.println(varDouble);
  }

  void nonPritimive() { // reference type

    class Main {}
    interface Shape {
      public void draw(); // interface method (does not have a body)

      public void color(); // interface method (does not have a body)
    }
    int[] arr = {1, 2, 3, 4, 5};
    String s = "tpointtech";
    enum Grade {
      FIRST,
      SECOND,
      THIRD
    }
  }
}
