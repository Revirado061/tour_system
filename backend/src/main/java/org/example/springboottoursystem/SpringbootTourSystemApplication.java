package org.example.springboottoursystem;

import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboottoursystem.service.DiaryService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "org.example.springboottoursystem.mapper", annotationClass = Mapper.class)
public class SpringbootTourSystemApplication implements CommandLineRunner {

	@Resource
	private DiaryService diaryService;

	@Autowired
	public SpringbootTourSystemApplication(DiaryService diaryService) {
		this.diaryService = diaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTourSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 加载所有日记数据到 Elasticsearch
		diaryService.loadAllDiariesToElasticsearch();
		System.out.println("All diaries loaded to Elasticsearch.");
	}
}
