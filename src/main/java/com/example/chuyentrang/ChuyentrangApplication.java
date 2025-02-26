package com.example.chuyentrang;

import com.example.chuyentrang.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ChuyentrangApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChuyentrangApplication.class, args);

	}




}
