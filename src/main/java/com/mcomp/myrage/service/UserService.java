package com.mcomp.myrage.service;

import com.mcomp.myrage.model.UserData;
import com.mcomp.myrage.model.enums.UserStatus;
import com.mcomp.myrage.repository.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserService {
  private static final Integer freeQueryLimit = 20;

  @Autowired
  private UserDataRepository userDataRepository;


  public Optional<UserData> retrieve(String email) {
    return userDataRepository.findByEmail(email);
  }

  public UserData retrieveOrCreate(String email) {
    Optional<UserData> preexistingUser = retrieve(email);
    return preexistingUser.orElseGet(() -> userDataRepository.saveAndFlush(UserData.builder()
        .id(UUID.randomUUID())
        .email(email)
        .build()));
  }

  public Boolean isOverQueryLimit(UserData userData) {
    Instant lastQueryAt = userData.getLastQueryAt();
    if (lastQueryAt == null) return false;
    Instant today = Instant.now();
    if (ChronoUnit.DAYS.between(today, lastQueryAt) >= 1) {
      return false;
    }
    return userData.getRecentQueriesCount() >= UserService.freeQueryLimit;
  }

  public void markQuery(UserData userData) {
    userData.setLastQueryAt(Instant.now());
    userData.setRecentQueriesCount(userData.getRecentQueriesCount() + 1);
    userDataRepository.saveAndFlush(userData);
  }

}
