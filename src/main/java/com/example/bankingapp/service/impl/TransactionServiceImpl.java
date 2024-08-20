package com.example.bankingapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapp.dto.AccountTransactionsDto;
import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Transaction;
import com.example.bankingapp.mapper.TransactionMapper;
import com.example.bankingapp.repository.TransactionRepository;
import com.example.bankingapp.service.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

  
    @Override
    public AccountTransactionsDto getTransactionsByAccountId(Long accountId) {
    	  List<Transaction> transactions = transactionRepository.findByAccountId(accountId); 
    	  List<TransactionDto> transactionDtos =transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    	  return new AccountTransactionsDto(accountId, transactionDtos);
    }
}
