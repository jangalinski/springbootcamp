package com.github.jangalinski.springboot.clock;

import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
public class BuzzwordClockApplication {

  public static String TOPIC = "clock";

  public static void main(String... args) {
    SpringApplication.run(BuzzwordClockApplication.class, args);
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

  @Component
  @EnableScheduling
  public static class Clock {
    @Autowired
    private EventBus eventBus;

    @Scheduled(fixedDelay = 1000L)
    public void everySecond() {
      eventBus.notify(TOPIC, Event.wrap(LocalTime.now()));
    }
  }

  @SpringUI
  @Push
  public static class ApplicationUI extends UI implements Consumer<Event<LocalTime>> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private EventBus eventBus;

    private final Label labelTime = new Label(LocalTime.now().format(dateTimeFormatter));

    @Override
    protected void init(VaadinRequest vaadinRequest) {

      labelTime.setStyleName("h1");
      final VerticalLayout layout = new VerticalLayout();
      layout.setMargin(true);
      layout.setSpacing(true);
      layout.addComponent(labelTime);
      setContent(layout);

      eventBus.on($(TOPIC), this);
    }

    @Override
    public void accept(reactor.bus.Event<LocalTime> event) {
      access(() -> labelTime.setValue(event.getData().format(dateTimeFormatter)));
    }
  }
}
