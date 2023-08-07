package io.github.gulybyte.todo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.util.TodoCreator;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest// h2 enable
//@ActiveProfiles("test")// disable flyway
public class TodoRepositoryTest {

    private final Integer INDEX = 0;


    @Autowired
    private TodoRepository repository;

    @Test
    void whenSaveWithCreateDateAndOrderTodoIsNullThenReturnCreateDateAndOrderTodoIsNotNull() {//testing beforeSave()

        var todoToBeSaved = TodoCreator.createTodoWithCreateDateAndOrderTodoIsNull();

        Todo response = this.repository.save(todoToBeSaved);

        assertNotNull(response);
        assertNotNull(response.getCreatedDate());
        assertNotNull(response.getOrderTodo());

    }


    @Test
    void whenFindAllWithoutMarkDoneWithDoneFalseThenReturnListOfTodoNotEmpty() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneFalse();

        this.repository.save(todoToBeSaved);

        List<Todo> response = this.repository.findAllWithoutMarkDoneNonPageable();
        var responseObj = response.get(INDEX);

        assertNotNull(response);
        assertEquals(1, response.size());

        assertEquals(responseObj.getDone(), false);

    }


    @Test
    void whenFindAllWithMarkDoneWithDoneTrueThenReturnPageOfListOfTodoNotEmpty() {

        var todoToBeSaved = TodoCreator.createTodoWithDoneTrue();

        this.repository.save(todoToBeSaved);

		var page = PageRequest.of(0, 5, Sort.Direction.DESC, "doneDate");

        Page<Todo> response = repository.findAllWithMarkDone(page);

        assertEquals(1, response.getNumberOfElements());

    }


}
