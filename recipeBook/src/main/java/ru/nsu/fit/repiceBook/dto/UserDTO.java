package ru.nsu.fit.repiceBook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

  private Integer id;

  @Email(message = "Incorrect email")
  @NotBlank(message = "Email cannot be empty")
  private String email;

}
