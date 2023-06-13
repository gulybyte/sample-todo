package io.github.GuilhermeLuisFranca404.todo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;

public interface ServiceTodo {

    Todo save(Todo todo);

	List<Todo> findAllWithoutMarkDone();
	Page<List<Todo>> findAllWithMarkDone(int pageNumber);

	void deleteByIdWithMarkDone(Long id);
	Todo markAsDone(Long id);
	Todo changeOrderById(Long id);

}
