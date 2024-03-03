package ru.nsu.fit.repiceBook.services;

import ru.nsu.fit.repiceBook.model.User;

public interface UserService {

    /**
     * Получает данные пользователя из контекста приложения
     * @return данные пользователя
     */
    User getCurrUser();

    /**
     * Получает данные пользователя по id
     * @return данные дользователя
     */
    User getUserById(Long userId);
}
