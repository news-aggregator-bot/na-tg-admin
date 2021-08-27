package bepicky.bot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"bepicky.bot.core", "bepicky.bot.admin"})
public class AdminTgApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminTgApp.class, args);
    }
}
