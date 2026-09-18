package br.bryan.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.transaction.TransactionSystemException;
import jakarta.validation.ConstraintViolation;

@ControllerAdvice
public class ExceptionHandlerException {

    private MessageSource messageSource;

    public ExceptionHandlerException(MessageSource message) {
        this.messageSource = message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {

            String message = messageSource.getMessage(
                    err,
                    LocaleContextHolder.getLocale()
            );

            ErrorMessageDTO error = new ErrorMessageDTO(
                    message,
                    err.getField()
            );

            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleConstraintViolationException(
            ConstraintViolationException e) {

        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getConstraintViolations().forEach(err -> {

            ErrorMessageDTO error = new ErrorMessageDTO(
                    err.getMessage(),
                    err.getPropertyPath().toString()
            );

            dto.add(error);
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(TransactionSystemException.class)
public ResponseEntity<List<ErrorMessageDTO>> handleTransactionSystemException(
        TransactionSystemException e) {

    List<ErrorMessageDTO> dto = new ArrayList<>();

    Throwable cause = e;

    while (cause != null) {

        if (cause instanceof ConstraintViolationException validationException) {

            for (ConstraintViolation<?> violation : validationException.getConstraintViolations()) {

                ErrorMessageDTO error = new ErrorMessageDTO(
                        violation.getMessage(),
                        violation.getPropertyPath().toString()
                );

                dto.add(error);
            }

            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }

        cause = cause.getCause();
    }

    return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
}
}