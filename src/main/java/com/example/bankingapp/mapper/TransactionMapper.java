package com.example.bankingapp.mapper;
import org.springframework.stereotype.Component;

import com.example.bankingapp.dto.TransactionDto;
import com.example.bankingapp.entity.Transaction;

@Component
public class TransactionMapper {

    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
     
        transaction.setDate(transactionDto.getDate());
        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setBalanceAfter(transactionDto.getBalanceAfter());
        transaction.setAccountId(transactionDto.getAccountId()); // Setting the account entity
        return transaction;
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto(
            transaction.getAccountId(),
            transaction.getTransactionId(),
            transaction.getDate(),
            transaction.getType(),
            transaction.getAmount(),
            transaction.getBalanceAfter()
        		 );
        return transactionDto;
    }
}
