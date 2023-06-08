package com.example.testingSpringBoot3;

import com.example.testingSpringBoot3.client.GithubClient;
import com.example.testingSpringBoot3.domain.Customer;
import com.example.testingSpringBoot3.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Arrays;

@SpringBootApplication
public class TestingSpringBoot3Application {

    public static void main(String[] args) {
        SpringApplication.run(TestingSpringBoot3Application.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(CustomerRepository repository, GithubClient githubClient) {
        return event -> {
            repository.saveAll(Arrays.asList(new Customer(1L, "Wagner"), new Customer(2L, "Alice"), new Customer(3L, "Fatima")));
            githubClient.byUsername("WagnerPaulino").subscribe(System.out::println);
        };
    }

    @Bean
    GithubClient githubClient(WebClient.Builder builder) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(builder.baseUrl("https://api.github.com/").build()))
                .build()
                .createClient(GithubClient.class);
    }

}
