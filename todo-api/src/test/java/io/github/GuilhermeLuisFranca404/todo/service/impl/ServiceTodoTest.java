package io.github.GuilhermeLuisFranca404.todo.service.impl;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;
import io.github.GuilhermeLuisFranca404.todo.repository.TodoRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class ServiceTodoTest {

    private static final Integer       INDEX       = 0;
    private static final Long          ID          = 1L;
    private static final String        DESCRIPTION = "write tests to my API";
    private static final Boolean       DONE        = null;
    private static final LocalDateTime CREATE_DATE = null;
    private static final LocalDateTime DONE_DATE   = null;
    private static final LocalDateTime ORDER_TODO  = null;

    @InjectMocks
    private ServiceTodoImpl service;

    @Mock
    private TodoRepository repository;

    private Todo todo;
    private List<Todo> listTodo;
    private Optional<Todo> optionalTodo;
    private Page<List<Todo>> pageListTodo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }



    @Test
    void whenSaveThenBodyHasTodoObject() {//arrumar nomebclatura

        when(repository.save(any())).thenReturn(todo);

        var response = service.save(todo);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(CREATE_DATE, response.getCreatedDate());
        assertEquals(DONE_DATE, response.getDoneDate());
        assertEquals(ORDER_TODO, response.getOrderTodo());

        assertEquals(/* DONE */false, response.getDone());

    }


    @Test
    void whenFindAllWithoutMarkDoneReturnAnListOfTodo() {

        when(repository.findAllWithoutMarkDone()).thenReturn(listTodo);

        var response = service.findAllWithoutMarkDone();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(todo.getClass(), response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(DESCRIPTION, response.get(INDEX).getDescription());
        assertEquals(DONE, response.get(INDEX).getDone());
        assertEquals(CREATE_DATE, response.get(INDEX).getCreatedDate());
        assertEquals(DONE_DATE, response.get(INDEX).getDoneDate());
        assertEquals(ORDER_TODO, response.get(INDEX).getOrderTodo());

    }


    @Test
    void whenfindAllWithMarkDoneReturnAnPageableListOfTodo() {

        when(repository.findAllWithMarkDone(any())).thenReturn(pageListTodo);

        var response = service.findAllWithMarkDone(INDEX);
        var responseObj = response.toList().get(INDEX).get(INDEX);

        assertNotNull(response);
        assertEquals(1, response.toList().size());
        assertEquals(todo.getClass(), responseObj.getClass());

        assertEquals(ID, responseObj.getId());
        assertEquals(DESCRIPTION, responseObj.getDescription());
        assertEquals(DONE, responseObj.getDone());
        assertEquals(CREATE_DATE, responseObj.getCreatedDate());
        assertEquals(DONE_DATE, responseObj.getDoneDate());
        assertEquals(ORDER_TODO, responseObj.getOrderTodo());

    }


    @Test
    void whenDeleteByIdWithMarkDoneWithSuccess() {

        Optional<Todo> todoOptional = repository.findById(ID);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            if (Boolean.TRUE.equals(todo.getDone())) {
                repository.deleteById(ID);
            }
        }

    }


    @Test
    void whenMarkAsDoneHasDoneTrueAndDoneDateNewDateTime() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.markAsDone(ID);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(CREATE_DATE, response.getCreatedDate());
        assertEquals(ORDER_TODO, response.getOrderTodo());

        assertEquals(true, response.getDone());
        assertNotNull(response.getDoneDate());
        assertEquals(LocalDateTime.now().getClass(), response.getDoneDate().getClass());

    }


    @Test
    void whenChangeOrderByIdHasOrderTodoNewDateTime() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.changeOrderById(ID);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(DESCRIPTION, response.getDescription());
        assertEquals(DONE, response.getDone());
        assertEquals(CREATE_DATE, response.getCreatedDate());
        assertEquals(DONE_DATE, response.getDoneDate());

        assertNotNull(response.getOrderTodo());
        assertEquals(LocalDateTime.now().getClass(), response.getOrderTodo().getClass());

    }







    private void startUser() {
        todo = new Todo(ID, DESCRIPTION, DONE, CREATE_DATE, DONE_DATE, ORDER_TODO);
        listTodo = List.of(todo);
        optionalTodo = Optional.of(todo);
        pageListTodo = new PageImpl<>(Collections.singletonList(listTodo));
    }

}
