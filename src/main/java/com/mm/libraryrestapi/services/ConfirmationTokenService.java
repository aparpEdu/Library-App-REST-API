package com.mm.libraryrestapi.services;

import com.mm.libraryrestapi.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    String confirmToken(String token);

    ConfirmationToken confirmResetToken(String token);
    void setConfirmationDate(String token);
}
