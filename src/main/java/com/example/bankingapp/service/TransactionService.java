package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountTransactionsDto;


public interface TransactionService {
	  AccountTransactionsDto  getTransactionsByAccountId(Long accountId);
}
