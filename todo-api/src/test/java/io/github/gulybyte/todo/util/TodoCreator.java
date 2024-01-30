package io.github.gulybyte.todo.util;

import java.time.LocalDateTime;

import io.github.gulybyte.todo.filter.body.TodoContextFilter;
import io.github.gulybyte.todo.filter.body.TodoDescriptionFilter;
import io.github.gulybyte.todo.model.Todo;

public class TodoCreator {

    private static final Long DEFAULT_LONG = 1L;
    private static final String DEFAULT_STRING = "any String not empty";
    private static final Boolean DEFAULT_BOOLEAN = null;
    private static final LocalDateTime DEFAULT_DATE = null;


    public static Todo createTodoDefault(){
        return Todo.builder()
                .description(DEFAULT_STRING)
                .build();
    }

    public static Todo createTodoWithoutDescription(){
        return Todo.builder()
                .description(null)
                .build();
    }

    public static Todo createTodoWithDefaultContext(){
        return Todo.builder()
                .description(DEFAULT_STRING)
                .contextTodo("none")
                .build();
    }

    public static Todo createTodoWithDoneFalse(){
        return Todo.builder()
                .description(DEFAULT_STRING)
                .done(false)
                .createdDate(DEFAULT_DATE)
                .doneDate(DEFAULT_DATE)
                .orderTodo(DEFAULT_DATE)
                .build();
    }

    public static Todo createTodoWithDoneTrue(){
        return Todo.builder()
                .description(DEFAULT_STRING)
                .done(true)
                .createdDate(DEFAULT_DATE)
                .doneDate(DEFAULT_DATE)
                .orderTodo(DEFAULT_DATE)
                .build();
    }

    public static Todo createTodoWithCreateDateAndOrderTodoIsNull(){
        return Todo.builder()
                .description(DEFAULT_STRING)
                .done(DEFAULT_BOOLEAN)
                .createdDate(null)
                .doneDate(DEFAULT_DATE)
                .orderTodo(null)
                .build();
    }



    public static TodoDescriptionFilter createTodoDescriptionFilterDefault(){
        return TodoDescriptionFilter.builder()
                .id(DEFAULT_LONG)
                .description(DEFAULT_STRING)
                .build();
    }

    public static TodoDescriptionFilter createTodoDescriptionFilterWithoutIdAndDescription(){
        return TodoDescriptionFilter.builder()
                .id(null)
                .description(null)
                .build();
    }

    public static TodoDescriptionFilter createTodoDescriptionFilterWithNewDescription(){
        return TodoDescriptionFilter.builder()
                .id(DEFAULT_LONG)
                .description("new description")
                .build();
    }


    public static TodoContextFilter createTodoContextFilterDefault(){
        return TodoContextFilter.builder()
                .id(DEFAULT_LONG)
                .context("none")
                .build();
    }

    public static TodoContextFilter createTodoContextFilterWithoutIdAndContext(){
        return TodoContextFilter.builder()
                .id(null)
                .context(null)
                .build();
    }

    public static TodoContextFilter createTodoContextFilterWithNewContext(){
        return TodoContextFilter.builder()
                .id(DEFAULT_LONG)
                .context("not none")
                .build();
    }

}
