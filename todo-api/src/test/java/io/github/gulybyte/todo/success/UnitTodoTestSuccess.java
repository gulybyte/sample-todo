package io.github.gulybyte.todo.success;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.impl.ServiceTodoImpl;
import io.github.gulybyte.todo.util.TodoCreator;
import static io.github.gulybyte.todo.util.ArgumentsMatchersTodo.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * THESE TESTS HAVE LOW RELEVANCE; INTEGRATION TESTS ENSURE SUCCESS CASES MORE APPROPRIATELY.
 * IT IS POSSIBLE THAT THIS CLASS WILL CEASE TO EXIST SOON
*/
@Deprecated
@DataJpaTest
@ActiveProfiles("test")
class UnitTodoTestSuccess {

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
    void save() {

        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.save(anyTodo());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNull(capturedParameterResponse.getId());
        assertFalse(capturedParameterResponse.getDone());

    }


    @Test
    void updateDescription() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.updateDescription(anyTodoDescriptionFilter(TodoCreator.createTodoDescriptionFilterWithNewDescription()));
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNotEquals(response.getDescription(), capturedParameterResponse.getDescription());

    }


    @Test
    void findAllWithoutMarkDone() {

        when(repository.findAllWithoutMarkDone()).thenReturn(anyListTodo());

        var response = service.findAllWithoutMarkDone();

        assertEquals(response, repository.findAllWithoutMarkDone());

    }


    @Test
    void findAllWithMarkDone() {

        when(repository.findAllWithMarkDone(any())).thenReturn(anyPageTodo());

        var response = service.findAllWithMarkDone(INDEX);

        assertEquals(response, repository.findAllWithMarkDone(any()));

    }


    @Test
    void deleteById() {

        when(repository.existsById(anyLong())).thenReturn(true);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(anyLong());

        verify(repository, times(1)).deleteById(anyLong());

    }


    @Test
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
    void changeOrderById() {

        when(repository.findById(anyLong())).thenReturn(anyOptionalTodo());
        when(repository.save(todoParamCaptor.capture())).thenReturn(anyTodo());

        var response = service.changeOrderById(anyLong());
        var capturedParameterResponse = todoParamCaptor.getValue();

        assertEquals(response, repository.save(any()));

        assertNotEquals(response.getOrderTodo(), capturedParameterResponse.getOrderTodo());

    }

}
