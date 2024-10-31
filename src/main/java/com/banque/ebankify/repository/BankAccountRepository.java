package com.banque.ebankify.repository;

import com.banque.ebankify.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByOwnerId(Long ownerId); // Requête pour obtenir les comptes bancaires d’un utilisateur spécifique
}
