package com.mst.library.app;

import com.mst.library.exception.NoSuchOptionException;

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
    static Option createFromInt(int option) throws NoSuchOptionException {
        try{
        return Option.values()[option];
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("No such an option - " + option);
        }
        }
}
