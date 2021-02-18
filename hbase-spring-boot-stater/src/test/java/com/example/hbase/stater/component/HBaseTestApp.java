package com.example.hbase.stater.component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jackie wang
 * @since 2021/2/2 9:39
 */
@SpringBootApplication(scanBasePackages = {"com.example.hbase.stater"})
public class HBaseTestApp {

    public static void main(String[] args) {
        SpringApplication.run(HBaseTestApp.class, args);
    }

}
