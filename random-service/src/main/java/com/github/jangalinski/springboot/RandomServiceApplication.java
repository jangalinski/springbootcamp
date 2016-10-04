package com.github.jangalinski.springboot;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class RandomServiceApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(RandomServiceApplication.class)
      .properties("server.port=0")
      .properties("spring.application.name=random-service")
      .run(args);
  }


  @RequestMapping(value = "/random", method = RequestMethod.GET)
  public String getRandomUuid() {
    return UUID.randomUUID().toString();
  }

  @Bean
  public InfoContributor infoContributor() {
    return info -> info.withDetail("random", "/random");
  }

}
