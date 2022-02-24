package br.com.nagata.dev.controller;

import java.io.IOException;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import br.com.nagata.dev.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(value = "/api/v1/operation", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperationController {

  private OperationService service;

  @Autowired
  public OperationController(OperationService service) {
    this.service = service;
  }

  @PatchMapping
  @Operation(summary = "Send message on RabbitMQ")
  public ResponseEntity<?> sendMessage(@RequestBody JsonNode message)
      throws AmqpException, IOException {
    service.sendMessage(message);
    return ResponseEntity.accepted().body(null);
  }
}
