package ru.nsu.fit.repiceBook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Integer id;

  @Email(message = "Incorrect email")
  @NotBlank(message = "Email cannot be empty")
  private String email;

}
