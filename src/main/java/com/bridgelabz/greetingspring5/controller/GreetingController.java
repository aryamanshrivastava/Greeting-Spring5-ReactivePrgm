package com.bridgelabz.greetingspring5.controller;


import com.bridgelabz.greetingspring5.model.Greeting;
import com.bridgelabz.greetingspring5.model.Response;
import com.bridgelabz.greetingspring5.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GreetingController {
    @Autowired
    private IGreetingService service;

    @PostMapping("/greeting")
    public Mono<ResponseEntity<Response>> saveGreeting(@RequestBody Greeting greeting) {
        return service.saveGreeting(greeting)
                .map(savedGreeting -> new ResponseEntity<>(new Response(200, "Greeting saved successfully"), HttpStatus.OK))
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(new Response(500, "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @GetMapping("/greeting")
    public Mono<ResponseEntity<Flux<Greeting>>> allGreeting() {
        return service.getGreeting()
                .collectList()
                .map(greetings -> new ResponseEntity<>(Flux.fromIterable(greetings), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/greetingById/{id}")
    public Mono<ResponseEntity<Response>> getGreetingById(@PathVariable int id) {
        return service.getGreetingById(id)
                .map(greeting -> new ResponseEntity<>(new Response(200, "Greeting retrieved successfully"), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(new Response(404, "Greeting not found"), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleteById/{id}")
    public Mono<ResponseEntity<Response>> deleteGreetingById(@PathVariable int id) {
        return service.deleteGreetingById(id)
                .map(deletedGreeting -> new ResponseEntity<>(new Response(204, "Greeting deleted successfully"), HttpStatus.NO_CONTENT))
                .defaultIfEmpty(new ResponseEntity<>(new Response(404, "Greeting not found"), HttpStatus.NOT_FOUND));
    }
}
