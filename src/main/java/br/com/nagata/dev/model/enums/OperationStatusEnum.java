package br.com.nagata.dev.model.enums;

import lombok.Getter;

@Getter
public enum OperationStatusEnum {
  STARTED, PROCESSING, ERROR, FINISHED;
}
