package io.github.GuilhermeLuisFranca404.todo.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;
import io.github.GuilhermeLuisFranca404.todo.service.ServiceTodo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ControllerTodoTest {

    private final Integer       INDEX       = 0;
    private final Long          ID          = 1L;
    private final String        DESCRIPTION = "write tests to my API";
    private final Boolean       DONE        = null;
    private final LocalDateTime CREATE_DATE = LocalDateTime.of(2023, 6, 14, 12, 0);
    private final LocalDateTime DONE_DATE   = LocalDateTime.of(2023, 6, 14, 12, 1);
    private final LocalDateTime ORDER_TODO  = LocalDateTime.of(2023, 6, 14, 12, 2);

    private Todo todo;
    private List<Todo> listTodo;
    private Page<List<Todo>> pageListTodo;

    @InjectMocks
    private ControllerTodo controller;

    @Mock
    private ServiceTodo service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }



    @Test
    void whenSaveThenReturnSuccessAndBodyIsObjectTodo() {

        when(service.save(any())).thenReturn(todo);

        var response = controller.save(todo);
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(todo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        testAllAttrsTodoObjEqualsResponseBody(responseBody);

    }


    @Test
    void whenFindAllWithoutMarkDoneThenReturnSuccessAndBodyIsListObjectTodo()  {

        when(service.findAllWithoutMarkDone()).thenReturn(listTodo);

        var response = controller.findAllWithoutMarkDone();
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(listTodo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        var responseBodyObj = responseBody.get(INDEX);

        testAllAttrsTodoObjEqualsResponseBody(responseBodyObj);

    }


    @Test
    void whenFindAllWithMarkDoneThenReturnSuccessAndBodyIsPageListObjectTodo()  {

        when(service.findAllWithMarkDone(INDEX)).thenReturn(pageListTodo);

        var response = controller.findAllWithMarkDone(INDEX);
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(pageListTodo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        var responseBodyObj = responseBody.toList().get(INDEX).get(INDEX);

        testAllAttrsTodoObjEqualsResponseBody(responseBodyObj);

    }


    @Test
    void whenDeleteByIdWithMarkDoneThenReturnNoContent() {

        doNothing().when(service).deleteByIdWithMarkDone(ID);

        var response = controller.deleteByIdWithMarkDone(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).deleteByIdWithMarkDone(anyLong());

    }


    @Test
    void whenMarkAsDoneThenReturnSuccessAndBodyIsTodo() {

        when(service.markAsDone(ID)).thenReturn(todo);

        var response = controller.markAsDone(ID);
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(todo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        testAllAttrsTodoObjEqualsResponseBody(responseBody);

    }

    @Test
    void whenChangeOrderByIdThenReturnSuccessAndBodyIsTodo() {

        when(service.changeOrderById(ID)).thenReturn(todo);

        var response = controller.changeOrderById(ID);
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(todo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        testAllAttrsTodoObjEqualsResponseBody(responseBody);

    }


    @Test
    void whenUndoneMarkAsDoneThenReturnSuccessAndBodyIsTodo() {

        when(service.undoneMarkAsDone(ID)).thenReturn(todo);

        var response = controller.undoneMarkAsDone(ID);
        var responseBody = response.getBody();

        assertNotNull(response);
        assertNotNull(responseBody);
        assertEquals(todo, responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        testAllAttrsTodoObjEqualsResponseBody(responseBody);

    }



    private void testAllAttrsTodoObjEqualsResponseBody(Todo responseBody) {
        assertEquals(ID, responseBody.getId());
        assertEquals(DESCRIPTION, responseBody.getDescription());
        assertEquals(DONE, responseBody.getDone());
        assertEquals(CREATE_DATE, responseBody.getCreatedDate());
        assertEquals(DONE_DATE, responseBody.getDoneDate());
        assertEquals(ORDER_TODO, responseBody.getOrderTodo());
    }

    private void startUser() {
        todo = new Todo(ID, DESCRIPTION, DONE, CREATE_DATE, DONE_DATE, ORDER_TODO);
        listTodo = List.of(todo);
        pageListTodo = new PageImpl<>(Collections.singletonList(listTodo));
    }

}
