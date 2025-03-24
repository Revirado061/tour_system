package org.example.springboottoursystem;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "org.example.springboottoursystem.mapper", annotationClass = Mapper.class)
public class SpringbootTourSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTourSystemApplication.class, args);
	}

}
