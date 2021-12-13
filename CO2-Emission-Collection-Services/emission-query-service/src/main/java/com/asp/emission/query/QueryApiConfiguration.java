package com.asp.emission.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages="com.asp")
public class QueryApiConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(QueryApiConfiguration.class, args);
    }
}
