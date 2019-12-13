package com.mst.library.app;

import com.mst.library.model.Book;

public class Library {
    public static void main(String[] args) {
        final String appName = "com.mst.library.app.Library v0.7";

        Book[] books = new Book[1000];

        books[0] = new Book("Core Java Volume 1 - Fundamentals", "Cay S. Horstman",
                2016, 872, "Pearson Education", "9788373271890");
        books[1] = new Book("Brave New World", "Aldous Huxley", 1932, 311,
                "Vintage Classics", "8798654456664");
        books[2] = new Book("The Little Prince", "Antoine de Saint-Exupery", 1942, 100,
                "Publisher1");

        System.out.println(appName);
        System.out.println("Available books");
        books[0].printInfo();
        books[1].printInfo();
        books[2].printInfo();


    }

}
