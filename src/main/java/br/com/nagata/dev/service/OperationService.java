package br.com.nagata.dev.service;

import java.io.IOException;
import org.springframework.amqp.AmqpException;
import com.fasterxml.jackson.databind.JsonNode;

public interface OperationService {

  void sendMessage(JsonNode message) throws AmqpException, IOException;
}
