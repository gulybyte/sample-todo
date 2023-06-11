package io.github.GuilhermeLuisFranca404.todo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.GuilhermeLuisFranca404.todo.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {


    @Query(value = "select c from Todo c where c.done = false")
	List<Todo> findAllWithoutMarkDone();

    @Query(value = "select c from Todo c where c.done = true")
	Page<List<Todo>> findAllWithMarkDone(Pageable pageable);

}
