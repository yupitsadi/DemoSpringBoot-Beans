# Spring Boot Bean Configuration Demo

This project demonstrates different ways to create and configure self-defined beans in a Spring Boot application.

## Project Overview

This demo showcases:
- XML-based bean configuration
- Constructor injection
- Property injection
- Bean dependencies
- Spring Boot integration with traditional Spring XML configuration

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/myApp/demo/
│   │       ├── bean/
│   │       │   └── UserConfig.java          # Simple POJO bean
│   │       ├── service/
│   │       │   ├── UserService.java         # Service with constructor injection
│   │       │   └── OrderService.java        # Service with multiple dependencies
│   │       └── MySpringBootApplication.java # Main Spring Boot application
│   └── resources/
│       └── applicationContext.xml           # XML bean configuration
```

## Bean Configuration Methods

### 1. XML-Based Bean Configuration

The project uses `applicationContext.xml` to define beans:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
         http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- Simple POJO bean with property injection -->
    <bean id="userConfig" class="com.myApp.demo.bean.UserConfig">
        <property name="name" value="Aditya"/>
        <property name="className" value="Spring Boot"/>
    </bean>

    <!-- Service bean with constructor injection -->
    <bean id="userService" class="com.myApp.demo.service.UserService">
        <constructor-arg ref="userConfig"/>
    </bean>

    <!-- Service bean with multiple constructor arguments -->
    <bean id="orderService" class="com.myApp.demo.service.OrderService">
        <constructor-arg ref="userService"/>
        <constructor-arg ref="userConfig"/>
    </bean>

</beans>
```

### 2. Bean Classes

#### UserConfig.java - Simple POJO Bean
```java
package com.myApp.demo.bean;

public class UserConfig {
    private String name;
    private String className;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
}
```

#### UserService.java - Service with Constructor Injection
```java
package com.myApp.demo.service;

import com.myApp.demo.bean.UserConfig;

public class UserService {
    private UserConfig userConfig;

    public UserService(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public void printUserDetails() {
        System.out.println("Name: " + userConfig.getName());
        System.out.println("Class: " + userConfig.getClassName());
    }
}
```

#### OrderService.java - Service with Multiple Dependencies
```java
package com.myApp.demo.service;

import com.myApp.demo.bean.UserConfig;

public class OrderService {
    private UserConfig userConfig;
    private UserService userService;

    public OrderService(UserService userService, UserConfig userConfig) {
        this.userService = userService;
        this.userConfig = userConfig;
    }
}
```

### 3. Spring Boot Integration

The main application class demonstrates how to use XML configuration with Spring Boot:

```java
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
        
        // Load XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        // Retrieve beans
        UserService userService = (UserService) context.getBean("userService");
    }
}
```

## How to Run

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

## Alternative Bean Configuration Methods

### 1. Java Configuration (Recommended for Spring Boot)

Instead of XML, you can use Java configuration:

```java
@Configuration
public class AppConfig {
    
    @Bean
    public UserConfig userConfig() {
        UserConfig config = new UserConfig();
        config.setName("Aditya");
        config.setClassName("Spring Boot");
        return config;
    }
    
    @Bean
    public UserService userService(UserConfig userConfig) {
        return new UserService(userConfig);
    }
    
    @Bean
    public OrderService orderService(UserService userService, UserConfig userConfig) {
        return new OrderService(userService, userConfig);
    }
}
```

### 2. Component Scanning with Annotations

You can also use annotations for automatic bean detection:

```java
@Component
public class UserConfig {
    // ... properties and methods
}

@Service
public class UserService {
    @Autowired
    private UserConfig userConfig;
    // ... methods
}
```

## Key Concepts Demonstrated

1. **Bean Definition**: How to define beans in XML configuration
2. **Dependency Injection**: Constructor-based and property-based injection
3. **Bean References**: How beans can reference other beans
4. **Spring Boot Integration**: Using XML configuration with Spring Boot
5. **Bean Lifecycle**: How Spring manages bean creation and dependencies

## Best Practices

1. **Use Java Configuration** for new Spring Boot projects
2. **Prefer Constructor Injection** over field injection for required dependencies
3. **Keep beans stateless** when possible
4. **Use meaningful bean names** and IDs
5. **Document bean dependencies** clearly

## Dependencies

- Spring Boot 3.5.3
- Java 21
- Gradle build system

## Contributing

Feel free to contribute by adding more bean configuration examples or improving the documentation.

## License

This project is for educational purposes. 