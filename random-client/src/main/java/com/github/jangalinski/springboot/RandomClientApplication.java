package com.github.jangalinski.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class RandomClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(RandomClientApplication.class, args);
  }

  @Autowired
  private RandomClient randomClient;

  @RequestMapping(value = "/random", method = RequestMethod.GET)
  public String getRandom() {
    return "random-" + randomClient.getRandom();
  }


  @FeignClient("random-service")
  interface RandomClient {

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    String getRandom();
  }


}
