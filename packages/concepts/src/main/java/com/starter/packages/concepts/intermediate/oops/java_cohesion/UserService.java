package com.starter.packages.concepts.intermediate.oops.java_cohesion;

/*
Low Cohesive :- Doing everything in single class, like save to db, sent mail, validate.
 */
public class UserService {
  void saveInDB() {
    System.out.println("Saved to DB");
  }

  void sendMail() {
    System.out.println("Mail sent");
  }

  void validate() {
    System.out.println("Input validated");
  }
}
