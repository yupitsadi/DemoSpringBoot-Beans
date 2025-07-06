package com.myApp.demo;

import com.myApp.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
	}

}
