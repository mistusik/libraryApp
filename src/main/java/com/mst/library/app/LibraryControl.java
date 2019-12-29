package com.mst.library.app;

import com.mst.library.io.DataReader;
import com.mst.library.model.Book;
import com.mst.library.model.Library;
import com.mst.library.model.Magazine;

public class LibraryControl {


    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        Option option;

        do{
            printOptions();
            option = Option.createFromInt(dataReader.getInt());
            switch (option){
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case EXIT:
                    exit();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case  PRINT_MAGAZINES:
                    printMagazines();
                    break;
                default:
                    System.out.println("Wrong option, try again");
            }

        }while (option != Option.EXIT);
    }

    private void printMagazines() {
        library.printMagazines();
    }


    private void addMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
    }

    private void exit() {
        System.out.println("End");
        dataReader.close();
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
    }

    private void printOptions() {
        System.out.println("Choose options.");
        for (Option value : Option.values()) {
            System.out.println(value);
        }
    }
}
