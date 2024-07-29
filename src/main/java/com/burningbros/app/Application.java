package com.burningbros.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		String[] beanNames = context.getBeanDefinitionNames();
		System.out.println("Number of beans loaded: " + beanNames.length);
	}
	
}
