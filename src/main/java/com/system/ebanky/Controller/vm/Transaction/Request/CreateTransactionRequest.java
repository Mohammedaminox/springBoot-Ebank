package com.system.ebanky.Controller.vm.Transaction.Request;

import com.system.ebanky.DTO.TransactionDTO;

public class CreateTransactionRequest {
        private TransactionDTO transaction;

    public TransactionDTO getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionDTO transaction) {
        this.transaction = transaction;
    }
}
