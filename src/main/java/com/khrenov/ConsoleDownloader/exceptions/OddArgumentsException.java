package com.khrenov.ConsoleDownloader.exceptions;

public class OddArgumentsException extends ArgumentsException {

    public OddArgumentsException() {
        super("Number of arguments should be even!");
    }
}
