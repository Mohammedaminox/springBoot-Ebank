package com.banque.ebankify.repository;

import com.banque.ebankify.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId); // Requête pour obtenir les prêts d’un utilisateur
    List<Loan> findByStatus(Loan.Status status); // Requête pour obtenir les prêts par statut
}
