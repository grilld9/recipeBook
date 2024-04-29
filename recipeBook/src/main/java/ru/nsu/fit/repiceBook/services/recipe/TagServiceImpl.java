package ru.nsu.fit.repiceBook.services.recipe;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.repiceBook.dto.recipe.TagDTO;
import ru.nsu.fit.repiceBook.model.Tag;
import ru.nsu.fit.repiceBook.repositories.recipe.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {


    private final TagRepository tagRepository;
    @Override
    public List<Tag> get(List<TagDTO> tags) {
        return tags.stream()
                .map(tag -> tagRepository.findByName(tag.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
