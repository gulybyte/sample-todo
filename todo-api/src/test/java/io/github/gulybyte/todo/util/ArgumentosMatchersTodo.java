package io.github.gulybyte.todo.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import io.github.gulybyte.todo.filter.body.TodoPutFilter;
import io.github.gulybyte.todo.model.Todo;

public class ArgumentosMatchersTodo {

    public static final Integer INDEX = 0;


    public static Todo anyTodo() {
        return TodoCreator.createTodoDefault();
    }


    public static List<Todo> anyListTodo() {
        return List.of(anyTodo());
    }
    public static List<Todo> anyListTodo(Todo todo) {
        return List.of(todo);
    }
    public static List<Todo> anyEmptyListTodo() {
        return Collections.emptyList();
    }


    public static Optional<Todo> anyOptionalTodo() {
        return Optional.of(anyTodo());
    }
    public static Optional<Todo> anyOptionalTodo(Todo todo) {
        return Optional.of(todo);
    }
    public static Optional<Todo> anyEmptyOptionalTodo() {
        return Optional.empty();
    }


    public static Page<Todo> anyPageTodo() {
        return new PageImpl<>(Collections.singletonList(anyTodo()));
    }
    public static Page<Todo> anyPageTodo(Todo todo) {
        return new PageImpl<>(Collections.singletonList(todo));
    }
    public static Page<Todo> anyEmptyPageTodo() {
        return Page.empty();
    }



    public static TodoPutFilter anyTodoPutFilter() {
        return TodoCreator.createTodoPutFilterDefault();
    }
    public static TodoPutFilter anyTodoPutFilter(TodoPutFilter TodoPutFilter) {
        return TodoPutFilter;
    }

}
