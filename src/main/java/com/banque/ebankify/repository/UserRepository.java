package com.banque.ebankify.repository;

import com.banque.ebankify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByRole(User.Role role); // Requête pour obtenir les utilisateurs par rôle
}
