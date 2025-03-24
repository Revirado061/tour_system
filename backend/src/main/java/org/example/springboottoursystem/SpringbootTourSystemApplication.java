package org.example.springboottoursystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootTourSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTourSystemApplication.class, args);
	}

}
