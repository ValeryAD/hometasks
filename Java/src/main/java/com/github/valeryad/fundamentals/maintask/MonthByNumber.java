package com.github.valeryad.fundamentals.maintask;

//Ввести число от 1 до 12.
//Вывести на консоль название месяца, соответствующего данному числу.
//Осуществить проверку корректности ввода чисел.

import java.util.Scanner;

public class MonthByNumber {
    final static String INPUT_REQUEST = "Please type in integer from 1 to 12";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        String month;

        do {
            System.out.println(INPUT_REQUEST);
            while (!sc.hasNextInt()) {
                System.out.println(INPUT_REQUEST);
                sc.nextLine();
            }

            num = sc.nextInt();
        } while (num < 1 || num > 12);

        switch (num) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                month = "No such month";
        }

        System.out.println(month);
    }
}
