package com.mst.library.io.file;

import com.mst.library.exception.DataExportException;
import com.mst.library.exception.DataImportException;
import com.mst.library.exception.InvalidDataException;
import com.mst.library.model.Book;
import com.mst.library.model.Library;
import com.mst.library.model.Magazine;
import com.mst.library.model.Publication;

import java.io.*;
import java.util.Scanner;

public class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";

    @Override
    public void exportData(Library library) {
        Publication[] publications = library.getPublications();
        try(
                var fileWriter = new FileWriter(FILE_NAME);
                var bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Publication publication : publications){
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException(" Unable to make changes to the file - " + FILE_NAME);
        }

    }

    @Override
    public Library importData() {
       Library library = new Library();
       try(
               Scanner fileReader = new Scanner(new File(FILE_NAME))){
           while (fileReader.hasNextLine()){
               String line = fileReader.nextLine();
               Publication publication = createObjectFromString(line);
               library.addPublication(publication);
           }
       } catch (FileNotFoundException e) {
           throw new DataImportException("No file found" + FILE_NAME);
       }
       return library;
    }

    private Publication createObjectFromString(String csvText) {
        String [] split = csvText.split(";");
        String type = split[0];
        if (Book.TYPE.equals(type)){
            return createBook(split);
        } else if (Magazine.TYPE.equals(type)){
            return createMagazine(split);
        }
        throw new InvalidDataException("Unknown publication type " + type);
    }

    private Book createBook(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        String author = data[4];
        int pages = Integer.valueOf(data[5]);
        String isbn = data[6];
        return new Book(title, author, year, pages, publisher, isbn);
    }

    private Magazine createMagazine(String[] data) {
        String title = data[1];
        String publisher = data[2];
        int year = Integer.valueOf(data[3]);
        int month = Integer.valueOf(data[4]);
        int day = Integer.valueOf(data[5]);
        String language = data[6];
        return new Magazine(title, publisher, language, year, month, day);
    }


}
