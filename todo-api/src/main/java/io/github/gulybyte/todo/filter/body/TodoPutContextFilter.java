package io.github.gulybyte.todo.filter.body;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class TodoPutContextFilter {

    @NotNull(message = "Id cannot be null.")
    private Long id;

    @Size(max = 20, message = "Your context of the todo is too long (max: 20 characters)")
    private String context;

}
