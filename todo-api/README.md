## open:
```
cd todo-api
```

## Project Setup

```sh
./gradlew install
```

### Run with Hot-Reload for Development

In your IDE (Eclipse, IntelliJ IDEA, Visual Studio Code, etc).

Don't worry about the database; Spring will automatically initialize it for you using Docker

### Compile and Minify for Production

**Compile**:
```sh
./gradlew build
```

**Run**:
```sh
java -jar build/libs/todo-1.jar
```
