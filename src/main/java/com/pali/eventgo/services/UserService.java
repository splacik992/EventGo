package com.pali.eventgo.services;


import com.pali.eventgo.entity.AppUser;

import java.util.List;

public interface UserService {
    AppUser findByUserName(String name);
    void saveUser(AppUser appUser);
    List<AppUser> findAll();
}

