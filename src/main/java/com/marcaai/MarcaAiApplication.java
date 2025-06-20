package com.marcaai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MarcaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarcaAiApplication.class, args);
	}

}
