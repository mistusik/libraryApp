package com.mst.library.app;

import com.mst.library.io.DataReader;
import com.mst.library.model.Book;
import com.mst.library.model.Library;

public class LibraryControl {

    private final int exit = 0;
    private final int addBook = 1;
    private final int printBooks = 2;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    public void controlLoop(){
        int option;

        do{
            printOptions();
            option = dataReader.getInt();
            switch (option){
                case addBook:
                    addBook();
                    break;
                case printBooks:
                    printBooks();
                    break;
                case exit:
                    exit();
                    break;
                default:
                    System.out.println("Wrong option, try again");
            }

        }while (option !=exit);
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
        System.out.println("Choose options");
        System.out.println(exit + " - exit");
        System.out.println(addBook + " - add new book");
        System.out.println(printBooks + " - print existing books");
    }
}
