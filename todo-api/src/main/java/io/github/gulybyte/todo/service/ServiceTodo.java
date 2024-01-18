package io.github.gulybyte.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import io.github.gulybyte.todo.filter.body.TodoPutContextFilter;
import io.github.gulybyte.todo.filter.body.TodoPutDescriptionFilter;
import io.github.gulybyte.todo.model.Todo;

public interface ServiceTodo {

    Todo save(Todo todo);
    Todo updateDescription(TodoPutDescriptionFilter todoBody);
    Todo updateContext(TodoPutContextFilter todoBody);

	List<Todo> findAllWithoutMarkDone();
	Page<Todo> findAllWithMarkDone(int pageNumber);

	void deleteById(Long id);

	Todo markAsDone(Long id);
	Todo undoneMarkAsDone(Long id);
	Todo changeOrderById(Long id);

}
