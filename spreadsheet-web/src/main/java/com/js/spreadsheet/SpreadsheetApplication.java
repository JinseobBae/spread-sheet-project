package com.js.spreadsheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpreadsheetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpreadsheetApplication.class, args);
    }

}
