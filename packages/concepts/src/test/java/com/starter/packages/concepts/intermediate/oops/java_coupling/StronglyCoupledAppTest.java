package com.starter.packages.concepts.intermediate.oops.java_coupling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StronglyCoupledAppTest {
  StronglyCoupledApp app = new StronglyCoupledApp();

  @Test
  void isDBConnected() {
    assertEquals(true, app.isDBConnected());
  }
}
