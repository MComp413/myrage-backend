package com.mcomp.myrage.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

  ACTIVE("Ativo"),
  CLOSED("Encerrado");

  private final String label;

  @Override
  public String toString() {
    return this.getLabel();
  }
}
