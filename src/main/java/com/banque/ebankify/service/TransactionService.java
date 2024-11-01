package com.banque.ebankify.service;

import com.banque.ebankify.entity.Transaction;
import com.banque.ebankify.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactionsBySenderId(Long senderId) {
        return transactionRepository.findBySenderId(senderId);
    }

    public Transaction createTransaction(Transaction transaction) {
        // Logique de vérification des fonds, limites de virement, etc.
        return transactionRepository.save(transaction);
    }

    public void approveTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setRequiresApproval(false);
        transactionRepository.save(transaction);
    }

    public List<Transaction> getHighValueTransactions(Double amount) {
        return transactionRepository.findByAmountGreaterThanAndRequiresApprovalTrue(amount);
    }
}
