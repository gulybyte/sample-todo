package io.github.gulybyte.todo.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.gulybyte.todo.exception.status.ConflictException;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.impl.ServiceTodoImpl;
import io.github.gulybyte.todo.util.ArgumentsMatchersTodo;
import io.github.gulybyte.todo.util.TodoCreator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class UnitTodoTestConflict extends ArgumentsMatchersTodo {

    @InjectMocks
    private ServiceTodoImpl service;

    @Mock
    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateDescription() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());

        assertThrows(ConflictException.class,
            () -> service.updateDescription(anyTodoDescriptionFilter(createTodoDescriptionFilterDefault())));

    }


    @Test
    void updateContext() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(createTodoWithDefaultContext()));

        assertThrows(ConflictException.class,
            () -> service.updateContext(anyTodoContextFilter(createTodoContextFilterDefault())));

    }


    @Test
    void markAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(createTodoWithDoneTrue()));

        assertThrows(ConflictException.class,
            () -> service.markAsDone(anyLong()));

    }


    @Test
    void undoneMarkAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(createTodoWithDoneFalse()));

        assertThrows(ConflictException.class,
            () -> service.undoneMarkAsDone(anyLong()));

    }

}
