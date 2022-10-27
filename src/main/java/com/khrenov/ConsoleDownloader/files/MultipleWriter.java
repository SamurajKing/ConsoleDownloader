package com.khrenov.ConsoleDownloader.files;

import com.khrenov.ConsoleDownloader.downloads.DownloadObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MultipleWriter {
    private ArrayList<FileOutputStream> writers;

    public MultipleWriter(String outputFolderPath, DownloadObject obj) throws FileNotFoundException {
        writers = new ArrayList<>();
        for (String filename : obj.getFilenames()) {
            writers.add(new FileOutputStream(outputFolderPath + "/" + filename));
        }
    }

    public void write(byte[] buffer, int off, int len) throws IOException {
        for (FileOutputStream fo : writers) {
            fo.write(buffer, off, len);
        }
    }
}
