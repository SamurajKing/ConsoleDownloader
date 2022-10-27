package com.khrenov.ConsoleDownloader.downloads;


import com.khrenov.ConsoleDownloader.files.MultipleWriter;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;

public class Downloader implements Callable<Long> {
    private DownloadObject obj;
    private String outputFolderPath;

    public Downloader(DownloadObject obj, String outputFolderPath) {
        this.obj = obj;
        this.outputFolderPath = outputFolderPath;
    }

    @Override
    public Long call() throws Exception {
        long bytesDownloaded = 0;
        URL url = new URL(obj.getLink());
        InputStream in = url.openStream();
        MultipleWriter fs = new MultipleWriter(outputFolderPath, obj);
        byte[] buffer = new byte[2048];
        int length;

        while ((length = in.read(buffer)) != -1) {
            fs.write(buffer, 0, length);
            bytesDownloaded += length;
        }
        return bytesDownloaded;
    }
}
