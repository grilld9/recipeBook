package ru.nsu.fit.repiceBook.exceptions;

public class InvalidJWT extends RuntimeException{

  public InvalidJWT(String message) {
    super(message);
  }
}
