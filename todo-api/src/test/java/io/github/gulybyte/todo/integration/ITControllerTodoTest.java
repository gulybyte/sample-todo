package io.github.gulybyte.todo.integration;

import java.util.List;

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
@ActiveProfiles("test")
/*
 * APENAS VERIFICA STATUS E .getBody().getClass() DE OK OU NO_CONTENT, VERIFICAR SE HOUVE MUDANÇAS É OS TESTS SERVICE
 * TODO: PRÓXIMO TODO VERIFICAR STATUS E EXCEPTIONS
 */
public class ITControllerTodoTest {

    private final Integer INDEX = 0;

    @Autowired
    private TodoRepository repository;

    @Autowired
    private TestRestTemplate testRest;



    @Test/* criar teste com falha @Valid */
    void save() {

        var entity = TodoCreator.createTodoDefault();
        var requestEntity = createRequestEntityTodo(entity);

        ResponseEntity<Todo> response = testRest.exchange("/", HttpMethod.POST,
            requestEntity, new ParameterizedTypeReference<Todo>() {});

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test/* criar teste lista vazia */
    void findAllWithoutMarkDone() {

        repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/", HttpMethod.GET,
            null, new ParameterizedTypeReference<List<Todo>>() {});

        assertEquals(response.getBody().get(INDEX).getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test/* criar teste null com done=false */
    void findAllWithMarkDone() {

        repository.save(TodoCreator.createTodoWithDoneTrue());

        var response = testRest.exchange("/finalized", HttpMethod.GET,
            null, new ParameterizedTypeReference<CustomPageDeserializer<Todo>>() {});

        assertEquals(response.getBody().toList().get(0).getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }



    @Test/* criar teste com o id dito não existente (não tem essa func ainda) */
    void deleteById() {

        var savedTodo = repository.save(TodoCreator.createTodoDefault());

        var response = testRest.exchange("/{id}", HttpMethod.DELETE,
            null, Void.class, savedTodo.getId());/* TODO: VERIFICAR SE ESTÁ VALIDADANDO ID CORRETAMENTE */

        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

    }



    /* TESTAR ID NÃO EXISTA, E CASO já estiver done true */
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
        var requestEntity = createRequestDescription("new description");
        var savedTodo = repository.save(entity);

        var response = testRest.exchange("/{id}/update-description", HttpMethod.PATCH,
            requestEntity, new ParameterizedTypeReference<Todo>() {}, savedTodo.getId());

        assertEquals(response.getBody().getClass(), Todo.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }







    private HttpEntity<Todo> createRequestEntityTodo(Todo todo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(todo, headers);
    }

    private HttpEntity<String> createRequestDescription(String description) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>("{\"description\": \""+description+"\"}", headers);
    }

/*
 * MODELO ACIMA, SÓ SEGUIR E TERMINAR..., FAZER REVISÃO EM TODOS OS OUTROS TESTES, ORDEM NO ASSERTEQUAL, DIMINUIR
 * NOMENCLATURA (é nescessario descrever?, o código já não faz?, e que tau usar @DisplayName?)
 */



}
