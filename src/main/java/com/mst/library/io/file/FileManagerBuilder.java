package com.mst.library.io.file;

import com.mst.library.io.ConsolePrinter;
import com.mst.library.io.DataReader;
import com.mst.library.exception.NoSuchFileTypeException;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }
    public FileManager build(){
        printer.printLine("Choose data Type: ");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return  new SerializableFileManager();
            default:
                throw new NoSuchFileTypeException("Wrong file type: ");
        }
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            //serial, SERIAL
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk =true;
            }catch (IllegalArgumentException e){
                printer.printLine("Wrong file type: ");
            }
        }while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
