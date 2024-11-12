package com.banque.ebankify.service;

import com.banque.ebankify.dto.request.BankAccountRequestDTO;
import com.banque.ebankify.dto.response.BankAccountResponseDTO;
import com.banque.ebankify.entity.BankAccount;
import com.banque.ebankify.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public BankAccountResponseDTO createBankAccount(BankAccountRequestDTO requestDTO) {
        BankAccount bankAccount = new BankAccount();
        // Set bankAccount fields from requestDTO here
        bankAccountRepository.save(bankAccount);
        return new BankAccountResponseDTO(bankAccount);
    }

    @Transactional(readOnly = true)
    public BankAccountResponseDTO getBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("BankAccount not found"));
        return new BankAccountResponseDTO(bankAccount);
    }

    @Transactional(readOnly = true)
    public List<BankAccountResponseDTO> getAllBankAccounts() {
        return bankAccountRepository.findAll().stream().map(BankAccountResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public BankAccountResponseDTO updateBankAccount(Long id, BankAccountRequestDTO requestDTO) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("BankAccount not found"));
        // Update bankAccount fields from requestDTO here
        bankAccountRepository.save(bankAccount);
        return new BankAccountResponseDTO(bankAccount);
    }

    @Transactional
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
