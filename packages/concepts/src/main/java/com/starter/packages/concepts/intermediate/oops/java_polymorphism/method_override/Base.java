package com.starter.packages.concepts.intermediate.oops.java_polymorphism.method_override;

/*
*Runtime polymorphism, also known as Dynamic Method Dispatch,
*Dynamic (Runtime) Binding:
* The method to be called is determined at runtime based on the
* actual object, not the reference type.
* It happens with method overriding.
* Dynamic binding is also known as Late binding.
*Overriding Rule :-
*Same Method Name
*Same Parameters
*IS-A Relationship (Inheritance)
*Same Return Type or Covariant Return Type
*Access Modifier Restrictions
*No Final Methods
*No Static Methods
* Rule: Runtime polymorphism can't be achieved by data members.

*/
public class Base {
  public String displayClassName() {
    return "Base";
  }
}
