package com.banque.ebankify.dto.response;

// BankAccountResponseDTO : Utilisé pour retourner les informations d'un compte bancaire
import com.banque.ebankify.entity.BankAccount;

import java.math.BigDecimal;

public class BankAccountResponseDTO {
    private Long id;
    private BigDecimal balance;
    private String status;

    public BankAccountResponseDTO(BankAccount bankAccount) {
        this.id = bankAccount.getId();
        this.balance = bankAccount.getBalance();

    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
