Introduction
============

This is a demonstration of Spring Cloud Config, using client and server.  Afterwards, we will also show the usage of Spring Cloud Bus.

Modules

1. spring-cloud-server - using a the server configuration
1. spring-cloud-client - client that is connected to the server

Demo Sequence
=============

1. using Server only
1. connect a single client to server
1. add a bus with multiple clients

______________

Rabbit MQ setup
===============

This is part of the Spring Cloud Bus

# Commands

## Enable GUI

```sudo rabbitmq-server enable rabbitmq_management```

## Restart the server

```sudo service rabbitmq-server restart```

# GUI

## Sample URL

http://localhost:15672/

## Default Credentials

user: guest
password: guest



References
==========

* http://www.baeldung.com/spring-cloud-bootstrapping
* http://zoltanaltfatter.com/2016/06/30/centralized-configuration-with-spring-cloud-config/
* https://nofluffjuststuff.com/magazine/2016/06/cloud_native_spring_configuring_microservices
* http://cloud.spring.io/spring-cloud-static/spring-cloud-config/1.3.1.RELEASE/



# TODO

* Security
* PCF deployment

