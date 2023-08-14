package io.github.gulybyte.todo.service.impl.todo.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.gulybyte.todo.exception.status.NotFoundException;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.impl.ServiceTodoImpl;
import io.github.gulybyte.todo.util.TodoCreator;

import static io.github.gulybyte.todo.util.ArgumentosMatchersTodo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTodoTestNotFoundException {

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

        when(repository.findById(anyLong())).thenReturn(anyEmptyOptionalTodo());

        assertThrows(NotFoundException.class,
            () -> service.updateDescription(anyTodoPutFilter(TodoCreator.createTodoPutFilterDefault())));

    }


    @Test
    void findAllWithoutMarkDone() {

        when(repository.findAllWithoutMarkDone()).thenReturn(anyEmptyListTodo());

        assertThrows(NotFoundException.class,
            () -> service.findAllWithoutMarkDone());

    }


    @Test
    void findAllWithMarkDone() {

        when(repository.findAllWithMarkDone(any())).thenReturn(anyEmptyPageTodo());

        assertThrows(NotFoundException.class,
            () -> service.findAllWithMarkDone(anyInt()));

    }


    @Test
    void deleteById() {

        when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(NotFoundException.class,
            () -> service.deleteById(anyLong()));

    }


    @Test
    void markAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyEmptyOptionalTodo());

        assertThrows(NotFoundException.class,
            () -> service.markAsDone(anyLong()));

    }


    @Test
    void undoneMarkAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyEmptyOptionalTodo());

        assertThrows(NotFoundException.class,
            () -> service.undoneMarkAsDone(anyLong()));

    }


    @Test
    void changeOrderById() {

        when(repository.findById(anyLong())).thenReturn(anyEmptyOptionalTodo());

        assertThrows(NotFoundException.class,
            () -> service.changeOrderById(anyLong()));

    }


}
