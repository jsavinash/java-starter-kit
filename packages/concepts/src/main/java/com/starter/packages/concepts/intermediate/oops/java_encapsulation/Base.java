package com.starter.packages.concepts.intermediate.oops.java_encapsulation;

/*
 * Encapsulation means hiding internal details of a class
 * and only exposing what’s necessary via access modifier.
 * Access Modifier ||  Within Class ||  Within Package || Outside Package by Subclass Only || Outside Package
 * private         ||       Yes     ||        No       ||                No                ||       No
 * Default         ||       Yes     ||        Yes      ||                No                ||       No
 * protected       ||       Yes     ||        Yes      ||                Yes               ||       No
 * public          ||       Yes     ||        Yes      ||                Yes               ||       Yes
 */
public class Base {
  String address = "Avinash"; // Default
  public String firstname = "Avinash"; // Public
  private String lastname = "Avinash"; // Private
  protected String companyName = "Avinash"; // Protected
}
