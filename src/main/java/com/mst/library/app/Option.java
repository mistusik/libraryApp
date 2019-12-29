package com.mst.library.app;

public enum Option {
    EXIT(0, "EXIT"),
    ADD_BOOK(1, " Add book"),
    ADD_MAGAZINE(2, " Add magazine"),
    PRINT_BOOKS(3, " Print books"),
    PRINT_MAGAZINES(4, " Print magazines");

    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
    static Option createFromInt(int option){
        return Option.values()[option];
        }



}