Spring Cloud Config Client
==========================


## Setup

1. Import the client dependencies

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.retry</groupId>
            <artifactId>spring-retry</artifactId>
        </dependency>
```

Note the 'aop' and 'retry' to handle the refresh and tolerance for connection to server

2. Configure the application name, profile and config repo source in bootstrap.properties


## Relevant files

* bootstrap.properties
    - important to set the name, profiles and the connection info the server

* application.properties - default spring app config

## Requirements

1. Spring boot 1.4+
1. Spring actuator (for http endpoints)


## Commands

### Using spring-bootrun
```
mvn spring-boot:run -Dspring.cloud.bootstrap.location=/home/user/git/spring-cloud/spring-cloud-client/src/main/resources/bootstrap.properties
```

### Tests for multiple clients
```
java -jar -Dspring.cloud.bootstrap.location=file:/home/user/git/spring-cloud/spring-cloud-client/config/ -Dspring.config.location=file:/home/user/git/spring-cloud/spring-cloud-client/config/ -DINSTANCE_INDEX=1 -DINSTANCE_PORT=9080 spring-cloud-client-1.0.0-SNAPSHOT.jar
java -jar -Dspring.cloud.bootstrap.location=file:/home/user/git/spring-cloud/spring-cloud-client/config/ -Dspring.config.location=file:/home/user/git/spring-cloud/spring-cloud-client/config/ -DINSTANCE_INDEX=2 -DINSTANCE_PORT=9081 spring-cloud-client-1.0.0-SNAPSHOT.jar
```

Note: change the search pattern for which directory to look bootstrap.properties for

spring.cloud.bootstrap.location - classpath:/, file:/

## Resources

|Path|Description|
|----|-----------|
|/{app}/{profile}|	Configuration data for app in Spring profile (comma-separated).|
|/{app}/{profile}/{label}|	Add a git label|
|/{app}/{profiels}{label}/{path}|	An environment-specific plain text config file (at "path")|

Note:
* label - could be branch or tag


# API

## Management Info

http://localhost:8888/mgmt/info

## Fetching properites

http://localhost:8080/user


@RefreshScope
=============

A Spring @Bean that is marked as @RefreshScope will get special treatment when there is a configuration change. This addresses the problem of stateful beans that only get their configuration injected when they are initialized.

Refresh scope beans are lazy proxies that initialize when they are used (i.e. when a method is called), and the scope acts as a cache of initialized values. To force a bean to re-initialize on the next method call you just need to invalidate its cache entry.


# Endpoints

For a Spring Boot Actuator application there are some additional management endpoints:

* POST to /env to update the Environment and rebind @ConfigurationProperties and log levels
* /refresh for re-loading the boot strap context and refreshing the @RefreshScope beans
* /restart for closing the ApplicationContext and restarting it (disabled by default)
* /pause and /resume for calling the Lifecycle methods (stop() and start() on the ApplicationContext)


## To trigger the client refresh:
```
curl -X POST http://localhost:8080/refresh
```

if the management path is '/mgmt'

```
curl -X POST http://localhost:8080/mgmt/refresh
```

Of course this works only for 1 node and the client has to trigger the request




CLOUD BUS
=========

# Reference

http://tech.asimio.net/2017/02/02/Refreshable-Configuration-using-Spring-Cloud-Config-Server-Spring-Cloud-Bus-RabbitMQ-and-Git.html

# Setup

1. Add libraries.  Swap AMQP implementation
```
        <!-- for spring cloud bus -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>

            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
        <!-- end for spring cloud bus -->
```

2.  AMQP Config properties

 spring:
  rabbitmq:
    host: host_value
    port: 5672


Use Cases
=========

1. Feature toggle

```
http://localhost:8080/features
```

2. Resource Message Bundle

```
http://localhost:8888/foo/default/master/messages.properties
```
