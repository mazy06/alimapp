package com.alimapp.userservice.service;

import com.alimapp.userservice.model.AppUser;
import com.alimapp.userservice.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    /**
     * create a subscription
     * @param subscription
     */
    void createSubscription(Subscription subscription);

    /**
     * List of subscription users
     * @param appUserId
     * @return
     */
    List<AppUser> getAppUserSubscription(Long appUserId);

    /**
     * List of subscribers users
     * @param appUserId
     * @return
     */
    List<AppUser> getAppUserSubscribers(Long appUserId);
}
