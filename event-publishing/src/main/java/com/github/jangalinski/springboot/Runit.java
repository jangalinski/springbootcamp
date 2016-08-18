package com.github.jangalinski.springboot;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class Runit implements CommandLineRunner,Ordered{

    private static final int ORDER = 20;
    private final Logger logger = getLogger(this.getClass());

    @Override
    public void run(String... strings) throws Exception {
        logger.error("running {}", ORDER);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
