package com.alimapp.userservice.repository;

import com.alimapp.userservice.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);

    Optional<AppUser> findById(Long appUserId);

    @Query(value = "select i from AppUser i where i.id in :ids")
    List<AppUser> findAllByListOfIds(@Param("ids") List<Long> appUserIds);

}
