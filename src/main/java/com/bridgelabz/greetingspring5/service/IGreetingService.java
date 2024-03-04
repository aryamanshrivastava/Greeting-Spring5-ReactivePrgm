package com.bridgelabz.greetingspring5.service;

import com.bridgelabz.greetingspring5.model.Greeting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IGreetingService {
    Mono<Greeting> saveGreeting(Greeting greeting);

    Flux<Greeting> getGreeting();

    Mono<Greeting> getGreetingById(int id);

    Mono<Greeting> deleteGreetingById(int id);
}
