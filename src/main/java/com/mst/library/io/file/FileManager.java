package com.mst.library.io.file;

import com.mst.library.model.Library;

public interface FileManager {

    Library importData();
    void exportData(Library library);
}
