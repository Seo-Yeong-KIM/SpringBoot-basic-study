package com.example.testprojcet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing // Base Entity의 어노테이션을 사용하려면 필요
public class TestprojcetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestprojcetApplication.class, args);
	}

}
