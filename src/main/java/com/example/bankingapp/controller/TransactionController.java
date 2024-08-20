package com.example.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.dto.AccountTransactionsDto;
import com.example.bankingapp.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public ResponseEntity<AccountTransactionsDto> getTransactionsByAccountId(@PathVariable Long accountId) {
    	AccountTransactionsDto accountTransactionsDto = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok(accountTransactionsDto);
    }
}
