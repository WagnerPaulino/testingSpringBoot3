package com.example.testingSpringBoot3.client;

import com.example.testingSpringBoot3.domain.Customer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface GithubClient {

    @GetExchange("/users/{username}")
    Flux<Customer> byUsername(@PathVariable String username);
}
