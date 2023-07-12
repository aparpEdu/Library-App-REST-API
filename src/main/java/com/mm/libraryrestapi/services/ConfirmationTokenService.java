package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    String confirmToken(String token);
    int setConfirmationDate(String token);
}
