package io.github.GuilhermeLuisFranca404.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EntityScan(basePackages = "io.github.GuilhermeLuisFranca404.todo.model")//diz para escanear tudo neste pacote como uma entidade de modelo
@ComponentScan(basePackages =  "io.github.GuilhermeLuisFranca404.*")//mapeando todos os pacotes
@EnableJpaRepositories(basePackages = {"io.github.GuilhermeLuisFranca404.todo.repository"})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
