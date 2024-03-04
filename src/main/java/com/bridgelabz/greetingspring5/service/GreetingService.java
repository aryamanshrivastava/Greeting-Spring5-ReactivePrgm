package com.bridgelabz.greetingspring5.service;


import com.bridgelabz.greetingspring5.model.Greeting;
import com.bridgelabz.greetingspring5.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GreetingService implements IGreetingService {
    @Autowired
    private GreetingRepository repo;

    @Override
    public Mono<Greeting> saveGreeting(Greeting greeting){
        return repo.save(greeting);
    }

    @Override
    public Flux<Greeting> getGreeting(){
        return repo.findAll();
    }

    @Override
    public Mono<Greeting> getGreetingById(int id){
        return repo.findById(id);
    }

}

