package br.com.nagata.dev.service.impl;

import java.io.IOException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import br.com.nagata.dev.service.OperationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OperationServiceImpl implements OperationService {

  private RabbitTemplate jmsTemplate;
  private Queue queue;

  @Autowired
  public OperationServiceImpl(@Qualifier("operationJmsTemplate") RabbitTemplate jmsTemplate,
      @Qualifier("devQueue") Queue queue) {
    this.jmsTemplate = jmsTemplate;
    this.queue = queue;
  }

  @Override
  public void sendMessage(JsonNode message) throws AmqpException, IOException {
    log.info("sending message...");
    jmsTemplate.send(queue.getName(), new Message(message.toString().getBytes()));
    log.info("message sent successfully!");
  }
}
