package com.javaprogramming.advance.javacollections.set;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaHashMap {
  static void main() {
    HashMap<String, String> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    map.put("key4", "value4");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println(String.format("Key : Value :: %s : %s", entry.getKey(), entry.getValue()));
    }
    Set<String> keys = map.keySet();
    Collection<String> values = map.values();
    System.out.println(keys);
    System.out.println(values);
    System.out.println(map.get("key1"));
    System.out.println(map.isEmpty());
    System.out.println(map.size());
    System.out.println(map.containsKey("key1"));
    System.out.println(map.containsValue("value1"));
    System.out.println(map.getOrDefault("value1", "not-found"));
    map.putIfAbsent("key5", "value5");
    System.out.println(map); // No insertion order maintained.
    map.forEach((key, value) -> System.out.println(String.format("%s : %s ", key, value)));
  }
}
