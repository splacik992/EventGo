package com.pali.eventgo.services;


import com.pali.eventgo.entity.AppUser;

import java.util.List;


public interface UserService {
    AppUser findByUserName(String name);
    AppUser findUserByEmail(String email);
    void saveUser(AppUser appUser);
    List<AppUser> findAll();
    AppUser updateUser(AppUser appUser);
}

