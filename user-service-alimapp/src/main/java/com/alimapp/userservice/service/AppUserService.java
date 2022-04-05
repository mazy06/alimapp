package com.alimapp.userservice.service;

import com.alimapp.userservice.model.AppRole;
import com.alimapp.userservice.model.AppUser;

import java.util.List;

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
     * Get a user
     * @param username
     * @return
     */
    AppUser getUser(String username);

    /**
     * Get a list of all users
     * @return
     */
    List<AppUser> getUsers();
}
