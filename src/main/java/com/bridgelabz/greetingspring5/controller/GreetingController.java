package com.bridgelabz.greetingspring5.controller;


import com.bridgelabz.greetingspring5.model.Greeting;
import com.bridgelabz.greetingspring5.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GreetingController {
    @Autowired
    private IGreetingService service;

    @PostMapping("/greeting")
    public Mono<Greeting> saveGreeting(@RequestBody Greeting greeting){
        return service.saveGreeting(greeting);
    }
    @GetMapping("/greeting")
    public Flux<Greeting> allGreeting(){
        return service.getGreeting();
    }
    @GetMapping("/greetingById/{id}")
    public Mono<Greeting> getGreetingById(@PathVariable int id) {
        return service.getGreetingById(id);
    }
}
