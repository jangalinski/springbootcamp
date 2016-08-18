package com.github.jangalinski.springboot;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.bus.EventBus;
import reactor.bus.selector.Selectors;
import reactor.fn.Consumer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.github.jangalinski.springboot.Application.TOPIC;
import static org.slf4j.LoggerFactory.getLogger;
import static reactor.bus.selector.Selectors.$;

@Theme("valo")
@SpringUI
@Push
public class ApplicationUI  extends UI {

  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final Label labelTime = new Label("???");

  @Autowired
  private EventBus eventBus;

  @Override
  protected void init(VaadinRequest vaadinRequest) {

    final VerticalLayout layout = new VerticalLayout();
    layout.setMargin(true);
    layout.setSpacing(true);
    layout.addComponent(labelTime);
    setContent(layout);

    eventBus.on(Selectors.$(TOPIC), new Consumer<reactor.bus.Event<LocalTime>>() {
      @Override
      public void accept(reactor.bus.Event<LocalTime> event) {
        access(() -> labelTime.setValue(event.getData().format(dateTimeFormatter)));
      }
    });
  }
}
