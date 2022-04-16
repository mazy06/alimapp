package com.alimapp.userservice.controller;

import com.alimapp.userservice.model.AppUser;
import com.alimapp.userservice.model.Subscription;
import com.alimapp.userservice.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/subscriber/{targetAppUserId}")
    public ResponseEntity<List<AppUser>> getUserSubscribers(@PathVariable Long targetAppUserId) {
        return ResponseEntity.ok(subscriptionService.getAppUserSubscribers(targetAppUserId));
    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<List<AppUser>> getUserSubscriptions(@PathVariable Long appUserId){
        return ResponseEntity.ok(subscriptionService.getAppUserSubscription(appUserId));
    }

    @PostMapping
    public ResponseEntity<Void> addAppUserSubscription(@RequestBody Subscription subscription) {
        subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok().build();
    }

}
