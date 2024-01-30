package io.github.gulybyte.todo.util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import io.github.gulybyte.todo.filter.body.TodoContextFilter;
import io.github.gulybyte.todo.filter.body.TodoDescriptionFilter;
import io.github.gulybyte.todo.model.Todo;
import io.github.gulybyte.todo.util.wrapper.CustomPageDeserializer;

public class ArgumentsMatchersTodo extends TodoCreator {

    public static final Integer INDEX = 0;

    /* REPONSES TYPE */

    public static final Class<? extends Object> ANY_RESPONSE_TYPE = new Object().getClass();
    public static final Class<? extends Todo> TODO_RESPONSE_TYPE = new Todo().getClass();
    public static final ParameterizedTypeReference<List<Todo>> LIST_RESPONSE_TYPE = new ParameterizedTypeReference<List<Todo>>() {};
    public static final ParameterizedTypeReference<CustomPageDeserializer<Todo>> PAGE_RESPONSE_TYPE = new ParameterizedTypeReference<CustomPageDeserializer<Todo>>() {};




    /* CREATE OBJECTS */

    public static Todo anyTodo() {
        return createTodoDefault();
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



    public static TodoDescriptionFilter anyTodoDescriptionFilter() {
        return createTodoDescriptionFilterDefault();
    }
    public static TodoDescriptionFilter anyTodoDescriptionFilter(TodoDescriptionFilter TodoDescriptionFilter) {
        return TodoDescriptionFilter;
    }

    public static TodoContextFilter anyTodoContextFilter() {
        return createTodoContextFilterDefault();
    }
    public static TodoContextFilter anyTodoContextFilter(TodoContextFilter TodoContextFilter) {
        return TodoContextFilter;
    }
}
