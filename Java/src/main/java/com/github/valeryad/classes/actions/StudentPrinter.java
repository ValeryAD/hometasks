package com.github.valeryad.classes.actions;

import com.github.valeryad.classes.entities.Faculty;
import com.github.valeryad.classes.entities.Student;


import java.text.SimpleDateFormat;

public class StudentPrinter {

    private static final String CLARIFY_DATA = "to be clarified";
    private static final String FORMAT_STR = "%10s|%-12s|%-12s|%-14s|%-15s|%-20s|%-15s|%-15s|%-15s|%-15s|%-36s|%-15s|%-15s";
    private static final String rowDivider;
    private static final int ROW_DIVIDER_LENGTH = 220;
    private static final String STUD_IS_NULL_MESSAGE = "\t\t\t\ta student object you're trying to print is null";
    private static final String STUD_ARRAY_IS_NULL_MESSAGE = "\t\t\t\tan array you're trying to print is null";


    static {
        StringBuilder sb = new StringBuilder("-");
        for (int i = 0; i < ROW_DIVIDER_LENGTH; i++) {
            sb.append("-");
        }
        rowDivider = sb.toString();
    }

    public static void printStudent(Student student) {
        if (student == null) {
            System.err.println(rowDivider + "\n" + STUD_IS_NULL_MESSAGE + "\n" + rowDivider);
            return;
        }
        SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");

        String str = String.format(FORMAT_STR,
                student.getId(),
                student.getSecondName() != null ? student.getSecondName() : CLARIFY_DATA,
                student.getName() != null ? student.getName() : CLARIFY_DATA,
                student.getPatronymic() != null ? student.getPatronymic() : CLARIFY_DATA,
                student.getAddress() != null && student.getAddress().getCity() != null ? student.getAddress().getCity() : CLARIFY_DATA,
                student.getAddress() != null && student.getAddress().getStreet() != null ? student.getAddress().getStreet() : CLARIFY_DATA,
                student.getAddress() != null && student.getAddress().getHouseNumber() != null ? student.getAddress().getHouseNumber() : CLARIFY_DATA,
                student.getAddress() != null && student.getAddress().getApartment() != null ? student.getAddress().getApartment() : CLARIFY_DATA,
                student.getAddress() != null && student.getAddress().getPhoneNumber() > 0 ? student.getAddress().getPhoneNumber() : CLARIFY_DATA,
                student.getDateOfBirth() != null ? dateForm.format(student.getDateOfBirth()) : CLARIFY_DATA,
                student.getFaculty() != null ? student.getFaculty() : CLARIFY_DATA,
                student.getYearOfStudy() > 0 ? student.getYearOfStudy() : CLARIFY_DATA,
                student.getGroup() != null ? student.getGroup() : CLARIFY_DATA);
        System.out.println(str);

    }

    public static void printStudentsGroupedByFacultyAndYearOfStudy(Student[] students) {
        Faculty[] faculties = Faculty.values();
        Student[] studentOfFaculty;

        for (Faculty faculty : faculties) {
            studentOfFaculty = StudentAction.getStudentsOfFaculty(students, faculty);
            for (int i = 1; i <= StudentGenerator.MAX_YEAR_OF_STUDY; i++) {
                System.out.println(StudentPrinter.rowDivider + "\n");
                System.out.printf("\t\t\tfaculty of %s, year of study %d:\n", faculty, i);
                System.out.println(StudentPrinter.rowDivider + "\n");
                for (Student student : studentOfFaculty) {
                    if (student.getYearOfStudy() == i) {
                        StudentPrinter.printStudent(student);
                        System.out.println(StudentPrinter.rowDivider);
                    }
                }
            }
        }
    }

    public static void printStudentsOfFaculty(Student[] students, Faculty faculty) {

        Student[] studentsOfFaculty = StudentAction.getStudentsOfFaculty(students, faculty);
        StudentPrinter.printArray(studentsOfFaculty);
        System.out.printf("The faculty of %s has %d students\n", faculty.getName(), studentsOfFaculty.length);
    }

    public static void printArray(Student[] students) {

        if (students == null) {
            System.err.println(rowDivider + "\n" + STUD_ARRAY_IS_NULL_MESSAGE + "\n" + rowDivider);
            return;
        }

        String head = String.format(FORMAT_STR, "ID", "second name", "name", "patronymic",
                "city", "street", "house number", "apartment", "phone",
                "date of birth", "faculty", "year of study", "group");

        System.out.println(rowDivider);
        System.out.println(head);
        System.out.println(rowDivider);

        for (int i = 0; i < students.length; i++) {
            printStudent(students[i]);
            System.out.println(rowDivider);
        }
        System.out.println();
    }

    public static void printAllGroups() {
        int facultyAmount = Faculty.values().length;
        StringBuilder sb = new StringBuilder();
        Student tempSt = new Student();
        for (int i = 1; i <= StudentGenerator.MAX_YEAR_OF_STUDY; i++) {
            tempSt.setYearOfStudy(i);
            System.out.print("\t\t");
            for (int j = 1; j <= facultyAmount; j++) {
                for (int k = 1; k <= StudentGenerator.GROUPS_AMOUNT; k++) {
                    System.out.printf("%d%d%d\t", StudentAction.countLastDigitOfYearOfAdmission(tempSt), j, k);
                }
            }
            System.out.println();
        }
    }

}
