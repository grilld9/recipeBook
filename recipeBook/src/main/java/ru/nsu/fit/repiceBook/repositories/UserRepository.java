package ru.nsu.fit.repiceBook.repositories;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.repiceBook.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
