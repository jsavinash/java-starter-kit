package com.starter.packages.concepts.intermediate.oops.java_coupling;

public class WeaklyCoupledApp {
  MyDB mydb = null;

  WeaklyCoupledApp(MyDB mydb) {
    this.mydb = mydb;
  }

  boolean isDBConnected() {
    if (this.mydb instanceof MyDB) {
      return mydb.connected();
    }
    return false;
  }
}
