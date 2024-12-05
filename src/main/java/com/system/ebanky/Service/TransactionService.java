package com.system.ebanky.Service;

import com.system.ebanky.DTO.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionDTO transactionDTO);


    TransactionDTO getTransactionById(Long transactionId);

    List<TransactionDTO> getTransactionsByAccountId(Long accountId);

    TransactionDTO getTransactionofAccountById(Long accountId,Long id);

    List<TransactionDTO> getAllTransactions();

    boolean cancelTransaction(Long transactionId);

    boolean acceptTransaction(Long id);
}
