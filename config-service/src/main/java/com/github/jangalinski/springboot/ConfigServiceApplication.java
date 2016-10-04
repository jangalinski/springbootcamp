package com.github.jangalinski.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigServiceApplication.class, args);
  }

  @Bean
  InfoContributor infoContributor() {
    return builder -> {

      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      try {
        for (Resource r : resolver.getResources("classpath:configuration/*.yml")) {
          builder.withDetail(r.getFilename(), "/" + r.getFilename().replaceAll("\\.yml","") + "/native");
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    };

  }

}
