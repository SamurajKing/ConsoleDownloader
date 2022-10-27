package com.khrenov.ConsoleDownloader.parsers;

import com.khrenov.ConsoleDownloader.exceptions.FewOptionsException;
import com.khrenov.ConsoleDownloader.exceptions.InvalidOptionException;
import com.khrenov.ConsoleDownloader.exceptions.OddArgumentsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsoleParser {
    private int threadsNumber;
    private String sourceFilePath;
    private String outputFolderPath;

    public ConsoleParser(String[] args) throws OddArgumentsException, InvalidOptionException, FewOptionsException {
        if (args.length % 2 != 0) {
            throw new OddArgumentsException();
        }
        HashMap<String, String> flags = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            flags.put(args[i], args[i + 1]);
        }
        ArrayList<String> validOptions = new ArrayList<>(List.of("-n", "-f", "-o"));
        for (String argument : flags.keySet()) {
            if (!validOptions.contains(argument)) {
                throw new InvalidOptionException();
            }
        }
        if (flags.keySet().size() != validOptions.size()) {
            throw new FewOptionsException();
        }
        initParams(flags);
    }

    private void initParams(HashMap<String, String> flags) {
        threadsNumber = Integer.parseInt(flags.get("-n"));
        sourceFilePath = flags.get("-f");
        outputFolderPath = flags.get("-o");
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getOutputFolderPath() {
        return outputFolderPath;
    }
}
