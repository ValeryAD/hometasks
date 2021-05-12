package com.github.valeryad.classes.actions;

import com.github.valeryad.classes.entities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class StudentAction {

    public static Student[] findStudentsBornAfterYear(Student[] students, int yearOfBirth) {
        Date minDate = new GregorianCalendar(yearOfBirth + 1, 0, 1).getTime();
        int counter = 0;

        for (Student student : students) {
            if (minDate.before(student.getDateOfBirth())) {
                counter++;
            }
        }

        Student[] studentsBornAfterYear = new Student[counter];
        counter = 0;

        for (Student student : students) {
            if (minDate.before(student.getDateOfBirth())) {
                studentsBornAfterYear[counter++] = student;
            }
        }

        return studentsBornAfterYear;
    }

    public static Student[] getStudentsOfFaculty(Student[] students, Faculty faculty) {

        int counter = 0;

        for (Student student : students) {
            if (faculty.equals(student.getFaculty())) {
                counter++;
            }
        }

        Student[] studentsOfFaculty = new Student[counter];
        counter = 0;
        for (Student student : students) {
            if (faculty.equals(student.getFaculty())) {
                studentsOfFaculty[counter++] = student;
            }
        }
        return studentsOfFaculty;
    }

    public static Student[] getStudentsOfGroup(Student[] students, String group) {
        int counter = 0;

        if (group.length() == 2) {
            group = "0" + group;
        } else if (group.length() == 1) {
            group = "00" + group;
        }

        for (Student student : students) {
            if (student.getGroup().equals(group)) {
                counter++;
            }
        }

        if (counter == 0) {
            return new Student[0];
        }

        Student[] studentsOfGroup = new Student[counter];
        counter = 0;

        for (Student student : students) {
            if (student.getGroup().equals(group)) {
                studentsOfGroup[counter++] = student;
            }
        }
        return studentsOfGroup;
    }


    public static Student findTheYoungestStudent(Student[] students) {
        if (students == null) return null;

        Student youngestStudent = students[0];

        for (Student student : students) {
            if (student != null && student.getDateOfBirth().before(youngestStudent.getDateOfBirth())) {
                youngestStudent = student;
            }
        }
        return youngestStudent;
    }

    public static Student findTheOldestStudent(Student[] students) {
        if (students == null) return null;

        Student oldestStudent = students[0];

        for (Student student : students) {
            if (student != null && student.getDateOfBirth().after(oldestStudent.getDateOfBirth())) {
                oldestStudent = student;
            }
        }
        return oldestStudent;
    }

    public static int countLastDigitOfYearOfAdmission(Student student) {
        if (student.getYearOfStudy() == 0) return -1;
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy");
        int thisYear = Integer.valueOf(sdFormat.format(new Date()));
        sdFormat = new SimpleDateFormat("M");
        boolean iSFirstHalfOfTheYear = Integer.valueOf(sdFormat.format(new Date())) < 8;

        int yearOfAdmission = thisYear - (student.getYearOfStudy() - 1) - (iSFirstHalfOfTheYear ? 1 : 0);

        return yearOfAdmission % 10;
    }
}
