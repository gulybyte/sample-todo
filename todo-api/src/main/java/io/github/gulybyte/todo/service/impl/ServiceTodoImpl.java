package io.github.gulybyte.todo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.gulybyte.todo.exception.status.ConflictException;
import io.github.gulybyte.todo.exception.status.NotFoundException;
import io.github.gulybyte.todo.filter.body.TodoContextFilter;
import io.github.gulybyte.todo.filter.body.TodoDescriptionFilter;
import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.ServiceTodo;

import static io.github.gulybyte.todo.util.UniversalConstants.*;

@Service
public class ServiceTodoImpl implements ServiceTodo {

    @Autowired
	private TodoRepository repository;

    @Override @Transactional
    public Todo save(Todo todo) {
        todo.setId(null);
        if (todo.getDone() == null) todo.setDone(false);
        return repository.save(todo);
    }


    @Override @Transactional
    public Todo updateDescription(TodoDescriptionFilter todoBody) {
        var todoToSave = repository.findById(todoBody.getId()).map(todo -> {
            if (todo.getDescription().equals(todoBody.getDescription()))
                throw new ConflictException("Feature description remains the same");
            todo.setDescription(todoBody.getDescription());
            return todo;
        })
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));

        return repository.save(todoToSave);
    }


    @Override @Transactional
    public Todo updateContext(TodoContextFilter todoBody) {
        var todoToSave = repository.findById(todoBody.getId()).map(todo -> {
            if (todo.getContextTodo().equals(todoBody.getContext()))
                throw new ConflictException("Feature context remains the same");
            todo.setContextTodo(todoBody.getContext());
            return todo;
        })
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));

        return repository.save(todoToSave);
    }


    @Override
    public List<Todo> findAllWithoutMarkDone() {
        var todo = repository.findAllWithoutMarkDone();

        if (todo.isEmpty())
            throw new NotFoundException(NOT_FOUND);

        return todo;
    }


    @Override
    public Page<Todo> findAllWithMarkDone(int pageNumber) {
		var page = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "doneDate");
        var todo = repository.findAllWithMarkDone(page);

        if (!todo.hasContent())
            throw new NotFoundException(NOT_FOUND);

        return todo;
    }


    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException(NOT_FOUND);
        repository.deleteById(id);
    }


    @Override
    public Todo markAsDone(Long id) {
        var todoToSave = repository.findById(id).map(todo -> {
            if (todo.getDone())
                throw new ConflictException("Todo is already mark as done");
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            return todo;
        })
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));

        return repository.save(todoToSave);
    }


    @Override
    public Todo undoneMarkAsDone(Long id) {
        var todoToSave = repository.findById(id).map(todo -> {
            if (!todo.getDone())
                throw new ConflictException("Todo not already mark as done yet");
            todo.setDone(false);
            todo.setDoneDate(null);
            return todo;
        })
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));

        return repository.save(todoToSave);
    }


    @Override
    public Todo changeOrderById(Long id) {
        var todoToSave = repository.findById(id).map(todo -> {
            todo.setOrderTodo(LocalDateTime.now());
            return todo;
        })
        .orElseThrow(() -> new NotFoundException(NOT_FOUND));

        return repository.save(todoToSave);
    }

}
