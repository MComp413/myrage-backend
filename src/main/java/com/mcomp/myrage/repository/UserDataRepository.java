package com.mcomp.myrage.repository;

import com.mcomp.myrage.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDataRepository extends JpaRepository<UserData, UUID> {

  UserData saveAndFlush(UserData userData);

  Optional<UserData> findByEmail(String email);
}
