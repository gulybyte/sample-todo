package io.github.gulybyte.todo.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FieldsExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}
