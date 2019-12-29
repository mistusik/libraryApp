package com.mst.library.model;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Library {



    private static final int MAX_PUBLICATIONS = 2000;
    private int publicationsNumber;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    public void addBook (Book book){
        if (publicationsNumber< MAX_PUBLICATIONS){
            publications[publicationsNumber] = book;
            publicationsNumber++;
        }else {
            System.out.println("Max books limit has been reached");
        }
    }
    public void printBooks(){
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book){
                System.out.println(publications[i]);
                countBooks++;
            }
        }
        if (countBooks==0){
            System.out.println("No books in the library.");
        }
    }
    public void addMagazine (Magazine magazine){
        if (publicationsNumber< MAX_PUBLICATIONS){
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        }else {
            System.out.println("Max magazines limit has been reached.");
        }
    }
    public void printMagazines(){
        int countMagazines = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine){
                System.out.println(publications[i]);
                countMagazines++;
            }
        }
        if (countMagazines==0){
            System.out.println("No Magazines in the library.");
        }
    }

}
