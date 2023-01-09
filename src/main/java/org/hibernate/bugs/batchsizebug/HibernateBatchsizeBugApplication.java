package org.hibernate.bugs.batchsizebug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateBatchsizeBugApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateBatchsizeBugApplication.class, args);
    }

}
