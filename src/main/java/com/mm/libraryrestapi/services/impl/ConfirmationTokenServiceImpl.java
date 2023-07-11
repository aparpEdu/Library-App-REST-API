package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.ConfirmationToken;
import com.mm.libraryrestapi.repositories.ConfirmationTokenRepository;
import com.mm.libraryrestapi.services.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

   private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
