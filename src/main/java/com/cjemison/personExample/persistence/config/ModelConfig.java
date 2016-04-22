package com.cjemison.personExample.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.cjemison.personExample.persistence.repository")
@EnableTransactionManagement
public class ModelConfig {
}
