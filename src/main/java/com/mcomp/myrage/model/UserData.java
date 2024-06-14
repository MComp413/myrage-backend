package com.mcomp.myrage.model;

import com.mcomp.myrage.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserData {
  @Id
  @Column
  @GeneratedValue
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  @Builder.Default
  private UserStatus status = UserStatus.ACTIVE;

  @Column
  private Instant lastQueryAt;

  @Column(nullable = false)
  @Builder.Default
  private Integer recentQueriesCount = 0;

  @Column(nullable = false)
  @Builder.Default
  private Integer documentsStoredCount = 0;
}
