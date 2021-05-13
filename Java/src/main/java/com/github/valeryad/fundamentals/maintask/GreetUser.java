package com.github.valeryad.fundamentals.maintask;

//Приветствовать любого пользователя при вводе его имени через командную строку.

import java.util.Scanner;

public class GreetUser {
    final static String NAME_REQUEST = "Please type in your name";
    final static String GREETING = "Hello, %s! Nice to meet you!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(NAME_REQUEST);

        System.out.printf(GREETING, sc.nextLine());
    }
}
