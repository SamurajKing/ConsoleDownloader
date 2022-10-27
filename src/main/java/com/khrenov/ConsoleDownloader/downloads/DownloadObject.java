package com.khrenov.ConsoleDownloader.downloads;

import java.util.ArrayList;

public class DownloadObject {
    private String link;
    private ArrayList<String> filenames;

    public DownloadObject(String link, ArrayList<String> filenames) {
        this.link = link;
        this.filenames = filenames;
    }

    public ArrayList<String> getFilenames() {
        return filenames;
    }

    public String getLink() {
        return link;
    }
}
