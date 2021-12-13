package com.asp.emission.command.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@Configuration
@ComponentScan("com.asp.emission")
@EnableMongoRepositories(basePackages = "com.asp.emission")
public class AppConfig {
}
