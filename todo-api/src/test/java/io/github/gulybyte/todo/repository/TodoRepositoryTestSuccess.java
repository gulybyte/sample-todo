package io.github.gulybyte.todo.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.data.domain.PageRequest;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.util.TodoCreator;
import static io.github.gulybyte.todo.util.ArgumentsMatchersTodo.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TodoRepositoryTestSuccess {

    @Autowired
    private TodoRepository repository;

    @Test
    @DisplayName("Testing @PrePersist of Todo")
    void beforeSave() {

        var todoToBeSaved = TodoCreator.createTodoWithCreateDateAndOrderTodoIsNull();

        Todo response = this.repository.save(todoToBeSaved);

        assertNotNull(response);
        assertNotNull(response.getCreatedDate());
        assertNotNull(response.getOrderTodo());

    }


    @Test
    @DisplayName("When findAllWithoutMarkDone() with done=false then return not empty")
    void findAllWithoutMarkDone() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneFalse();

        this.repository.save(todoToBeSaved);

        List<Todo> response = this.repository.findAllWithoutMarkDone();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(response.get(INDEX).getDone(), false);

    }


    @Test
    @DisplayName("When findAllWithMarkDone() with done=true then return not empty")
    void findAllWithMarkDone() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneTrue();

        this.repository.save(todoToBeSaved);

		var page = PageRequest.of(0, 5, Sort.Direction.DESC, "doneDate");

        Page<Todo> response = repository.findAllWithMarkDone(page);

        assertEquals(1, response.getNumberOfElements());

    }


}
