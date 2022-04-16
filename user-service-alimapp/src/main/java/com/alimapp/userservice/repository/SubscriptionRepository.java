package com.alimapp.userservice.repository;

import com.alimapp.userservice.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findAllByAppUserId(Long appUserId);
    List<Subscription> findAllByTargetAppUserId(Long targetAppUserId);
}
