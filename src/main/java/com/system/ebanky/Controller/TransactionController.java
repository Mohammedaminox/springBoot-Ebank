package com.system.ebanky.Controller;

import com.system.ebanky.Controller.vm.Transaction.Request.ApproveTransactionRequest;
import com.system.ebanky.DTO.TransactionDTO;
import com.system.ebanky.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{account}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccount(@PathVariable Long account) {
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(account);
        if (transactions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(transactions);
        }
    }



    @PostMapping("/")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO request) {
        try {
            TransactionDTO transactionDTO = transactionService.createTransaction(request);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transaction creation failed: " + e.getMessage());
        }
        return ResponseEntity.ok("Transaction creation successfully");
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<String> approveTransaction(@RequestBody ApproveTransactionRequest request,@PathVariable long id) {
        String role = request.getRole();
        String choix = request.getChoix();
//        Long id = request.getId();
        if ("MANAGER".equals(role) || "ADMIN".equals(role)) {
            if ("approve".equals(choix)) {
                transactionService.acceptTransaction(id);
                return ResponseEntity.ok("the transaction got accepted");
            } else if ("decline".equals(choix)) {
                transactionService.cancelTransaction(id);
                return ResponseEntity.ok("the transaction got rejected");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("some problem has occurred");
    }




}
