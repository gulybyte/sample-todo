package io.github.gulybyte.todo.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.util.TodoCreator;
import static io.github.gulybyte.todo.util.ArgumentosMatchersTodo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTodoTestSuccess {

    @InjectMocks
    private ServiceTodoImpl service;

    @Mock
    private TodoRepository repository;

    @Captor
    private ArgumentCaptor<Todo> todoParamCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("When save() with success, then return repository and id=null and done=false")
    void save() {

        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.save(anyTodo());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNull(capturedParameterResponse.getId());
        assertFalse(capturedParameterResponse.getDone());

    }


    @Test
    @DisplayName("When updateDescription() with success, then return repository and new description")
    void updateDescription() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.updateDescription(anyTodoPutFilter(TodoCreator.createTodoPutFilterWithNewDescription()));
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNotEquals(response.getDescription(), capturedParameterResponse.getDescription());

    }


    @Test
    @DisplayName("When findAllWithoutMarkDone() with success, then return repository")
    void findAllWithoutMarkDone() {

        when(repository.findAllWithoutMarkDone()).thenReturn(anyListTodo());

        var response = service.findAllWithoutMarkDone();

        assertEquals(response, repository.findAllWithoutMarkDone());

    }


    @Test
    @DisplayName("When findAllWithMarkDone() with success, then return repository")
    void findAllWithMarkDone() {

        when(repository.findAllWithMarkDone(any())).thenReturn(anyPageTodo());

        var response = service.findAllWithMarkDone(INDEX);

        assertEquals(response, repository.findAllWithMarkDone(any()));

    }


    @Test
    @DisplayName("When deleteById() with success, then have success")
    void deleteById() {

        when(repository.existsById(anyLong())).thenReturn(true);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(anyLong());

        verify(repository, times(1)).deleteById(anyLong());

    }


    @Test
    @DisplayName("When markAsDone() with success, then return repository and done=true and doneDate!=null")
    void markAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(TodoCreator.createTodoWithDoneFalse()));
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.markAsDone(anyLong());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertTrue(capturedParameterResponse.getDone());
        assertNotNull(capturedParameterResponse.getDoneDate());

    }


    @Test
    @DisplayName("When undoneMarkAsDone() with success, then return repository and done=false and doneDate=null")
    void undoneMarkAsDone() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo(TodoCreator.createTodoWithDoneTrue()));
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.undoneMarkAsDone(anyLong());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertFalse(capturedParameterResponse.getDone());
        assertNull(capturedParameterResponse.getDoneDate());

    }


    @Test
    @DisplayName("When changeOrderById() with success, then return repository and new orderTodo")
    void changeOrderById() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.changeOrderById(anyLong());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNotEquals(response.getOrderTodo(), capturedParameterResponse.getOrderTodo());

    }

}
