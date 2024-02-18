package com.multimodule.domain.repository;

import com.multimodule.domain.model.CreateUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreateUserRepository extends JpaRepository<CreateUser, Integer>{

    Optional<CreateUser> findByName(String username);
}
