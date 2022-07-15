package com.emailbatch.service;

public interface EmailSenderService {
    void sendActionEmail(String to, String subjects, String text) throws Exception;
}
