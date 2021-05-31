package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_user);
}
