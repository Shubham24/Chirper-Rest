package io.github.shubham24.chirperrest.exceptionhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> ResponseStatusExceptionHandler(ResponseStatusException e) {
        return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
    }
    
}
