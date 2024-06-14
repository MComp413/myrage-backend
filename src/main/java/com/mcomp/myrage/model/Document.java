package com.mcomp.myrage.model;

import java.time.Instant;
import java.util.UUID;

import com.mcomp.myrage.model.enums.DocumentStatus;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"integrity_hash", "owner_id"})
})
public class Document {
  @Id
  @Column
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String storagePath;

  @Column(nullable = false, name="integrity_hash")
  private String integrityHash;

  @Column(nullable = false)
  private Integer size;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DocumentStatus status;

  @Column(nullable = false)
  @CreatedDate
  private Instant createdAt;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(nullable = false, name="owner_id")
  private UserData owner;

}
