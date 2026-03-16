package com.project.backend.exceptions.user;

public class PasswordIsEmptyException extends RuntimeException {
  public PasswordIsEmptyException() {
    super("Password cannot be empty or null!");
  }
}
