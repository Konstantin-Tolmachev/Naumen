package com.practikum.naumen.repo;

import com.practikum.naumen.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByOrderByIdDesc();
}


