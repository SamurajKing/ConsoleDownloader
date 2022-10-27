package com.khrenov.ConsoleDownloader.exceptions;

public class InvalidOptionException extends ArgumentsException {
    public InvalidOptionException() {
        super("Some option is invalid!");
    }
}
