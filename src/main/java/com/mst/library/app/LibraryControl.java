package com.mst.library.app;

import com.mst.library.exception.NoSuchOptionException;
import com.mst.library.io.ConsolePrinter;
import com.mst.library.io.DataReader;
import com.mst.library.model.Book;
import com.mst.library.model.Library;
import com.mst.library.model.Magazine;
import com.mst.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {


    private DataReader dataReader = new DataReader();
    private Library library = new Library();
    private ConsolePrinter printer = new ConsolePrinter();

    public void controlLoop(){
        Option option;

        do{
            printOptions();
            option = getOption();
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

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk){
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            }catch (NoSuchOptionException e){
                printer.printLine(e.getMessage());
            }catch (InputMismatchException e){
                printer.printLine("Value you entered is not a number. Please try again. ");
            }
        }
                return option;

    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
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
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
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
