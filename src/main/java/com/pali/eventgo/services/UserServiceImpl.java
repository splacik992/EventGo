package com.pali.eventgo.services;

import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.entity.Role;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.RoleRepository;
import com.pali.eventgo.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final static String USER_NOT_FOUND = "User z takim email nie istnieje";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AppUser findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser findUserByEmail(String email) {
        AppUser appUser = userRepository.findAppUserByEmail(email);
        if (appUser == null) {
            throw new ResourceNotExistException(USER_NOT_FOUND);
        }

        return userRepository.findAppUserByEmail(email);
    }

    @Override
    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER");
        appUser.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        appUser.setHashCodeToEnableAccount(
                givenUsingApache_whenGeneratingRandomAlphanumericString_thenCorrect());
        userRepository.save(appUser);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }


    public String givenUsingApache_whenGeneratingRandomAlphanumericString_thenCorrect() {
        return RandomStringUtils.randomAlphanumeric(100);
    }

}
