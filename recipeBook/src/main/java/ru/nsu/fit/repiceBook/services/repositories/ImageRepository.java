package ru.nsu.fit.repiceBook.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.repiceBook.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
