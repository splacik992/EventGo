package com.pali.eventgo.services;


import com.pali.eventgo.entity.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    AppUser findByUserName(String name);
    void saveUser(AppUser appUser);
    List<AppUser> findAll();
}

