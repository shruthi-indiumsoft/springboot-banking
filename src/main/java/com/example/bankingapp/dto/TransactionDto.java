package com.example.bankingapp.dto;

import java.util.Date;

import com.example.bankingapp.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
	private Long accountId;
	private Long transactionId;
	private Date date;
	private String type;
	private double amount;
	private double balanceAfter;
}
