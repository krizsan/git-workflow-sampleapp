package se.ivankrizsan.spring.hellowebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello World reactive Spring Boot web application.
 *
 * @author Ivan Krizsan
 */
@SpringBootApplication
public class HelloWebappApplication {

    /**
     * Main method used to start the application.
     *
     * @param args Command line arguments. Not used.
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloWebappApplication.class, args);
    }
}
