package br.com.nagata.dev.client;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.nagata.dev.model.dto.DeleteOperationDTO;
import br.com.nagata.dev.model.dto.InsertOperationDTO;
import br.com.nagata.dev.model.dto.UpdateOperationDTO;
import br.com.nagata.dev.model.enums.MessageTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OperationListener implements MessageListener {

  private ObjectMapper mapper;

  @Autowired
  public OperationListener(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void onMessage(Message message) {
    log.info("starting message processing...");

    try {
      JsonNode jsonMessage = mapper.readValue(message.getBody(), JsonNode.class);
      log.info("message: " + jsonMessage.toString());

      switch (MessageTypeEnum.valueOf(jsonMessage.get("messageType").asText())) {
        case INSERT:
          InsertOperationDTO insertDto = mapper.convertValue(jsonMessage, InsertOperationDTO.class);
          log.info(insertDto.toString());
          break;
        case UPDATE:
          UpdateOperationDTO updateDto = mapper.convertValue(jsonMessage, UpdateOperationDTO.class);
          log.info(updateDto.toString());
          break;
        case DELETE:
          DeleteOperationDTO deleteDto = mapper.convertValue(jsonMessage, DeleteOperationDTO.class);
          log.info(deleteDto.toString());
          break;
      }
      log.info("message processing completed successfully!");
    } catch (Exception e) {
      log.error("error converting message: {}", e.getMessage());
    }
  }
}
