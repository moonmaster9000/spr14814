package spr14814.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}

class Foo{
    private String name;

    Foo(String name) {
        this.name = name;
    }
}

@RestController
class FoosController {
    @GetMapping("/foo")
    public Foo getFoo(){
        return new Foo("foo");
    }

    @GetMapping("/foos")
    public String getFoos(){
        throw new FooNotFoundException();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FooNotFoundException.class)
    public NotFoundResponse handleFooNotFound(FooNotFoundException e){
        return new NotFoundResponse("foo not found");
    }
}

class FooNotFoundException extends RuntimeException {}

class NotFoundResponse {
    private String message;

    NotFoundResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

