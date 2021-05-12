package com.github.valeryad.classes.entities;

public abstract class Person {
    private long id;
    private String secondName;
    private String name;
    private String patronymic;

    public Person() {
        id = getNextId();
    }

    public Person(String secondName, String name, String patronymic) {
        this.id = getNextId();
        this.secondName = secondName;
        this.name = name;
        this.patronymic = patronymic;
    }

    protected abstract long getNextId();


    public long getId() {
        return id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public int hashCode() {
        int result = 31 * secondName.hashCode() + name.hashCode();
        result = 31 * patronymic.hashCode() + (int) id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Person person = (Person) obj;

        return this.id == person.id;
    }

}
