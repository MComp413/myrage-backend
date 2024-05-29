package com.mcomp.myrage.model;

import java.time.LocalDate;
import java.util.UUID;

import com.mcomp.myrage.model.enums.DocumentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
  private Integer id;
  private String name;
  private String storagePath;
  private Integer size;
  private DocumentStatus status;
  private LocalDate createdAt;
  private Integer ownerId; // trocar por User owner

}
