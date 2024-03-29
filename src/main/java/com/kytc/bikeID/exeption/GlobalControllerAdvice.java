package com.kytc.bikeID.exeption;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;


@ResponseBody
@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Exception handler for validation errors.
     *
     * @param ex      incoming exception
     * @param request WebRequest autowired object
     * @return ResponseEntity&lt;ErrorDetails&gt;
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorDetails> handleValidationException(
            ValidationException ex, WebRequest request) {

        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundExceptions(Exception ex, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Getter
    @Setter
    public static class ErrorDetails {

        private Date timestamp;

        private String message;

        private String details;

        /**
         * All parameters constructor.
         *
         * @param timestamp date
         * @param message   exception message
         * @param details   request description details
         */
        ErrorDetails(Date timestamp, String message, String details) {

            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }

        @JsonCreator
        public ErrorDetails() {

        }

    }

}
