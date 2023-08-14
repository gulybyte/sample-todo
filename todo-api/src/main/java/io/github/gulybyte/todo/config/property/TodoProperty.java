package io.github.gulybyte.todo.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("todoapi")
@Component
@Getter @Setter
public class TodoProperty {

    private String domains;

}
