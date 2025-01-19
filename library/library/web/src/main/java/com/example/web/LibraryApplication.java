package com.example.web;

import com.example.service.DataUpdateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.service", "com.example.core", "com.example.web"})
@EnableJpaRepositories(basePackages = "com.example.core.repository")
@EntityScan(basePackages = "com.example.core.entity")
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void updateDataOnStartup(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        DataUpdateService dataUpdateService = context.getBean(DataUpdateService.class);
        dataUpdateService.updateBooksFromApi();
    }

}
