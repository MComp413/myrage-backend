package com.mcomp.myrage.model;

import java.time.LocalDate;
import java.util.UUID;

import com.mcomp.myrage.model.enums.DocumentStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Document {
  @Id
  @Column
  @GeneratedValue
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String storagePath;

  @Column(nullable = false)
  private Integer size;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DocumentStatus status;

  @Column
  private Integer ownerId; // trocar por User owner

}
