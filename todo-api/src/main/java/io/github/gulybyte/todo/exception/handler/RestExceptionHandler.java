package io.github.gulybyte.todo.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.gulybyte.todo.exception.ExceptionDetails;
import io.github.gulybyte.todo.exception.FieldsExceptionDetails;
import io.github.gulybyte.todo.exception.status.BadRequestException;
import io.github.gulybyte.todo.exception.status.ConflictException;
import io.github.gulybyte.todo.exception.status.NotFoundException;
import io.github.gulybyte.todo.exception.status.details.BadRequestExceptionDetails;
import io.github.gulybyte.todo.exception.status.details.ConflictExceptionDetails;
import io.github.gulybyte.todo.exception.status.details.NotFoundExceptionDetails;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre)
    {
        var badRequestException = BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request, Invalid input.")
                .details(bre.getMessage())
                .developerMessage(bre.fillInStackTrace().toString())
                .build();

        return new ResponseEntity<>(badRequestException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handleNotFoundException(NotFoundException nfe)
    {
        var notFoundException = NotFoundExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource Not Found.")
                .details(nfe.getMessage())
                .developerMessage(nfe.fillInStackTrace().toString())
                .build();

        System.out.println("teste de agora");

        return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handleConflictException(ConflictException ce)
    {
        var conflictException = ConflictExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .title("Conflict with current state of the resource.")
                .details(ce.getMessage())
                .developerMessage(ce.fillInStackTrace().toString())
                .build();

        return new ResponseEntity<>(conflictException, HttpStatus.CONFLICT);
    }






    /* TIRAR ISSO QUANTO ANTES */
    @ExceptionHandler(ConstraintViolationException.class)// common exception in HttpMethod.PATCH
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception)
    {
        var details = "";
        var splitDetails = Pattern.compile("interpolatedMessage='(.*?)'").matcher(exception.getMessage());

        while (splitDetails.find())
            details += splitDetails.group(1);

        var exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request, Invalid input")
                .details(details)
                .developerMessage(exception.getMessage())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }






    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request)
    {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        var fieldExceptionDetails = FieldsExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request, Invalid Fields")
                .details("Check the field(s) error")
                .developerMessage(exception.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();

        return new ResponseEntity<>(fieldExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
            HttpHeaders headers, HttpStatusCode status, WebRequest request)
    {
        var exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
