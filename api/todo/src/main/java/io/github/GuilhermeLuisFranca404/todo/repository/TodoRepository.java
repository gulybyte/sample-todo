package io.github.GuilhermeLuisFranca404.todo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Long> {

	
	
	
}
