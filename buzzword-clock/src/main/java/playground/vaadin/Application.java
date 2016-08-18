package io.github.jangalinski.playground.vaadin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;

import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@EnableScheduling
public class Application {

  public static final String TOPIC = "test.topic";
  private static final Logger logger = getLogger(Application.class);

  public static void main(final String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  public static class ReactorConfiguration {

    static {
      Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    public EventBus eventBus() {
      return EventBus.create(Environment.get());
    }
  }

  @Autowired
  private EventBus eventBus;

  @Scheduled(fixedDelay = 1000L)
  public void everySecond() {
    eventBus.notify(TOPIC, Event.wrap(LocalTime.now()));
  }

}
