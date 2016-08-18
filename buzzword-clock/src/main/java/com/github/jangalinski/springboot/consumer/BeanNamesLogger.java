package com.github.jangalinski.springboot.consumer;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Consumer;

@Component
public class BeanNamesLogger implements Consumer<ApplicationContext> {

    private final Logger logger = getLogger(this.getClass());

    @Override
    public void accept(final ApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.asList(context.getBeanDefinitionNames()).stream().sorted().forEach(n -> logger.info(n));
    }
}
