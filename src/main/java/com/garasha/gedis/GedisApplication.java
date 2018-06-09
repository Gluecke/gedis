package com.garasha.gedis;

import com.garasha.gedis.email.Email;
import com.garasha.gedis.email.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class GedisApplication implements ApplicationRunner {

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final Random rand = new Random();
        final List<Email> emails = Stream.of(
                new Email(UUID.randomUUID().toString(), "test|" + rand.nextInt(), "test@gmail.com"),
                new Email(UUID.randomUUID().toString(), "foobar|" + rand.nextInt(), "foo@gmail.com"))
                .collect(Collectors.toList());

        emailRepository.saveAll(emails);
    }

    public static void main(String[] args) {
        SpringApplication.run(GedisApplication.class, args);
    }
}
