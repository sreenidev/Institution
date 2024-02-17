package com.multimodule.domain.repository;

import com.multimodule.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    Optional<User> findByPhone (Long phone);
    Optional<User> findByPercentage (Double percentage);

}
