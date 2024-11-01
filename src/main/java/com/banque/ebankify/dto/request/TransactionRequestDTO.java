package com.banque.ebankify.dto.request;

// TransactionRequestDTO : Utilisé pour initier une transaction
import java.math.BigDecimal;

public class TransactionRequestDTO {
    private Long senderId;
    private Long receiverId;
    private BigDecimal amount;

    // Getters et Setters
    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }

    public Long getReceiverId() { return receiverId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}

