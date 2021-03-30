package com.safetrust.interview.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * ContactApplication is used to initialize application
 *
 */
@SpringBootApplication
@ComponentScan( { "com.safetrust.interview.contact" } )
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

}
