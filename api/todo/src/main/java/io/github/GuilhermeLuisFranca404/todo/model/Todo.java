package io.github.GuilhermeLuisFranca404.todo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	//** ATRIBUTOS **\\
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String description;//descricao da tarefa
	
	@Column
	private Boolean done;//indica se a tarefa foi feita ou nao
	
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")//formatando a data e hora
	private LocalDateTime createdDate, doneDate;//createdData: guarda a data de cadastro; doneDate: grava a data e hora da tarefa concluida;
	
	
	
	
	//** GETTERS and SETTERS **\\
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
	
	
	//** METHODS **\\
	
	@PrePersist
	public void beforaSave() {//este metdo sera executado antes de salvar no banco
		
		setCreatedDate(LocalDateTime.now());//setara o local e data atual
		
		
	}
	
	
	
	
}
