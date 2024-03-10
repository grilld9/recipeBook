package ru.nsu.fit.repiceBook.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.model.User;
import ru.nsu.fit.repiceBook.services.repositories.UserRepository;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getCurrUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userEmail = authentication.getName();
        } else {
            throw new AuthenticationServiceException("Пользователь не авторизован");
        }
        return userRepository.findByEmail(userEmail).orElseThrow(
                () -> new NoSuchElementException("Пользователя " + userEmail + " не существует"));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Пользователь с id=" + userId + " не найден"));
    }
}
