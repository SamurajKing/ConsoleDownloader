package com.khrenov.ConsoleDownloader;

import com.khrenov.ConsoleDownloader.parsers.ConsoleParser;
import com.khrenov.ConsoleDownloader.downloads.Downloader;
import com.khrenov.ConsoleDownloader.downloads.DownloadObject;
import com.khrenov.ConsoleDownloader.exceptions.FewOptionsException;
import com.khrenov.ConsoleDownloader.exceptions.IncorrectStructureException;
import com.khrenov.ConsoleDownloader.exceptions.InvalidOptionException;
import com.khrenov.ConsoleDownloader.exceptions.OddArgumentsException;
import com.khrenov.ConsoleDownloader.files.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        try {
            ConsoleParser options = new ConsoleParser(args);
            new Main().download(options);
        } catch (OddArgumentsException |
                IncorrectStructureException |
                InvalidOptionException |
                FewOptionsException |
                FileNotFoundException e) {
            System.out.println(e);
            showUsage();
        } catch (IOException e) {
            System.out.println("Some I/O exception");
            showUsage();
        }
    }

    private void download(ConsoleParser options) throws IOException, IncorrectStructureException {
        FileUtils.createFolder(options.getOutputFolderPath());
        ArrayList<DownloadObject> objs = FileUtils.loadLinks(options.getSourceFilePath());
        ExecutorService exec = Executors.newFixedThreadPool(options.getThreadsNumber());
        ArrayList<Future<Long>> results = new ArrayList<>();
        double startTime = new Date().getTime();
        for (DownloadObject obj : objs) {
            results.add(exec.submit(new Downloader(obj, options.getOutputFolderPath())));
        }

        exec.shutdown();

        long allBytes = 0;
        for (Future<Long> fl : results) {
            try {
                allBytes += fl.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        double endTime = new Date().getTime();
        double resultTime = (endTime - startTime) / 1000;
        System.out.println("Time - " + resultTime + " s.");
        System.out.println("Downloaded - " + allBytes + " bytes");
    }

    private static void showUsage() {
        System.out.println("USAGE: java -jar ConsoleDownloader-1.0.jar [option...] [argument...]");
        System.out.println("-n                  setting up number of threads");
        System.out.println("-f                  setting up location of file with links");
        System.out.println("-o                  setting up output folder");
    }
}
