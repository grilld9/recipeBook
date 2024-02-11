package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "source_ingredients")
@Builder
public class SourceIngredient {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
}
