package com.alimapp.userservice.repository;

import com.alimapp.userservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    Optional<AppUser> findById(Long appUserId);

}
