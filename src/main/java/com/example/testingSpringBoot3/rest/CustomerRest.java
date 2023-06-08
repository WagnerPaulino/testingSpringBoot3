package com.example.testingSpringBoot3.rest;

import com.example.testingSpringBoot3.domain.Customer;
import com.example.testingSpringBoot3.repository.CustomerRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerRest {

    private final CustomerRepository repository;
    private final ObservationRegistry registry;

    @GetMapping
    List<Customer> all(){
        return repository.findAll();
    }

    @GetMapping("/{name}")
    Customer byName(@PathVariable String name){
        Assert.state(Character.isUpperCase(name.charAt(0)), "the letter must start with capital letter!");
        return Observation.createNotStarted("byName", this.registry).observe(() -> repository.findByName(name));
    }
}
