package org.example.tutormatch.repository;

import org.springframework.stereotype.Repository;
import org.example.tutormatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
