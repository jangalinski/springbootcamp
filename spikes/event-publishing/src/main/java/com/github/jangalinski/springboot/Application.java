package com.github.jangalinski.springboot;

import static org.slf4j.LoggerFactory.getLogger;

import org.glassfish.jersey.jaxb.internal.XmlCollectionJaxbProvider;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class Application implements CommandLineRunner, Ordered {

    private static final int ORDER = 10;
    private final Logger logger = getLogger(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private DataSource dataSource;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private Runit runit;

    @Autowired
    private Configuration configuration;



    @Override
    public void run(String... strings) throws Exception {
        logger.error("started {}", ORDER);

        logger.info(configuration.toString());
    }

    @RequestMapping("/")
    public String test() throws SQLException {
        return dataSource.getConnection().toString();
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}

