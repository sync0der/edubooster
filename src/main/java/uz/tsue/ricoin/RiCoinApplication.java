package uz.tsue.ricoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class RiCoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiCoinApplication.class, args);
    }

}
