package com.mm.libraryrestapi.services;

public interface EmailService {
    void send(String receiver, String email);
    String buildEmail(String name, String link);
}
