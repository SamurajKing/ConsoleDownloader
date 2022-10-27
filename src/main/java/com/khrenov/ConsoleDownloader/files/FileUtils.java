package com.khrenov.ConsoleDownloader.files;

import com.khrenov.ConsoleDownloader.downloads.DownloadObject;
import com.khrenov.ConsoleDownloader.exceptions.IncorrectStructureException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileUtils {
    public static void createFolder(String path) {
        File folder = new File(path);
        if (!folder.isDirectory()) {
            folder.mkdir();
        }
    }
    public static ArrayList<DownloadObject> loadLinks(String filename) throws IOException, IncorrectStructureException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String s;
        ArrayList<DownloadObject> objs = new ArrayList<>();
        HashMap<String, ArrayList<String>> lst = new HashMap<>();
        while ((s = reader.readLine()) != null) {
            String[] twos = s.split(" ");
            if (twos.length != 2 || twos[0].isEmpty() || twos[1].isEmpty()) {
                throw new IncorrectStructureException();
            }
            if (lst.containsKey(twos[0])) {
                lst.get(twos[0]).add(twos[1]);
            } else {
                DownloadObject curObj = new DownloadObject(twos[0], new ArrayList<>());
                objs.add(curObj);
                lst.put(twos[0], curObj.getFilenames());
            }
        }
        return objs;
    }
}
