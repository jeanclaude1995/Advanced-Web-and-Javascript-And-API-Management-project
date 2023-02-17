package fr.john.project.exception;

import javax.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice
{
  @ExceptionHandler(value = IllegalArgumentException.class)
  public final ResponseEntity<String> handleCustomException(IllegalArgumentException exception)
  {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(value = EntityExistsException.class)
  public final ResponseEntity<String> handleCustomException(EntityExistsException exception)
  {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}