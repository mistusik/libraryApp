package com.mst.library.io;

import com.mst.library.model.Book;
import com.mst.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);

    public Book readAndCreateBook(){
        System.out.println("Title");
        String title = sc.nextLine();
        System.out.println("Author");
        String author = sc.nextLine();
        System.out.println("Publisher");
        String publisher = sc.nextLine();
        System.out.println("ISBN");
        String isbn = sc.nextLine();
        System.out.println("Relese");
        int releaseDate = getInt();
        System.out.println("Pages");
        int pages = getInt();
        return new Book (title, author, releaseDate, pages, publisher, isbn);
    }

    public Magazine readAndCreateMagazine(){
        System.out.println("Title");
        String title = sc.nextLine();
        System.out.println("Publisher");
        String publisher = sc.nextLine();
        System.out.println("Language");
        String language = sc.nextLine();
        System.out.println("Year of release ");
        int year = getInt();
        System.out.println("Month");
        int month = getInt();
        System.out.println("Day");
        int day = getInt();
        return new Magazine (title, publisher, language, year, month, day);
    }

    public int getInt(){
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    public void close () {
        sc.close();
    }
}
