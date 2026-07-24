package com.javaprogramming.advance.javacollections.list;

import java.util.LinkedList;

public class JavaLinkedList {
    public static void main(String[] args){
        LinkedList<String> list = new LinkedList<>();
        list.add("apple");
        list.add("mango");
        list.add("banana");
        list.add("grapes");

        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
