package com.system.ebanky.Repository;

import com.system.ebanky.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByEmail(String email);
}
