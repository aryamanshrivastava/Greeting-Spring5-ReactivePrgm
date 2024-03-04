package com.bridgelabz.greetingspring5.repository;

import com.bridgelabz.greetingspring5.model.Greeting;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends R2dbcRepository<Greeting,Integer> {
}
