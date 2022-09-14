package ru.job4j.urlshortcut;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import ru.job4j.urlshortcut.filter.PasswordGenerator;

import javax.sql.DataSource;

@SpringBootApplication
public class Job4jUrlShortcutApplication {
    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordGenerator passwordGenerator() {
        return new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .usePunctuation(true)
                .build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jUrlShortcutApplication.class, args);
    }
}