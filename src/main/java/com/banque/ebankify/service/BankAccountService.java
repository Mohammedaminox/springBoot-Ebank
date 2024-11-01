package com.banque.ebankify.service;

import com.banque.ebankify.entity.BankAccount;
import com.banque.ebankify.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public List<BankAccount> getAccountsByOwnerId(Long ownerId) {
        return bankAccountRepository.findByOwnerId(ownerId);
    }

    public BankAccount createAccount(BankAccount account) {
        return bankAccountRepository.save(account);
    }

    public BankAccount updateAccount(Long id, BankAccount accountDetails) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(accountDetails.getBalance());
        account.setStatus(accountDetails.getStatus());
        return bankAccountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
