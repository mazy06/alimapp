package com.alimapp.userservice.service;

import com.alimapp.userservice.model.AppRole;
import com.alimapp.userservice.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    /**
     * Create new user
     * @param user
     * @return
     */
    AppUser saveUser(AppUser user);

    /**
     * Create new role
     * @param role
     * @return
     */
    AppRole saveRole(AppRole role);

    /**
     * Assign a role to the user
     * @param username
     * @param roleName
     */
    void addRoleToUser(String username, String roleName);

    /**
     * User altrady exist
     * @param appUserId
     * @return
     */
    boolean exist(Long appUserId);

    /**
     * Get a user by username
     * @param username
     * @return
     */
    AppUser getUser(String username);

    /**
     * Get a user by id
     * @param appUserId
     * @return
     */
    Optional<AppUser> getUser(Long appUserId);

    /**
     * Get a list of all users
     * @return
     */
    List<AppUser> getUsers();

    /**
     * List
     * @param appUserIds
     * @return
     */
    List<AppUser> getAllByIds(List<Long> appUserIds);
}
