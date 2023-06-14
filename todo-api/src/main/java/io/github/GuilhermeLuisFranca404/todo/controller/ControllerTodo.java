package io.github.GuilhermeLuisFranca404.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;
import io.github.GuilhermeLuisFranca404.todo.service.ServiceTodo;

@RestController
@RequestMapping("/api-rest/todos")
@CrossOrigin(origins = {"*"})
public class ControllerTodo {

    @Autowired
    private ServiceTodo service;

	@PostMapping
	public ResponseEntity<Todo> save(@RequestBody Todo todo) {
        return ResponseEntity.ok().body(service.save(todo));
	}


	@GetMapping
	public ResponseEntity<List<Todo>> findAllWithoutMarkDone(){
		return ResponseEntity.ok().body(service.findAllWithoutMarkDone());
	}


	@GetMapping("finalized")
	public ResponseEntity<Page<List<Todo>>> findAllWithMarkDone(
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return ResponseEntity.ok().body(service.findAllWithMarkDone(pageNumber));
	}


	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteByIdWithMarkDone(@PathVariable Long id) {
        service.deleteByIdWithMarkDone(id);
        return ResponseEntity.noContent().build();
	}


	@PatchMapping("{id}/done")
	public ResponseEntity<Todo> markAsDone(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.markAsDone(id));
	}


    @PatchMapping("{id}/order")
	public ResponseEntity<Todo> changeOrderById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.changeOrderById(id));
	}

}