package com.banque.ebankify.controller;

import com.banque.ebankify.dto.request.BankAccountRequestDTO;
import com.banque.ebankify.dto.response.BankAccountResponseDTO;
import com.banque.ebankify.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankaccountService;

    public BankAccountController(BankAccountService bankaccountService) {
        this.bankaccountService = bankaccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createAccount(@RequestBody BankAccountRequestDTO accountRequestDTO) {
        BankAccountResponseDTO response = bankaccountService.createBankAccount(accountRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> getAccountById(@PathVariable Long id) {
        BankAccountResponseDTO response = bankaccountService.getBankAccountById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponseDTO>> getAllAccounts() {
        List<BankAccountResponseDTO> response = bankaccountService.getAllBankAccounts();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> updateAccount(@PathVariable Long id, @RequestBody BankAccountRequestDTO bankaccountRequestDTO) {
        BankAccountResponseDTO response = bankaccountService.updateBankAccount(id, bankaccountRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        bankaccountService.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }
}
