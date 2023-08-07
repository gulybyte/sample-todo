package io.github.gulybyte.todo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
    @NotBlank(message = "The description cannot be blank!")
    @Size(max = 350, message = "Description is too long (max: 350 characters)!!")
	private String description;

	@Column
	private Boolean done;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdDate, doneDate, orderTodo;

	@PrePersist
	public void beforeSave() {
        final var DATE_TIME_NOW = LocalDateTime.now();
		setCreatedDate(DATE_TIME_NOW);
		setOrderTodo(DATE_TIME_NOW);
	}


}
