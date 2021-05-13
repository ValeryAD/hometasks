package com.github.valeryad.fundamentals.maintask;

//Ввести целые числа как аргументы командной строки, подсчитать их сумму (произведение) и вывести результат на консоль.

public class IntegerArgs {
    final static String SUM_RESULT = "Sum of arguments is %d";
    final static String MULT_RESULT = "Product of arguments is %d";

    public static void main(String[] args) {
        long sum = 0;
        long product = args.length == 0 ? 0 : 1;

        for (int i = 0; i < args.length; i++) {
            sum += Integer.parseInt(args[i]);
            product *= Integer.parseInt(args[i]);
        }

        System.out.printf(SUM_RESULT, sum);
        System.out.println();
        System.out.printf(MULT_RESULT, product);
    }
}
