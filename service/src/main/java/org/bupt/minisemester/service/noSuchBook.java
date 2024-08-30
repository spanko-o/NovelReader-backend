package org.bupt.minisemester.service;

public class noSuchBook extends Exception {
    String message;
    public noSuchBook(String message) {
        super(message);
        this.message = message;
    }
}
