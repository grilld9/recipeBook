package ru.nsu.fit.repiceBook.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.MediaType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Lob
    private byte[] image;

    private String mediaType;
}
