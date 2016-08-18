package com.github.jangalinski.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class RandomServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RandomServiceApplication.class, args);
  }

  @RequestMapping(value = "/random", method = RequestMethod.GET)
  public String getRandomUuid() {
    return UUID.randomUUID().toString();
  }

}
