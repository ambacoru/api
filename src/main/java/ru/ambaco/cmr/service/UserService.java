package ru.ambaco.cmr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ambaco.cmr.entities.ConfirmationToken;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.repositories.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ConfirmationTokenService confirmationTokenService;
    private final UserRepository userRepository;

    public void saveConfirmationToken(User appUser, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);

    }
}
