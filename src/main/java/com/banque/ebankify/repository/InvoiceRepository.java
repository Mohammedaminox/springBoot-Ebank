package com.banque.ebankify.repository;

import com.banque.ebankify.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUserId(Long userId); // Requête pour obtenir les factures d’un utilisateur spécifique
}
