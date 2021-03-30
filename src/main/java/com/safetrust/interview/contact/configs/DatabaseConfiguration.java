package com.safetrust.interview.contact.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories( "com.safetrust.interview.contact.repositories" )
@EnableTransactionManagement
public class DatabaseConfiguration {
}
