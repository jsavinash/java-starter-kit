package com.starter.packages.concepts.intermediate.oops.java_coupling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WeaklyCoupledAppTest {
  MyDB mydb = new MyDB();
  WeaklyCoupledApp app = new WeaklyCoupledApp(mydb);

  @Test
  void isDBConnected() {
    assertEquals(true, app.isDBConnected());
  }
}
