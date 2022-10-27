package com.khrenov.ConsoleDownloader.exceptions;

public class FewOptionsException extends ArgumentsException {
    public FewOptionsException() {
        super("Not all options included!");
    }
}
