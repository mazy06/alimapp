package com.alimapp.userservice.service;

import com.alimapp.userservice.model.AppUser;
import com.alimapp.userservice.model.Subscription;
import com.alimapp.userservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AppUserService appUserService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public void createSubscription(Subscription subscription) {
        if (!appUserService.exist(subscription.getAppUserId()) || !appUserService.exist(subscription.getTargetAppUserId())) {
            throw new NoSuchElementException("User or target user doesn't exist");
        }
        subscriptionRepository.save(subscription);
    }

    @Override
    public List<AppUser> getAppUserSubscription(Long appUserId) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByTargetAppUserId(appUserId);
        List<Long> ids = subscriptions.stream().map(Subscription::getTargetAppUserId).toList();
        return appUserService.getAllByIds(ids);
    }

    @Override
    public List<AppUser> getAppUserSubscribers(Long appUserId) {
        List<Subscription> subscriptions = subscriptionRepository.findAllByAppUserId(appUserId);
        List<Long> ids = subscriptions.stream().map(Subscription::getAppUserId).toList();
        return appUserService.getAllByIds(ids);
    }
}
