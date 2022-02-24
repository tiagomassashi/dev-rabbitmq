package br.com.nagata.dev.model.dto;

import br.com.nagata.dev.model.enums.MessageTypeEnum;
import br.com.nagata.dev.model.enums.OperationStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateOperationDTO {

  private MessageTypeEnum messageType;
  private String reference;
  private OperationStatusEnum operationStatus;
}
