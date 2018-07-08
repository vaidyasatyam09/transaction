package com.n26.transaction_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionAppApplication.class, args);
	}
}
