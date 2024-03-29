package io.github.gulybyte.todo.filter.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class TodoContextFilter {

    @NotNull(message = "Id cannot be null.")
    private Long id;

    @NotBlank(message = "The context cannot be blank.")
    @Size(max = 20, message = "Your context of the todo is too long (max: 20 characters)")
    private String context;

}
