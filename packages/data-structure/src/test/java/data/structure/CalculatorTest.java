package data.structure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {
  private final Calculator calculator = new Calculator();

  @Test
  @DisplayName("Test the addition method with various inputs")
  void testAdd() {
    // Test positive numbers
    assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    // Test negative numbers
    assertEquals(-3, calculator.add(-1, -2), "-1 + -2 should equal -3");
    // Test with zero
    assertEquals(0, calculator.add(0, 0), "0 + 0 should equal 0");
  }

  @Test
  @DisplayName("Test the subtraction method")
  void testSubtract() {
    assertEquals(2, calculator.subtract(5, 3), "5 - 3 should equal 2");
    assertEquals(-5, calculator.subtract(0, 5), "0 - 5 should equal -5");
  }

  @Test
  @DisplayName("Test the multiplication method")
  void testMultiply() {
    assertEquals(15, calculator.multiply(3, 5), "3 * 5 should equal 15");
    assertEquals(0, calculator.multiply(10, 0), "10 * 0 should equal 0");
  }

  @Test
  @DisplayName("Test the division method and division by zero exception")
  void testDivide() {
    // Test normal division
    assertEquals(2, calculator.divide(8, 4), "8 / 4 should equal 2");

    // Test division by zero, expecting an ArithmeticException
    Exception exception =
        assertThrows(
            ArithmeticException.class,
            () -> calculator.divide(1, 0),
            "Division by zero should throw an exception");

    // Verify the exception message
    assertEquals("/ by zero", exception.getMessage(), "Exception message should be '/ by zero'");
  }
}
