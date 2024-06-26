package com.mcomp.myrage.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DocumentStatus {

  QUEUED("Queued"),
  PROCESSING("Processing"),
  STORED("Stored"),
  REMOVED("Removed");

  private final String label;

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
