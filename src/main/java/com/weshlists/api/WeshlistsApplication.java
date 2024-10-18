package com.weshlists.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.weshlists.api.config.CorsConfig;


@SpringBootApplication
@Import(CorsConfig.class)
public class WeshlistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeshlistsApplication.class, args);
	}

}
