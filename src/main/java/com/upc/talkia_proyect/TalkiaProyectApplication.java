package com.upc.talkia_proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TalkiaProyectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalkiaProyectApplication.class, args);
    }

}
