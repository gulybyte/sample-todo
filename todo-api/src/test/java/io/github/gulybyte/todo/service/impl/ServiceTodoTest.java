/* package io.github.gulybyte.todo.service.impl;

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

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTodoTest {

    private final Integer       INDEX           = 0;
    private final Long          ID              = 1L;
    private final String        DESCRIPTION     = "write tests to my API";
    private final String        NEW_DESCRIPTION = "new description";
    private final Boolean       DONE            = null;
    private final LocalDateTime CREATE_DATE     = LocalDateTime.of(2023, 6, 14, 12, 0);
    private final LocalDateTime DONE_DATE       = LocalDateTime.of(2023, 6, 14, 12, 1);
    private final LocalDateTime ORDER_TODO      = LocalDateTime.of(2023, 6, 14, 12, 2);


    private Todo todo;
    private List<Todo> listTodo;
    private Optional<Todo> optionalTodo;
    private Page<Todo> pageListTodo;

    @InjectMocks
    private ServiceTodoImpl service;

    @Mock
    private TodoRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }



    @Test
    void whenSaveThenBodyIsObjectTodoAndDoneIsFalse() {

        when(repository.save(any())).thenReturn(todo);

        var response = service.save(todo);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(false, response.getDone());

        testAttrsTodoObjEqualsResponseBody(response, true, true, false, true, true, true);

    }


    @Test
    void whenFindAllWithoutMarkDoneThenBodyIsListObjectTodo() {

        when(repository.findAllWithoutMarkDoneNonPageable()).thenReturn(listTodo);

        var response = service.findAllWithoutMarkDoneNonPageable();
        var responseObj = response.get(INDEX);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(todo.getClass(), responseObj.getClass());

        testAllAttrsTodoObjEqualsResponseBody(responseObj);

    }


    @Test
    void whenFindAllWithMarkDoneThenBodyIsPageOfListObjectTodo() {

        when(repository.findAllWithMarkDone(any())).thenReturn(pageListTodo);

        var response = service.findAllWithMarkDone(INDEX);
        var responseObj = response.toList().get(INDEX);

        assertNotNull(response);
        assertEquals(1, response.toList().size());
        assertEquals(pageListTodo, response);

        testAllAttrsTodoObjEqualsResponseBody(responseObj);

    }


    @Test
    void whenDeleteByIdWithMarkDoneThenHaveSuccess() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(ID);

        verify(repository, times(1)).deleteById(anyLong());

    }


    @Test
    void whenMarkAsDoneThenBodyIsObjectTodoAndDoneIsTrueAndDoneDateIsNewDate() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.markAsDone(ID);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(true, response.getDone());
        assertNotNull(response.getDoneDate());
        assertNotEquals(DONE_DATE, response.getDoneDate());

        testAttrsTodoObjEqualsResponseBody(response, true, true, false, true, false, true);

    }


    @Test
    void whenChangeOrderByIdThenBodyIsObjectTodoAndOrderTodoIsNewDate() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.changeOrderById(ID);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertNotNull(response.getOrderTodo());
        assertNotEquals(ORDER_TODO, response.getOrderTodo());

        testAttrsTodoObjEqualsResponseBody(response, true, true, true, true, true, false);

    }


    @Test
    void whenUndoneMarkAsDoneThenBodyIsObjectTodoAndDoneIsFalseAndDoneDateIsNull() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.undoneMarkAsDone(ID);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertEquals(false, response.getDone());
        assertNull(response.getDoneDate());

        testAttrsTodoObjEqualsResponseBody(response, true, true, false, true, false, true);

    }


    @Test
    void whenUpdateDescriptionByIdThenBodyIsObjectTodoAndNewDescription() {

        when(repository.findById(anyLong())).thenReturn(optionalTodo);

        var response = service.updateDescriptionById(ID, NEW_DESCRIPTION);

        assertNotNull(response);
        assertEquals(todo.getClass(), response.getClass());

        assertNotEquals(DESCRIPTION, response.getDescription());
        assertEquals(NEW_DESCRIPTION, response.getDescription());

        testAttrsTodoObjEqualsResponseBody(response, true, false, true, true, true, true);

    }






    private void testAllAttrsTodoObjEqualsResponseBody(Todo response) {
        testAttrsTodoObjEqualsResponseBody(response, true, true, true, true, true, true);
    }

    private void testAttrsTodoObjEqualsResponseBody(Todo response,
            boolean id, boolean description, boolean done, boolean createDate,
            boolean doneDate, boolean orderTodo) {
        if(id) assertEquals(ID, response.getId());
        if(description) assertEquals(DESCRIPTION, response.getDescription());
        if(done) assertEquals(DONE, response.getDone());
        if(createDate) assertEquals(CREATE_DATE, response.getCreatedDate());
        if(doneDate) assertEquals(DONE_DATE, response.getDoneDate());
        if(orderTodo) assertEquals(ORDER_TODO, response.getOrderTodo());
    }

    private void startUser() {
        todo = new Todo(ID, DESCRIPTION, DONE, CREATE_DATE, DONE_DATE, ORDER_TODO);
        listTodo = List.of(todo);
        optionalTodo = Optional.of(todo);
        pageListTodo = new PageImpl<>(Collections.singletonList(todo));
    }

}
 */
