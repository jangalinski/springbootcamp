package com.github.jangalinski.springboot;

import static org.slf4j.LoggerFactory.getLogger;

import com.github.jangalinski.springboot.consumer.BeanNamesLogger;
import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication
public class MainApplication extends SpringBootServletInitializer implements CommandLineRunner, ApplicationContextAware {

    private final Logger logger = getLogger(this.getClass());

    public static void main(String... args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
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
