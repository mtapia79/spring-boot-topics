package com.example.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.example.demo.dashboard.GithubProperties;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperties.class)
@EnableCaching
public class TestSpringBootApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}
}
