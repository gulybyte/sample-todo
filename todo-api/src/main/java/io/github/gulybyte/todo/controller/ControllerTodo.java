package io.github.gulybyte.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.gulybyte.todo.filter.body.UpdateDescriptionTodoPutRequestBodyFilter;
import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.service.ServiceTodo;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class ControllerTodo {

    @Autowired
    private ServiceTodo service;

	@PostMapping
	public ResponseEntity<Todo> save(@RequestBody @Valid Todo todo) {
        return new ResponseEntity<>(service.save(todo), HttpStatus.CREATED);
	}


    @PutMapping("update-description")
    public ResponseEntity<Todo> updateDescription(@RequestBody @Valid UpdateDescriptionTodoPutRequestBodyFilter todoBody) {
        return ResponseEntity.ok(service.updateDescription(todoBody));
    }


	@GetMapping
	public ResponseEntity<List<Todo>> findAllWithoutMarkDone(){
		return ResponseEntity.ofNullable(service.findAllWithoutMarkDoneNonPageable());
	}


	@GetMapping("finalized")
	public ResponseEntity<Page<Todo>> findAllWithMarkDone(
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return ResponseEntity.ofNullable(service.findAllWithMarkDone(pageNumber));
	}


	@DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
	}


	@PatchMapping("{id}/done")
	public ResponseEntity<Todo> markAsDone(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.markAsDone(id));
	}


    @PatchMapping("{id}/undone")
    public ResponseEntity<Todo> undoneMarkAsDone(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.undoneMarkAsDone(id));
    }


    @PatchMapping("{id}/order")
	public ResponseEntity<Todo> changeOrderById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.changeOrderById(id));
	}

}
