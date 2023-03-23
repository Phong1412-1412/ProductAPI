package com.example.day4_productmvc;

import com.example.day4_productmvc.config.CheckConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class Day4ProductMvcApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(Day4ProductMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CheckConfig config = new CheckConfig(dataSource);
        if(config.checkConnectDB(dataSource)) {
            System.out.println("Kết nối thành công đến database.");
        }
        else {
            System.out.println("Kết nối thất bại đến database.");
        }
    }
}
