package com.sangsil.ssrframework.domain.user.repository;

import com.sangsil.ssrframework.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}