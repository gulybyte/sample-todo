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
import io.github.gulybyte.todo.filter.body.UpdateDescriptionTodoPutRequestBodyFilter;
import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.repository.TodoRepository;
import io.github.gulybyte.todo.service.ServiceTodo;

@Service
public class ServiceTodoImpl implements ServiceTodo {

    @Autowired
	private TodoRepository repository;

    @Override @Transactional
    public Todo save(Todo todo) {
        if (todo.getDone() == null) todo.setDone(false);
        return repository.save(todo);
    }


    @Override @Transactional
    public Todo updateDescription(UpdateDescriptionTodoPutRequestBodyFilter todoBody) {
        return repository.findById(todoBody.getId()).map(todo -> {
            if (todo.getDescription().equals(todoBody.getDescription()))
                throw new ConflictException("Feature description remains the same");
            todo.setDescription(todoBody.getDescription());
            return repository.save(todo);
        })
        .orElseThrow(() -> new NotFoundException("Todo Not Found!"));
    }


    @Override
    public List<Todo> findAllWithoutMarkDoneNonPageable() {
        return repository.findAllWithoutMarkDoneNonPageable().isEmpty() ? null : repository.findAllWithoutMarkDoneNonPageable();
    }


    @Override
    public Page<Todo> findAllWithMarkDone(int pageNumber) {
		var page = PageRequest.of(pageNumber, 5, Sort.Direction.DESC, "doneDate");
        return repository.findAllWithMarkDone(page).isEmpty() ? null : repository.findAllWithMarkDone(page);
    }


    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundException("Todo Not Found!");
        repository.deleteById(id);
    }


    @Override
    public Todo markAsDone(Long id) {
        return repository.findById(id).map(todo -> {
            if (todo.getDone())
                throw new ConflictException("Todo is already mark as done");
            todo.setDone(true);
            todo.setDoneDate(LocalDateTime.now());
            return repository.save(todo);
        })
        .orElseThrow(() -> new NotFoundException("Todo Not Found!"));
    }


    @Override
    public Todo undoneMarkAsDone(Long id) {
        return repository.findById(id).map(todo -> {
            if (!todo.getDone())
                throw new ConflictException("Todo not already mark as done yet");
            todo.setDone(false);
            todo.setDoneDate(null);
            return repository.save(todo);
        })
        .orElseThrow(() -> new NotFoundException("Todo Not Found!"));
    }


    @Override
    public Todo changeOrderById(Long id) {
        return repository.findById(id).map(todo -> {
            todo.setOrderTodo(LocalDateTime.now());
            return repository.save(todo);
        })
        .orElseThrow(() -> new NotFoundException("Todo Not Found!"));
    }

}
