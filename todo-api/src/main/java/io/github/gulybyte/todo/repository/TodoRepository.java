package io.github.gulybyte.todo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.gulybyte.todo.model.Todo;

@Repository
@org.springframework.transaction.annotation.Transactional
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "select c from Todo c"
                    + " where c.done = false"
                    + " order by orderTodo desc")
	List<Todo> findAllWithoutMarkDone();

    @Query(value = "select c from Todo c where c.done = true")
	Page<Todo> findAllWithMarkDone(Pageable pageable);


}
