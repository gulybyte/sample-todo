package io.github.gulybyte.todo.success;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.github.gulybyte.todo.util.ITTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class ITTodoTestSuccess extends ITTestUtils {

    @Autowired
    private TestRestTemplate testRest;


    @Test
    @Sql(scripts = "/sql/todo_DELETE_ALL.sql", executionPhase = BEFORE_TEST_METHOD)
    void save() {

        var response = testRest.postForEntity("/",
            createRequestEntity(createTodoDefault()),
            TODO_RESPONSE_TYPE);

        assertNotNull(response.getBody().getCreatedDate());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void updateDescription() {

        var response = testRest.exchange("/update-description", HttpMethod.PATCH,
            createRequestEntity(anyTodoDescriptionFilter()),
            TODO_RESPONSE_TYPE);

        assertNotEquals("first todo", response.getBody().getDescription());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void updateContext() {

        var response = testRest.exchange("/update-context", HttpMethod.PATCH,
            createRequestEntity(anyTodoContextFilter(createTodoContextFilterWithNewContext())),
            TODO_RESPONSE_TYPE);

        assertNotEquals("none", response.getBody().getContextTodo());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void findAllWithoutMarkDone() {

        var response = testRest.exchange("/", HttpMethod.GET,
            null, LIST_RESPONSE_TYPE);

        assertEquals("first todo", response.getBody().get(INDEX).getDescription());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_SIX_DONE_TRUE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void findAllWithMarkDone() {

        var response = testRest.exchange("/todo-done", HttpMethod.GET,
            null, PAGE_RESPONSE_TYPE);

        assertEquals(2, response.getBody().getTotalPages());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void deleteById() {

        var response = testRest.exchange("/{id}", HttpMethod.DELETE,
            null, Void.class, 1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void markAsDone() {

        var response = testRest.exchange("/{id}/done", HttpMethod.PATCH,
            null, TODO_RESPONSE_TYPE, 1);

        assertEquals(true, response.getBody().getDone());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE_DONE_TRUE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void undoneMarkAsDone() {

        var response = testRest.exchange("/{id}/undone", HttpMethod.PATCH,
            null, TODO_RESPONSE_TYPE, 1);

        assertEquals(false, response.getBody().getDone());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @Sql(scripts = {"/sql/todo_DELETE_ALL.sql","/sql/todo_INSERT_ONE.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void changeOrderById() {

        var oldOrder = testRest.exchange("/", HttpMethod.GET,
            null, LIST_RESPONSE_TYPE);

        var response = testRest.exchange("/{id}/order", HttpMethod.PATCH,
            null, TODO_RESPONSE_TYPE, 1);

        assertNotEquals(oldOrder.getBody().get(INDEX).getOrderTodo(), response.getBody().getOrderTodo());
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}
