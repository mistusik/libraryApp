package com.mst.library.app;

public class LibraryApp {

   private static final String APP_NAME = "Library v1.83";

    public static void main(String[] args) {

        System.out.println(APP_NAME);
        LibraryControl libControl = new LibraryControl();
        libControl.controlLoop();
    }
}
