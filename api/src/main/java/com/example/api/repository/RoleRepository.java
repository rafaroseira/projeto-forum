package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.model.Role;
import com.example.api.model.EnumRole;

public interface RoleRepository extends JpaRepository<Role, Byte> {
    Role findByNomeRole(EnumRole nomeRole);
}
