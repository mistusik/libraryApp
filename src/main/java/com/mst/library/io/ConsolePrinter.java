package com.mst.library.io;

import com.mst.library.model.Book;
import com.mst.library.model.Magazine;
import com.mst.library.model.Publication;

public class ConsolePrinter {
    public void printBooks(Publication[] publications){
        int countBooks = 0;
        for (Publication publication : publications) {
            if (publication instanceof Book){
                System.out.println(publication);
                countBooks++;
            }
        }
        if (countBooks==0){
            printLine("No books in the library.");
        }
    }

    public void printMagazines(Publication[] publications) {
        int countMagazines = 0;
        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                    System.out.println(publication);
                    countMagazines++;
                }
            }
            if (countMagazines == 0) {
                printLine("No magazines in the library");
            }
        }
        public void printLine (String text){
            System.out.println(text);
        }
    }
