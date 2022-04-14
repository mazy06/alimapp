package com.alimapp.userservice.service;

import com.alimapp.userservice.model.AppRole;
import com.alimapp.userservice.model.AppUser;
import com.alimapp.userservice.repository.AppRoleRepository;
import com.alimapp.userservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Saving new role {} to the database", role.getName());
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to the user {}", roleName, username);
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role = appRoleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {} ", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Optional<AppUser> getUser(Long appUserId) {
        log.info("Fetching user {} ", appUserId);
        return appUserRepository.findById(appUserId);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all user");
        return appUserRepository.findAll();
    }

}
