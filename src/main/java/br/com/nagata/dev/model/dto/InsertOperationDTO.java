package br.com.nagata.dev.model.dto;

import java.time.LocalDate;
import br.com.nagata.dev.model.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InsertOperationDTO {

  private MessageTypeEnum messageType;
  private String reference;
  private String customerCode;
  private LocalDate negotiationDate;
}
