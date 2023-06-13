package io.github.GuilhermeLuisFranca404.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String description;

	@Column
	private Boolean done;

	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdDate, doneDate, orderTodo;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getDoneDate() {
		return doneDate;
	}
	public void setDoneDate(LocalDateTime doneDate) {
		this.doneDate = doneDate;
	}
    public LocalDateTime getOrderTodo() {
        return orderTodo;
    }
    public void setOrderTodo(LocalDateTime orderTodo) {
        this.orderTodo = orderTodo;
    }

	@PrePersist
	public void beforeSave() {
        final var DATE_TIME_NOW = LocalDateTime.now();
		setCreatedDate(DATE_TIME_NOW);
		setOrderTodo(DATE_TIME_NOW);
	}

}
