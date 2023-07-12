package com.mm.libraryrestapi.services.impl;

import com.mm.libraryrestapi.entity.ConfirmationToken;
import com.mm.libraryrestapi.exception.LibraryAPIException;
import com.mm.libraryrestapi.repositories.ConfirmationTokenRepository;
import com.mm.libraryrestapi.repositories.UserRepository;
import com.mm.libraryrestapi.services.ConfirmationTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

   private final ConfirmationTokenRepository confirmationTokenRepository;
   private final UserRepository userRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new LibraryAPIException(HttpStatus.NOT_FOUND,"Token not found"));
        if(confirmationToken.getConfirmedAt() != null){
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }
        if(confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))){
            throw new LibraryAPIException(HttpStatus.BAD_REQUEST,"Token expired");
        }
        setConfirmationDate(token);
        userRepository.confirmEmail(confirmationToken.getUser().getEmail());
        return "Successfully confirmed";
    }

    @Override
    public int setConfirmationDate(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }


}