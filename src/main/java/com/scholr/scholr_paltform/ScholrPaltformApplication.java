package com.scholr.scholr_paltform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScholrPaltformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScholrPaltformApplication.class, args);
	}

}
