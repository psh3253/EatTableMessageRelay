package com.astar.eattable.messagerelay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EatTableMessageRelayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatTableMessageRelayApplication.class, args);
    }

}
