package com.starter.packages.concepts.intermediate.oops.java_coupling;

public class StronglyCoupledApp {
  MyDB mydb = new MyDB();

  boolean isDBConnected() {
    return mydb.connected();
  }
}
