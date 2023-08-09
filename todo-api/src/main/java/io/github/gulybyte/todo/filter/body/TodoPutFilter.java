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
public class TodoPutFilter {

    @NotNull(message = "Id cannot be null.")
    private Long id;

    @NotBlank(message = "The description cannot be blank.")
    @Size(max = 350, message = "Description is too long (max: 350 characters).")
    private String description;

}
