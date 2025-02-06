package ru.ftptpf.exeptions.practice;

public class Task4Exception extends RuntimeException {

    public Task4Exception(String message) {
        super(message);
    }

    public Task4Exception(Throwable cause) {
        super(cause);
    }
}
