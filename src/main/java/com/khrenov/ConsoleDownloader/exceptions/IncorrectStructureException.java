package com.khrenov.ConsoleDownloader.exceptions;

public class IncorrectStructureException extends Exception {
    public IncorrectStructureException() {
        super("Structure of links file is wrong!");
    }
}
