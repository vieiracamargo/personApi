package github.com.crudapi.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> validation(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(
                fieldErrors.stream().map(
                        fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage())).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundError("Registro não encontrado."));
    }

}
