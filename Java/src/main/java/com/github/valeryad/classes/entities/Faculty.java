package com.github.valeryad.classes.entities;

public enum Faculty {
    RADIOENGINEERING_AND_ELECTRONICS("Radioengineering and Electronics"),
    ENGINEERING_AND_ECONOMICS("Engineering and Economics"),
    INFOCOMMUNICATIONS("Infocommunications"),
    COMPUTER_AIDED_DESIGN("Computer-Aided Design");

    private String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}