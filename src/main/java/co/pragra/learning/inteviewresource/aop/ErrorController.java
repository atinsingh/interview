package co.pragra.learning.inteviewresource.aop;

import co.pragra.learning.inteviewresource.entity.ErrorResponse;
import co.pragra.learning.inteviewresource.exceptions.CompanyNotFoundException;
import co.pragra.learning.inteviewresource.exceptions.InvalidUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCompanyNot(Exception ex) {
        return ResponseEntity.status(404)
                .body(ErrorResponse.builder().code(2038).msg(ex.getMessage()).errorTime(Instant.now()).build());
    }

    @ExceptionHandler({ InvalidUserException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleInvalidUser(Exception ex) {
        return ResponseEntity.status(400)
                .body(ErrorResponse.builder().code(2033).msg(ex.getMessage()).errorTime(Instant.now()).build());
    }
}
