package io.github.gulybyte.todo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.util.TodoCreator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TodoRepositoryExceptionsTest {

    @Autowired
    private TodoRepository repository;

    @Test
    void whenFindAllWithoutMarkDoneWithDoneTryeThenReturnListOfTodoEmpty() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneTrue();

        this.repository.save(todoToBeSaved);

        List<Todo> response = this.repository.findAllWithoutMarkDone();

        assertNotNull(response);
        assertEquals(0, response.size());

    }


    @Test
    void whenFindAllWithMarkDoneWithDoneFalseThenReturnPageOfListOfTodoEmpty() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneFalse();

        this.repository.save(todoToBeSaved);

        var page = PageRequest.of(0, 5, Sort.Direction.DESC, "doneDate");

        Page<List<Todo>> response = repository.findAllWithMarkDone(page);

        assertEquals(0, response.getNumberOfElements());

    }

}
