package com.example.hbase.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jackie wang
 * @since 2021/2/5 16:13
 */
@SpringBootApplication(scanBasePackages = {"com.example.hbase.consumer"})
//@SpringBootApplication(scanBasePackages = {"com.example.hbase.stater","com.example.hbase.consumer"})
public class HbaseConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(HbaseConsumerApp.class, args);
    }

}
