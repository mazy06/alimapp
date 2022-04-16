package com.alimapp.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, updatable = false)
    private Long id;
    private Long appUserId;
    private Long targetAppUserId;
    private LocalDate subscribeDate;
}
