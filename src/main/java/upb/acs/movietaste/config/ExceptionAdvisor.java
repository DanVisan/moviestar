package upb.acs.movietaste.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
    public ResponseEntity<HashMap> invalidMovieIDHandler() {
        HashMap<String, String> body = new HashMap<>();
        body.put("error", "Rating references an unsaved movie. Please try rating a valid movie.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<HashMap> movieIDAlreadyRegisteredHandler() {
        HashMap<String, String> body = new HashMap<>();
        body.put("error", "You already registered this action");
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(body);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<HashMap> modelNotValidHandler() {
        HashMap<String, String> body = new HashMap<>();
        body.put("error", "Model is not valid");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<HashMap> internalServerErrorHandler() {
        HashMap<String, String> body = new HashMap<>();
        body.put("error", "Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
