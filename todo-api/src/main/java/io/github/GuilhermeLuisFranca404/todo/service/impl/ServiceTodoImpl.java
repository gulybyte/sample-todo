package io.github.GuilhermeLuisFranca404.todo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;
import io.github.GuilhermeLuisFranca404.todo.repository.TodoRepository;
import io.github.GuilhermeLuisFranca404.todo.service.ServiceTodo;

@Service
public class ServiceTodoImpl implements ServiceTodo {

    @Autowired
	private TodoRepository repository;

    @Override
    public Todo save(Todo todo) {
        if (todo.getDone() == null) todo.setDone(false);
        return repository.save(todo);
    }


    @Override
    public List<Todo> findAllWithoutMarkDone() {
        return repository.findAllWithoutMarkDone();
    }


    @Override
    public Page<List<Todo>> findAllWithMarkDone(int pageNumber) {
		var page = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "doneDate");
        return repository.findAllWithMarkDone(page);
    }


    @Override
    public void deleteByIdWithMarkDone(Long id) {
        repository.deleteById(id);
    }


    @Override
    public Todo markAsDone(Long id) {
        return repository.findById(id).map(todo -> {
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            repository.save(todo);
            return todo;
        }).orElse(null);
    }


    @Override
    public Todo changeOrderById(Long id) {
        return repository.findById(id).map(todo -> {
            todo.setOrderTodo(LocalDateTime.now());
            repository.save(todo);
            return todo;
        }).orElse(null);
    }

}
