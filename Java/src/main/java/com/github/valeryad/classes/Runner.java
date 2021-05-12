package com.github.valeryad.classes;

import com.github.valeryad.classes.actions.StudentAction;
import com.github.valeryad.classes.actions.StudentGenerator;
import com.github.valeryad.classes.actions.StudentPrinter;
import com.github.valeryad.classes.entities.Faculty;
import com.github.valeryad.classes.entities.Student;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Runner {

    private final static String MAIN_MENU = "Choose item to execute:\n" +
            "1 - TASK 1 \tprint list of students of specified faculty\n" +
            "2 - TASK 2 \tprint list of students for every faculty and year of study\n" +
            "3 - TASK 3 \tprint list of students that born after specified year\n" +
            "4 - TASK 4 \tprint list of students of specified group\n" +
            "0 - for exit\n";
    private final static String WRONG_INPUT_MESSAGE = "Wrong input. Please type in integer value representing item of menu";
    private static final String FACULTY_REQUEST_MESSAGE = "\nChoose faculty:\n";
    private static final String FOR_RETURN = "0 - for return\n";
    private static final String TYPE_YEAR_REQUEST = "type in value of the year (integer value)\n";
    private static final String TYPE_YEAR_MESSAGE = "\nThe youngest student was born in %s, the oldest one in %s\n";
    private static final int AMOUNT_OF_STUDENTS_TO_GENERATE = 400;
    private static final String GROUP_INPUT_REQUEST = "Type in number of a group you wish to print.";
    private static final String GROUP_OUTPUT_ANNOUNCE = "Next groups exist in our university:\n";
    private static final String GROUP_NOT_EXISTS_MESSAGE = "\nThere's no group with such number\n";

    private static Scanner scanner;
    private static Student[] students;


    static {
        scanner = new Scanner(System.in);
        students = StudentGenerator.generateStudentArr(AMOUNT_OF_STUDENTS_TO_GENERATE);
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println(MAIN_MENU);
            choice = askUserForChoice(WRONG_INPUT_MESSAGE);

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    runTask1();
                    break;
                case 2:
                    runTask2();
                    break;
                case 3:
                    runTask3();
                    break;
                case 4:
                    runTask4();
                    break;
                default:
                    System.out.println(WRONG_INPUT_MESSAGE);
                    continue;
            }

        } while (choice != 0);

    }

    private static void runTask1() {
        Faculty[] faculties = Faculty.values();
        int choice = 0;
        Faculty selectedFaculty;
        Student[] studResult;

        do {
            System.out.println(FACULTY_REQUEST_MESSAGE);
            choice = 0;
            for (int i = 0; i < faculties.length; i++) {
                System.out.printf("%d - %s\n", i + 1, faculties[i].getName());
            }
            System.out.println(FOR_RETURN);

            choice = askUserForChoice(WRONG_INPUT_MESSAGE);


            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= faculties.length) {
                selectedFaculty = faculties[choice - 1];
                StudentPrinter.printStudentsOfFaculty(students, selectedFaculty);
            } else {
                System.out.println(WRONG_INPUT_MESSAGE);
            }
        } while (true);
    }

    private static void runTask2() {
        StudentPrinter.printStudentsGroupedByFacultyAndYearOfStudy(students);
    }

    private static void runTask3() {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy");
        String leastYear = dformat.format(StudentAction.findTheYoungestStudent(students).getDateOfBirth());
        String greatestYear = dformat.format(StudentAction.findTheOldestStudent(students).getDateOfBirth());
        int year = 0;

        System.out.printf(TYPE_YEAR_MESSAGE, leastYear, greatestYear);

        do {
            System.out.println(TYPE_YEAR_REQUEST);
            year = askUserForChoice(TYPE_YEAR_REQUEST);
        } while (year < 0);

        StudentPrinter.printArray(StudentAction.findStudentsBornAfterYear(students, year));
    }

    private static void runTask4() {
        String group = "";
        System.out.println(GROUP_INPUT_REQUEST);
        System.out.println(GROUP_OUTPUT_ANNOUNCE);
        StudentPrinter.printAllGroups();

        group = String.valueOf(askUserForChoice(GROUP_INPUT_REQUEST));

        Student[] studentsOfGroup = StudentAction.getStudentsOfGroup(students, group);

        if (studentsOfGroup.length != 0) {
            StudentPrinter.printArray(studentsOfGroup);
        } else {
            System.out.println(GROUP_NOT_EXISTS_MESSAGE);
        }

    }

    private static int askUserForChoice(String messageIfWrongInput) {
        int choice = 0;

        while (!scanner.hasNextInt()) {
            System.out.println(messageIfWrongInput);
            scanner.nextLine();
        }

        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

}
