package io.github.gulybyte.todo.integration;

/* import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.util.TodoCreator;
import io.github.gulybyte.todo.wrapper.CustomPageDeserializer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase//h2
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test") */
/*
 * APENAS VERIFICA STATUS E .getBody().getClass() DE OK OU NO_CONTENT, VERIFICAR SE HOUVE MUDANÇAS É OS TESTS SERVICE
 * TODO: PRÓXIMO TODO VERIFICAR STATUS E EXCEPTIONS
 */
public class ITControllerTodoTest {

    /*private final Integer INDEX = 0;

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TestRestTemplate testRest;



    @Test
    void save() {

        var entity = TodoCreator.createTodoDefault();
        var requestEntity = createRequestEntity(entity);

        ResponseEntity<Todo> response = testRest.exchange("/", HttpMethod.POST,
            requestEntity, new ParameterizedTypeReference<Todo>() {});

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test
    void findAllWithoutMarkDone() {

        repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/", HttpMethod.GET,
            null, new ParameterizedTypeReference<List<Todo>>() {});

        assertEquals(response.getBody().get(INDEX).getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test
    void findAllWithMarkDone() {

        repository.save(TodoCreator.createTodoWithDoneTrue());

        var response = testRest.exchange("/finalized", HttpMethod.GET,
            null, new ParameterizedTypeReference<CustomPageDeserializer<Todo>>() {});

        assertEquals(response.getBody().toList().get(0).getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test
    void deleteById() {

        var savedTodo = repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/{id}", HttpMethod.DELETE,
            null, Void.class, savedTodo.getId());

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }


    @Test
    void markAsDone() {

        var savedTodo = repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/{id}/done", HttpMethod.PATCH,
            null, new ParameterizedTypeReference<Todo>() {}, savedTodo.getId());

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void changeOrderById() {

        var savedTodo = repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/{id}/order", HttpMethod.PATCH,
            null, new ParameterizedTypeReference<Todo>() {}, savedTodo.getId());

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void undoneMarkAsDone() {

        var savedTodo = repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/{id}/undone", HttpMethod.PATCH,
            null, new ParameterizedTypeReference<Todo>() {}, savedTodo.getId());

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    void updateDescriptionById() {

        var entity = TodoCreator.createTodoDefault();
        var savedTodo = repository.save(entity);

        var description = createJson("description", "new description");
        var requestEntity = createRequestEntity(description);

        var response = testRest.exchange("/{id}/update-description", HttpMethod.PATCH,
            requestEntity, new ParameterizedTypeReference<Todo>() {}, savedTodo.getId());

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }







    private <T> HttpEntity<T> createRequestEntity(T obj) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(obj, headers);
    }

    private String createJson(String key, String value) {
        return "{\""+key+"\": \""+value+"\"}";
    }*/



}
