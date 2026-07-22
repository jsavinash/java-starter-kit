package com.javaprogramming.basic;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimes {
  public static void main(String[] args) {
    // Current date/time
    LocalDateTime now = LocalDateTime.now();
    LocalDate today = LocalDate.now();
    LocalTime time = LocalTime.now();

    System.out.println("Now: " + now);
    System.out.println("Today: " + today);
    System.out.println("Time: " + time);

    // Specific date
    LocalDate christmas = LocalDate.of(2024, 12, 25);
    System.out.println("Christmas: " + christmas);

    // Parse
    LocalDate parsed = LocalDate.parse("2024-12-01");
    System.out.println("Parsed: " + parsed);

    // Format
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    System.out.println("Formatted: " + now.format(fmt));

    // Add/Subtract
    LocalDate nextWeek = today.plusDays(7);
    LocalDate lastMonth = today.minusMonths(1);
    System.out.println("Next week: " + nextWeek);
    System.out.println("Last month: " + lastMonth);

    // Difference
    long daysBetween = ChronoUnit.DAYS.between(today, christmas);
    System.out.println("Days to Christmas: " + daysBetween);

    // Compare
    System.out.println("Is after: " + christmas.isAfter(today));
    System.out.println("Is before: " + christmas.isBefore(today));

    // Zoned
    ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
    System.out.println("UTC: " + utc);

    // Timestamp
    long timestamp = System.currentTimeMillis();
    System.out.println("Timestamp: " + timestamp);

    // Day of week
    System.out.println("Day of week: " + today.getDayOfWeek());
    System.out.println("Day of year: " + today.getDayOfYear());

    // Period
    Period period = Period.between(today, christmas);
    System.out.println("Period: " + period.getMonths() + " months, " + period.getDays() + " days");
  }
}
