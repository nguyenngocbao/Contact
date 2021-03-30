package com.safetrust.interview.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * ContactApplication is used to initialize application
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan( { "com.safetrust.interview.contact" } )
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

}
