package com.banque.ebankify.repository;

import com.banque.ebankify.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderId(Long senderId); // Requête pour obtenir les transactions d’un utilisateur
    List<Transaction> findByType(Transaction.Type type); // Requête pour obtenir les transactions par type
    List<Transaction> findByAmountGreaterThanAndRequiresApprovalTrue(Double amount); // Requête pour les transactions nécessitant une approbation
}
