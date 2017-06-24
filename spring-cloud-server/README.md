Spring Cloud Config Server
==========================


# Setup

1. Import the server dependencies

```
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
```

2. Configure the port and config repo source


## Config Repository

The default repository is a GIT repo.  There is also a support for file system based.

### File System Repository

There is also a "native" profile in the Config Server that doesn’t use Git, but just loads the config files from the local classpath or file system (any static URL you want to point to with "spring.cloud.config.server.native.searchLocations"). To use the native profile just launch the Config Server with "spring.profiles.active=native".

NOTE:

Remember to use the file: prefix for file resources (the default without a prefix is usually the classpath). Just as with any Spring Boot configuration you can embed ${}-style environment placeholders, but remember that absolute paths in Windows require an extra "/", e.g. file:///${user.home}/config-repo

WARNING:

The default value of the searchLocations is identical to a local Spring Boot application (so [classpath:/, classpath:/config, file:./, file:./config]). This does not expose the application.properties from the server to all clients because any property sources present in the server are removed before being sent to the client.

TIP:

A filesystem backend is great for getting started quickly and for testing. To use it in production you need to be sure that the file system is reliable, and shared across all instances of the Config Server.
The search locations can contain placeholders for {application}, {profile} and {label}. In this way you can segregate the directories in the path, and choose a strategy that makes sense for you (e.g. sub-directory per application, or sub-directory per profile).



## Resources

|Path|Description|
|----|-----------|
|/{app}/{profile}|	Configuration data for app in Spring profile (comma-separated).|
|/{app}/{profile}/{label}|	Add a git label|
|/{app}/{profiels}{label}/{path}|	An environment-specific plain text config file (at "path")|

Note:
* label - could be branch or tag


## Relevant files

* application.yml - default spring app config, using git
* application-native.yml - spring app config using file based repo
* application-bus.yml - spring app config with cloud bus

* config/ - directory for file based repo

# API

## Management Info

http://localhost:8888/mgmt/info

## Fetching properties

Example:

http://localhost:8888/foo/development



# CLOUD BUS

## Reference

http://tech.asimio.net/2017/02/02/Refreshable-Configuration-using-Spring-Cloud-Config-Server-Spring-Cloud-Bus-RabbitMQ-and-Git.html

## Setup

1. Add libraries.  Swap AMQP implementation

  <!-- for the cloud bus -->
    <dependency>
             <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-config-monitor</artifactId>
           </dependency>
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
       </dependency>

2.  Config properties

 spring:
    bus:
      enabled: true
  rabbitmq:
    host: host_value
    port: 5672



## Endpoints

### all endpoints

Example:

http://localhost:8888/mgmt/mappings

#### Trigger Change Event

/monitor endpoint  POST

  to trigger the server that changes were made

example
```
curl -v -X POST "http://localhost:8888/monitor" -H "Content-Type: application/json" -H "X-Event-Key: repo:push" -H "X-Hook-UUID: webhook-uuid" -d '{"push": {"changes": []} }'
```


#### Simulation of trigger
```
curl -v -X POST "http://localhost:8888/monitor" -H "Content-Type: application/json" -H "X-Event-Key: repo:push" -H "X-Hook-UUID: webhook-uuid" -d '{"push": {"changes": ["user.name"]} }'
```

Demo Steps:
===========

1. Modify the property
2. Show that server changed but client did not
3. Simulate the trigger
4. Show that client changed


## Endpoint bus refresh

This is triggering the refresh from the Server endpoint.  This will then create broadcast events

### Example:
```
/bus/refresh?destination=customers:9000
```

### Example with app-profile:
```
/bus/refresh?destination=foo-development:9000
```

### Examples using curl
```
curl -X POST http://localhost:8888/mgmt/bus/refresh?destination=foo-development:1

curl -X POST http://localhost:8888/mgmt/bus/refresh?destination=foo:**

curl -X POST http://localhost:8888/mgmt/bus/refresh?destination=foo:**:**

curl -X POST http://localhost:8888/mgmt/bus/refresh?destination=foo:development:1
```

### Example with app-profile, all instances:
```
/bus/refresh?destination=foo-development:**
```


### Additional stuff

* for tracing, spring.cloud.bus.trace.enabled=true
* endpoint available at /trace



Use Cases
=========


1. Feature toggle



2. Resource Message Bundle

Read up https://github.com/spring-cloud/spring-cloud-config/blob/master/docs/src/main/asciidoc/spring-cloud-config.adoc#serving-plain-text

```
http://localhost:8888/foo/default/master/messages.properties
```
