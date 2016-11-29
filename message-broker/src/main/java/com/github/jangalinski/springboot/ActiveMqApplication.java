package com.github.jangalinski.springboot;

import javax.jms.ConnectionFactory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.style.ToStringCreator;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import lombok.extern.slf4j.Slf4j;

/**
 * "https://github.com/spring-guides/gs-messaging-jms"
 */
@SpringBootApplication
@EnableJms
@Slf4j
public class ActiveMqApplication implements ApplicationRunner {

  public static void main(String[] args) {
    SpringApplication.run(ActiveMqApplication.class, args);
  }

  @Bean
  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    // This provides all boot's default to this factory, including the message converter
    configurer.configure(factory, connectionFactory);
    // You could still override some of Boot's default if necessary.
    return factory;
  }

  /**
   * Serialize message content to json using TextMessage
   * @return messageConverter
   */
  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");

    return converter;
  }

  @Autowired
  private JmsTemplate jmsTemplate;

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    log.info("args={}", ToStringBuilder.reflectionToString(args, ToStringStyle.SHORT_PREFIX_STYLE));
    // Send a message with a POJO - the template reuse the message converter
    log.info("Sending an email message.");
    jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
  }
}
