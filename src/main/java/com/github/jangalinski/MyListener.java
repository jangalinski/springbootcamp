package com.github.jangalinski;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

public class MyListener extends EventPublishingRunListener {

    private final Logger logger = getLogger(this.getClass());

    public MyListener(SpringApplication application, String[] args) {
        super(application, args);

        logger.error("====> " + application.getClass());
    }

    @Override
    public void started() {
        logger.error("============================== started");

        for (int i=1; i<4; i++) {
            try {
                Thread.sleep(i*1000L);
                logger.error("==== waited for {}",i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        logger.error("============================== environmentPrepared: {}", configurableEnvironment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        logger.error("============================== contectPrepared: {}", configurableApplicationContext);

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
        logger.error("============================== contextLoaded: {}", configurableApplicationContext);
    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
        logger.error("============================== finished: {} {}", configurableApplicationContext, throwable);
    }
}
