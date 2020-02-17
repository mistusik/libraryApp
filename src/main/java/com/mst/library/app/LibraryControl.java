package com.mst.library.app;

import com.mst.library.exception.DataExportException;
import com.mst.library.exception.DataImportException;
import com.mst.library.exception.InvalidDataException;
import com.mst.library.exception.NoSuchOptionException;
import com.mst.library.io.ConsolePrinter;
import com.mst.library.io.DataReader;
import com.mst.library.io.file.FileManager;
import com.mst.library.io.file.FileManagerBuilder;
import com.mst.library.model.Book;
import com.mst.library.model.Library;
import com.mst.library.model.Magazine;
import com.mst.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {


    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

     LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try{
            library = fileManager.importData();
            printer.printLine("Data import successful: ");
        }catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("New data base initialized: ");
            library = new Library();
        }
    }


     void controlLoop(){
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
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case  PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case DELETE_BOOK:
                    deleteBook();
                    break;
                case DELETE_MAGAZINE:
                    deleteMagazine();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Wrong option, try again");
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
                printer.printLine(e.getMessage() + "Try again! ");
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
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Adding the magazine was unsuccessful ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Magazines limit int the library has been reached. You cannot add any more magazines. ");
        }
    }

    private void deleteMagazine() {
         try {
             Magazine magazine = dataReader.readAndCreateMagazine();
             if (library.removePublication(magazine))
                 printer.printLine("Magazine delated. ");
             else
                 printer.printLine("Unable to find this magazine. ");
         }catch (InputMismatchException e){
             printer.printLine("Incorrect data entered. ");
         }
     }

    private void exit() {
         try {
             fileManager.exportData(library);
             printer.printLine("Successful data export:");
         }catch (DataExportException e){
             printer.printLine(e.getMessage());
         }
        dataReader.close();
        printer.printLine("End");
     }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e) {
            printer.printLine("Adding the book was unsuccessful ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Books limit int the library has been reached. You cannot add any more books. ");
        }
    }


    private void deleteBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book))
                printer.printLine("Book delated. ");
            else
                printer.printLine("Unable to find this book. ");
        }catch (InputMismatchException e){
            printer.printLine("Incorrect data entered. ");
        }

    }

    private void printOptions() {
        printer.printLine("Choose options.");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private enum Option {
        EXIT(0, "EXIT"),
        ADD_BOOK(1, " Add book"),
        ADD_MAGAZINE(2, " Add magazine"),
        PRINT_BOOKS(3, " Print books"),
        PRINT_MAGAZINES(4, " Print magazines"),
        DELETE_BOOK(5, " Delete book"),
        DELETE_MAGAZINE(6, " Delete magazine");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
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

}
