package br.com.nagata.dev.model.dto;

import br.com.nagata.dev.model.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteOperationDTO {

  private MessageTypeEnum messageType;
  private String reference;
}
