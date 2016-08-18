package com.github.jangalinski.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class MainApplication implements CommandLineRunner, ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @Override
    public void run(final String... strings) throws Exception {
        logger.info("=========== run");
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        new BeanNamesLogger().accept(context);
    }
}
