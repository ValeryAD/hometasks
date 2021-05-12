package com.github.valeryad.classes.actions;

import com.github.valeryad.classes.entities.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class StudentGenerator {
    private static final int MAX_APARTMENT_VALUE = 320;
    private static final int MAX_HOUSENUM_VALUE = 120;
    private static final String PHONE_PREFIX = "37529";
    public static final int GROUPS_AMOUNT = 2;
    public static final int MAX_YEAR_OF_STUDY = 6;


    private static Random rand;

    static {
        rand = new Random();
    }


    public static Student[] generateStudentArr(int amount) {
        Student[] students = new Student[amount];

        for (int i = 0; i < amount; i++) {
            students[i] = generateStudent();
        }

        return students;
    }

    public static Student generateStudent() {
        Student student = new Student();
        fillFullName(student);
        fillAddress(student);
        fillDateOfBirth(student);
        fillFaculty(student);
        fillYearOfStudy(student);
        fillGroup(student);

        return student;
    }

    private static void fillFullName(Student student) {
        boolean isMale = rand.nextBoolean();

        String[] maleNames = {"Валерий", "Дмитрий", "Владимир",
                "Андрей", "Никита", "Павел", "Александр",
                "Виталий", "Валентин", "Георгий", "Анатолий"};
        String[] femaleNames = {"Ольга", "Вера", "Наталья", "Александра",
                "Валентина", "Галина", "Мальвина", "Нина", "Оксана"};
        String[] malePatronymic = {"Валерьевич", "Дмитриевич", "Владимирович",
                "Андреевич", "Сергеевич", "Павлович", "Александрович",
                "Витальевич", "Валентинович", "Георгиевич", "Анатольевич"};
        String[] femalePatronymic = {"Валерьевна", "Дмитриевна", "Владимировна",
                "Андреевна", "Сергеевна", "Павловна", "Александровна",
                "Витальевна", "Валентиновна", "Георгиевна", "Анатольевна"};
        String[] maleSecondName = {"Иванов", "Петров", "Cидоров", "Колбасов", "Гончаров",
                "Селезнев", "Пушкарев", "Пушкин", "Пугачев", "Свердлов", "Хрущев",
                "Лещев", "Помидоров", "Картунков", "Соловьев", "Комаров"};
        if (isMale) {
            student.setName(getRandFromArr(maleNames));
            student.setSecondName(getRandFromArr(maleSecondName));
            student.setPatronymic(getRandFromArr(malePatronymic));
        } else {
            student.setName(getRandFromArr(femaleNames));
            student.setSecondName(getRandFromArr(maleSecondName) + "а");
            student.setPatronymic(getRandFromArr(femalePatronymic));
        }
    }

    private static void fillAddress(Student student) {
        String[] cities = {"Минск", "Барановичи", "Бобруйск", "Марьина Горка", "Логойск", "Жодино", "Минск"};
        String[] streets = {"ул. Ленина", "пр. Пушкина", "ул. Максима Танка", "ул. Набережная", "ул. Лесная", "ул. Полевая", "ул. Садовая"};
        Address adr = new Address();
        adr.setCity(getRandFromArr(cities));
        adr.setStreet(getRandFromArr(streets));
        adr.setHouseNumber(String.valueOf(rand.nextInt(MAX_HOUSENUM_VALUE + 1)));
        adr.setApartment(String.valueOf(rand.nextInt(MAX_APARTMENT_VALUE + 1)));
        adr.setPhoneNumber(Long.parseLong(PHONE_PREFIX + String.valueOf(rand.nextInt(1000_00_00))));
        student.setAddress(adr);
    }

    private static void fillDateOfBirth(Student student) {
        int year = (rand.nextInt(21) + 1982);
        int month = rand.nextInt(12);
        int day = (rand.nextInt(27) + 1);
        Date dateOfBirth = new GregorianCalendar(year, month, day).getTime();
        student.setDateOfBirth(dateOfBirth);
    }

    private static void fillFaculty(Student student) {
        Faculty[] faculties = Faculty.values();
        student.setFaculty(faculties[rand.nextInt(faculties.length)]);
    }

    private static void fillYearOfStudy(Student student) {
        student.setYearOfStudy(rand.nextInt(MAX_YEAR_OF_STUDY) + 1);
    }


    //First number is last digit of an admission year, second is ordinal number of faculty, third is number of a group
    private static void fillGroup(Student student) {
        int firstDigit = StudentAction.countLastDigitOfYearOfAdmission(student);

        student.setGroup(String.valueOf(firstDigit) +
                (student.getFaculty().ordinal() + 1) + (rand.nextInt(GROUPS_AMOUNT) + 1));
    }

    private static String getRandFromArr(String[] arr) {
        return arr[rand.nextInt(arr.length)];
    }
}
