package ru.ambaco.cmr.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminGenerator implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("creation de roles");
    }
}
