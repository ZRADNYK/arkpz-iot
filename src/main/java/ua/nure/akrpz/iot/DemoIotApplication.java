package ua.nure.akrpz.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoIotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoIotApplication.class, args);
    }

}
