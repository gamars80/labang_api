package com.mobin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	

	 
	 @Bean
	 public MessageSource messageSource() {
		 ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:/messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        messageSource.setCacheSeconds(10);
	        return messageSource;
	 }
}

