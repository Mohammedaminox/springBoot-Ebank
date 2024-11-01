package com.banque.ebankify.dto.request;

// BankAccountRequestDTO : Utilisé pour la création et la mise à jour des comptes bancaires
import java.math.BigDecimal;

public class BankAccountRequestDTO {
    private BigDecimal balance;
    private Long ownerId;

    // Getters et Setters
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
