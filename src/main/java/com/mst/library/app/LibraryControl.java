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


    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();


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
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException e) {
            printer.printLine("Adding the magazine was unsuccessful ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Magazines limit int the library has been reached. You cannot add any more magazines. ");
        }
    }

    private void exit() {
        printer.printLine("End");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
        } catch (InputMismatchException e) {
            printer.printLine("Adding the book was unsuccessful ");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine("Books limit int the library has been reached. You cannot add any more books. ");
        }
    }

    private void printOptions() {
        printer.printLine("Choose options.");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }
}
