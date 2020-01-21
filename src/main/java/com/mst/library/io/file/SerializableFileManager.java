package com.mst.library.io.file;

import com.mst.library.exception.DataExportException;
import com.mst.library.exception.DataImportException;
import com.mst.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.txt";

    @Override
    public void exportData(Library library) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No such file - " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Unable make changes to the file - " + FILE_NAME);
        }
    }

    @Override
    public Library importData() {
        try (
           FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            return (Library)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("No such file - " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Cannot read the file - " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Wrong data type - " + FILE_NAME);
        }


    }


}
