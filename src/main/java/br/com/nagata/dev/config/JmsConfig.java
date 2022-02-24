package br.com.nagata.dev.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.nagata.dev.client.OperationListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.backing-services.jms")
public class JmsConfig {

  private String hostname;
  private String virtualHost;
  private int port;
  private String username;
  private String password;
  private int threads;
  private String queue;

  @Bean
  @Qualifier("connectionFactory")
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setHost(hostname);
    connectionFactory.setVirtualHost(virtualHost);
    connectionFactory.setPort(port);
    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);

    return connectionFactory;
  }

  @Bean
  @Qualifier("devQueue")
  public Queue queue() {
    return new Queue(queue, true);
  }

  @Bean
  public SimpleMessageListenerContainer messageContainer(
      @Qualifier("connectionFactory") ConnectionFactory connectionFactory,
      @Qualifier("devQueue") Queue queue, OperationListener listener) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue);
    container.setConcurrentConsumers(threads);
    container.setMessageListener(listener);
    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
    container.setAutoStartup(true);

    return container;
  }

  @Bean
  @Qualifier("operationJmsTemplate")
  public RabbitTemplate operationJmsTemplate(
      @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }
}
